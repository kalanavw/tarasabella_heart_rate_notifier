package com.tarasabella.hrateservice;

import com.tarasabella.hrateservice.model.Role;
import com.tarasabella.hrateservice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HrateServiceApplication
{

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
			roleRepository.deleteAll();
			roleRepository.saveAll( roles );
		};
	}
}
