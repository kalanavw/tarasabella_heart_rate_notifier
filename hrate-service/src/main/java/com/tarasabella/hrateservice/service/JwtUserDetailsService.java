package com.tarasabella.hrateservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService
{
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
	{
		com.tarasabella.hrateservice.model.User user = this.userService.findByUsername( username ).orElseThrow( () -> new UsernameNotFoundException( "User Not Found with username: " + username ) );

		List<GrantedAuthority> authorities = user.getRoles().stream().map( role -> new SimpleGrantedAuthority( role.getRole() ) ).collect( Collectors.toList() );

		return new User( user.getUsername(), user.getPassword(), authorities );
	}
}
