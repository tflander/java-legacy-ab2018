package com.neopragma.legacy.screen;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Name {
    private String firstName = null;
    private String middleName = null;
    private String lastName = null;
}
