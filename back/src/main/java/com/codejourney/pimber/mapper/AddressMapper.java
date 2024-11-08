package com.codejourney.pimber.mapper;

import com.codejourney.pimber.dto.AddressDto;
import com.codejourney.pimber.dto.PartyDto;
import com.codejourney.pimber.entity.Address;
import com.codejourney.pimber.entity.Party;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);

    List<AddressDto> toDtos(List<Address> addresses);

    List<Address> toEntities(List<AddressDto> addressDtos);
}
