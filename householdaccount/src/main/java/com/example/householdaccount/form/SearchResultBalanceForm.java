package com.example.householdaccount.form;

import lombok.Data;

@Data
public class SearchResultBalanceForm {
	private String balanceType;
	private String balanceCode;
	private Integer amount;
	private String balanceDate;
	private Integer incomeType;
	private String expenditureExpenseItemName;
	private String note;

}
