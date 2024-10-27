package com.greenfin.sequence.generator.controller;

import com.greenfin.sequence.generator.model.entity.Sequence;
import com.greenfin.sequence.generator.service.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sequence")
public class SequenceController {

    private final SequenceService sequenceService;

    /**
     * Generates an account number.
     *
     * @return The generated account number.
     */
    @PostMapping
    public Sequence generateAccountNumber() {
        return sequenceService.create();
    }
}
