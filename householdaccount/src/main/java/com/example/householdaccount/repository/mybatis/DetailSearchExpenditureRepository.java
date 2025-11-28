package com.example.householdaccount.repository.mybatis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;
import com.example.householdaccount.entity.SearchResultExpenditure;

public interface DetailSearchExpenditureRepository extends  JpaRepository<SearchResultExpenditure,ExpenditureNoVO>,JpaSpecificationExecutor<SearchResultExpenditure>{

}
