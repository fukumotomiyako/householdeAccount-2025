package com.example.householdaccount.repository.mybatis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;

public interface ExpenditureRepository extends JpaRepository<Expenditure,ExpenditureNoVO>{

	@Query(value = "SELECT e.VERSION FROM EXPENDITURE_INFO AS e WHERE e.EXPENDITURE_NO = :expenditureNo",nativeQuery = true)
	Integer findByExpenditureNo(@Param("expenditureNo") String expenditureNo);
}
