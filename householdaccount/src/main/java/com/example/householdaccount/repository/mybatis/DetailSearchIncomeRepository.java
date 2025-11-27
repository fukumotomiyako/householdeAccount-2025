package com.example.householdaccount.repository.mybatis;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultIncome;

public interface DetailSearchIncomeRepository extends JpaRepository<SearchResultIncome,IncomeNoVO>, JpaSpecificationExecutor<SearchResultIncome> {
 
}
