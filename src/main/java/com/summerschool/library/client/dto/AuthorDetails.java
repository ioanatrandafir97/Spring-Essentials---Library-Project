package com.summerschool.library.client.dto;

import lombok.Data;

@Data
public class AuthorDetails {
    private Integer id;
    private String name;
    private Integer yearOfBirth;
    private Integer yearOfDeath;

}
