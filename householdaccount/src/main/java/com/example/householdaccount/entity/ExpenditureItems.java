package com.example.householdaccount.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpenditureItems {
	
	@Id
	@Column(name="expenditure_expense_item_code")
	private Integer expenditureExpenseItemCode;

	@Column(name="expenditure_expense_item_name")
	private String expenditureExpenseItemName;
	
	@Column(name="expenditure_expense_item_name_kana")
	private String expenditureExpenseItemNameKana;
	

	public int getExpenditureExpenseItemCode() {
		return expenditureExpenseItemCode;
	}

	public void setExpenditureExpenseItemCode(int expenditureExpenseItemCode) {
		this.expenditureExpenseItemCode = expenditureExpenseItemCode;
	}

	public String getExpenditureExpenseItemName() {
		return expenditureExpenseItemName;
	}

	public void setExpenditureExpenseItemName(String expenditureExpenseItemName) {
		this.expenditureExpenseItemName = expenditureExpenseItemName;
	}

	public String getExpenditureExpenseItemNameKana() {
		return expenditureExpenseItemNameKana;
	}

	public void setExpenditureExpenseItemNameKana(String expenditureExpenseItemNameKana) {
		this.expenditureExpenseItemNameKana = expenditureExpenseItemNameKana;
	}
}
