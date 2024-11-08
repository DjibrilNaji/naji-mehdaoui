package com.codejourney.pimber.controller;

import com.codejourney.pimber.dto.PartyDto;
import com.codejourney.pimber.service.PartyService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<PartyDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(partyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PartyDto> save(@RequestBody PartyDto partyDto) {
        return ResponseEntity.ok(partyService.save(partyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyDto> update(@PathVariable UUID id, @RequestBody PartyDto partyDto) {
        return ResponseEntity.ok(partyService.update(id, partyDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        partyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
