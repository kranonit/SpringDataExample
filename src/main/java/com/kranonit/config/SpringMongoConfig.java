package com.kranonit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

	@Override
	public String getDatabaseName() {
		return "kranonit";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new Mongo("localhost");
	}
}