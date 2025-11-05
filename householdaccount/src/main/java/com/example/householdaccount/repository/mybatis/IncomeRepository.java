package com.example.householdaccount.repository.mybatis;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.Income.IncomeNoVO;

public interface IncomeRepository extends JpaRepository<Income,IncomeNoVO>{

}
