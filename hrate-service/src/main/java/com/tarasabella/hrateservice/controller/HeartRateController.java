package com.tarasabella.hrateservice.controller;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.model.HeartRate;
import com.tarasabella.hrateservice.service.HeartRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/10/2020.
 */
@RestController
@RequestMapping("heartRates")
public class HeartRateController
{
	@Autowired
	private HeartRateService heartRateService;

	@GetMapping
	public ResponseEntity<EsResponse<List<HeartRate>>> findHeartRates( @RequestParam(required = false) String username )
	{
		return ResponseEntity.ok( this.heartRateService.findAllHeartRates( username ) );
	}

	@PostMapping
	public ResponseEntity<EsResponse<HeartRate>> createNewHeartRate( @RequestBody HeartRate heartRate )
	{
		return ResponseEntity.ok( this.heartRateService.createNewHeartRate( heartRate ) );
	}
}
