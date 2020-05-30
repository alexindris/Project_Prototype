package com.tcm.prototype.persistence;

import com.tcm.prototype.domain.Data;

import org.springframework.data.repository.CrudRepository;

public interface DataRepositoryCrud extends CrudRepository<Data, String> {

	
}
