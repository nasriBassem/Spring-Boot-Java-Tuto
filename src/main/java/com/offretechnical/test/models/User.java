package com.offretechnical.test.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Constraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.offretechnical.test.constraints.BirthDate;
import com.offretechnical.test.constraints.Country;

/**
 * Modéle des users
 * 
 * @author bn
 *
 */
@Table(name = "user")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotNull(message = "User birthdate must not be empty")
	@BirthDate
	private Date birthdate;

	@Column(nullable = false)
	@NotEmpty(message = "User name must not be empty")
	private String userName;

	@Column(nullable = false)
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

	/**
	 * Cosntructeur
	 * 
	 * @param id
	 * @param birthdate
	 * @param userName
	 * @param country
	 * @param phoneNumber
	 * @param gender
	 */
	public User(Date birthdate, String userName, String country, String phoneNumber, String gender) {
		super();
		this.birthdate = birthdate;
		this.userName = userName;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public User() {
		super();

	}

	@Override
	public String toString() {
		return "User [id=" + id + ", birthdate=" + birthdate + ", userName=" + userName + ", country=" + country
				+ ", phoneNumber=" + phoneNumber + ", gender=" + gender + "]";
	}
	
	

}
