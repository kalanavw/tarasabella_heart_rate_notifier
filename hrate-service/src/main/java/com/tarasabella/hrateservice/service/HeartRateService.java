package com.tarasabella.hrateservice.service;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.model.HeartRate;
import com.tarasabella.hrateservice.model.HeartRateData;
import com.tarasabella.hrateservice.repository.HeartRateRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/10/2020.
 */
@Service
public class HeartRateService
{
	private static final Logger logger = LogManager.getLogger( HeartRateService.class );
	@Autowired
	private HeartRateRepository heartRateRepository;

	public EsResponse<HeartRate> createNewHeartRate( HeartRate heartRate )
	{
		Set<HeartRateData> heartRateDataSet = new LinkedHashSet<>();
		for ( int i = 0; i < heartRate.getTotalRateCount(); i++ )
		{
			heartRateDataSet.add( new HeartRateData( i + 1, LocalDateTime.now(), LocalDateTime.now().format( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss SSS" ) ) ) );
			try
			{
				Thread.sleep( 2 );
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
		heartRate.setHeartRateData( heartRateDataSet );
		try
		{
			return new EsResponse<>( 1, this.heartRateRepository.save( heartRate ), "Observation success" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			logger.error( "Observation error", e );
			return new EsResponse<>( -1, "Observation error" );
		}
	}

	public EsResponse<List<HeartRate>> findAllHeartRates( String username )
	{
		try
		{
			if ( username != null && !username.trim().isEmpty() )
			{
				return new EsResponse<>( 1, this.heartRateRepository.findByUserUsernameOrderByCreatedDateDesc( username ), "Heart rate data found" );
			}
			return new EsResponse<>( 1, this.heartRateRepository.findAllByOrderByCreatedDateDesc(), "Heart rate data found" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			logger.error( "error in findAllHeartRates", e );
			return new EsResponse<>( -1, "Heart rate data search failed" );
		}
	}
}
