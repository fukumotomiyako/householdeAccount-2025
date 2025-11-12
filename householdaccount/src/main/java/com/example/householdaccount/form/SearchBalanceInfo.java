package com.example.householdaccount.form;

import lombok.Data;

@Data
public class SearchBalanceInfo {//編集のための検索結果がはいる
	private String balanceType;
	private String balanceNo;
	private Integer amount;
	private String balanceDate;
	private Integer incomeType;
	private String expenditureExpenseItemName;
	private String note;
	
}
