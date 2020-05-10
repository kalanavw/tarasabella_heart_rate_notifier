package com.tarasabella.hrateservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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

	@Column(name = "TOTAL_RATE_COUNT", nullable = false, updatable = false)
	private int totalRateCount;

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate = LocalDateTime.now();

	@JsonIgnore
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_HEART_RATES"))
	private User user;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinTable(name = "HEART_RATES_HAS_DATA", joinColumns = @JoinColumn(name = "HEART_RATES_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_HEART_RATES2DATA")), inverseJoinColumns = @JoinColumn(name = "HEART_RATES_DATA_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_HEART_RATES_DATA2HEART_RATES")))
	private Set<HeartRateData> heartRateData;
}
