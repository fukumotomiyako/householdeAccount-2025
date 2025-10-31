package com.example.householdaccount.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//@Table(name="expenditure_expense",schema="expense_sys")
//@Table(name="expense_sys_expenditure_expense",schema="public")
@Table(name="expenditure_item",schema="public" )
public class ExpenditureItems {
	
	@Id
	@Column
	private String expenditure_expense_item_code;

	@Column
	private String expenditure_expense_item_name;
	
	@Column
	private String expenditure_expense_item_name_kana;
	

	public String getExpenditure_expense_item_code() {
		return expenditure_expense_item_code;
	}

	public void setExpenditure_expense_item_code(String expenditure_expense_item_code) {
		this.expenditure_expense_item_code = expenditure_expense_item_code;
	}

	public String getExpenditure_expense_item_name() {
		return expenditure_expense_item_name;
	}

	public void setExpenditure_expense_item_name(String expenditure_expense_item_name) {
		this.expenditure_expense_item_name = expenditure_expense_item_name;
	}

	public String getExpenditure_expense_item_name_kana() {
		return expenditure_expense_item_name_kana;
	}

	public void setExpenditure_expense_item_name_kana(String expenditure_expense_item_name_kana) {
		this.expenditure_expense_item_name_kana = expenditure_expense_item_name_kana;
	}

	


}
