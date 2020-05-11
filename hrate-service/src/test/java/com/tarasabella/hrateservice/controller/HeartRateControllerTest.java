package com.tarasabella.hrateservice.controller;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.model.HeartRate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/11/2020.
 */
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class HeartRateControllerTest
{
	@Mock
	private HeartRateController heartRateController;

	@Test
	public void findHeartRates()
	{
		ResponseEntity<EsResponse<List<HeartRate>>> responseEntity = new ResponseEntity<>( HttpStatus.ACCEPTED );

		BDDMockito.given( heartRateController.findHeartRates( "" ) ).willReturn( responseEntity );
		ResponseEntity<EsResponse<List<HeartRate>>> allRoles = this.heartRateController.findHeartRates( "" );
		Assert.assertEquals( responseEntity.getStatusCode(), allRoles.getStatusCode() );
	}

	@Test
	public void createNewHeartRate()
	{
		HeartRate heartRate = new HeartRate();
		heartRate.setId( 1L );
		heartRate.setTotalRateCount( 75 );
		ResponseEntity<EsResponse<HeartRate>> responseEntity = new ResponseEntity<>( HttpStatus.ACCEPTED );
		BDDMockito.given( heartRateController.createNewHeartRate( heartRate ) ).willReturn( responseEntity );
		ResponseEntity<EsResponse<HeartRate>> allRoles = this.heartRateController.createNewHeartRate( heartRate );
		Assert.assertEquals( responseEntity.getStatusCode(), allRoles.getStatusCode() );
	}
}