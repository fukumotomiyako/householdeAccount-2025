package com.example.householdaccount.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.form.ExpenditureEditForm;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeEditForm;
import com.example.householdaccount.form.IncomeForm;
import com.example.householdaccount.repository.mybatis.ExpenditureRepository;
import com.example.householdaccount.repository.mybatis.GetExpenditureItemsRepository;
import com.example.householdaccount.repository.mybatis.IncomeRepository;

@ExtendWith(MockitoExtension.class)
public class ServiceJunitTestMock {

	@InjectMocks
	private HouseholdService householdService;
	@Mock
	private IncomeRepository incomeRepository;
	@Mock
	private ExpenditureRepository expenditureRepository;
	@Mock
	private Income income;
	@Mock
	private Expenditure expenditure;
	@Mock
	private GetExpenditureItemsRepository expenditureItemRepository;

	@Test
	void DB停止時の収入登録() throws Exception {

		// 日付(String)をDate型に変換
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

//		formに値をセット
		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(1);
		incomeCommand.setPrice(1);
		incomeCommand.setDate(sqlDate);
		incomeCommand.setNote(null);

		when(incomeRepository.save(income)).thenThrow(new RuntimeException());

		Exception exception = assertThrows(Exception.class, () -> {
			householdService.createIncomeInfo(incomeCommand);
		});
		assertEquals("システムエラーが発生しました", exception.getMessage());
	}

	@Test
	void DB停止時の支出登録() throws Exception {
		// 日付(String)をDate型に変換
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

//		formに値をセット
		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setPrice(1);
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setNote(null);
		
		String expenditureItemName = expenditureCommand.getSelectExpenditure();

		when(expenditureItemRepository
				.findByExpenditureExpenseItemName(expenditureItemName)).thenThrow(new RuntimeException());

		Exception exception = assertThrows(Exception.class, () -> {
			householdService.createExpenditureInfo(expenditureCommand);
		});
		assertEquals("システムエラーが発生しました", exception.getMessage());
	}

//編集
	@Test
	void DB停止時収入編集() throws Exception {
		String strDate = "2026-01-10";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		IncomeEditForm incomeEditForm = new IncomeEditForm();
		incomeEditForm.setBalanceType("収入");
		incomeEditForm.setBalanceNo("I251200016");
		incomeEditForm.setBalanceDate(sqlDate);
		incomeEditForm.setIncomeType(1);
		incomeEditForm.setAmount(500);
		incomeEditForm.setNote("あああ");

		when(incomeRepository.save(income)).thenThrow(new RuntimeException());

		Exception exception = assertThrows(Exception.class, () -> {
			householdService.incomeEdit(incomeEditForm);
		});
		assertEquals("システムエラーが発生しました", exception.getMessage());
	}

	@Test
	void DB停止時支出編集() throws Exception {
		String strDate = "2026-01-10";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
		expenditureEditForm.setBalanceType("支出");
		expenditureEditForm.setBalanceNo("E251200012");
		expenditureEditForm.setBalanceDate(sqlDate);
		expenditureEditForm.setExpenditureExpenseItemName("日用品");
		expenditureEditForm.setAmount(500);
		expenditureEditForm.setNote("いいい");
		
		String expenditureItemName = expenditureEditForm.getExpenditureExpenseItemName();

		when(expenditureItemRepository
				.findByExpenditureExpenseItemName(expenditureItemName)).thenThrow(new RuntimeException());

		Exception exception = assertThrows(Exception.class, () -> {
			householdService.expenditureEdit(expenditureEditForm);
		});
		assertEquals("システムエラーが発生しました", exception.getMessage());
	}
	
//削除	
	@Test
	void DB停止時収入削除() throws Exception{
		
		String incomeNo = "I251200016";
		when(incomeRepository.findById(incomeNo)).thenThrow(new RuntimeException());
		
		Exception exception = assertThrows(Exception.class, () -> {
			householdService.incomeDelete(incomeNo);
		});
		assertEquals("システムエラーが発生しました", exception.getMessage());
	}
	
	@Test
	void DB停止時支出削除() throws Exception{
		
		String expenditureNo = "E251200012";
		when(expenditureRepository.findById(expenditureNo)).thenThrow(new RuntimeException());
		
		Exception exception = assertThrows(Exception.class, () -> {
			householdService.expenditureDelete(expenditureNo);
		});
		assertEquals("システムエラーが発生しました", exception.getMessage());
	}
}
