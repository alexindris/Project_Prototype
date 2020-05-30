package com.tcm.prototype.persistence;

import com.tcm.prototype.domain.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
//@Bean(name="entityManagerFactory")
public interface DataRepositoryCrud extends CrudRepository<Data, String> {

	
}
