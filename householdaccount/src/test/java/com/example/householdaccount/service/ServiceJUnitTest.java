package com.example.householdaccount.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeForm;
//import org.junit.runner.RunWith;

//@RunWith(SpringRunner.class)
@SpringBootTest//調べる
@Transactional
@Rollback
public class ServiceJUnitTest {

	@Autowired
	HouseholdService householdService;

//	@BeforeEach
//	void setUp() {
//		householdService = new HouseholdService();
//	}
	
	// 収入正常系テスト
	@Test
	void 収入正常登録１() throws Exception{

		// 日付(String)をDate型に変換
		String strDate = "2025-12-31";
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = dateFormat.parse(strDate);
		Date sqlDate = java.sql.Date.valueOf(strDate);
		

//		formに値をセット
		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(1);
		incomeCommand.setPrice(99999999);
		incomeCommand.setDate(sqlDate);
		incomeCommand.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");

//		Formを引数にservice収入登録メソッド呼び出し
		Income income = householdService.createIncomeInfo(incomeCommand);

//		値比較
		assertEquals(sqlDate, income.getIncomeDate());
		assertEquals(1, income.getIncomeType());
		assertEquals(99999999, income.getAmount());
		assertEquals(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ",
				income.getNote());
	}

	@Test
	void 収入正常登録2() throws Exception {
		// 日付(String)をDate型に変換
		String strDate = "2025-12-31";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(strDate);

//		formに値をセット
		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(1);
		incomeCommand.setPrice(1);
		incomeCommand.setDate(date);
		incomeCommand.setNote(null);

//		EntityをFormを引数に呼び出す(service呼び出し)
		Income income = householdService.createIncomeInfo(incomeCommand);

//		値比較
		assertEquals(date, income.getIncomeDate());
		assertEquals(1, income.getIncomeType());
		assertEquals(1, income.getAmount());
		assertEquals(null, income.getNote());
	}

	@Test
	void 支出正常登録1() throws Exception {
		// 日付(String)をDate型に変換
		String strDate = "2025-12-31";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(strDate);

//		formに値をセット
		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setDate(date);
		expenditureCommand.setPrice(99999999);
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setNote(
				"いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい");
		
		Expenditure expenditure = householdService.createExpenditureInfo(expenditureCommand);
		
		assertEquals(date,expenditure.getExpenditureDate());
		assertEquals(99999999,expenditure.getAmount());
		assertEquals("食費",expenditure.getExpenditureExpenseItemName());
		assertEquals("いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい"
				,expenditure.getNote());
	}

}
