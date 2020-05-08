package com.tarasabella.hrateservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "HEART_RATES")
public class HeartRate
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_HEART_RATES"))
	private User user;

	@OneToMany(mappedBy = "heartRate", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	private Set<HeartRateData> heartRateData = new HashSet<>();
}
