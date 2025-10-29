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

import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeForm;

//import com.example.householdaccount.entity.ExpenditureItem;
//import com.example.householdaccount.entity.Income;
//import com.example.householdaccount.entity.Income.IncomeNoVO;
//import com.example.householdaccount.entity.SearchResultExpenditure;
//import com.example.householdaccount.entity.SearchResultIncome;
//import com.example.householdaccount.entity.SearchResultIncomeForEditAndDelete;
//import com.example.householdaccount.entity.SearchResultExpenditureForEditAndDelete;
//import com.example.householdaccount.form.DetailSearchForm;
//import com.example.householdaccount.form.ExpenditureHouseholdForm;
//import com.example.householdaccount.form.IncomeHouseholdForm;
//import com.example.householdaccount.form.SearchResultBalanceForm;
//import com.example.householdaccount.form.SearchResultBalanceFormForEditAndDelete;
//import com.example.householdaccount.form.SendFrontBalanceResultFormForEditAndDelete;
//import com.example.householdaccount.service.HouseholdServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholeController {
//	@Autowired
//	HouseholdServices householdServices;
	
//	//収入データと支出データを同時に検索して、同時に結果を返す
//	@GetMapping("/searchBalanceList")
//    public List<SearchResultBalanceForm> getSearchBalance(@RequestParam("ID") String balanceCode) {
//		
//		List<SearchResultIncome> searchIncomeResult = householdServices.getSearchIncomeInfoList(balanceCode);
//		List<SearchResultExpenditure> searchExpenditureResult = householdServices.getSearchExpenditureInfoList(balanceCode);
//		
//		List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();
//		
//		
//		for(int i = 0; i < searchIncomeResult.size(); i++) {
//			SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
//			String code = String.valueOf(searchIncomeResult.get(i).getIncomeNo());
//			String date = new SimpleDateFormat("yyyy-MM-dd").format(searchIncomeResult.get(i).getIncomeDate());
//			
//			searchResultBalanceForm.setBalanceType("収入");
//			searchResultBalanceForm.setBalanceCode(code);
//			searchResultBalanceForm.setAmount(searchIncomeResult.get(i).getAmount());
//			searchResultBalanceForm.setBalanceDate(date);
//			searchResultBalanceForm.setIncomeType(searchIncomeResult.get(i).getIncomeType());
//			searchResultBalanceForm.setNote(searchIncomeResult.get(i).getNote());
//			searchBalanceResult.add(searchResultBalanceForm);
//		}
//		
//		for(int i = 0; i < searchExpenditureResult.size(); i++) {
//			SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
//			String code = String.valueOf(searchExpenditureResult.get(i).getExpenditureNo());
//			String date = new SimpleDateFormat("yyyy-MM-dd").format(searchExpenditureResult.get(i).getExpenditureDate());
//			
//			searchResultBalanceForm.setBalanceType("支出");
//			searchResultBalanceForm.setBalanceCode(code);
//			searchResultBalanceForm.setAmount(searchExpenditureResult.get(i).getAmount());
//			searchResultBalanceForm.setBalanceDate(date);
//			searchResultBalanceForm.setExpenditureExpenseItemName(searchExpenditureResult.get(i).getExpenditureExpenseItemName());
//			searchResultBalanceForm.setNote(searchExpenditureResult.get(i).getNote());
//			searchBalanceResult.add(searchResultBalanceForm);
//		}
//        
//        return searchBalanceResult;
//    }
//	
//	
//	//sisaku
//	//収入データと支出データを同時に検索して、同時に結果を返す
//		@GetMapping("/searchBalance")
//	    public SendFrontBalanceResultFormForEditAndDelete getSearchBalanceForEditAndDelete(@RequestParam("ID") String balanceCode) {
//			
//			SearchResultIncomeForEditAndDelete searchResultIncomeForEdit = householdServices.getSearchIncomeInfo(balanceCode);
//			SearchResultExpenditureForEditAndDelete searchResultExpenditureForEdit = householdServices.getSearchExpenditureInfo(balanceCode);
//			
//			SendFrontBalanceResultFormForEditAndDelete searchResultBalanceFormForEdit = new SendFrontBalanceResultFormForEditAndDelete();
//			
//			if(searchResultIncomeForEdit != null) {
//				String code = String.valueOf(searchResultIncomeForEdit.getIncomeNo());
//	            String date = new SimpleDateFormat("yyyy-MM-dd").format(searchResultIncomeForEdit.getIncomeDate());
//				
//				searchResultBalanceFormForEdit.setBalanceType("収入");
//				searchResultBalanceFormForEdit.setBalanceCode(code);
//				searchResultBalanceFormForEdit.setAmount(searchResultIncomeForEdit.getAmount());
//				searchResultBalanceFormForEdit.setBalanceDate(date);
//				searchResultBalanceFormForEdit.setIncomeType(searchResultIncomeForEdit.getIncomeType());
//				searchResultBalanceFormForEdit.setNote(searchResultIncomeForEdit.getNote());
//				searchResultBalanceFormForEdit.setVersion(searchResultIncomeForEdit.getVersion());
//			}else {
//				String code = String.valueOf(searchResultExpenditureForEdit.getExpenditureNo());
//				String date = new SimpleDateFormat("yyyy-MM-dd").format(searchResultExpenditureForEdit.getExpenditureDate());
//				
//				searchResultBalanceFormForEdit.setBalanceType("支出");
//				searchResultBalanceFormForEdit.setBalanceCode(code);
//				searchResultBalanceFormForEdit.setAmount(searchResultExpenditureForEdit.getAmount());
//				searchResultBalanceFormForEdit.setBalanceDate(date);
//				searchResultBalanceFormForEdit.setExpenditureExpenseItemName(searchResultExpenditureForEdit.getExpenditureExpenseItemName());
//				searchResultBalanceFormForEdit.setNote(searchResultExpenditureForEdit.getNote());
//				searchResultBalanceFormForEdit.setVersion(searchResultExpenditureForEdit.getVersion());
//			}
//	
//	        return searchResultBalanceFormForEdit;
//	    }
//		
//	
//	//収入データを詳細検索して結果を返す
//	@GetMapping("/detailSearchIncome")
//    public List<SearchResultBalanceForm> getDetailSearchIncome(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
//    		@RequestParam("fromAmount") Optional<Integer> fromAmount,@RequestParam("toAmount") Optional<Integer> toAmount,
//    		@RequestParam("incomeType") Optional<Integer> incomeType,@RequestParam("expenditureItemName") Optional<String> expenditureItemName,
//    		@RequestParam("note") Optional<String> note) {
//		
//		Optional<Date> incomeFromDate;
//		Optional<Date> incomeToDate;
//		Optional<Boolean> deleteFlag = Optional.ofNullable(false);
//		
//		if(fromDate.isEmpty()) {
//			incomeFromDate = Optional.empty();
//		}else {
//			Date sqlDate = java.sql.Date.valueOf(fromDate);
//			incomeFromDate = Optional.ofNullable(sqlDate);
//		}
//		
//		if(toDate.isEmpty()) {
//			incomeToDate = Optional.empty();
//		}else {
//			Date sqlDate = java.sql.Date.valueOf(toDate);
//			incomeToDate = Optional.ofNullable(sqlDate);
//		}
//		
//		
//		List<SearchResultIncome> searchIncomeResult = householdServices.getDetailSearchIncomeInfo(incomeFromDate,incomeToDate,
//				fromAmount,toAmount,incomeType,expenditureItemName,note,deleteFlag);
//		
//		List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();
//		
//		for(int i = 0; i < searchIncomeResult.size(); i++) {
//			SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
//			String code = String.valueOf(searchIncomeResult.get(i).getIncomeNo());
//			String date = new SimpleDateFormat("yyyy-MM-dd").format(searchIncomeResult.get(i).getIncomeDate());
//			
//			searchResultBalanceForm.setBalanceType("収入");
//			searchResultBalanceForm.setBalanceCode(code);
//			searchResultBalanceForm.setAmount(searchIncomeResult.get(i).getAmount());
//			searchResultBalanceForm.setBalanceDate(date);
//			searchResultBalanceForm.setIncomeType(searchIncomeResult.get(i).getIncomeType());
//			searchResultBalanceForm.setNote(searchIncomeResult.get(i).getNote());
//			searchBalanceResult.add(searchResultBalanceForm);
//		}
//        
//        return searchBalanceResult;
//    }
//	
//	//支出データを詳細検索して結果を返す
//		@GetMapping("/detailSearchExpenditure")
//	    public List<SearchResultBalanceForm> getDetailSearchExpenditure(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
//	    		@RequestParam("fromAmount") Optional<Integer> fromAmount,@RequestParam("toAmount") Optional<Integer> toAmount,
//	    		@RequestParam("incomeType") Optional<Integer> incomeType,@RequestParam("expenditureItemName") String expenditureItemName,
//	    		@RequestParam("note") Optional<String> note) {
//			
//			Optional<Date> expenditureFromDate;
//			Optional<Date> expenditureToDate;
//			Optional<String> optionalExpenditureItemName;
//			Optional<Boolean> deleteFlag = Optional.ofNullable(false);
//			
//			//string型をoptional<>に変換
//			if(fromDate.isEmpty()) {
//				expenditureFromDate = Optional.empty();
//			}else {
//				Date sqlDate = java.sql.Date.valueOf(fromDate);
//				expenditureFromDate = Optional.ofNullable(sqlDate);
//			}
//			
//			if(toDate.isEmpty()) {
//				expenditureToDate = Optional.empty();
//			}else {
//				Date sqlDate = java.sql.Date.valueOf(toDate);
//				expenditureToDate = Optional.ofNullable(sqlDate);
//			}
//			
//			if(expenditureItemName.isEmpty()) {
//				optionalExpenditureItemName = Optional.empty();
//			}else {
//				optionalExpenditureItemName = Optional.ofNullable(expenditureItemName);
//			}
//			
//			//詳細検索
//			List<SearchResultExpenditure> searchExpenditureResult = householdServices.getDetailSearchExpenditureInfo(expenditureFromDate,expenditureToDate,
//					fromAmount,toAmount,incomeType,optionalExpenditureItemName,note,deleteFlag);
//			
//			//詳細検索結果を格納するリスト
//			List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();
//			
//			//詳細検索結果を格納
//			for(int i = 0; i < searchExpenditureResult.size(); i++) {
//				SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
//				String code = String.valueOf(searchExpenditureResult.get(i).getExpenditureNo());
//				String date = new SimpleDateFormat("yyyy-MM-dd").format(searchExpenditureResult.get(i).getExpenditureDate());
//				
//				searchResultBalanceForm.setBalanceType("支出");
//				searchResultBalanceForm.setBalanceCode(code);
//				searchResultBalanceForm.setAmount(searchExpenditureResult.get(i).getAmount());
//				searchResultBalanceForm.setBalanceDate(date);
//				searchResultBalanceForm.setExpenditureExpenseItemName(searchExpenditureResult.get(i).getExpenditureExpenseItemName());
//				searchResultBalanceForm.setNote(searchExpenditureResult.get(i).getNote());
//				searchBalanceResult.add(searchResultBalanceForm);
//			}
//	        return searchBalanceResult;
//	    }
//		
//		//収支データを詳細検索して結果を返す
//		@GetMapping("/detailSearchBalance")
//	    public List<SearchResultBalanceForm> getDetailSearchBalance(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
//	    		@RequestParam("fromAmount") Optional<Integer> fromAmount,@RequestParam("toAmount") Optional<Integer> toAmount,
//	    		@RequestParam("incomeType") Optional<Integer> incomeType,@RequestParam("expenditureItemName") String expenditureItemName,
//	    		@RequestParam("note") Optional<String> note) {
//			
//			Optional<Date> incomeFromDate;
//			Optional<Date> incomeToDate;
//			
//			Optional<Date> expenditureFromDate;
//			Optional<Date> expenditureToDate;
//			
//			Optional<String> optionalExpenditureItemName;
//			
//			Optional<Boolean> deleteFlag = Optional.ofNullable(false);
//			
//			//string型をoptional<string>に変換
//			if(fromDate.isEmpty()) {
//				incomeFromDate = Optional.empty();
//				expenditureFromDate = Optional.empty();
//			}else {
//				Date sqlDate = java.sql.Date.valueOf(fromDate);
//				incomeFromDate = Optional.ofNullable(sqlDate);
//				expenditureFromDate = Optional.ofNullable(sqlDate);
//			}
//			
//			if(toDate.isEmpty()) {
//				incomeToDate = Optional.empty();
//				expenditureToDate = Optional.empty();
//			}else {
//				Date sqlDate = java.sql.Date.valueOf(toDate);
//				incomeToDate = Optional.ofNullable(sqlDate);
//				expenditureToDate = Optional.ofNullable(sqlDate);
//			}
//			
//			if(expenditureItemName.isEmpty()) {
//				optionalExpenditureItemName = Optional.empty();
//			}else {
//				optionalExpenditureItemName = Optional.ofNullable(expenditureItemName);
//			}
//			
//			//詳細検索
//			List<SearchResultIncome> searchIncomeResult = householdServices.getDetailSearchIncomeInfo(incomeFromDate,incomeToDate,
//					fromAmount,toAmount,incomeType,optionalExpenditureItemName,note,deleteFlag);
//			
//			//詳細検索
//			List<SearchResultExpenditure> searchExpenditureResult = householdServices.getDetailSearchExpenditureInfo(expenditureFromDate,expenditureToDate,
//					fromAmount,toAmount,incomeType,optionalExpenditureItemName,note,deleteFlag);
//			
//			
//			//詳細検索結果を格納するリスト
//			List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();
//			
//			//詳細検索結果を格納(収入)
//			for(int i = 0; i < searchIncomeResult.size(); i++) {
//				SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
//				String code = String.valueOf(searchIncomeResult.get(i).getIncomeNo());
//				String date = new SimpleDateFormat("yyyy-MM-dd").format(searchIncomeResult.get(i).getIncomeDate());
//				
//				searchResultBalanceForm.setBalanceType("収入");
//				searchResultBalanceForm.setBalanceCode(code);
//				searchResultBalanceForm.setAmount(searchIncomeResult.get(i).getAmount());
//				searchResultBalanceForm.setBalanceDate(date);
//				searchResultBalanceForm.setIncomeType(searchIncomeResult.get(i).getIncomeType());
//				searchResultBalanceForm.setNote(searchIncomeResult.get(i).getNote());
//				searchBalanceResult.add(searchResultBalanceForm);
//			}
//			
//			//詳細検索結果を格納(支出)
//			for(int i = 0; i < searchExpenditureResult.size(); i++) {
//				SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
//				String code = String.valueOf(searchExpenditureResult.get(i).getExpenditureNo());
//				String date = new SimpleDateFormat("yyyy-MM-dd").format(searchExpenditureResult.get(i).getExpenditureDate());
//				
//				searchResultBalanceForm.setBalanceType("支出");
//				searchResultBalanceForm.setBalanceCode(code);
//				searchResultBalanceForm.setAmount(searchExpenditureResult.get(i).getAmount());
//				searchResultBalanceForm.setBalanceDate(date);
//				searchResultBalanceForm.setExpenditureExpenseItemName(searchExpenditureResult.get(i).getExpenditureExpenseItemName());
//				searchResultBalanceForm.setNote(searchExpenditureResult.get(i).getNote());
//				searchBalanceResult.add(searchResultBalanceForm);
//			}
//			
//	        // ユーザーリストを返す
//			
//	        return searchBalanceResult;
//	    }
//	
//	//支出費目を取得
//	@GetMapping("/expenseItems")
//    public List<ExpenditureItem> getExpenseItem() {
//		List<ExpenditureItem> expenditureItemList = householdServices.expenditureItemsInfo();
//        
//        return expenditureItemList;
//    }
	
	//登録(収入)
	@RequestMapping(value = "/income", method = RequestMethod.POST) 
	public String incomeCreate( @RequestBody @Validated IncomeForm incomeCommmand,BindingResult result){
		
		if(result.hasErrors()) {
		     return "登録できません";
		    }
		
		System.out.println("収入");
		System.out.println(incomeCommmand.getRadioName());
		System.out.println(incomeCommmand.getSelectIncome());
		System.out.println(incomeCommmand.getDate());
		System.out.println(incomeCommmand.getPrice());
		System.out.println(incomeCommmand.getNote());

		return "登録しました";
	}
	
	//登録(支出)
	@RequestMapping(value = "/expenditure", method = RequestMethod.POST) 
	public String expenditureCreate( @RequestBody @Validated ExpenditureForm expenditureCommand, BindingResult result){
		
		if(result.hasErrors()) {
		     return "登録できません";
		    }
		
		
		System.out.println("支出");
		System.out.println(expenditureCommand);
//		householdServices.postCreateExpenditureInfo(expenditureCommand);
		
		return "登録しました";
	}
	
//	//編集
//	@RequestMapping(value = "/edit", method = RequestMethod.POST) 
//	public String eidt( @RequestBody @Validated SearchResultBalanceFormForEditAndDelete editCommand, BindingResult result){
//		
//		if(result.hasErrors()) {
//		     return "編集できません";
//		    }
//		
//		if(editCommand.getBalanceType().equals("収入")) {
//			System.out.println("収入の編集です");
//			householdServices.postEditIncomeInfo(editCommand);
//		}
//		
//		if(editCommand.getBalanceType().equals("支出")) {
//			System.out.println("支出の編集です");
//			householdServices.postEditExpenditureInfo(editCommand);
//		}
//		
//		
//		return "編集しました、再度検索してください";
//	}
//	
//	//削除
//	@RequestMapping(value = "/delete", method = RequestMethod.POST) 
//	public String delete( @RequestBody @Validated SearchResultBalanceFormForEditAndDelete deleteCommand, BindingResult result){
//		
//		if(result.hasErrors()) {
//		     return "削除できません";
//		    }
//		
//		if(deleteCommand.getBalanceType().equals("収入")) {
//			householdServices.postDeleteIncomeInfo(deleteCommand);
//		}
//		
//		if(deleteCommand.getBalanceType().equals("支出")) {
//			householdServices.postDeleteExpenditureInfo(deleteCommand);
//		}
//		
//		
//		
//		return "削除しました,再度検索してください";
//	}
//	
//	
//	@ExceptionHandler(DataAccessException.class)
//    public String dataAccessExceptionHandler(DataAccessException e) {
//        return "'exclusive_error':システム管理者に連絡してください";
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String exceptionHandler(Exception e) {
//        return "'exclusive_error':システム管理者に連絡してください";
//    }

}
