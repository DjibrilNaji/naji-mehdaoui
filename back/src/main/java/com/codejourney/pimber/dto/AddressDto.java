package com.codejourney.pimber.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private Long num_path;
    private String name_path;
    private String city;
    private String postal_code;
}
