package com.greenfin.account.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.greenfin.account.service.model.dto.external.SequenceDto;

@FeignClient(name = "sequence-generator",url = "http://localhost:8083/")
public interface SequenceService {

    /**
     * Generates a new account number.
     *
     * @return the generated account number as a SequenceDto object.
     */
    @PostMapping("/sequence")
    SequenceDto generateAccountNumber();
}
