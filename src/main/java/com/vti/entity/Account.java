package com.vti.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Account")
@Setter @Getter
public class Account implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "UserName")
	private String username;
	
	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "Password")
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DepartmentID", nullable = false)
	private Department department;
	@Column(name = "Role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	public Account(String username, String firstName, String lastName, Role role){
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	Account() {}

	public enum Role {
		USER, MANAGER, ADMIN;
	}
	
}
