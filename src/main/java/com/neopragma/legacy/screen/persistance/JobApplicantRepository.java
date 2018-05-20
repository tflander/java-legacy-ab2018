package com.neopragma.legacy.screen.persistance;

import com.neopragma.legacy.screen.JobApplicant;
import com.neopragma.legacy.screen.formatter.NameFormatter;

public class JobApplicantRepository {

    public void save(JobApplicant... jobApplicants) {
        NameFormatter nameFormatter = new NameFormatter();

        //TODO save information to a database
        for (JobApplicant jobApplicant: jobApplicants) {
            System.out.println("Saving to database: " + nameFormatter.formatLastNameFirst(jobApplicant.getName()));
        }
    }

}
