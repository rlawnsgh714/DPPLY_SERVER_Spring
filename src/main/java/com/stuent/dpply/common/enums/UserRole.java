package com.stuent.dpply.common.enums;

import java.util.Arrays;

public enum UserRole {
    STUDENT(1), ADMIN(2);

    private final int number;

    UserRole(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static UserRole valueOfNumber(int number) {
        return Arrays.stream(values())
                .filter(value -> value.number == number)
                .findAny()
                .orElse(null);
    }
}
