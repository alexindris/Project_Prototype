package com.tcm.prototype.persistence;

import com.tcm.prototype.domain.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration

@Repository
public interface DataRepositoryCrud extends JpaRepository<Data, String> {
	
	/*@Bean
	public Data findByName(String name);*/
}
