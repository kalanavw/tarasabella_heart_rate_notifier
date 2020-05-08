package com.tarasabella.hrateservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "HEART_RATES_DATA")
public class HeartRateData
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private long id;

	@Column(name = "PULSE_COUNT")
	private int count;

	@Column(name = "PULSE_TIME")
	private LocalDateTime pulseTime;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "HEART_RATE_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_HEART_RATE_DATA"))
	private HeartRate heartRate;
}
