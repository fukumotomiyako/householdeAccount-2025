package com.example.householdaccount.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.householdaccount.entity.Income.IncomeNoVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "income_info")
public class SearchBalanceIncomeInfo {

	@EmbeddedId
	@Column(name="income_no")
	private IncomeNoVO incoemNo;
	
	@Column(name="amount")
	private String amount;
	
	@Column(name="income_type")
	private String incomeType;
	
	@Column(name="income_date")
	private String incomeDate;
	
	@Column(name="note")
	private String note;
}
