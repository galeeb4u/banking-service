package com.greenfin.user.service.service.implementation;


import com.greenfin.user.service.exception.EmptyFields;
import com.greenfin.user.service.exception.ResourceConflictException;
import com.greenfin.user.service.exception.ResourceNotFound;
import com.greenfin.user.service.external.AccountService;
import com.greenfin.user.service.model.Status;
import com.greenfin.user.service.model.dto.CreateUser;
import com.greenfin.user.service.model.dto.UserDto;
import com.greenfin.user.service.model.dto.UserUpdate;
import com.greenfin.user.service.model.dto.UserUpdateStatus;
import com.greenfin.user.service.model.dto.response.Response;
import com.greenfin.user.service.model.entity.User;
import com.greenfin.user.service.model.entity.UserProfile;
import com.greenfin.user.service.model.external.Account;
import com.greenfin.user.service.model.mapper.UserMapper;
import com.greenfin.user.service.repository.UserRepository;
import com.greenfin.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final AccountService accountService;

    private UserMapper userMapper = new UserMapper();

    @Value("${spring.application.success}")
    private String responseCodeSuccess;

    @Value("${spring.application.not_found}")
    private String responseCodeNotFound;

    /**
     * Creates a new user.
     *
     * @param userDto The user data transfer object containing user information.
     * @return A response indicating the result of the user creation.
     * @throws ResourceConflictException If the emailId is already registered as a user.
     * @throws RuntimeException If the user with identification number is not found.
     */
    @Override
    public Response createUser(CreateUser userDto) {

        Optional<User> userVerify =userRepository.findUserByEmailId(userDto.getEmailId());
        if(userVerify.isPresent()){
            log.error("This emailId is already registered as a user");
            throw new ResourceConflictException("This emailId is already registered as a user");
        }


        UserProfile userProfile = UserProfile.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName()).build();

        User user = User.builder()
                .emailId(userDto.getEmailId())
                .contactNo(userDto.getContactNo())
                .status(Status.PENDING).userProfile(userProfile)
                .authId(userDto.getPassword())
                .identificationNumber(UUID.randomUUID().toString()).build();

        userRepository.save(user);
        return Response.builder()
                .responseMessage("User created successfully")
                .responseCode(responseCodeSuccess).build();
    }

    @Override
    public List<User> readAllUsers() {

        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User readUser(String authId) {
        User user = userRepository.findUserByAuthId(authId).
                orElseThrow(() -> new ResourceNotFound("User not found on the server"));
        return user;
    }

    @Override
    public Response updateUserStatus(Long id, UserUpdateStatus userUpdate) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found on the server"));


        user.setStatus(userUpdate.getStatus());
        userRepository.save(user);

        return Response.builder()
                .responseMessage("User updated successfully")
                .responseCode(responseCodeSuccess).build();
    }

    @Override
    public Response updateUser(Long id, UserUpdate userUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found on the server"));


        BeanUtils.copyProperties(userUpdate, user.getUserProfile());
        user.setContactNo(userUpdate.getContactNo());
        userRepository.save(user);

        return Response.builder()
                .responseCode(responseCodeSuccess)
                .responseMessage("user updated successfully").build();
    }

    @Override
    public UserDto readUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> userMapper.convertToDto(user))
                .orElseThrow(() -> new ResourceNotFound("User not found on the server"));
    }

    @Override
    public UserDto readUserByAccountId(String accountId) {

        ResponseEntity<Account> response = accountService.readByAccountNumber(accountId);
        if(Objects.isNull(response.getBody())){
            throw new ResourceNotFound("account not found on the server");
        }
        Long userId = response.getBody().getUserId();
        return userRepository.findById(userId)
                .map(user -> userMapper.convertToDto(user))
                .orElseThrow(() -> new ResourceNotFound("User not found on the server"));
    }
    }


