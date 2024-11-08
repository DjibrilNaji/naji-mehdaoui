package com.codejourney.pimber.mapper;

import com.codejourney.pimber.dto.PartyDto;
import com.codejourney.pimber.entity.Party;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartyMapper {
    PartyDto toDto(Party party);

    Party toEntity(PartyDto userDto);

    List<PartyDto> toDtos(List<Party> users);

    List<Party> toEntities(List<PartyDto> userDtos);
}
