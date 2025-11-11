package com.example.householdaccount.form;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SearchBalanceInfo {//編集のための検索結果がはいる
	private String balanceType;
	private String balanceCode;
	private Integer amount;
	private String balanceDate;
	private Integer incomeType;
	private String expenditureExpenseItemName;
	private String note;
	
}
