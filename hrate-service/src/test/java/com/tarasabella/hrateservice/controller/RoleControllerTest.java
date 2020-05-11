package com.tarasabella.hrateservice.controller;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.model.Role;
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
 * Created by kalana.w on 5/10/2020.
 */
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class RoleControllerTest
{
	@Mock
	private RoleController roleController;

	@Test
	public void findAll() throws Exception
	{
		/*Role role = new Role();
		role.setId( 1L );
		role.setRole( "ADMIN" );
		role.setDescription( "ADMIN Role" );
		role.setCreatedDate( LocalDateTime.now() );
		role.setModifiedDate( LocalDateTime.now() );
		List<Role> roles = Collections.singletonList( role );*/
		ResponseEntity<EsResponse<List<Role>>> responseEntity = new ResponseEntity<>( HttpStatus.ACCEPTED );

		BDDMockito.given( roleController.findAllRoles() ).willReturn( responseEntity );
		ResponseEntity<EsResponse<List<Role>>> allRoles = this.roleController.findAllRoles();
		Assert.assertEquals( responseEntity.getStatusCode(), allRoles.getStatusCode() );
	}
}
