package com.home.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum Action {
    DELETED("deleted"), CREATED("created");

    private final String value;
}
