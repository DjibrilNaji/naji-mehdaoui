package com.codejourney.pimber.service;

import com.codejourney.pimber.dto.PartyDto;
import com.codejourney.pimber.entity.*;
import com.codejourney.pimber.mapper.PartyMapper;
import com.codejourney.pimber.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PartyService {
    private final PartyMapper partyMapper;
    private final PartyRepository partyRepository;
    private final TypeRepository typeRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BoardGameRepository boardGameRepository;
    private final VideoGameRepository videoGameRepository;

    public PartyService(PartyMapper partyMapper, PartyRepository partyRepository, TypeRepository typeRepository, UserRepository userRepository, AddressRepository addressRepository, BoardGameRepository boardGameRepository, VideoGameRepository videoGameRepository) {
        this.partyMapper = partyMapper;
        this.partyRepository = partyRepository;
        this.typeRepository = typeRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.boardGameRepository = boardGameRepository;
        this.videoGameRepository = videoGameRepository;
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

        if (party.getType() != null && party.getType().getId() != null) {
            Type type = typeRepository.findById(party.getType().getId())
                    .orElseThrow(() -> new NotFoundException("Type not found with id " + party.getType().getId()));
            party.setType(type);
        }

        if (party.getCreator() != null && party.getCreator().getId() != null) {
            User creator = userRepository.findById(party.getCreator().getId())
                    .orElseThrow(() -> new NotFoundException("Creator not found with id " + party.getCreator().getId()));
            party.setCreator(creator);
        }

        if (party.getAddress() != null && party.getAddress().getId() != null) {
            Address address = addressRepository.findById(party.getAddress().getId())
                    .orElseThrow(() -> new NotFoundException("Address not found with id " + party.getAddress().getId()));
            party.setAddress(address);
        }

        if (party.getPhotos() != null && !party.getPhotos().isEmpty()) {
            party.getPhotos().forEach(photo -> photo.setParty(party));
        }

        if (party.getInvitedUsers() != null && !party.getInvitedUsers().isEmpty()) {
            Set<User> invitedUserList = new HashSet<>();

            party.getInvitedUsers().forEach(invitedUserDto -> {
                User user = userRepository.findById(invitedUserDto.getId())
                        .orElseThrow(() -> new NotFoundException("User not found with id " + invitedUserDto.getId()));

                invitedUserList.add(user);
            });
            party.setInvitedUsers(invitedUserList);
        }

        if (party.getBoardGames() != null && !party.getBoardGames().isEmpty()) {
            Set<BoardGame> updatedBoardGames = new HashSet<>();

            party.getBoardGames().forEach(boardGameDto -> {
                if (boardGameDto.getId() != null) {
                    BoardGame boardGame = boardGameRepository.findById(boardGameDto.getId())
                            .orElseThrow(() -> new NotFoundException("BoardGame not found with id " + boardGameDto.getId()));

                    updatedBoardGames.add(boardGame);
                }
            });

            party.setBoardGames(updatedBoardGames);
        }

        if (party.getVideoGames() != null && !party.getVideoGames().isEmpty()) {
            Set<BoardGame> updatedBoardGames = new HashSet<>();

            party.getBoardGames().forEach(boardGameDto -> {
                if (boardGameDto.getId() != null) {
                    BoardGame boardGame = boardGameRepository.findById(boardGameDto.getId())
                            .orElseThrow(() -> new NotFoundException("BoardGame not found with id " + boardGameDto.getId()));

                    updatedBoardGames.add(boardGame);
                }
            });

            party.setBoardGames(updatedBoardGames);
        }

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
