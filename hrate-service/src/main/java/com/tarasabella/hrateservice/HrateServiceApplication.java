package com.tarasabella.hrateservice;

import com.tarasabella.hrateservice.model.Role;
import com.tarasabella.hrateservice.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HrateServiceApplication
{
	private static final Logger logger = LogManager.getLogger( HrateServiceApplication.class );
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main( String[] args )
	{
		printHeart();
		SpringApplication.run( HrateServiceApplication.class, args );
	}

	private static void printHeart()
	{
		char heart = '♥';
		for ( int i = 5; i >= 0; i-- )
		{
			for ( int j = 0; j <= i; j++ )
			{
				System.out.print( heart );
				System.out.print( heart );
			}
			System.out.println();
		}
		System.out.println( heart );
		for ( int i = 0; i <= 5; i++ )
		{
			for ( int j = 0; j <= i; j++ )
			{
				System.out.print( heart );
				System.out.print( heart );
			}
			System.out.println();
		}
	}

	@Bean
	@Transactional
	public CommandLineRunner demoData( RoleRepository roleRepository )
	{
		return args -> {
			List<Role> roles = new ArrayList<Role>()
			{
				{
					add( new Role( "USER", "With basic privileges" ) );
					add( new Role( "ADMIN", "With full privileges" ) );
				}
			};
			this.jdbcTemplate.execute( "DELETE FROM USER_ROLES" );
			roleRepository.deleteAll();
			roleRepository.saveAll( roles );

			logger.debug( "Debugging log" );
			logger.info( "Info log" );
			logger.warn( "Hey, This is a warning!" );
			logger.error( "Oops! We have an Error. OK" );
			logger.fatal( "Damn! Fatal error. Please fix me." );
		};
	}
}
