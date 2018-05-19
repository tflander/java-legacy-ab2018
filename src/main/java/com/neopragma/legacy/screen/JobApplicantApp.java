package com.neopragma.legacy.screen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class JobApplicantApp {

    public static void main(String[] args) throws URISyntaxException, IOException {
        JobApplicant jobApplicant = new JobApplicant();
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String middleName;
        String lastName;
        String ssn;
        String zipCode;
        while (true) {
            System.out.println("Please enter info about a job candidate or 'quit' to quit");
            System.out.println("First name?");
            firstName = scanner.nextLine();
            if (firstName.equals("quit")) {
                scanner.close();
                System.out.println("Bye-bye!");
                break;
            }
            System.out.println("Middle name?");
            middleName = scanner.nextLine();
            System.out.println("Last name?");
            lastName = scanner.nextLine();
            System.out.println("SSN?");
            ssn = scanner.nextLine();
            System.out.println("Zip Code?");
            zipCode = scanner.nextLine();
            jobApplicant.setName(firstName, middleName, lastName);
            jobApplicant.setSsn(ssn);
            jobApplicant.setAddress(jobApplicant.populateAddressUsingZipCode(zipCode));
            jobApplicant.save();
            System.out.println("saved " + jobApplicant.toString());
        }
    }

}
