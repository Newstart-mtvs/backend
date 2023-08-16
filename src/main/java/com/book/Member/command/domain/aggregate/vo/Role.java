package com.book.Member.command.domain.aggregate.vo;

public enum Role {
    ROLE_USER("USER"),
    ROLE_ANONYMOUS("ANONYMOUS"),
    ROLE_ADMIN("ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}