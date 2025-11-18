package com.example.householdaccount.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdaccount.entity.ExpenditureItems;
import com.example.householdaccount.entity.SearchBalanceExpenditureInfo;
import com.example.householdaccount.entity.SearchBalanceIncomeInfo;
import com.example.householdaccount.entity.SearchResultExpenditure;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.form.IncomeEditForm;
import com.example.householdaccount.form.ExpenditureEditForm;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeForm;
import com.example.householdaccount.form.SearchBalanceInfo;
import com.example.householdaccount.form.SearchResultBalanceForm;
import com.example.householdaccount.service.HouseholdService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholedController {

	@Autowired
	HouseholdService householdService;

//	//支出費目取得
	@RequestMapping(value = "/expenditureItems", method = RequestMethod.GET)
	public List<ExpenditureItems> getExpenditureItems() {
		List<ExpenditureItems> expenditureItemList = householdService.getExpenditureItems();
		return expenditureItemList;
	}

	// 登録(収入)
	@RequestMapping(value = "/income", method = RequestMethod.POST)
	public String incomeCreate(@RequestBody @Validated IncomeForm incomeCommmand, BindingResult result) {

		if (result.hasErrors()) {
			return "登録できません";
		}

		System.out.println("収入");
		System.out.println(incomeCommmand.getRadioName());
		System.out.println(incomeCommmand.getSelectIncome());
		System.out.println(incomeCommmand.getDate());
		System.out.println(incomeCommmand.getPrice());
		System.out.println(incomeCommmand.getNote());

		householdService.createIncomeInfo(incomeCommmand);

		return "登録しました";
	}

	// 登録(支出)
	@RequestMapping(value = "/expenditure", method = RequestMethod.POST)
	public String expenditureCreate(@RequestBody @Validated ExpenditureForm expenditureCommand, BindingResult result) {

		if (result.hasErrors()) {
			return "登録できません";
		}

		System.out.println("支出");
		System.out.println(expenditureCommand.getRadioName());
		System.out.println(expenditureCommand.getDate());
		System.out.println(expenditureCommand.getSelectExpenditure());
		System.out.println(expenditureCommand.getPrice());
		System.out.println(expenditureCommand.getNote());
		householdService.createExpenditureInfo(expenditureCommand);

		return "登録しました";
	}

	// 金額情報検索(編集)
	@GetMapping("/search/balance")
	public SearchBalanceInfo getSerchBalanceInfo(@RequestParam("No") String balanceNo) {

		// 検索
		SearchBalanceIncomeInfo serchBalanceIncomeResult = householdService.getSearchIncomeInfo(balanceNo);
		SearchBalanceExpenditureInfo serchBalanceExpenditureResult = householdService
				.getSearchExpenditureInfo(balanceNo);

		// 検索結果を格納するForm
		SearchBalanceInfo serchBalanceResult = new SearchBalanceInfo();

		//支出(serchBalanceExpenditureResult)がnullの場合
		if (serchBalanceExpenditureResult == null) {
		
			//日付をYYYY-MM-DDの形に整える
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(serchBalanceIncomeResult.getIncomeDate());
			
			//NoをString型に変換(引数に指定した型をString型に変換する)
			String No = String.valueOf(serchBalanceIncomeResult.getIncoemNo());

			// 検索結果(収入)をセット
			serchBalanceResult.setBalanceType("収入");
			serchBalanceResult.setBalanceNo(No);
			serchBalanceResult.setBalanceDate(date);
			serchBalanceResult.setIncomeType(serchBalanceIncomeResult.getIncomeType());
			serchBalanceResult.setAmount(serchBalanceIncomeResult.getAmount());
			serchBalanceResult.setNote(serchBalanceIncomeResult.getNote());
			
		} else {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(serchBalanceExpenditureResult.getExpenditureDate());
			
			String No = String.valueOf(serchBalanceExpenditureResult.getExpenditureNo());


			// 検索結果(支出)をセット
			serchBalanceResult.setBalanceType("支出");
			serchBalanceResult.setBalanceNo(No);
			serchBalanceResult.setBalanceDate(date);
			serchBalanceResult
					.setExpenditureExpenseItemName(serchBalanceExpenditureResult.getExpenditureExpenseItemName());
			serchBalanceResult.setAmount(serchBalanceExpenditureResult.getAmount());
			serchBalanceResult.setNote(serchBalanceExpenditureResult.getNote());
		}

		return serchBalanceResult;
	}

	// 編集
	//収入
	@RequestMapping(value = "/income/edit", method = RequestMethod.PATCH)
	public String incomeEdit(@RequestBody @Validated IncomeEditForm editincomeForm, BindingResult result) {
		
		if(result.hasErrors()) {
		     return "編集できません";
		    }
		
		//service呼び出し
		householdService.incomeEdit(editincomeForm);
		
		return "編集が完了しました";
	}
	
	//支出
	@RequestMapping(value = "/expenditure/edit", method = RequestMethod.PATCH)
	public String expenditureEdit(@RequestBody @Validated  ExpenditureEditForm expenditureEditForm, BindingResult result) {
		
		if(result.hasErrors()) {
		     return "編集できません";
		    }
		
		//service呼び出し
		householdService.expenditureEdit(expenditureEditForm);
		
		return "編集が完了しました";
	}
	
	//削除
	//収入
	@RequestMapping(value="/income/{incomeNo}/delete",method=RequestMethod.PUT)
	public String incomeDelete(@PathVariable String incomeNo) {
		//service呼び出し
		householdService.incomeDelete(incomeNo);
		return "削除しました";
	}
	
	//支出
	@RequestMapping(value="/expenditure/{expenditureNo}/delete",method=RequestMethod.PUT)
	public String expenditureDelete(@PathVariable String expenditureNo) {
		//service呼び出し
		householdService.expenditureDelete(expenditureNo);
		return "削除しました";
	}

	// 収入データと支出データを同時に検索して、同時に結果を返す(通常検索)
	@GetMapping("/searchBalanceList")
	public List<SearchResultBalanceForm> getSearchBalance(@RequestParam("ID") String balanceCode) {

		// 収支コードをもとに収支データを検索
		List<SearchResultIncome> searchIncomeResult = householdService.getSearchIncomeInfoList(balanceCode);
		List<SearchResultExpenditure> searchExpenditureResult = householdService
				.getSearchExpenditureInfoList(balanceCode);

		// 収支データを格納するlist
		List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();

		for (int i = 0; i < searchIncomeResult.size(); i++) {
			// 収支データを格納するform
			SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();

			// コードと日付をフォーマットをフロントエンド用に変換
			String code = String.valueOf(searchIncomeResult.get(i).getIncomeNo());
			String date = new SimpleDateFormat("yyyy-MM-dd").format(searchIncomeResult.get(i).getIncomeDate());

			// 収入データの検索結果を格納
			searchResultBalanceForm.setBalanceType("収入");
			searchResultBalanceForm.setBalanceCode(code);
			searchResultBalanceForm.setAmount(searchIncomeResult.get(i).getAmount());
			searchResultBalanceForm.setBalanceDate(date);
			searchResultBalanceForm.setIncomeType(searchIncomeResult.get(i).getIncomeType());
			searchResultBalanceForm.setNote(searchIncomeResult.get(i).getNote());
			searchBalanceResult.add(searchResultBalanceForm);
		}

		for (int i = 0; i < searchExpenditureResult.size(); i++) {
			// 支出データを格納するform
			SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();

			// コードと日付のフォーマットをフロントエンド用に変換
			String code = String.valueOf(searchExpenditureResult.get(i).getExpenditureNo());
			String date = new SimpleDateFormat("yyyy-MM-dd")
					.format(searchExpenditureResult.get(i).getExpenditureDate());

			// 支出データの検索結果を格納
			searchResultBalanceForm.setBalanceType("支出");
			searchResultBalanceForm.setBalanceCode(code);
			searchResultBalanceForm.setAmount(searchExpenditureResult.get(i).getAmount());
			searchResultBalanceForm.setBalanceDate(date);
			searchResultBalanceForm
					.setExpenditureExpenseItemName(searchExpenditureResult.get(i).getExpenditureExpenseItemName());
			searchResultBalanceForm.setNote(searchExpenditureResult.get(i).getNote());
			searchBalanceResult.add(searchResultBalanceForm);
		}

		return searchBalanceResult;
	}
}
