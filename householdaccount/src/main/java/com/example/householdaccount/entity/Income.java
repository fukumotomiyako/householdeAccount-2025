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

import com.example.householdaccount.common.SystemItemVO;
import com.example.householdaccount.form.IncomeForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="income_info")
public class Income {
	
	@Id
	@Column(name="income_no")
	private IncomeNoVO incomeNo;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="income_type")
	private Integer incomeType;
	
	@Column(name="income_date")
	private Date incomeDate;
	
	@Column(name="note")
	private String note;
	
	@Column(name="delete_frag")
	private boolean deleteFlag;
	
//	@Column(name="initial_create_date_and_time")
//	private LocalDateTime initialCreateDateAndTime;
//	
//	@Column(name="last_update_date_and_time")
//	private LocalDateTime lastUpdateDateAndTime;
//	
//	@Column(name="initial_create_user_code")
//	private String initialCreateUserCode;
//	
//	@Column(name="last_update_user_code")
//	private String lastUpdateUserCode;
	
	@Column(name="version")
	private Integer version;
	
	private SystemItemVO systemItemVO = new SystemItemVO();
	//システムアイテムVOコンストラクタ作成
	//システムアイテムVO呼び出し
	
	public Income() {
	}//引数なしのコンストラクタ
	
	public Income(String incomeNo,IncomeForm incomeCommand) {
		//this.incomeNo　= VO呼び出す 
		this.incomeNo=IncomeNoVO.of(incomeNo);
		//incomenumberVO型にしている
		this.amount = incomeCommand.getPrice();
		this.incomeType = incomeCommand.getSelectIncome();
		this.incomeDate =incomeCommand.getDate();
		this.note = incomeCommand.getNote();
		//不変だから直接入れる
		this.deleteFlag = false;
//		this.initialCreateDateAndTime=LocalDateTime.now();
//		this.lastUpdateDateAndTime=LocalDateTime.now();
//		this.initialCreateUserCode="aa";
//		this.lastUpdateUserCode="bb";
		//不変だから直接入れる
		this.version=0;
	}
	
	@ValueObject
	@Embeddable//クラスをEntityに埋め込む
	//クラス内のすべてのフィールドを引数にとるコンストラクタ(そのコンストラクタを呼び出す"of()を作成
	@AllArgsConstructor(staticName="of")
	//引数なしのコンストラクタを自動生成　force = true→強制的に初期化してコンストラクタ作成
	//access = AccessLevel.PROTECTED→作成されるコンストラクタの修飾子をPROTECTEDにする
	@NoArgsConstructor(force=true,access=AccessLevel.PROTECTED)
	//特定のフィールドを文字列として出力
	@JsonSerialize(using=ToStringSerializer.class)
	public static class IncomeNoVO implements Serializable,Identifier{
		
		@Column(nullable=false,length=10)
		private final String incomeNo;
		
		@Override
		public String toString() {
			return this.incomeNo;
		}
	}
	
	//Getter,Setter
	public IncomeNoVO getIncomeNo() {
		return incomeNo;
	}

	public void setIncomeNo(IncomeNoVO incomeNo) {
		this.incomeNo = incomeNo;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(Integer incomeType) {
		this.incomeType = incomeType;
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
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

//	public LocalDateTime getInitialCreateDateAndTime() {
//		return initialCreateDateAndTime;
//	}
//
//	public void setInitialCreateDateAndTime(LocalDateTime initialCreateDateAndTime) {
//		this.initialCreateDateAndTime = initialCreateDateAndTime;
//	}
//
//	public LocalDateTime getLastUpdateDateAndTime() {
//		return lastUpdateDateAndTime;
//	}
//
//	public void setLastUpdateDateAndTime(LocalDateTime lastUpdateDateAndTime) {
//		this.lastUpdateDateAndTime = lastUpdateDateAndTime;
//	}
//
//	public String getInitialCreateUserCode() {
//		return initialCreateUserCode;
//	}
//
//	public void setInitialCreateUserCode(String initialCreateUserCode) {
//		this.initialCreateUserCode = initialCreateUserCode;
//	}
//
//	public String getLastUpdateUserCode() {
//		return lastUpdateUserCode;
//	}
//
//	public void setLastUpdateUserCode(String lastUpdateUserCode) {
//		this.lastUpdateUserCode = lastUpdateUserCode;
//	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
