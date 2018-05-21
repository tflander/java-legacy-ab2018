package com.neopragma.legacy.screen.builder;

import com.neopragma.legacy.screen.JobApplicant;
import com.neopragma.legacy.screen.formatter.SocialSecurityNumberFormatter;
import com.neopragma.legacy.screen.persistance.JobApplicantRepository;

import java.io.IOException;
import java.net.URISyntaxException;

public class JobApplicantApi {

    private final JobApplicantRepository jobApplicantRepository = new JobApplicantRepository();
    private final DefaultNameBuilder defaultNameBuilder = new DefaultNameBuilder();
    private final AddressBuilder addressBuilder = new AddressBuilder();
    private final SocialSecurityNumberFormatter socialSecurityNumberFormatter = new SocialSecurityNumberFormatter();

    public JobApplicant createJobApplicant(String firstName,
                                           String middleName,
                                           String lastName,
                                           String ssn,
                                           String zipCode) throws URISyntaxException, IOException {
        JobApplicant jobApplicant = new JobApplicant();
        jobApplicant.setName(defaultNameBuilder.buildName(firstName, middleName, lastName));

        jobApplicant.setSsn(socialSecurityNumberFormatter.removeDashes(ssn));
        jobApplicant.setAddress(addressBuilder.buildAddressFromZipCode(zipCode));
        jobApplicantRepository.save(jobApplicant);
        return jobApplicant;
    }

}
