package com.xworkz.finalProject.defaultValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DefaultValues {
    ZERO(0,0L,""),
    ONE(1,1L,""),
    LOCK_ACCOUNT(2,0L,""),
    STATUS(0,0L,"Active");
    private final int intValue;
   private final long longValue;
   private final String defaultStatus;

}
