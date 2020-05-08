package com.tarasabella.hrateservice.service;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.model.Role;
import com.tarasabella.hrateservice.model.User;
import com.tarasabella.hrateservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public EsResponse<User> createNewUser( User user )
	{
		user.setRoles( Stream.of( new Role( 1L ) ).collect( Collectors.toSet() ) );
		user.setPassword( this.bcryptEncoder.encode( user.getPassword() ) );
		return new EsResponse<>( 1, this.userRepository.save( user ), "user created success" );
	}

	public Optional<User> findByUsername( String username )
	{
		return this.userRepository.findByUsername( username );
	}
}
