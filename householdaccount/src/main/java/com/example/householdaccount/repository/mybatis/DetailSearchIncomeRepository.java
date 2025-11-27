package com.example.householdaccount.repository.mybatis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.specification.DetailSearchIncomeSpecification;
import com.example.householdaccount.entity.SearchBalanceIncomeInfo;

public interface DetailSearchIncomeRepository extends JpaRepository<SearchBalanceIncomeInfo,IncomeNoVO>, JpaSpecificationExecutor<SearchBalanceIncomeInfo> {
//	List<SearchBalanceIncomeInfo> findByIncomeInfo(DetailSearchIncomeSpecification<SearchBalanceIncomeInfo> spec);
}
