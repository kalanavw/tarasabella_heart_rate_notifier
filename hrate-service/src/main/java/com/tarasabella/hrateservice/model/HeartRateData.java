package com.tarasabella.hrateservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class HeartRateData
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private long id;

	@Column(name = "PULSE_COUNT")
	private int value;

	@Column(name = "PULSE_TIME")
	private LocalDateTime time;

	@Column(name = "PULSE_TIME_STR")
	private String pulseTimeString;

	public HeartRateData( int value, LocalDateTime time, String pulseTimeString )
	{
		this.value = value;
		this.time = time;
		this.pulseTimeString = pulseTimeString;
	}
}
