package com.tarasabella.hrateservice.controller;

import com.tarasabella.hrateservice.model.EsResponse;
import com.tarasabella.hrateservice.model.Role;
import com.tarasabella.hrateservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/9/2020.
 */
@RestController
@RequestMapping("roles")
public class RoleController
{
	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<EsResponse<List<Role>>> findAllRoles()
	{
		return ResponseEntity.ok( this.roleService.findAllRoles() );
	}
}
