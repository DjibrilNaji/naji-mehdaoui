package com.codejourney.pimber.service;

import com.codejourney.pimber.dto.PartyDto;
import com.codejourney.pimber.entity.*;
import com.codejourney.pimber.mapper.PartyMapper;
import com.codejourney.pimber.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PartyService {
    private final PartyMapper partyMapper;
    private final PartyRepository partyRepository;

    public PartyService(PartyMapper partyMapper, PartyRepository partyRepository) {
        this.partyMapper = partyMapper;
        this.partyRepository = partyRepository;
    }

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private BoardGameRepository boardGameRepository;

    @Autowired
    private PartyVideoGameRepository videoGameRepository;

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

        saveData(savedParty);

        return partyMapper.toDto(savedParty);
    }

    @Transactional
    public PartyDto update(UUID id, PartyDto partyDto) {
        Party existingParty = partyRepository.findById(id).orElseThrow(() -> new NotFoundException("Party not found with id " + id));
        partyDto.setId(existingParty.getId());
        Party partyUpdated = partyMapper.toEntity(partyDto);
        Party savedParty = partyRepository.save(partyUpdated);

        saveData(savedParty);

        return partyMapper.toDto(savedParty);
    }

    public void delete(UUID id) {
        Party party = partyRepository.findById(id).orElseThrow(() -> new NotFoundException("Party not found with id " + id));
        partyRepository.delete(party);
    }

    public void saveData(Party party){
        addressRepository.save(party.getAddress());

        Set<Photo> partyPhotos = party.getPhotos();
        if (!partyPhotos.isEmpty()) {
            party.getPhotos().forEach(photo -> photoRepository.save(photo));
        }

        Set<BoardGame> boardGames = party.getBoardGames();
        if (!boardGames.isEmpty()) {
            party.getBoardGames().forEach(boardGame -> boardGameRepository.save(boardGame));
        }

        Set<PartyVideoGame> partyVideoGames = party.getVideoGames();
        if (!partyVideoGames.isEmpty()) {
            party.getVideoGames().forEach(videoGame -> videoGameRepository.save(videoGame));
        }
    }
}
