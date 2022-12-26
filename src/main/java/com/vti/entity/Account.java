package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vti.converter.RoleConverter;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Account")
@Data
@NoArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id")
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
	@Convert(converter = RoleConverter.class)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "Department_ID", nullable = false)
	private Department department;
	
	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
}
