package com.codejourney.pimber.controller;

import com.codejourney.pimber.dto.PartyDto;
import com.codejourney.pimber.service.PartyService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parties")
public class PartyController {
    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping
    public ResponseEntity<List<PartyDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(partyService.findAll(pageable));
    }

}
