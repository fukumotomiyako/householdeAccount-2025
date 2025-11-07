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
public class SearchResultIncome {
	//private String balanceClassification = "収入";
	
		@EmbeddedId
		@Column(name="income_no")
		private IncomeNoVO incomeNo;
		
		@Column(name="income_date")
		private Date incomeDate;
		
		private Integer amount;
		
		@Column(name="income_type")
		private Integer incomeType;
		
		private String note;

		@Column(name="delete_frag")
		private boolean deleteFlag;

}
