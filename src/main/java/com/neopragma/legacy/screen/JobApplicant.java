package com.neopragma.legacy.screen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Job applicant class.
 */
public class JobApplicant {
	
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	private Address address = new Address();
	private AddressProvider addressProvider = new AddressProvider();
	
	public void setName(String firstName, String middleName, String lastName) {
		this.firstName = firstName == null ? "" : firstName;
		this.middleName = middleName == null ? "" : middleName;
		this.lastName = lastName == null ? "" : lastName;
	}

	
	public void setSpanishName(String primerNombre, String segundoNombre,
							   String primerApellido, String segundoApellido) {
		this.firstName = primerNombre == null ? "" : primerNombre;
		this.middleName = segundoNombre == null ? "" : segundoNombre;
		if ( primerApellido != null ) {
            this.lastName = primerApellido + (segundoApellido == null ? null : " " + segundoApellido);
		} else {
			this.lastName = "";
		}
	}
	
	public String formatLastNameFirst() {
		StringBuilder sb = new StringBuilder(lastName);
		sb.append(", ");
		sb.append(firstName);
		if ( middleName.length() > 0 ) {
			sb.append(" ");
			sb.append(middleName);
		}
		return sb.toString();
	}
	
	public int validateName() {
		if ( firstName.length() > 0 && lastName.length() > 0 ) {
			return 0;
		} else {
			return 6;
		}
	}
	
	private String ssn;
	
	private final String[] specialCases = new String[] {
	    "219099999", "078051120"
	};

	public void setSsn(String ssn) {
		if ( ssn.matches("(\\d{3}-\\d{2}-\\d{4}|\\d{9})") ) {
  		    this.ssn = ssn.replaceAll("-", "");
		} else {
  		    this.ssn = "";
		}    
	}
	
	public String formatSsn() {
        return ssn.substring(0, 3) + "-" +
                ssn.substring(3, 5) +
                "-" +
                ssn.substring(5);
	}

	public int validateSsn() {
		if ( !ssn.matches("\\d{9}") ) {
			return 1;
		}
		if ( "000".equals(ssn.substring(0,3)) || 
			 "666".equals(ssn.substring(0,3)) ||
			 "9".equals(ssn.substring(0,1)) ) {
			return 2;
		}
		if ( "0000".equals(ssn.substring(5)) ) {
			return 3;
		}
        for (String specialCase : specialCases) {
            if (ssn.equals(specialCase)) {
                return 4;
            }
        }
		return 0;
	}

	public String getCity() {
		return address.getCity();
	}

	public String getState() {
		return address.getState();
	}
	
	public void add(String firstName,
			       String middleName,
			       String lastName,
			       String ssn,
			       String zipCode) throws URISyntaxException, IOException {
		setName(firstName, middleName, lastName);
		setSsn(ssn);
		address = addressProvider.populateUsingZipCode(zipCode);
		save();
	}
	
	public void save() {
		//TODO save information to a database
		System.out.println("Saving to database: " + formatLastNameFirst());
	}

    public Address populateAddressUsingZipCode(String zipCode) throws IOException, URISyntaxException {
        return addressProvider.populateUsingZipCode(zipCode);
    }

    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSsn() {
        return ssn;
    }
}
