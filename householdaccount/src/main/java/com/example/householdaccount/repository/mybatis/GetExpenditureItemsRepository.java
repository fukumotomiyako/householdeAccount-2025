package com.example.householdaccount.repository.mybatis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.householdaccount.entity.ExpenditureItems;

@Repository
public interface GetExpenditureItemsRepository extends JpaRepository<ExpenditureItems,String>{
														//ExpenditureItemsの主キーの型(Integer)
//	@Query(value = "SELECT * FROM expenditure_item", nativeQuery = true)
//	public List<ExpenditureItems> findExpenditureByBalanceCode();

	
}
