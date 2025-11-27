package com.example.householdaccount.form;

import java.util.Date;

import lombok.Data;

@Data
public class DetailSearchResultForm {//収入の詳細検索結果が入る
	private String balanceType;
	private String incomeNO;
	private Integer amount;
	private Integer incomeType;
	private Date incomeDate;
	private String note;
	private Boolean deleteFlag;
}
