package com.tarasabella.hrateservice.repository;

import com.tarasabella.hrateservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
}
