package com.tarasabella.hrateservice.service;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.model.Role;
import com.tarasabella.hrateservice.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/9/2020.
 */
@Service
public class RoleService
{
	private static final Logger logger = LogManager.getLogger( RoleService.class );
	@Autowired
	private RoleRepository roleRepository;

	public EsResponse<List<Role>> findAllRoles()
	{
		try
		{
			return new EsResponse<>( 1, this.roleRepository.findAll(), "role search" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			logger.error( "Observation error", e );
			return new EsResponse<>( 1, this.roleRepository.findAll(), "role search error" );
		}
	}
}
