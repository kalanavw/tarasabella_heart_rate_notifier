package com.tarasabella.hrateservice.controller;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/9/2020.
 */
@RestController
@RequestMapping("users")
public class UserController
{
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<EsResponse<?>> findUsers( @RequestParam(required = false) String username )
	{
		return ResponseEntity.ok( this.userService.findUsers( username ) );
	}
}
