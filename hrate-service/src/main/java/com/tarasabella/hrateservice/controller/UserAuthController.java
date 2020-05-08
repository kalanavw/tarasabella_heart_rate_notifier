package com.tarasabella.hrateservice.controller;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.model.JwtRequest;
import com.tarasabella.hrateservice.model.JwtResponse;
import com.tarasabella.hrateservice.model.User;
import com.tarasabella.hrateservice.service.JwtUserDetailsService;
import com.tarasabella.hrateservice.service.UserService;
import com.tarasabella.hrateservice.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@RestController
@RequestMapping("session/")
@CrossOrigin
public class UserAuthController
{
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserService userService;

	@PostMapping("authenticate")
	public ResponseEntity<EsResponse> createAuthenticationToken( @RequestBody JwtRequest authenticationRequest ) throws Exception
	{

		authenticate( authenticationRequest.getUsername(), authenticationRequest.getPassword() );
		final UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername( authenticationRequest.getUsername() );
		final String token = this.jwtTokenUtil.generateToken( userDetails );
		return ResponseEntity.ok( new EsResponse<>( 1, new JwtResponse( token ), "authentication success" ) );
	}

	private void authenticate( String username, String password ) throws Exception
	{
		try
		{
			this.authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( username, password ) );
		}
		catch ( DisabledException e )
		{
			throw new Exception( "USER_DISABLED", e );
		}
		catch ( BadCredentialsException e )
		{
			throw new Exception( "INVALID_CREDENTIALS", e );
		}
	}

	@PostMapping("register")
	public ResponseEntity<EsResponse<User>> createNewUser( @RequestBody User user )
	{
		return ResponseEntity.ok( this.userService.createNewUser( user ) );
	}
}
