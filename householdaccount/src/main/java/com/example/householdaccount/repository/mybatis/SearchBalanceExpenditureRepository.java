package com.example.householdaccount.repository.mybatis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;
import com.example.householdaccount.entity.SearchBalanceExpenditureInfo;

public interface SearchBalanceExpenditureRepository extends JpaRepository<SearchBalanceExpenditureInfo,ExpenditureNoVO>{
	@Query(value = "SELECT e.EXPENDITURE_NO, e.EXPENDITURE_DATE, e.EXPENDITURE_EXPENSE_ITEM_NAME, e.AMOUNT, e.NOTE FROM EXPENDITURE_INFO AS e WHERE e.EXPENDITURE_NO = %:balanceNo%", nativeQuery = true)
	SearchBalanceExpenditureInfo findByBalanceNo(String balanceNo);
}
