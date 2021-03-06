package com.tarasabella.hrateservice.repository;

import com.tarasabella.hrateservice.model.HeartRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 5/8/2020.
 */
@Repository
public interface HeartRateRepository extends JpaRepository<HeartRate, Long>
{
	List<HeartRate> findByUserUsernameOrderByCreatedDateDesc( String userName );

	List<HeartRate> findAllByOrderByCreatedDateDesc();
}
