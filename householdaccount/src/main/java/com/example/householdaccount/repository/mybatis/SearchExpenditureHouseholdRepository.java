package com.example.householdaccount.repository.mybatis;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;
import com.example.householdaccount.entity.SearchResultExpenditure;

public interface SearchExpenditureHouseholdRepository extends JpaRepository<SearchResultExpenditure, ExpenditureNoVO>{

	@Query(value = "SELECT e.EXPENDITURE_NO, e.EXPENDITURE_DATE, e.EXPENDITURE_EXPENSE_ITEM_NAME, e.AMOUNT, e.NOTE, e.DELETE_FRAG FROM EXPENDITURE_INFO AS e WHERE e.EXPENDITURE_NO LIKE %:balanceCode% and e.DELETE_FRAG != true ORDER BY e.EXPENDITURE_NO ASC", nativeQuery = true)
	public List<SearchResultExpenditure> findExpenditureByBalanceCode(String balanceCode);
	
	public List<SearchResultExpenditure> findAll(Specification<SearchResultExpenditure> findAllSpecification);
}