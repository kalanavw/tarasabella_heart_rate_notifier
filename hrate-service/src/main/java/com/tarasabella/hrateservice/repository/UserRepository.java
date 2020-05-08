package com.tarasabella.hrateservice.repository;

import com.tarasabella.hrateservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByUsername( String username );
}
