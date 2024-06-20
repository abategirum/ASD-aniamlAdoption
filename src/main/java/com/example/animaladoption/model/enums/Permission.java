package com.example.animaladoption.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    MEMBER_READ("member:read"),
    MEMBER_WRITE("member:write");

    private final String permission;
}
