package com.example.householdaccount.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeForm;
import com.example.householdaccount.repository.mybatis.ExpenditureRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
@Rollback
public class ServiceJunitTestMock {

	@InjectMocks
	HouseholdService householdService;

	@MockBean
	ExpenditureRepository expenditureRepository;

	@Test
	void DB停止時の収入() throws Exception {

		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);
		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(1);
		incomeCommand.setPrice(1000);
		incomeCommand.setDate(sqlDate);
		incomeCommand.setNote("あああ");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createIncomeInfo(incomeCommand);
		});
		assertEquals("システムエラーが発生しました", exception.getMessage());
	}
	
	@Test
	void DB停止時の支出() throws Exception {

		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

//		formに値をセット
		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setPrice(1);
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setNote(null);
		
		Expenditure expenditure = new Expenditure();

		when(expenditureRepository.save(expenditure)).thenThrow(new Exception("システムエラーが発生しました"));

		assertThrows(Exception.class, () -> householdService.createExpenditureInfo(expenditureCommand));

	}
}
