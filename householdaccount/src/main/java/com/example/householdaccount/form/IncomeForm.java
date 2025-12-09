package com.example.householdaccount.form;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class IncomeForm {
	@NotNull
	private String radioName;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@NotNull
	private Integer selectIncome;
	
	@NotNull
	@Digits(integer = 8, fraction = 0)
	@Positive
	private  Integer price;
	
	@Size(max=200)
	private String note;
	
	//Getter Setter
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
	public Integer getSelectIncome() {
		return selectIncome;
	}
	public void setSelectIncome(Integer selectIncome) {
		this.selectIncome = selectIncome;
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
