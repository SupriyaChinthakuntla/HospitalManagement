package com.itcs6112.oas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "user_info")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "fname")
	private String fname;

	@Column(name = "lname")
	private String lname;

	@Column(name = "role")
	private String role;

	@Column(name = "password")
	@org.springframework.data.annotation.Transient
	private String password;


	@Transient
	private String testing;

	public String getTesting() {
		return this.testing;
	}

	public Integer getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFname() {
		return this.fname;
	}

	public String getLname() {
		return this.lname;
	}

	public String getRole() {
		return this.role;
	}

	// setters
	public void setTesting(final String testing) {
		this.testing = testing;
	}

	public String getPassword() {
		return this.password;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setFname(final String fname) {
		this.fname = fname;
	}

	public void setLname(final String lname) {
		this.lname = lname;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}