package com.example.householdaccount.repository.mybatis;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;

public interface ExpenditureRepository extends JpaRepository<Expenditure,ExpenditureNoVO>{

}
