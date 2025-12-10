package com.example.householdaccount.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeForm;
import com.example.householdaccount.repository.mybatis.ExpenditureRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Rollback
public class ServiceJUnitTest {

	@Autowired
	HouseholdService householdService;
	
	@Autowired
	ExpenditureRepository expenditureRepository;

	// 収入正常系テスト
	@Test
	void 収入正常登録１() throws Exception {

		// 日付(String)をDate型に変換
		String strDate = "2025-12-31";
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
		Date sqlDate = java.sql.Date.valueOf(strDate);

//		formに値をセット
		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(1);
		incomeCommand.setPrice(1);
		incomeCommand.setDate(sqlDate);
		incomeCommand.setNote(null);

//		EntityをFormを引数に呼び出す(service呼び出し)
		Income income = householdService.createIncomeInfo(incomeCommand);

//		値比較
		assertEquals(sqlDate, income.getIncomeDate());
		assertEquals(1, income.getIncomeType());
		assertEquals(1, income.getAmount());
		assertEquals(null, income.getNote());
	}

	// 支出正常系テスト
	@Test
	void 支出正常登録1() throws Exception {
		// 日付(String)をDate型に変換
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

//		formに値をセット
		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setPrice(99999999);
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setNote(
				"いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい");

		Expenditure expenditure = householdService.createExpenditureInfo(expenditureCommand);

		assertEquals(sqlDate, expenditure.getExpenditureDate());
		assertEquals(99999999, expenditure.getAmount());
		assertEquals("食費", expenditure.getExpenditureExpenseItemName());
		assertEquals(
				"いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい",
				expenditure.getNote());
	}

	@Test
	void 支出正常登録2() throws Exception {
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

		Expenditure expenditure = householdService.createExpenditureInfo(expenditureCommand);

		assertEquals(sqlDate, expenditure.getExpenditureDate());
		assertEquals(1, expenditure.getAmount());
		assertEquals("食費", expenditure.getExpenditureExpenseItemName());
		assertEquals(null, expenditure.getNote());
	}

	// 収入異常系テスト
	@Test
	void すべてがNULLの収入異常登録() throws Exception {

		// Form呼び出し
		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(null);
		incomeCommand.setPrice(null);
		incomeCommand.setDate(null);
		incomeCommand.setNote(null);

		// 第一引数内で指定した例外クラス(IllegalArgumentException)が、
		// 第二引数(householdService.createIncomeInfo(incomeCommand);)のクラスで発生するかを確認
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createIncomeInfo(incomeCommand);
		});
		assertEquals("値がNULLです",exception.getMessage());
	}

	@Test
	void 日付に数値以外入力時の収入異常登録() throws Exception {

		// 日付(String)をDate型に変換
		String strDate = "eeee-ee-ee";
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
		assertEquals("数字のみ入力できます",exception.getMessage());
	}

	@Test
	void 日付が年のみ入力時の収入異常登録() throws Exception {
		// 日付(String)をDate型に変換
		String strDate = "2025";
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
		assertEquals("数字のみ入力できます",exception.getMessage());
	}

	@Test
	void 収入種別未選択時の収入異常登録() throws Exception {
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(null);
		incomeCommand.setPrice(1000);
		incomeCommand.setDate(sqlDate);
		incomeCommand.setNote("あああ");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createIncomeInfo(incomeCommand);
		});
		assertEquals("値がNULLです",exception.getMessage());
	}

	@Test
	void 金額に数字以外の値入力時の収入異常登録() throws Exception {

		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		// String型をInteger型に変換
		Integer amount = Integer.parseInt("e");

		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(1);
		incomeCommand.setPrice(amount);
		incomeCommand.setDate(sqlDate);
		incomeCommand.setNote("あああ");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createIncomeInfo(incomeCommand);
		});
		assertEquals("数字のみ入力できます",exception.getMessage());
	}

	@Test
	void 金額に9桁以上入力時の収入異常登録() throws Exception {
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(1);
		incomeCommand.setPrice(100000000);
		incomeCommand.setDate(sqlDate);
		incomeCommand.setNote("あああ");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createIncomeInfo(incomeCommand);
		});
		assertEquals("不正な桁数です",exception.getMessage());
	}

	@Test
	void 備考に201文字以上入力時の収入異常登録() throws Exception {
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		IncomeForm incomeCommand = new IncomeForm();
		incomeCommand.setRadioName("収入");
		incomeCommand.setSelectIncome(1);
		incomeCommand.setPrice(1000);
		incomeCommand.setDate(sqlDate);
		incomeCommand.setNote(
				"あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createIncomeInfo(incomeCommand);
		});
		assertEquals("不正な桁数です",exception.getMessage());
	}

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
		assertEquals("システムエラーが発生しました",exception.getMessage());
	}

	// 支出異常登録

	@Test
	void すべてがNULLの支出異常登録() throws Exception {
		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setDate(null);
		expenditureCommand.setSelectExpenditure(null);
		expenditureCommand.setPrice(null);
		expenditureCommand.setNote(null);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createExpenditureInfo(expenditureCommand);
		});
		assertEquals("値がNULLです",exception.getMessage());
	}

	@Test
	void 日付に数値以外入力時の支出異常登録() throws Exception {
		String strDate = "eeee-ee-ee";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setPrice(1000);
		expenditureCommand.setNote("いいい");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createExpenditureInfo(expenditureCommand);
		});
		assertEquals("数字のみ入力できます",exception.getMessage());
	}

	@Test
	void 日付が年のみ入力時の支出異常登録() throws Exception {
		String strDate = "2025--";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setPrice(1000);
		expenditureCommand.setNote("いいい");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createExpenditureInfo(expenditureCommand);
		});
		assertEquals("数字のみ入力できます",exception.getMessage());
	}

	@Test
	void 支出費目未選択時の支出異常登録() throws Exception {
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setSelectExpenditure(null);
		expenditureCommand.setPrice(1000);
		expenditureCommand.setNote("いいい");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createExpenditureInfo(expenditureCommand);
		});
		assertEquals("値がNULLです",exception.getMessage());
	}

	@Test
	void 金額に数字以外の値入力時の支出異常登録() throws Exception {
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		// String型をInteger型に変換
		Integer amount = Integer.parseInt("e");

		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setPrice(amount);
		expenditureCommand.setNote("いいい");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createExpenditureInfo(expenditureCommand);
		});
		assertEquals("数字のみ入力できます",exception.getMessage());
	}

	@Test
	void 金額に9桁以上入力時の支出異常登録() throws Exception {
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);

		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setPrice(100000000);
		expenditureCommand.setNote("いいい");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createExpenditureInfo(expenditureCommand);
		});
		assertEquals("不正な桁数です",exception.getMessage());
	}

	@Test
	void 備考に201文字以上入力時の支出異常登録() throws Exception {
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);
		
		ExpenditureForm expenditureCommand = new ExpenditureForm();
		expenditureCommand.setRadioName("支出");
		expenditureCommand.setDate(sqlDate);
		expenditureCommand.setSelectExpenditure("食費");
		expenditureCommand.setPrice(1000);
		expenditureCommand.setNote("いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.createExpenditureInfo(expenditureCommand);
		});
		assertEquals("不正な桁数です",exception.getMessage());
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
		
		Expenditure expenditure = householdService.createExpenditureInfo(expenditureCommand);

//		Exception exception = assertThrows(Exception.class, () -> {
//			householdService.createExpenditureInfo(expenditureCommand);
//		});
//		assertEquals("システムエラーが発生しました",exception.getMessage());
		
		expenditureRepository = mock(ExpenditureRepository.class);
		when(expenditureRepository.save(expenditure)).thenThrow(new Exception("システムエラーが発生しました"));
		assertThrows(Exception.class,() -> {expenditure});
	}
}
