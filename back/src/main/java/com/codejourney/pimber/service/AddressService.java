package com.codejourney.pimber.service;

import com.codejourney.pimber.dto.AddressDto;
import com.codejourney.pimber.dto.PartyDto;
import com.codejourney.pimber.mapper.AddressMapper;
import com.codejourney.pimber.repository.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressService(AddressMapper addressMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;

    }
    public List<AddressDto> findAll(Pageable pageable) {
        return addressMapper.toDtos(addressRepository.findAll(pageable).getContent());
    }

}
