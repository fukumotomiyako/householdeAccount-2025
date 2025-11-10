package com.example.householdaccount.form;


public class SearchBalanceInfo {//編集のための検索結果がはいる
	private String balanceType;
	private String balanceCode;
	private Integer amount;
	private String balanceDate;
	private Integer incomeType;
	private String expenditureExpenseItemName;
	private String note;
	
	
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	public String getBalanceCode() {
		return balanceCode;
	}
	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	public Integer getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(Integer incomeType) {
		this.incomeType = incomeType;
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
