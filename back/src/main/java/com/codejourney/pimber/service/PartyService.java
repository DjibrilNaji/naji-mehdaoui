package com.codejourney.pimber.service;

import com.codejourney.pimber.dto.PartyDto;
import com.codejourney.pimber.entity.Party;
import com.codejourney.pimber.mapper.PartyMapper;
import com.codejourney.pimber.repository.PartyRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class PartyService {
    private final PartyMapper partyMapper;
    private final PartyRepository partyRepository;

    public PartyService(PartyMapper partyMapper, PartyRepository partyRepository) {
        this.partyMapper = partyMapper;
        this.partyRepository = partyRepository;
    }

    public List<PartyDto> findAll(Pageable pageable) {
        return partyMapper.toDtos(partyRepository.findAll(pageable).getContent());
    }

    public PartyDto findById(UUID id) {
        return partyMapper.toDto(partyRepository.findById(id).orElseThrow(() -> new NotFoundException("Party not found with id " + id)));
    }

    @Transactional
    public PartyDto save(PartyDto partyDto) {
        Party party = partyMapper.toEntity(partyDto);
        Party savedParty = partyRepository.save(party);

        return partyMapper.toDto(savedParty);
    }

    @Transactional
    public PartyDto update(UUID id, PartyDto partyDto) {
        Party existingParty = partyRepository.findById(id).orElseThrow(() -> new NotFoundException("Party not found with id " + id));
        partyDto.setId(existingParty.getId());
        Party partyUpdated = partyMapper.toEntity(partyDto);
        Party savedParty = partyRepository.save(partyUpdated);

        return partyMapper.toDto(savedParty);
    }

    public void delete(UUID id) {
        Party party = partyRepository.findById(id).orElseThrow(() -> new NotFoundException("Party not found with id " + id));
        partyRepository.delete(party);
    }

}
