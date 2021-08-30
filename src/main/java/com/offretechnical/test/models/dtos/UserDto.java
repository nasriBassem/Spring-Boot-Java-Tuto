package com.offretechnical.test.models.dtos;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.offretechnical.test.constraints.BirthDate;
import com.offretechnical.test.constraints.Country;

/**
 * users s' dto
 * 
 * @author bn
 *
 */
public class UserDto {
	private int id;

	@NotNull(message = "User birthdate must not be empty")
	@BirthDate
	private Date birthdate;

	@NotEmpty(message = "User name must not be empty")
	private String userName;

	@NotEmpty(message = "User country must not be empty")
	@Country
	private String country;

	private String phoneNumber;
	private String gender;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserDto [ birthdate=" + birthdate + ", userName=" + userName + ", country=" + country + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + "]";
	}

}
