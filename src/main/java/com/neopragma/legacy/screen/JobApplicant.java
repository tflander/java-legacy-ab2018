package com.neopragma.legacy.screen;

import lombok.Data;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Job applicant class.
 */
@Data
public class JobApplicant {
	
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	private Address address = new Address();
	private AddressProvider addressProvider = new AddressProvider();
	private String ssn;

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

	public void add(String firstName,
			       String middleName,
			       String lastName,
			       String ssn,
			       String zipCode) throws URISyntaxException, IOException {
		setName(firstName, middleName, lastName);
		setSsn(ssn);
		address = addressProvider.buildAddressFromZipCode(zipCode);
		save();
	}
	
	public void save() {
		//TODO save information to a database
		System.out.println("Saving to database: " + formatLastNameFirst());
	}

    public Address buildAddressFromZipCode(String zipCode) throws IOException, URISyntaxException {
        return addressProvider.buildAddressFromZipCode(zipCode);
    }

}
