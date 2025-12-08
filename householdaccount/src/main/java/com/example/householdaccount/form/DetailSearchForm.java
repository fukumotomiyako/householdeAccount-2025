package com.example.householdaccount.form;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DetailSearchForm {
	private String balanceType;
	private String balanceCode;
	private Integer amount;
	private String balanceDate;
	private Integer incomeType;
	private String expenditureExpenseItemName;
	private String note;

}
