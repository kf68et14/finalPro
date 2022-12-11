package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Account`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "AccountID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "UserName", length = 50, nullable = false, unique = false)
	private String username;
	
	@Column(name = "FirstName", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "LastName", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Role", nullable = true)
	@Enumerated(EnumType.STRING)	
	private Role role;

	@ManyToOne
	@JoinColumn(name = "DepartmentID", nullable = false)
	private Department department;
	
	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	public Account(String username, String firstName, String lastName, Role role, Department department){
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.department = department;
	}
	
}
