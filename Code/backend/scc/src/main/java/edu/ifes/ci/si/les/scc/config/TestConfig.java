package edu.ifes.ci.si.les.scc.config;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import edu.ifes.ci.si.les.scc.services._DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private _DBService dbService;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException, IOException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
}
