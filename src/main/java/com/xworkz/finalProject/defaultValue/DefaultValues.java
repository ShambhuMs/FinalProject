package com.xworkz.finalProject.defaultValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DefaultValues {
    ZERO(0, 0L, ""),
    STATUS(0, 0L, "unResolved");

    private final int intValue;
    private final long longValue;
    private final String defaultStatus;
}
