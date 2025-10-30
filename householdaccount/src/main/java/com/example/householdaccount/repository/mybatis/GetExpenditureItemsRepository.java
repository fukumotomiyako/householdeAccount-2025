package com.example.householdaccount.repository.mybatis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.householdaccount.entity.ExpenditureItems;

@Repository
public interface GetExpenditureItemsRepository extends JpaRepository<ExpenditureItems,Integer>{
														//ExpenditureItemsの主キーの型(Integer)
	
}
