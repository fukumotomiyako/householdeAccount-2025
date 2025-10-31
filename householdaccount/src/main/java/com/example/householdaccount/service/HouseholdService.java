package com.example.householdaccount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.ExpenditureItems;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeForm;
import com.example.householdaccount.repository.mybatis.ExpenditureRepository;
import com.example.householdaccount.repository.mybatis.GetExpenditureItemsRepository;
import com.example.householdaccount.repository.mybatis.IncomeRepository;

@Service
public class HouseholdService {
	
	@Autowired
	GetExpenditureItemsRepository expenditureItemRepository;
	@Autowired
	IncomeRepository incomeRepository;
	@Autowired
	ExpenditureRepository expenditureRepository;
	
	//支出費目取得
	public List<ExpenditureItems> getExpenditureItems(){
//		System.out.println("miyako");
//		List<ExpenditureItems> tmp = expenditureItemRepository.findAll();
//		System.out.println(tmp.get(0).getExpenditure_expense_item_name());
		//System.out.println(expenditureItemRepository.findExpenditureByBalanceCode());
		//リポジトリから取得した情報を返している
		return expenditureItemRepository.findAll();
	}
	
	//収入登録
	public Income createIncomeInfo(IncomeForm incomeCommand) {
		Income income = new Income();
//		income.setIncomeNo(); //incomeNoを作成する
		
		//Entity側で必要なものをセッターでデータを入れる
		//不要なものは記述しない
		
		return incomeRepository.save(income);
	}
	
	//支出登録
	public Expenditure createExpenditureInfo(ExpenditureForm expenditureCommand) {
		
		Expenditure expenditure = new Expenditure(); 

		return expenditureRepository.save(expenditure);
	}

}
