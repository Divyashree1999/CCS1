package com.cg.css.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "UserTable")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_Id")
	private int userId;

	@NotNull(message = "UserName is required")
	@Size(max = 15)
	@Column(name = "user_name", unique = true)
	private String userName;

	@Column(name = "first_name")
	@NotNull(message = "First name is required")
	@Size(max = 20)
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message = "Last name is required")
	@Size(max = 20)
	private String lastName;

	@Column(name = "email")
	@Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Invalid mail id!")
	@NotNull(message = "Email is required")
	@Email
	private String email;

	@Column(name = "income")
	@NotNull
	private int income;

	@Column(name = "phone_number")
	private BigInteger phoneNumber;

	@Column(name = "password")
	@NotNull(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[#?!@$%^&*-]).{8,32}$", message = " Password should contain atleast one Capital Letter, one digit and one symbol!")
	private String password;

	@Column(name = "confirmPassword")
	@NotNull(message = "Re-Enter your password")
	private String confirmPassword;

	@Column(name = "role")
	private String role = "customer";

	@NotNull(message = "Oops! Seems like you forgot to fill in the address!")
	@Column(name = "address")
	private String address;

	@NotNull(message = "Oops! Seems like you forgot to fill in your dob!")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@NotNull(message = "Oops! Seems like you forgot to fill in the typeOfEmployement!")
	@Column(name = "employementType")
	private String typeOfEmployement;

	@NotNull(message = "Oops! Seems like you forgot to fill in the organisation name!")
	@Column(name = "organisationName")
	private String organization;

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public BigInteger getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTypeOfEmployement() {
		return typeOfEmployement;
	}

	public void setTypeOfEmployement(String typeOfEmployement) {
		this.typeOfEmployement = typeOfEmployement;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", income=" + income + ", phoneNumber=" + phoneNumber + ", password="
				+ password + ",confirmPassword=" + confirmPassword + ", role=" + role + ", address=" + address
				+ ", DateOfBirth=" + dateOfBirth + ", TypeOfEmployement=" + typeOfEmployement + ", Organization="
				+ organization + "]";
	}

	public User(int userId, @NotNull(message = "UserName is required") @Size(max = 15) String userName,
			@NotNull(message = "First name is required") @Size(max = 20) String firstName,
			@NotNull(message = "Last name is required") @Size(max = 20) String lastName,
			@NotNull(message = "Email is required") @Email String email, @NotNull int income, BigInteger phoneNumber,
			@NotNull(message = "Password is mandatory") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[#?!@$%^&*-]).{8,32}$", message = " Password should contain atleast one Capital Letter, one digit and one symbol!") String password,
			@NotNull(message = "Re-Enter your password") String confirmPassword, String role,
			@NotNull(message = "Oops! Seems like you forgot to fill in the address!") String address,
			@NotNull(message = "Oops! Seems like you forgot to fill in your dob!") LocalDate dateOfBirth,
			@NotNull(message = "Oops! Seems like you forgot to fill in the typeOfEmployement!") String typeOfEmployement,
			@NotNull(message = "Oops! Seems like you forgot to fill in the organisation name!") String organization) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.income = income;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.typeOfEmployement = typeOfEmployement;
		this.organization = organization;
	}

}
