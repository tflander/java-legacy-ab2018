package com.neopragma.legacy.screen.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;
}
