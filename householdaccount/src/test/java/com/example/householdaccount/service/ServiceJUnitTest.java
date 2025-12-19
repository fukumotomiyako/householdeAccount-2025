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
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.Income.IncomeNoVO;
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

		List<SearchResultIncome> getincomeList = householdService.getDetailSearchIncomeList(fromDate, toDate, selectIncome,
				fromAmount, toAmount, note);
		
		List<SearchResultIncome> incomeList = new ArrayList<SearchResultIncome>();
		SearchResultIncome income = new SearchResultIncome();
		income.setAmount(99999999);
		income.setIncomeDate(sqlDate);
		IncomeNoVO No = IncomeNoVO.of("I251200002");
		income.setIncomeNo(No);
		income.setIncomeType(1);
		income.setNote("ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		incomeList.add(income);
		
		for (int i = 0; i<incomeList.size(); i++) {
		assertEquals(incomeList.get(i).getIncomeDate(),getincomeList.get(i).getIncomeDate());
		assertEquals(incomeList.get(i).getAmount(),getincomeList.get(i).getAmount());
		String getNo = String.valueOf(getincomeList.get(i).getIncomeNo());
		String no = String.valueOf(incomeList.get(i).getIncomeNo());
		assertEquals(no,getNo);
		assertEquals(incomeList.get(i).getIncomeType(),getincomeList.get(i).getIncomeType());
		assertEquals(incomeList.get(i).getNote(),getincomeList.get(i).getNote());
		}
	}
	
	@Test
	void from年のみ入力時の収入正常検索() throws Exception{
		String strDate = "2025-12-31";
		Date sqlDate = java.sql.Date.valueOf(strDate);
		String fromStrDate = "2025-12-31";
		Date fromSqlDate = java.sql.Date.valueOf(fromStrDate);
	}
}
