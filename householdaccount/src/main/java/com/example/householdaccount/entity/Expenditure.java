package com.example.householdaccount.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.beans.factory.annotation.Value;

import com.example.householdaccount.form.ExpenditureForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="expenditure")
public class Expenditure {
	
	@Id
	@Column(name="expenditure_no")
	private ExpenditureNoVO expenditureNo;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="expenditure_expense_item_code")
	private Integer expenditureExpenceItemCode;
	
	@Column(name="expenditure_expense_item_name")
	private String expenditureExpenseItemName;
	
	@Column(name="expenditure_date")
	private Date expenditureDate;
	
	@Column(name="note")
	private String note;
	
	@Column(name="delete_frag")
	private boolean deleteFlag;
	
	@Column(name="initial_create_date_and_time")
	private LocalDateTime initialCreateDateAndTime;
	
	@Column(name="last_update_date_and_time")
	private LocalDateTime lastUpdateDateAndTime;
	
	@Column(name="initial_create_user_code")
	private String initialCreateUserCode;
	
	@Column(name="last_update_user_code")
	private String lastUpdateUserCode;
	
	@Column(name="version")
	private Integer version;
	
	public Expenditure() {
	}
	public Expenditure(String expenditureNo,String strDate,ExpenditureForm expenditureCommand) {
		this.expenditureNo = ExpenditureNoVO.of(expenditureNo);
		this.amount = expenditureCommand.getPrice();
		this.expenditureExpenseItemName = expenditureCommand.getSelectExpenditure();
		this.expenditureDate = expenditureCommand.getDate();
		this.note = expenditureCommand.getNote();
		this.deleteFlag=false;
		this.version=0;
	}
	
	//支出VO
	@ValueObject
	@Embeddable
//	@Value
	@AllArgsConstructor(staticName="of")
	@NoArgsConstructor(force=true,access=AccessLevel.PROTECTED)
	@JsonSerialize(using=ToStringSerializer.class)
	public static class ExpenditureNoVO implements Serializable,Identifier{
		
		@Column(nullable=false,length=5)
		private final String expemditureNo;
		
		@Override
		public String toString() {
			return this.expemditureNo;
		}
	}

	//Setter Getter
	public ExpenditureNoVO getExpenditureNo() {
		return expenditureNo;
	}

	public void setExpenditureNo(ExpenditureNoVO expenditureNo) {
		this.expenditureNo = expenditureNo;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getExpenditureExpenceItemCode() {
		return expenditureExpenceItemCode;
	}

	public void setExpenditureExpenceItemCode(Integer expenditureExpenceItemCode) {
		this.expenditureExpenceItemCode = expenditureExpenceItemCode;
	}

	public String getExpenditureExpenseItemName() {
		return expenditureExpenseItemName;
	}

	public void setExpenditureExpenseItemName(String expenditureExpenseItemName) {
		this.expenditureExpenseItemName = expenditureExpenseItemName;
	}

	public Date getExpenditureDate() {
		return expenditureDate;
	}

	public void setExpenditureDate(Date expenditureDate) {
		this.expenditureDate = expenditureDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public LocalDateTime getInitialCreateDateAndTime() {
		return initialCreateDateAndTime;
	}

	public void setInitialCreateDateAndTime(LocalDateTime initialCreateDateAndTime) {
		this.initialCreateDateAndTime = initialCreateDateAndTime;
	}

	public LocalDateTime getLastUpdateDateAndTime() {
		return lastUpdateDateAndTime;
	}

	public void setLastUpdateDateAndTime(LocalDateTime lastUpdateDateAndTime) {
		this.lastUpdateDateAndTime = lastUpdateDateAndTime;
	}

	public String getInitialCreateUserCode() {
		return initialCreateUserCode;
	}

	public void setInitialCreateUserCode(String initialCreateUserCode) {
		this.initialCreateUserCode = initialCreateUserCode;
	}

	public String getLastUpdateUserCode() {
		return lastUpdateUserCode;
	}

	public void setLastUpdateUserCode(String lastUpdateUserCode) {
		this.lastUpdateUserCode = lastUpdateUserCode;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
