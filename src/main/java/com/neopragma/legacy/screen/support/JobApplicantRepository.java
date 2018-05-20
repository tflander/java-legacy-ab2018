package com.neopragma.legacy.screen.support;

import com.neopragma.legacy.screen.JobApplicant;

public class JobApplicantRepository {

    public void save(JobApplicant... jobApplicants) {
        DefaultNameBuilder defaultNameBuilder = new DefaultNameBuilder();

        //TODO save information to a database
        for (JobApplicant jobApplicant: jobApplicants) {
            System.out.println("Saving to database: " + defaultNameBuilder.formatLastNameFirst(jobApplicant.getName()));
        }
    }

}
