package com.example.householdaccount.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpenditureForm {
	private String radioName;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@NotNull
	private String selectExpenditure;
	
	@NotNull
	private  Integer price;
	
	@Size(max=200)
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
