package com.example.householdaccount.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenditure_info")
public class SearchBalanceExpenditureInfo {
	
	@EmbeddedId
	@Column(name="expenditure_no")
	private ExpenditureNoVO expenditureNo;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="expenditure_expense_item_name")
	private String expenditureExpenseItemName;
	
	@Column(name="expenditure_date")
	private Date expenditureDate;
	
	@Column(name="note")
	private String note;
}
