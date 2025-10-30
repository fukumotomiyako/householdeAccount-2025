package com.example.householdaccount.form;

import java.util.Date;

public class ExpenditureForm {
	private String radioName;
	private Date date;
	private String selectExpenditure;
	private  Integer price;
	private String note;
	
	public String getRadioName() {
		return radioName;
	}
	public void setRadioName(String radioName) {
		this.radioName = radioName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSelectExpenditure() {
		return selectExpenditure;
	}
	public void setSelectExpenditure(String selectExpenditure) {
		this.selectExpenditure = selectExpenditure;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
