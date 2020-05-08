package com.tarasabella.hrateservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "USERS")
@JsonIgnoreProperties(value = { "password" }, allowSetters = true)
public class User implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private long id;

	@Column(name = "USERNAME", unique = true)
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CONTACT_NO")
	private String contactNo;

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "MODIFIED_DATE", nullable = false)
	@LastModifiedDate
	private LocalDateTime modifiedDate = LocalDateTime.now();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	private Set<HeartRate> heartRates = new HashSet<>();
}
