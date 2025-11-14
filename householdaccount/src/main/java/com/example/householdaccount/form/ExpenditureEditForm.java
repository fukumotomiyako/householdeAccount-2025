package com.example.householdaccount.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpenditureEditForm {
	
	@NotNull
	private String balanceType;
	
	@NotNull
	private String balanceNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date balanceDate;
	
	@NotNull
	private Integer amount;
	
	@NotNull
	private String expenditureExpenseItemName;
	
	@Size(max=200)
	private String note;

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public String getBalanceNo() {
		return balanceNo;
	}

	public void setBalanceNo(String balanceNo) {
		this.balanceNo = balanceNo;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getExpenditureExpenseItemName() {
		return expenditureExpenseItemName;
	}

	public void setExpenditureExpenseItemName(String expenditureExpenseItemName) {
		this.expenditureExpenseItemName = expenditureExpenseItemName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	
}
