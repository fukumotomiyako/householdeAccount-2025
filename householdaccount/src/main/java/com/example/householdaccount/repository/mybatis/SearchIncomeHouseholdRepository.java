package com.example.householdaccount.repository.mybatis;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultIncome;

public interface SearchIncomeHouseholdRepository extends JpaRepository<SearchResultIncome, IncomeNoVO> {

	@Query(value = "SELECT i.INCOME_NO, i.INCOME_DATE, i.INCOME_TYPE, i.AMOUNT, i.NOTE , i.DELETE_FRAG FROM INCOME_INFO AS i WHERE i.INCOME_NO LIKE %:balanceCode% and i.DELETE_FRAG != true ORDER BY i.INCOME_NO ASC", nativeQuery = true)
	public List<SearchResultIncome> findIncomeByBalanceCode(String balanceCode);
	
	public List<SearchResultIncome> findAll(Specification<SearchResultIncome> findAllSpecification);
}
