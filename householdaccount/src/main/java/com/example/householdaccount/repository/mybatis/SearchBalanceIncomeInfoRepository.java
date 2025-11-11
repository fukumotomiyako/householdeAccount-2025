package com.example.householdaccount.repository.mybatis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchBalanceIncomeInfo;

public interface SearchBalanceIncomeInfoRepository extends JpaRepository<SearchBalanceIncomeInfo,IncomeNoVO>{
	@Query(value = "SELECT i.INCOME_NO, i.INCOME_DATE, i.INCOME_TYPE, i.AMOUNT, i.NOTE FROM INCOME_INFO AS i WHERE i.INCOME_NO =:balanceNo",nativeQuery = true)
	SearchBalanceIncomeInfo findByBalanceNo(@Param("balanceNo") String balanceNo);
	

}
