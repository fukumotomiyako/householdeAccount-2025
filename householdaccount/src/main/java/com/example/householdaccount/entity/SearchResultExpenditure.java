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
public class SearchResultExpenditure {

	@EmbeddedId
	@Column(name="expenditure_no")
	private ExpenditureNoVO expenditureNo;
	//リポジトリから取得した時点でVO型のため、VO型にするという処理が不要
	
	@Column(name="expenditure_date")
	private Date expenditureDate;
	
	private Integer amount;
	
	@Column(name="expenditure_expense_item_name")
	private String expenditureExpenseItemName;
	
	private String note;
	
	@Column(name="delete_frag")
	private boolean deleteFrag;
}
