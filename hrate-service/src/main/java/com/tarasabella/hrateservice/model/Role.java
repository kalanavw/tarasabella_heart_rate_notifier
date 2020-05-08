package com.tarasabella.hrateservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "ROLES")
@NoArgsConstructor
public class Role implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private long id;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "MODIFIED_DATE", nullable = false)
	@LastModifiedDate
	private LocalDateTime modifiedDate = LocalDateTime.now();

	public Role( String role, String description )
	{
		this.role = role;
		this.description = description;
	}

	public Role( long l )
	{
		this.id = l;
	}
}
