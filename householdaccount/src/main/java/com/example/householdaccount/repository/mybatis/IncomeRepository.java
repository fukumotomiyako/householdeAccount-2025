package com.example.householdaccount.repository.mybatis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.Income.IncomeNoVO;

public interface IncomeRepository extends JpaRepository<Income,IncomeNoVO>{

	@Query(value = "SELECT i.VERSION FROM INCOME_INFO AS i WHERE i.INCOME_NO = :incomeNo",nativeQuery = true)
	Integer findByIncomeNo(@Param("incomeNo") String incomeNo);
	
	@Query(value="SELECT * FROM INCOME_INFO AS i WHERE i.INCOME_NO = :incomeNo",nativeQuery = true)
	Income findById(@Param("incomeNo") String incomeNo);
}
