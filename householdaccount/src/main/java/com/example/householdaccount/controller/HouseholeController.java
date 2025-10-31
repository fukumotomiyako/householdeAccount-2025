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
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeForm;
import com.example.householdaccount.service.HouseholdService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholeController {
	
	@Autowired
	HouseholdService householdService;
	
//	//支出費目取得
	@RequestMapping(value = "/expenditureItems", method = RequestMethod.GET) 
	public List<ExpenditureItems> getExpenditureItems(){
		List<ExpenditureItems> expenditureItemList = householdService.getExpenditureItems();
		return expenditureItemList;
	}
	
	
	//登録(収入)
	@RequestMapping(value = "/income", method = RequestMethod.POST) 
	public String incomeCreate( @RequestBody @Validated IncomeForm incomeCommmand,BindingResult result){
		
		if(result.hasErrors()) {
		     return "登録できません";
		    }
//		householdService.createIncomeInfo(incomeCommmand);
		
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
		System.out.println(expenditureCommand.getRadioName());
		System.out.println(expenditureCommand.getDate());
		System.out.println(expenditureCommand.getSelectExpenditure());
		System.out.println(expenditureCommand.getPrice());
		System.out.println(expenditureCommand.getNote());
//		householdService.createExpenditureInfo(expenditureCommand);
		
		return "登録しました";
	}

}
