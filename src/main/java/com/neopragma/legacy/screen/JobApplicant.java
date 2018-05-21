package com.neopragma.legacy.screen;

import com.neopragma.legacy.screen.domain.Address;
import com.neopragma.legacy.screen.domain.Name;
import lombok.Data;

@Data
public class JobApplicant {

    private Address address;
    private String ssn;
    private Name name;

}
