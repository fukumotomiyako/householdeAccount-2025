package com.example.householdaccount.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultExpenditure;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.form.ExpenditureEditForm;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeEditForm;
import com.example.householdaccount.form.IncomeForm;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Rollback
public class ServiceJUnitTest {

	@Autowired
	private HouseholdService householdService;

//登録
	// 収入正常系テスト
//	@Test
//	void 収入正常登録１() throws Exception {
//
//		// 日付(String)をDate型に変換
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
////		formに値をセット
//		IncomeForm incomeCommand = new IncomeForm();
//		incomeCommand.setRadioName("収入");
//		incomeCommand.setSelectIncome(1);
//		incomeCommand.setPrice(99999999);
//		incomeCommand.setDate(sqlDate);
//		incomeCommand.setNote(
//				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
//
////		Formを引数にservice収入登録メソッド呼び出し
//		Income income = householdService.createIncomeInfo(incomeCommand);
//
////		値比較
//		assertEquals(sqlDate, income.getIncomeDate());
//		assertEquals(1, income.getIncomeType());
//		assertEquals(99999999, income.getAmount());
//		assertEquals(
//				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ",
//				income.getNote());
//	}
//
//	@Test
//	void 収入正常登録2() throws Exception {
//		// 日付(String)をDate型に変換
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
////		formに値をセット
//		IncomeForm incomeCommand = new IncomeForm();
//		incomeCommand.setRadioName("収入");
//		incomeCommand.setSelectIncome(1);
//		incomeCommand.setPrice(1);
//		incomeCommand.setDate(sqlDate);
//		incomeCommand.setNote(null);
//
////		EntityをFormを引数に呼び出す(service呼び出し)
//		Income income = householdService.createIncomeInfo(incomeCommand);
//
////		値比較
//		assertEquals(sqlDate, income.getIncomeDate());
//		assertEquals(1, income.getIncomeType());
//		assertEquals(1, income.getAmount());
//		assertEquals(null, income.getNote());
//	}
//
//	// 支出正常系テスト
//	@Test
//	void 支出正常登録1() throws Exception {
//		// 日付(String)をDate型に変換
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
////		formに値をセット
//		ExpenditureForm expenditureCommand = new ExpenditureForm();
//		expenditureCommand.setDate(sqlDate);
//		expenditureCommand.setPrice(99999999);
//		expenditureCommand.setRadioName("支出");
//		expenditureCommand.setSelectExpenditure("食費");
//		expenditureCommand.setNote(
//				"いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい");
//
//		Expenditure expenditure = householdService.createExpenditureInfo(expenditureCommand);
//		String code = String.valueOf(expenditure.getExpenditureExpenseItemCode());
//
//		assertEquals(sqlDate, expenditure.getExpenditureDate());
//		assertEquals(99999999, expenditure.getAmount());
//		assertEquals("食費", expenditure.getExpenditureExpenseItemName());
//		assertEquals("EI001", code);
//		assertEquals(
//				"いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい",
//				expenditure.getNote());
//	}
//
//	@Test
//	void 支出正常登録2() throws Exception {
//		// 日付(String)をDate型に変換
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
////		formに値をセット
//		ExpenditureForm expenditureCommand = new ExpenditureForm();
//		expenditureCommand.setDate(sqlDate);
//		expenditureCommand.setPrice(1);
//		expenditureCommand.setRadioName("支出");
//		expenditureCommand.setSelectExpenditure("食費");
//		expenditureCommand.setNote(null);
//
//		Expenditure expenditure = householdService.createExpenditureInfo(expenditureCommand);
//		String code = String.valueOf(expenditure.getExpenditureExpenseItemCode());
//
//		assertEquals(sqlDate, expenditure.getExpenditureDate());
//		assertEquals(1, expenditure.getAmount());
//		assertEquals("食費", expenditure.getExpenditureExpenseItemName());
//		assertEquals("EI001", code);
//		assertEquals(null, expenditure.getNote());
//	}
//
//	// 収入異常系テスト
//	@Test
//	void すべてがNULLの収入異常登録() throws Exception {
//
//		// Form呼び出し
//		IncomeForm incomeCommand = new IncomeForm();
//		incomeCommand.setRadioName("収入");
//		incomeCommand.setSelectIncome(null);
//		incomeCommand.setPrice(null);
//		incomeCommand.setDate(null);
//		incomeCommand.setNote(null);
//
//		// 第一引数内で指定した例外クラス(IllegalArgumentException)が、
//		// 第二引数(householdService.createIncomeInfo(incomeCommand);)のクラスで発生するかを確認
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createIncomeInfo(incomeCommand);
//		});
//		assertEquals("値がNULLです", exception.getMessage());
//	}
//
////	@Test
////	void 日付に数値以外入力時の収入異常登録() throws Exception {
////
////		// 日付(String)をDate型に変換
////		String strDate = "eeee-ee-ee";
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM-dd");
////		Date date = sdf.parse(strDate);
////
////		IncomeForm incomeCommand = new IncomeForm();
////		incomeCommand.setRadioName("収入");
////		incomeCommand.setSelectIncome(1);
////		incomeCommand.setPrice(1000);
////		incomeCommand.setDate(date);
////		incomeCommand.setNote("あああ");
////
////		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
////			householdService.createIncomeInfo(incomeCommand);
////		});
////		assertEquals("数字のみ入力できます", exception.getMessage());
////	}
//
//	@Test
//	void 日付が年のみ入力時の収入異常登録() throws Exception {
//		// 日付(String)をDate型に変換
//		String strDate = "2025";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		Date date = sdf.parse(strDate);
//
//		IncomeForm incomeCommand = new IncomeForm();
//		incomeCommand.setRadioName("収入");
//		incomeCommand.setSelectIncome(1);
//		incomeCommand.setPrice(1000);
//		incomeCommand.setDate(date);
//		incomeCommand.setNote("あああ");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createIncomeInfo(incomeCommand);
//		});
//		assertEquals("数字のみ入力できます", exception.getMessage());
//	}
//
//	@Test
//	void 収入種別未選択時の収入異常登録() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeForm incomeCommand = new IncomeForm();
//		incomeCommand.setRadioName("収入");
//		incomeCommand.setSelectIncome(null);
//		incomeCommand.setPrice(1000);
//		incomeCommand.setDate(sqlDate);
//		incomeCommand.setNote("あああ");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createIncomeInfo(incomeCommand);
//		});
//		assertEquals("値がNULLです", exception.getMessage());
//	}
//
////	@Test
////	void 金額に数字以外の値入力時の収入異常登録() throws Exception {
////		String strDate = "2025-12-31";
////		Date sqlDate = java.sql.Date.valueOf(strDate);
////		
////		IncomeForm incomeCommand = new IncomeForm();
////		incomeCommand.setRadioName("収入");
////		incomeCommand.setSelectIncome(1);
////		incomeCommand.setDate(sqlDate);
////		incomeCommand.setNote("あああ");
////		incomeCommand.setPrice();
////
////		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
////			householdService.createIncomeInfo(incomeCommand);
////		});
////		assertEquals("数字のみ入力できます", ex.getMessage());
////	}
//
//	@Test
//	void 金額に9桁以上入力時の収入異常登録() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeForm incomeCommand = new IncomeForm();
//		incomeCommand.setRadioName("収入");
//		incomeCommand.setSelectIncome(1);
//		incomeCommand.setPrice(100000000);
//		incomeCommand.setDate(sqlDate);
//		incomeCommand.setNote("あああ");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createIncomeInfo(incomeCommand);
//		});
//		assertEquals("不正な桁数です", exception.getMessage());
//	}
//
//	@Test
//	void 備考に201文字以上入力時の収入異常登録() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeForm incomeCommand = new IncomeForm();
//		incomeCommand.setRadioName("収入");
//		incomeCommand.setSelectIncome(1);
//		incomeCommand.setPrice(1000);
//		incomeCommand.setDate(sqlDate);
//		incomeCommand.setNote(
//				"あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createIncomeInfo(incomeCommand);
//		});
//		assertEquals("不正な桁数です", exception.getMessage());
//	}
//
////	@Test
////	void DB停止時の収入() throws IllegalStateException {
////
////		String strDate = "2025-12-31";
////		Date sqlDate = java.sql.Date.valueOf(strDate);
////		IncomeForm incomeCommand = new IncomeForm();
////		incomeCommand.setRadioName("収入");
////		incomeCommand.setSelectIncome(1);
////		incomeCommand.setPrice(1000);
////		incomeCommand.setDate(sqlDate);
////		incomeCommand.setNote("あああ");
////
////		Exception exception = assertThrows(IllegalStateException.class, () -> {
////			householdService.createIncomeInfo(incomeCommand);
////		});
////		assertEquals("システムエラーが発生しました", exception.getMessage());
////	}
//
//	// 支出異常登録
//
////	@Test
//	void すべてがNULLの支出異常登録() throws Exception {
//		ExpenditureForm expenditureCommand = new ExpenditureForm();
//		expenditureCommand.setRadioName("支出");
//		expenditureCommand.setDate(null);
//		expenditureCommand.setSelectExpenditure(null);
//		expenditureCommand.setPrice(null);
//		expenditureCommand.setNote(null);
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createExpenditureInfo(expenditureCommand);
//		});
//		assertEquals("値がNULLです", exception.getMessage());
//	}
//
////	@Test
////	void 日付に数値以外入力時の支出異常登録() throws Exception {
////		String strDate = "eeee-ee-ee";
////		Date sqlDate = java.sql.Date.valueOf(strDate);
////
////		ExpenditureForm expenditureCommand = new ExpenditureForm();
////		expenditureCommand.setRadioName("支出");
////		expenditureCommand.setDate(sqlDate);
////		expenditureCommand.setSelectExpenditure("食費");
////		expenditureCommand.setPrice(1000);
////		expenditureCommand.setNote("いいい");
////
////		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
////			householdService.createExpenditureInfo(expenditureCommand);
////		});
////		assertEquals("数字のみ入力できます", exception.getMessage());
////	}
//
//	@Test
//	void 日付が年のみ入力時の支出異常登録() throws Exception {
//		String strDate = "2025";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		Date date = sdf.parse(strDate);
//
//		ExpenditureForm expenditureCommand = new ExpenditureForm();
//		expenditureCommand.setRadioName("支出");
//		expenditureCommand.setDate(date);
//		expenditureCommand.setSelectExpenditure("食費");
//		expenditureCommand.setPrice(1000);
//		expenditureCommand.setNote("いいい");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createExpenditureInfo(expenditureCommand);
//		});
//		assertEquals("数字のみ入力できます", exception.getMessage());
//	}
//
//	@Test
//	void 支出費目未選択時の支出異常登録() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureForm expenditureCommand = new ExpenditureForm();
//		expenditureCommand.setRadioName("支出");
//		expenditureCommand.setDate(sqlDate);
//		expenditureCommand.setSelectExpenditure(null);
//		expenditureCommand.setPrice(1000);
//		expenditureCommand.setNote("いいい");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createExpenditureInfo(expenditureCommand);
//		});
//		assertEquals("値がNULLです", exception.getMessage());
//	}
//
////	@Test
////	void 金額に数字以外の値入力時の支出異常登録() throws Exception {
////		String strDate = "2025-12-31";
////		Date sqlDate = java.sql.Date.valueOf(strDate);
////
////		// String型をInteger型に変換
//////		Integer amount = "e";
////
////		ExpenditureForm expenditureCommand = new ExpenditureForm();
////		expenditureCommand.setRadioName("支出");
////		expenditureCommand.setDate(sqlDate);
////		expenditureCommand.setSelectExpenditure("食費");
////		expenditureCommand.setPrice("e");
////		expenditureCommand.setNote("いいい");
////
////		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
////			householdService.createExpenditureInfo(expenditureCommand);
////		});
////		assertEquals("数字のみ入力できます", exception.getMessage());
////	}
//
//	@Test
//	void 金額に9桁以上入力時の支出異常登録() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureForm expenditureCommand = new ExpenditureForm();
//		expenditureCommand.setRadioName("支出");
//		expenditureCommand.setDate(sqlDate);
//		expenditureCommand.setSelectExpenditure("食費");
//		expenditureCommand.setPrice(100000000);
//		expenditureCommand.setNote("いいい");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createExpenditureInfo(expenditureCommand);
//		});
//		assertEquals("不正な桁数です", exception.getMessage());
//	}
//
//	@Test
//	void 備考に201文字以上入力時の支出異常登録() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureForm expenditureCommand = new ExpenditureForm();
//		expenditureCommand.setRadioName("支出");
//		expenditureCommand.setDate(sqlDate);
//		expenditureCommand.setSelectExpenditure("食費");
//		expenditureCommand.setPrice(1000);
//		expenditureCommand.setNote(
//				"いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.createExpenditureInfo(expenditureCommand);
//		});
//		assertEquals("不正な桁数です", exception.getMessage());
//	}
//
////	@Test
////	void DB停止時の支出() throws Exception {
////
////		String strDate = "2025-12-31";
////		Date sqlDate = java.sql.Date.valueOf(strDate);
////		ExpenditureForm expenditureCommand = new ExpenditureForm();
////		expenditureCommand.setRadioName("支出");
////		expenditureCommand.setSelectExpenditure("食費");;
////		expenditureCommand.setPrice(1000);
////		expenditureCommand.setDate(sqlDate);
////		expenditureCommand.setNote("あああ");
////
////		Exception exception = assertThrows(Exception.class, () -> {
////			householdService.createExpenditureInfo(expenditureCommand);
////		});
////		assertEquals("システムエラーが発生しました", exception.getMessage());
////	}
//
////編集
//	// 収入正常系テスト
//	@Test
//	void すべて変更時の収入正常編集() throws Exception {
//		String strDate = "2026-01-01";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(sqlDate);
//		incomeEditForm.setIncomeType(2);
//		incomeEditForm.setAmount(99999999);
//		incomeEditForm.setNote(
//				"かかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかか");
//
//		Income income = householdService.incomeEdit(incomeEditForm);
//
//		String incomeNo = String.valueOf(income.getIncomeNo());
//		assertEquals("I251200016", incomeNo);
//		assertEquals(sqlDate, income.getIncomeDate());
//		assertEquals(2, income.getIncomeType());
//		assertEquals(99999999, income.getAmount());
//		assertEquals(
//				"かかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかか",
//				income.getNote());
//		assertEquals(1, income.getVersion());
//	}
//
//	@Test
//	void 日付のみ変更の収入正常編集() throws Exception {
//		String strDate = "2026-01-10";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(sqlDate);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(500);
//		incomeEditForm.setNote("あああ");
//
//		Income income = householdService.incomeEdit(incomeEditForm);
//
//		String incomeNo = String.valueOf(income.getIncomeNo());
//		assertEquals("I251200016", incomeNo);
//		assertEquals(sqlDate, income.getIncomeDate());
//		assertEquals(1, income.getIncomeType());
//		assertEquals(500, income.getAmount());
//		assertEquals("あああ", income.getNote());
//		assertEquals(1, income.getVersion());
//	}
//
//	@Test
//	void 収入種別のみ変更の収入正常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(sqlDate);
//		incomeEditForm.setIncomeType(2);
//		incomeEditForm.setAmount(500);
//		incomeEditForm.setNote("あああ");
//
//		Income income = householdService.incomeEdit(incomeEditForm);
//
//		String incomeNo = String.valueOf(income.getIncomeNo());
//		assertEquals("I251200016", incomeNo);
//		assertEquals(sqlDate, income.getIncomeDate());
//		assertEquals(2, income.getIncomeType());
//		assertEquals(500, income.getAmount());
//		assertEquals("あああ", income.getNote());
//		assertEquals(1, income.getVersion());
//	}
//
//	@Test
//	void 金額のみ変更の収入正常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(sqlDate);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(1);
//		incomeEditForm.setNote("あああ");
//
//		Income income = householdService.incomeEdit(incomeEditForm);
//
//		String incomeNo = String.valueOf(income.getIncomeNo());
//		assertEquals("I251200016", incomeNo);
//		assertEquals(sqlDate, income.getIncomeDate());
//		assertEquals(1, income.getIncomeType());
//		assertEquals(1, income.getAmount());
//		assertEquals("あああ", income.getNote());
//		assertEquals(1, income.getVersion());
//	}
//
//	@Test
//	void 備考のみ変更の収入正常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(sqlDate);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(500);
//		incomeEditForm.setNote(null);
//
//		Income income = householdService.incomeEdit(incomeEditForm);
//
//		String incomeNo = String.valueOf(income.getIncomeNo());
//		assertEquals("I251200016", incomeNo);
//		assertEquals(sqlDate, income.getIncomeDate());
//		assertEquals(1, income.getIncomeType());
//		assertEquals(500, income.getAmount());
//		assertEquals(null, income.getNote());
//		assertEquals(1, income.getVersion());
//	}
//
//	@Test
//	void 未変更時の収入正常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(sqlDate);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(500);
//		incomeEditForm.setNote("あああ");
//
//		Income income = householdService.incomeEdit(incomeEditForm);
//
//		String incomeNo = String.valueOf(income.getIncomeNo());
//		assertEquals("I251200016", incomeNo);
//		assertEquals(sqlDate, income.getIncomeDate());
//		assertEquals(1, income.getIncomeType());
//		assertEquals(500, income.getAmount());
//		assertEquals("あああ", income.getNote());
//		assertEquals(1, income.getVersion());
//	}
//
//	// 支出正常編集
//	@Test
//	void すべて変更時の支出正常編集() throws Exception {
//		String strDate = "2026-01-01";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(sqlDate);
//		expenditureEditForm.setExpenditureExpenseItemName("食費");
//		expenditureEditForm.setAmount(99999999);
//		expenditureEditForm.setNote(
//				"ききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっきききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっきききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっきききききききききききききききききききききききききききききき");
//
//		Expenditure expenditure = householdService.expenditureEdit(expenditureEditForm);
//
//		String expenditureNo = String.valueOf(expenditure.getExpenditure_no());
//		String expenditureItemNo = String.valueOf(expenditure.getExpenditureExpenseItemCode());
//		assertEquals("E251200012", expenditureNo);
//		assertEquals(sqlDate, expenditure.getExpenditureDate());
//		assertEquals("食費", expenditure.getExpenditureExpenseItemName());
//		assertEquals("EI001", expenditureItemNo);
//		assertEquals(99999999, expenditure.getAmount());
//		assertEquals(
//				"ききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっきききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっきききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっきききききききききききききききききききききききききききききき",
//				expenditure.getNote());
//		assertEquals(1, expenditure.getVersion());
//	}
//
//	@Test
//	void 日付のみ変更時の支出正常編集() throws Exception {
//		String strDate = "2026-01-10";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(sqlDate);
//		expenditureEditForm.setExpenditureExpenseItemName("日用品");
//		expenditureEditForm.setAmount(500);
//		expenditureEditForm.setNote("いいい");
//
//		Expenditure expenditure = householdService.expenditureEdit(expenditureEditForm);
//
//		String expenditureNo = String.valueOf(expenditure.getExpenditure_no());
//		String expenditureItemNo = String.valueOf(expenditure.getExpenditureExpenseItemCode());
//		assertEquals("E251200012", expenditureNo);
//		assertEquals(sqlDate, expenditure.getExpenditureDate());
//		assertEquals("日用品", expenditure.getExpenditureExpenseItemName());
//		assertEquals("EI002", expenditureItemNo);
//		assertEquals(500, expenditure.getAmount());
//		assertEquals("いいい", expenditure.getNote());
//		assertEquals(1, expenditure.getVersion());
//	}
//
//	@Test
//	void 支出費目のみ変更時の支出正常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(sqlDate);
//		expenditureEditForm.setExpenditureExpenseItemName("交際費");
//		expenditureEditForm.setAmount(500);
//		expenditureEditForm.setNote("いいい");
//
//		Expenditure expenditure = householdService.expenditureEdit(expenditureEditForm);
//
//		String expenditureNo = String.valueOf(expenditure.getExpenditure_no());
//		String expenditureItemNo = String.valueOf(expenditure.getExpenditureExpenseItemCode());
//		assertEquals("E251200012", expenditureNo);
//		assertEquals(sqlDate, expenditure.getExpenditureDate());
//		assertEquals("交際費", expenditure.getExpenditureExpenseItemName());
//		assertEquals("EI007", expenditureItemNo);
//		assertEquals(500, expenditure.getAmount());
//		assertEquals("いいい", expenditure.getNote());
//		assertEquals(1, expenditure.getVersion());
//	}
//
//	@Test
//	void 金額のみ変更時の支出正常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(sqlDate);
//		expenditureEditForm.setExpenditureExpenseItemName("日用品");
//		expenditureEditForm.setAmount(1);
//		expenditureEditForm.setNote("いいい");
//
//		Expenditure expenditure = householdService.expenditureEdit(expenditureEditForm);
//
//		String expenditureNo = String.valueOf(expenditure.getExpenditure_no());
//		String expenditureItemNo = String.valueOf(expenditure.getExpenditureExpenseItemCode());
//		assertEquals("E251200012", expenditureNo);
//		assertEquals(sqlDate, expenditure.getExpenditureDate());
//		assertEquals("日用品", expenditure.getExpenditureExpenseItemName());
//		assertEquals("EI002", expenditureItemNo);
//		assertEquals(1, expenditure.getAmount());
//		assertEquals("いいい", expenditure.getNote());
//		assertEquals(1, expenditure.getVersion());
//	}
//
//	@Test
//	void 備考のみ変更時の支出正常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(sqlDate);
//		expenditureEditForm.setExpenditureExpenseItemName("日用品");
//		expenditureEditForm.setAmount(500);
//		expenditureEditForm.setNote(null);
//
//		Expenditure expenditure = householdService.expenditureEdit(expenditureEditForm);
//
//		String expenditureNo = String.valueOf(expenditure.getExpenditure_no());
//		String expenditureItemNo = String.valueOf(expenditure.getExpenditureExpenseItemCode());
//		assertEquals("E251200012", expenditureNo);
//		assertEquals(sqlDate, expenditure.getExpenditureDate());
//		assertEquals("日用品", expenditure.getExpenditureExpenseItemName());
//		assertEquals("EI002", expenditureItemNo);
//		assertEquals(500, expenditure.getAmount());
//		assertEquals(null, expenditure.getNote());
//		assertEquals(1, expenditure.getVersion());
//	}
//
//	@Test
//	void 未変更時の支出正常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(sqlDate);
//		expenditureEditForm.setExpenditureExpenseItemName("日用品");
//		expenditureEditForm.setAmount(500);
//		expenditureEditForm.setNote("いいい");
//
//		Expenditure expenditure = householdService.expenditureEdit(expenditureEditForm);
//
//		String expenditureNo = String.valueOf(expenditure.getExpenditure_no());
//		String expenditureItemNo = String.valueOf(expenditure.getExpenditureExpenseItemCode());
//		assertEquals("E251200012", expenditureNo);
//		assertEquals(sqlDate, expenditure.getExpenditureDate());
//		assertEquals("日用品", expenditure.getExpenditureExpenseItemName());
//		assertEquals("EI002", expenditureItemNo);
//		assertEquals(500, expenditure.getAmount());
//		assertEquals("いいい", expenditure.getNote());
//		assertEquals(1, expenditure.getVersion());
//	}
//
//	// 収入異常編集
//	@Test
//	void すべて未入力時の収入異常編集() throws Exception {
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(null);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(null);
//		incomeEditForm.setNote(null);
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.incomeEdit(incomeEditForm);
//		});
//		assertEquals("値がNULLです", exception.getMessage());
//	}
//
//	@Test
//	void 日付未入力時の収入異常編集() throws Exception {
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(null);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(500);
//		incomeEditForm.setNote("あああ");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.incomeEdit(incomeEditForm);
//		});
//		assertEquals("値がNULLです", exception.getMessage());
//	}
//
//	@Test
//	void 日付に年のみ入力時の収入異常編集() throws Exception {
//		String strDate = "2026";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		Date date = sdf.parse(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(date);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(500);
//		incomeEditForm.setNote("あああ");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.incomeEdit(incomeEditForm);
//		});
//		assertEquals("数字のみ入力できます", exception.getMessage());
//	}
//
//	@Test
//	void 金額に9桁入力時の収入異常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(sqlDate);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(999999999);
//		incomeEditForm.setNote("あああ");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.incomeEdit(incomeEditForm);
//		});
//		assertEquals("不正な桁数です", exception.getMessage());
//	}
//
//	@Test
//	void 備考に201文字入力時の収入異常編集() throws Exception {
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		IncomeEditForm incomeEditForm = new IncomeEditForm();
//		incomeEditForm.setBalanceType("収入");
//		incomeEditForm.setBalanceNo("I251200016");
//		incomeEditForm.setBalanceDate(sqlDate);
//		incomeEditForm.setIncomeType(1);
//		incomeEditForm.setAmount(500);
//		incomeEditForm.setNote(
//				"かかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかかか");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.incomeEdit(incomeEditForm);
//		});
//		assertEquals("不正な桁数です", exception.getMessage());
//	}
//
//	// 支出異常編集
//	@Test
//	void すべて未入力時の支出異常編集() throws Exception {
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(null);
//		expenditureEditForm.setExpenditureExpenseItemName("日用品");
//		expenditureEditForm.setAmount(null);
//		expenditureEditForm.setNote(null);
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.expenditureEdit(expenditureEditForm);
//		});
//		assertEquals("値がNULLです", exception.getMessage());
//	}
//
//	@Test
//	void 日付に年のみ入力時の支出異常編集() throws Exception {
//
//		String strDate = "2026";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		Date date = sdf.parse(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(date);
//		expenditureEditForm.setExpenditureExpenseItemName("日用品");
//		expenditureEditForm.setAmount(500);
//		expenditureEditForm.setNote("いいい");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.expenditureEdit(expenditureEditForm);
//		});
//		assertEquals("数字のみ入力できます", exception.getMessage());
//	}
//
//	@Test
//	void 金額に9桁入力時の支出異常編集() throws Exception {
//
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(sqlDate);
//		expenditureEditForm.setExpenditureExpenseItemName("日用品");
//		expenditureEditForm.setAmount(999999999);
//		expenditureEditForm.setNote("いいい");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.expenditureEdit(expenditureEditForm);
//		});
//		assertEquals("不正な桁数です", exception.getMessage());
//	}
//
//	@Test
//	void 備考に201文字入力時の支出異常編集() throws Exception {
//
//		String strDate = "2025-12-31";
//		Date sqlDate = java.sql.Date.valueOf(strDate);
//
//		ExpenditureEditForm expenditureEditForm = new ExpenditureEditForm();
//		expenditureEditForm.setBalanceType("支出");
//		expenditureEditForm.setBalanceNo("E251200012");
//		expenditureEditForm.setBalanceDate(sqlDate);
//		expenditureEditForm.setExpenditureExpenseItemName("日用品");
//		expenditureEditForm.setAmount(500);
//		expenditureEditForm.setNote(
//				"ききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっきききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっきききききききききききききききききききききききききききききききききききききききききききっきききききっききききききっききききききききききききききききききききききききききききききき");
//
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			householdService.expenditureEdit(expenditureEditForm);
//		});
//		assertEquals("不正な桁数です", exception.getMessage());
//	}
//
//// 削除
//	// 収入正常系
//	@Test
//	void 収入正常削除() throws Exception {
//		Income income = householdService.incomeDelete("I251200016");
//		assertTrue(income.isDeleteFlag());
//	}
//
//	// 支出正常削除
//	@Test
//	void 支出正常削除() throws Exception {
//		Expenditure expenditure = householdService.expenditureDelete("E251200012");
//		assertTrue(expenditure.isDeleteFrag());
//	}

//詳細検索
	// 収入正常系

	@Test
	void すべて入力時の収入正常検索() throws Exception {
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);
		String fromStrDate = "2025-12-31";
		Date fromSqlDate = java.sql.Date.valueOf(fromStrDate);
		String toStrDate = "2025-12-31";
		Date toSqlDate = java.sql.Date.valueOf(toStrDate);

		Date fromDate = fromSqlDate;
		Date toDate = toSqlDate;
		Integer selectIncome = 1;
		Integer fromAmount = 1;
		Integer toAmount = 99999999;
		String note = "ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ";

		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(fromDate, toDate,
				selectIncome, fromAmount, toAmount, note);

		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();
		SearchResultIncome income = new SearchResultIncome();
		income.setAmount(99999999);
		income.setIncomeDate(sqlDate);
		IncomeNoVO No = IncomeNoVO.of("I251200002");
		income.setIncomeNo(No);
		income.setIncomeType(1);
		income.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());
		}
	}

	@Test
	void from年のみ入力時の収入正常検索() throws Exception {
		String strDate0 = "2025-12-31";
		Date sqlDate0 = java.sql.Date.valueOf(strDate0);
		String fromStrDate = "2025-12-31";
		Date fromSqlDate = java.sql.Date.valueOf(fromStrDate);
		Date fromDate = fromSqlDate;

		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(fromDate, null, null, null,
				null, null);
		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();
		SearchResultIncome income0 = new SearchResultIncome();
		income0.setAmount(99999999);
		income0.setIncomeDate(sqlDate0);
		IncomeNoVO No0 = IncomeNoVO.of("I251200002");
		income0.setIncomeNo(No0);
		income0.setIncomeType(1);
		income0.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income0);

		String strDate1 = "2026-01-01";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		SearchResultIncome income1 = new SearchResultIncome();
		income1.setAmount(1500);
		income1.setIncomeDate(sqlDate1);
		IncomeNoVO No1 = IncomeNoVO.of("I251200003");
		income1.setIncomeNo(No1);
		income1.setIncomeType(2);
		income1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income1);

		String strDate2 = "2026-02-01";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		SearchResultIncome income2 = new SearchResultIncome();
		income2.setAmount(1);
		income2.setIncomeDate(sqlDate2);
		IncomeNoVO No2 = IncomeNoVO.of("I251200004");
		income2.setIncomeNo(No2);
		income2.setIncomeType(1);
		income2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income2);

		SearchResultIncome income3 = new SearchResultIncome();
		income3.setAmount(2000);
		income3.setIncomeDate(sqlDate0);
		IncomeNoVO No3 = IncomeNoVO.of("I251200005");
		income3.setIncomeNo(No3);
		income3.setIncomeType(2);
		income3.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income3);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());
		}
	}

	@Test
	void 日付toのみ入力時の収入通常検索() throws Exception {
		String toStrDate = "2025-12-31";
		Date toSqlDate = java.sql.Date.valueOf(toStrDate);
		Date toDate = toSqlDate;

		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(null, toDate, null, null,
				null, null);
		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();

		SearchResultIncome income2 = new SearchResultIncome();
		income2.setAmount(99999999);
		income2.setIncomeDate(toDate);
		IncomeNoVO No2 = IncomeNoVO.of("I251200002");
		income2.setIncomeNo(No2);
		income2.setIncomeType(1);
		income2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income2);

		SearchResultIncome income5 = new SearchResultIncome();
		income5.setAmount(2000);
		income5.setIncomeDate(toDate);
		IncomeNoVO No5 = IncomeNoVO.of("I251200005");
		income5.setIncomeNo(No5);
		income5.setIncomeType(2);
		income5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income5);

		SearchResultIncome income1 = new SearchResultIncome();
		income1.setAmount(1000);
		String strDate1 = "2025-11-30";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		income1.setIncomeDate(sqlDate1);
		IncomeNoVO No1 = IncomeNoVO.of("I251200001");
		income1.setIncomeNo(No1);
		income1.setIncomeType(1);
		income1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income1);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 収入種別のみ入力時の正常検索() throws Exception {
		Integer selectIncome = 1;
		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(null, null, selectIncome,
				null, null, null);

		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		SearchResultIncome income2 = new SearchResultIncome();
		income2.setAmount(99999999);
		income2.setIncomeDate(sqlDate2);
		IncomeNoVO No2 = IncomeNoVO.of("I251200002");
		income2.setIncomeNo(No2);
		income2.setIncomeType(1);
		income2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income2);

		String strDate4 = "2026-02-01";
		Date sqlDate4 = java.sql.Date.valueOf(strDate4);
		SearchResultIncome income4 = new SearchResultIncome();
		income4.setAmount(1);
		income4.setIncomeDate(sqlDate4);
		IncomeNoVO No4 = IncomeNoVO.of("I251200004");
		income4.setIncomeNo(No4);
		income4.setIncomeType(1);
		income4.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income4);

		SearchResultIncome income1 = new SearchResultIncome();
		income1.setAmount(1000);
		String strDate1 = "2025-11-30";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		income1.setIncomeDate(sqlDate1);
		IncomeNoVO No1 = IncomeNoVO.of("I251200001");
		income1.setIncomeNo(No1);
		income1.setIncomeType(1);
		income1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income1);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 金額fromのみ入力時の正常検索() throws Exception {
		Integer fromAmount = 99999999;
		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(null, null, null,
				fromAmount, null, null);

		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		SearchResultIncome income2 = new SearchResultIncome();
		income2.setAmount(99999999);
		income2.setIncomeDate(sqlDate2);
		IncomeNoVO No2 = IncomeNoVO.of("I251200002");
		income2.setIncomeNo(No2);
		income2.setIncomeType(1);
		income2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income2);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());

			int num = 0;
			num = i + 1;
			System.out.println(num);
		}
	}

	@Test
	void 金額toのみ入力時の収入正常検索() throws Exception {
		Integer toAmount = 1;
		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(null, null, null, null,
				toAmount, null);

		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();
		String strDate4 = "2026-02-01";
		Date sqlDate4 = java.sql.Date.valueOf(strDate4);
		SearchResultIncome income4 = new SearchResultIncome();
		income4.setAmount(1);
		income4.setIncomeDate(sqlDate4);
		IncomeNoVO No4 = IncomeNoVO.of("I251200004");
		income4.setIncomeNo(No4);
		income4.setIncomeType(1);
		income4.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income4);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 備考のみ入力時の収入正常検索() throws Exception {
		String note = "あ";

		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(null, null, null, null,
				null, note);
		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();

		SearchResultIncome income2 = new SearchResultIncome();
		income2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		income2.setIncomeDate(sqlDate2);
		IncomeNoVO No2 = IncomeNoVO.of("I251200002");
		income2.setIncomeNo(No2);
		income2.setIncomeType(1);
		income2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income2);

		String strDate3 = "2026-01-01";
		Date sqlDate3 = java.sql.Date.valueOf(strDate3);
		SearchResultIncome income3 = new SearchResultIncome();
		income3.setAmount(1500);
		income3.setIncomeDate(sqlDate3);
		IncomeNoVO No3 = IncomeNoVO.of("I251200003");
		income3.setIncomeNo(No3);
		income3.setIncomeType(2);
		income3.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income3);

		String strDate4 = "2026-02-01";
		Date sqlDate4 = java.sql.Date.valueOf(strDate4);
		SearchResultIncome income4 = new SearchResultIncome();
		income4.setAmount(1);
		income4.setIncomeDate(sqlDate4);
		IncomeNoVO No4 = IncomeNoVO.of("I251200004");
		income4.setIncomeNo(No4);
		income4.setIncomeType(1);
		income4.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income4);

		SearchResultIncome income5 = new SearchResultIncome();
		income5.setAmount(2000);
		String strDate5 = "2025-12-31";
		Date sqlDate5 = java.sql.Date.valueOf(strDate5);
		income5.setIncomeDate(sqlDate5);
		IncomeNoVO No5 = IncomeNoVO.of("I251200005");
		income5.setIncomeNo(No5);
		income5.setIncomeType(2);
		income5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income5);

		SearchResultIncome income1 = new SearchResultIncome();
		income1.setAmount(1000);
		String strDate1 = "2025-11-30";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		income1.setIncomeDate(sqlDate1);
		IncomeNoVO No1 = IncomeNoVO.of("I251200001");
		income1.setIncomeNo(No1);
		income1.setIncomeType(1);
		income1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income1);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 日付fromto入力時の収入通常検索() throws Exception {
		String fromStrDate = "2025-12-31";
		Date fromSqlDate = java.sql.Date.valueOf(fromStrDate);
		String toStrDate = "2026-01-01";
		Date toSqlDate = java.sql.Date.valueOf(toStrDate);

		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(fromSqlDate, toSqlDate,
				null, null, null, null);
		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();

		SearchResultIncome income2 = new SearchResultIncome();
		income2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		income2.setIncomeDate(sqlDate2);
		IncomeNoVO No2 = IncomeNoVO.of("I251200002");
		income2.setIncomeNo(No2);
		income2.setIncomeType(1);
		income2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income2);

		String strDate3 = "2026-01-01";
		Date sqlDate3 = java.sql.Date.valueOf(strDate3);
		SearchResultIncome income3 = new SearchResultIncome();
		income3.setAmount(1500);
		income3.setIncomeDate(sqlDate3);
		IncomeNoVO No3 = IncomeNoVO.of("I251200003");
		income3.setIncomeNo(No3);
		income3.setIncomeType(2);
		income3.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income3);

		SearchResultIncome income5 = new SearchResultIncome();
		income5.setAmount(2000);
		String strDate5 = "2025-12-31";
		Date sqlDate5 = java.sql.Date.valueOf(strDate5);
		income5.setIncomeDate(sqlDate5);
		IncomeNoVO No5 = IncomeNoVO.of("I251200005");
		income5.setIncomeNo(No5);
		income5.setIncomeType(2);
		income5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income5);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 金額fromto入力時の収入通常検索() throws Exception {
		Integer toAmount = 2000;
		Integer fromAmount = 1000;
		List<SearchResultIncome> getIncomeList = householdService.getDetailSearchIncomeList(null, null, null,
				fromAmount, toAmount, null);
		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();

		String strDate3 = "2026-01-01";
		Date sqlDate3 = java.sql.Date.valueOf(strDate3);
		SearchResultIncome income3 = new SearchResultIncome();
		income3.setAmount(1500);
		income3.setIncomeDate(sqlDate3);
		IncomeNoVO No3 = IncomeNoVO.of("I251200003");
		income3.setIncomeNo(No3);
		income3.setIncomeType(2);
		income3.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income3);

		SearchResultIncome income5 = new SearchResultIncome();
		income5.setAmount(2000);
		String strDate5 = "2025-12-31";
		Date sqlDate5 = java.sql.Date.valueOf(strDate5);
		income5.setIncomeDate(sqlDate5);
		IncomeNoVO No5 = IncomeNoVO.of("I251200005");
		income5.setIncomeNo(No5);
		income5.setIncomeType(2);
		income5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income5);

		SearchResultIncome income1 = new SearchResultIncome();
		income1.setAmount(1000);
		String strDate1 = "2025-11-30";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		income1.setIncomeDate(sqlDate1);
		IncomeNoVO No1 = IncomeNoVO.of("I251200001");
		income1.setIncomeNo(No1);
		income1.setIncomeType(1);
		income1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income1);

		for (int i = 0; i < getIncomeList.size(); i++) {
			assertEquals(incomeList.get(i).getIncomeDate(), getIncomeList.get(i).getIncomeDate());
			assertEquals(incomeList.get(i).getAmount(), getIncomeList.get(i).getAmount());
			String getNo = String.valueOf(getIncomeList.get(i).getIncomeNo());
			String no = String.valueOf(incomeList.get(i).getIncomeNo());
			assertEquals(no, getNo);
			assertEquals(incomeList.get(i).getIncomeType(), getIncomeList.get(i).getIncomeType());
			assertEquals(incomeList.get(i).getNote(), getIncomeList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

//支出
	@Test
	void すべて入力時の支出正常検索() throws Exception {
		String strDateFromto = "2025-12-31";
		Date sqlDateFromTo = java.sql.Date.valueOf(strDateFromto);
		String selectExpenditure = "食費";
		Integer fromAmount = 1;
		Integer toAmount = 99999999;
		String note = "ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ";

		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(
				sqlDateFromTo, sqlDateFromTo, selectExpenditure, fromAmount, toAmount, note);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();

		SearchResultExpenditure expenditure2 = new SearchResultExpenditure();
		expenditure2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		expenditure2.setExpenditureDate(sqlDate2);
		expenditure2.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no2 = ExpenditureNoVO.of("E25120002");
		expenditure2.setExpenditureNo(no2);
		expenditure2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure2);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 日付fromのみ入力時の支出正常検索() throws Exception {
		String strDateFrom = "2025-12-31";
		Date sqlDateFrom = java.sql.Date.valueOf(strDateFrom);

		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(sqlDateFrom,
				null, null, null, null, null);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();

		SearchResultExpenditure expenditure4 = new SearchResultExpenditure();
		expenditure4.setAmount(1);
		String strDate4 = "2026-02-01";
		Date sqlDate4 = java.sql.Date.valueOf(strDate4);
		expenditure4.setExpenditureDate(sqlDate4);
		expenditure4.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no4 = ExpenditureNoVO.of("E25120004");
		expenditure4.setExpenditureNo(no4);
		expenditure4.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure4);

		SearchResultExpenditure expenditure5 = new SearchResultExpenditure();
		expenditure5.setAmount(2000);
		String strDate5 = "2025-12-31";
		Date sqlDate5 = java.sql.Date.valueOf(strDate5);
		expenditure5.setExpenditureDate(sqlDate5);
		expenditure5.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no5 = ExpenditureNoVO.of("E25120005");
		expenditure5.setExpenditureNo(no5);
		expenditure5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure5);

		SearchResultExpenditure expenditure2 = new SearchResultExpenditure();
		expenditure2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		expenditure2.setExpenditureDate(sqlDate2);
		expenditure2.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no2 = ExpenditureNoVO.of("E25120002");
		expenditure2.setExpenditureNo(no2);
		expenditure2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure2);

		SearchResultExpenditure expenditure3 = new SearchResultExpenditure();
		expenditure3.setAmount(1500);
		String strDate3 = "2026-01-01";
		Date sqlDate3 = java.sql.Date.valueOf(strDate3);
		expenditure3.setExpenditureDate(sqlDate3);
		expenditure3.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no3 = ExpenditureNoVO.of("E25120003");
		expenditure3.setExpenditureNo(no3);
		expenditure3.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure3);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 日付toのみ入力時の支出正常検索() throws Exception {
		String strDateto = "2025-12-31";
		Date sqlDateto = java.sql.Date.valueOf(strDateto);

		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(null,
				sqlDateto, null, null, null, null);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();

		SearchResultExpenditure expenditure5 = new SearchResultExpenditure();
		expenditure5.setAmount(2000);
		String strDate5 = "2025-12-31";
		Date sqlDate5 = java.sql.Date.valueOf(strDate5);
		expenditure5.setExpenditureDate(sqlDate5);
		expenditure5.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no5 = ExpenditureNoVO.of("E25120005");
		expenditure5.setExpenditureNo(no5);
		expenditure5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure5);

		SearchResultExpenditure expenditure1 = new SearchResultExpenditure();
		expenditure1.setAmount(1000);
		String strDate1 = "2025-11-30";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		expenditure1.setExpenditureDate(sqlDate1);
		expenditure1.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no1 = ExpenditureNoVO.of("E25120001");
		expenditure1.setExpenditureNo(no1);
		expenditure1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure1);

		SearchResultExpenditure expenditure2 = new SearchResultExpenditure();
		expenditure2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		expenditure2.setExpenditureDate(sqlDate2);
		expenditure2.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no2 = ExpenditureNoVO.of("E25120002");
		expenditure2.setExpenditureNo(no2);
		expenditure2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure2);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 支出費目のみ入力時の支出正常検索() throws Exception {

		String selectExpenditure = "食費";
		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(null, null,
				selectExpenditure, null, null, null);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();

		SearchResultExpenditure expenditure4 = new SearchResultExpenditure();
		expenditure4.setAmount(1);
		String strDate4 = "2026-02-01";
		Date sqlDate4 = java.sql.Date.valueOf(strDate4);
		expenditure4.setExpenditureDate(sqlDate4);
		expenditure4.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no4 = ExpenditureNoVO.of("E25120004");
		expenditure4.setExpenditureNo(no4);
		expenditure4.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure4);

		SearchResultExpenditure expenditure1 = new SearchResultExpenditure();
		expenditure1.setAmount(1000);
		String strDate1 = "2025-11-30";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		expenditure1.setExpenditureDate(sqlDate1);
		expenditure1.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no1 = ExpenditureNoVO.of("E25120001");
		expenditure1.setExpenditureNo(no1);
		expenditure1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure1);

		SearchResultExpenditure expenditure2 = new SearchResultExpenditure();
		expenditure2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		expenditure2.setExpenditureDate(sqlDate2);
		expenditure2.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no2 = ExpenditureNoVO.of("E25120002");
		expenditure2.setExpenditureNo(no2);
		expenditure2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure2);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 金額fromのみ入力時の支出正常検索() throws Exception {
		Integer fromAmount = 99999999;
		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(null, null,
				null, fromAmount, null, null);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();

		SearchResultExpenditure expenditure2 = new SearchResultExpenditure();
		expenditure2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		expenditure2.setExpenditureDate(sqlDate2);
		expenditure2.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no2 = ExpenditureNoVO.of("E25120002");
		expenditure2.setExpenditureNo(no2);
		expenditure2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure2);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

//			int num = 0;
//			num = i + 1;
//			System.out.println(num);
		}
	}

	@Test
	void 金額toのみ入力時の支出正常検索() throws Exception {
		Integer toAmount = 1;
		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(null, null,
				null, null, toAmount, null);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();
		SearchResultExpenditure expenditure4 = new SearchResultExpenditure();
		expenditure4.setAmount(1);
		String strDate4 = "2026-02-01";
		Date sqlDate4 = java.sql.Date.valueOf(strDate4);
		expenditure4.setExpenditureDate(sqlDate4);
		expenditure4.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no4 = ExpenditureNoVO.of("E25120004");
		expenditure4.setExpenditureNo(no4);
		expenditure4.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure4);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

			int num = 0;
			num = i + 1;
			System.out.println(num);
		}
	}

	@Test
	void 備考のみ入力時の支出正常検索() throws Exception {
		String note = "あ";
		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(null, null,
				null, null, null, note);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();

		SearchResultExpenditure expenditure4 = new SearchResultExpenditure();
		expenditure4.setAmount(1);
		String strDate4 = "2026-02-01";
		Date sqlDate4 = java.sql.Date.valueOf(strDate4);
		expenditure4.setExpenditureDate(sqlDate4);
		expenditure4.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no4 = ExpenditureNoVO.of("E25120004");
		expenditure4.setExpenditureNo(no4);
		expenditure4.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure4);

		SearchResultExpenditure expenditure5 = new SearchResultExpenditure();
		expenditure5.setAmount(2000);
		String strDate5 = "2025-12-31";
		Date sqlDate5 = java.sql.Date.valueOf(strDate5);
		expenditure5.setExpenditureDate(sqlDate5);
		expenditure5.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no5 = ExpenditureNoVO.of("E25120005");
		expenditure5.setExpenditureNo(no5);
		expenditure5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure5);

		SearchResultExpenditure expenditure1 = new SearchResultExpenditure();
		expenditure1.setAmount(1000);
		String strDate1 = "2025-11-30";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		expenditure1.setExpenditureDate(sqlDate1);
		expenditure1.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no1 = ExpenditureNoVO.of("E25120001");
		expenditure1.setExpenditureNo(no1);
		expenditure1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure1);

		SearchResultExpenditure expenditure2 = new SearchResultExpenditure();
		expenditure2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		expenditure2.setExpenditureDate(sqlDate2);
		expenditure2.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no2 = ExpenditureNoVO.of("E25120002");
		expenditure2.setExpenditureNo(no2);
		expenditure2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure2);

		SearchResultExpenditure expenditure3 = new SearchResultExpenditure();
		expenditure3.setAmount(1500);
		String strDate3 = "2026-01-01";
		Date sqlDate3 = java.sql.Date.valueOf(strDate3);
		expenditure3.setExpenditureDate(sqlDate3);
		expenditure3.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no3 = ExpenditureNoVO.of("E25120003");
		expenditure3.setExpenditureNo(no3);
		expenditure3.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure3);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

			int num = 0;
			num = i + 1;
			System.out.println(num);
		}
	}

	@Test
	void 日付fromto入力時の支出正常検索() throws Exception {
		String strDateFrom = "2025-12-31";
		Date sqlDateFrom = java.sql.Date.valueOf(strDateFrom);
		String strDateto = "2026-01-01";
		Date sqlDateto = java.sql.Date.valueOf(strDateto);

		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(sqlDateFrom,
				sqlDateto, null, null, null, null);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();

		SearchResultExpenditure expenditure5 = new SearchResultExpenditure();
		expenditure5.setAmount(2000);
		String strDate5 = "2025-12-31";
		Date sqlDate5 = java.sql.Date.valueOf(strDate5);
		expenditure5.setExpenditureDate(sqlDate5);
		expenditure5.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no5 = ExpenditureNoVO.of("E25120005");
		expenditure5.setExpenditureNo(no5);
		expenditure5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure5);

		SearchResultExpenditure expenditure2 = new SearchResultExpenditure();
		expenditure2.setAmount(99999999);
		String strDate2 = "2025-12-31";
		Date sqlDate2 = java.sql.Date.valueOf(strDate2);
		expenditure2.setExpenditureDate(sqlDate2);
		expenditure2.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no2 = ExpenditureNoVO.of("E25120002");
		expenditure2.setExpenditureNo(no2);
		expenditure2.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure2);

		SearchResultExpenditure expenditure3 = new SearchResultExpenditure();
		expenditure3.setAmount(1500);
		String strDate3 = "2026-01-01";
		Date sqlDate3 = java.sql.Date.valueOf(strDate3);
		expenditure3.setExpenditureDate(sqlDate3);
		expenditure3.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no3 = ExpenditureNoVO.of("E25120003");
		expenditure3.setExpenditureNo(no3);
		expenditure3.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure3);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

			int num = 0;
			num = i + 1;
			System.out.println(num);
		}
	}

	@Test
	void 金額fromto入力時の支出正常検索() throws Exception {
		Integer formAmount = 1000;
		Integer toAmount = 2000;
		List<SearchResultExpenditure> getExpenditureList = householdService.getDetailSearchExpenditureList(null, null,
				null, formAmount, toAmount, null);
		List<SearchResultExpenditure> expenditureList = new ArrayList<SearchResultExpenditure>();

		SearchResultExpenditure expenditure5 = new SearchResultExpenditure();
		expenditure5.setAmount(2000);
		String strDate5 = "2025-12-31";
		Date sqlDate5 = java.sql.Date.valueOf(strDate5);
		expenditure5.setExpenditureDate(sqlDate5);
		expenditure5.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no5 = ExpenditureNoVO.of("E25120005");
		expenditure5.setExpenditureNo(no5);
		expenditure5.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure5);

		SearchResultExpenditure expenditure1 = new SearchResultExpenditure();
		expenditure1.setAmount(1000);
		String strDate1 = "2025-11-30";
		Date sqlDate1 = java.sql.Date.valueOf(strDate1);
		expenditure1.setExpenditureDate(sqlDate1);
		expenditure1.setExpenditureExpenseItemName("食費");
		ExpenditureNoVO no1 = ExpenditureNoVO.of("E25120001");
		expenditure1.setExpenditureNo(no1);
		expenditure1.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure1);

		SearchResultExpenditure expenditure3 = new SearchResultExpenditure();
		expenditure3.setAmount(1500);
		String strDate3 = "2026-01-01";
		Date sqlDate3 = java.sql.Date.valueOf(strDate3);
		expenditure3.setExpenditureDate(sqlDate3);
		expenditure3.setExpenditureExpenseItemName("日用品");
		ExpenditureNoVO no3 = ExpenditureNoVO.of("E25120003");
		expenditure3.setExpenditureNo(no3);
		expenditure3.setNote(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		expenditureList.add(expenditure3);

		for (int i = 0; i < getExpenditureList.size(); i++) {
			assertEquals(expenditureList.get(i).getExpenditureDate(), getExpenditureList.get(i).getExpenditureDate());
			assertEquals(expenditureList.get(i).getAmount(), getExpenditureList.get(i).getAmount());
			String getNo = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			String no = String.valueOf(getExpenditureList.get(i).getExpenditureNo());
			assertEquals(no, getNo);
			assertEquals(expenditureList.get(i).getExpenditureExpenseItemName(),
					getExpenditureList.get(i).getExpenditureExpenseItemName());
			assertEquals(expenditureList.get(i).getNote(), getExpenditureList.get(i).getNote());

			int num = 0;
			num = i + 1;
			System.out.println(num);
		}
	}

//詳細検索　異常系
	// 収入
	@Test
	void すべて未入力時の収入異常検索() throws Exception {
		Date fromDate = null;
		Date toDate = null;
		Integer selecrIncome = null;
		Integer fromAmount = null;
		Integer toAmount = null;
		String note = null;
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchIncomeList(fromDate, toDate,
					selecrIncome, fromAmount, toAmount, note);
		});
		assertEquals("値がNULLです", exception.getMessage());
	}
	
	@Test
	void 日付fromがYYYYのみ入力時の収入異常検索() throws Exception{
		String strDate = "2025";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date fromDate = sdf.parse(strDate);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchIncomeList(fromDate, null,
					null, null, null, null);
		});
		assertEquals("数字のみ入力できます", exception.getMessage());
	}
	
	@Test
	void 日付toがYYYYのみの入力時の収入異常検索() throws Exception{
		String strDate = "2025";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date toDate = sdf.parse(strDate);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchIncomeList(null, toDate,
					null, null, null, null);
		});
		assertEquals("数字のみ入力できます", exception.getMessage());
	}
	
	@Test
	void 日付fromtoの入力時の収入異常検索() throws Exception{
		String strDateFrom = "2026-01-01";
		Date sqlDateFrom = java.sql.Date.valueOf(strDateFrom);
		String strDateTo = "2025-12-31";
		Date sqlDateTo = java.sql.Date.valueOf(strDateTo);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchIncomeList(sqlDateFrom, sqlDateTo,
					null, null, null, null);
		});
		assertEquals("from<toの形になっていません", exception.getMessage());
	}
	
	@Test
	void 金額fromの入力が9桁の収入異常検索() throws Exception{
		Integer fromAmount = 999999999;
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchIncomeList(null, null,
					null, fromAmount, null, null);
		});
		assertEquals("不正な桁数です", exception.getMessage());
	}
	
	@Test
	void 金額toの入力が9桁の収入異常検索() throws Exception{
		Integer toAmount = 999999999;
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchIncomeList(null, null,
					null, null, toAmount, null);
		});
		assertEquals("不正な桁数です", exception.getMessage());
	}
	
	@Test
	void 金額fromtoの入力時の収入異常検索() throws Exception{
		Integer fromAmount = 2000;
		Integer toAmount = 1000;
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchIncomeList(null, null,
					null, fromAmount, toAmount, null);
		});
		assertEquals("from<toの形になっていません", exception.getMessage());
	}
	
	@Test
	void noteが201文字入力時の収入異常入力() throws Exception{
		String note = "あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ";
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchIncomeList(null, null,
					null, null, null, note);
		});
		assertEquals("不正な桁数です", exception.getMessage());
	}
	
//支出
	@Test
	void すべて未入力時の支出異常検索() throws Exception {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchExpenditureList(null, null,
					null, null, null, null);
		});
		assertEquals("値がNULLです", exception.getMessage());
	}
	
	@Test
	void 日付fromがYYYYのみ入力時の支出異常検索() throws Exception{
		String strDate = "2025";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date fromDate = sdf.parse(strDate);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchExpenditureList(fromDate, null,
					null, null, null, null);
		});
		assertEquals("数字のみ入力できます", exception.getMessage());
	}
	
	@Test
	void 日付toがYYYYのみの入力時の支出異常検索() throws Exception{
		String strDate = "2025";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date toDate = sdf.parse(strDate);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchExpenditureList(null, toDate,
					null, null, null, null);
		});
		assertEquals("数字のみ入力できます", exception.getMessage());
	}
	
	@Test
	void 日付fromtoの入力時の支出異常検索() throws Exception{
		String strDateFrom = "2026-01-01";
		Date sqlDateFrom = java.sql.Date.valueOf(strDateFrom);
		String strDateTo = "2025-12-31";
		Date sqlDateTo = java.sql.Date.valueOf(strDateTo);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchExpenditureList(sqlDateFrom, sqlDateTo,
					null, null, null, null);
		});
		assertEquals("from<toの形になっていません", exception.getMessage());
	}
	
	@Test
	void 金額fromの入力が9桁の支出異常検索() throws Exception{
		Integer fromAmount = 999999999;
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchExpenditureList(null, null,
					null, fromAmount, null, null);
		});
		assertEquals("不正な桁数です", exception.getMessage());
	}
	
	@Test
	void 金額toの入力が9桁の支出異常検索() throws Exception{
		Integer toAmount = 999999999;
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchExpenditureList(null, null,
					null, null, toAmount, null);
		});
		assertEquals("不正な桁数です", exception.getMessage());
	}
	
	@Test
	void 金額fromtoの入力時の支出異常検索() throws Exception{
		Integer fromAmount = 2000;
		Integer toAmount = 1000;
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchExpenditureList(null, null,
					null, fromAmount, toAmount, null);
		});
		assertEquals("from<toの形になっていません", exception.getMessage());
	}
	
	@Test
	void noteが201文字入力時の支出異常入力() throws Exception{
		String note = "あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ";
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			householdService.getDetailSearchExpenditureList(null, null,
					null, null, null, note);
		});
		assertEquals("不正な桁数です", exception.getMessage());
	}
}
