package com.example.householdaccount.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.beans.factory.annotation.Value;

import com.example.householdaccount.common.SystemItemVO;
import com.example.householdaccount.entity.ExpenditureItems.ExpenditureExpenseItemCodeVO;
import com.example.householdaccount.form.ExpenditureEditForm;
import com.example.householdaccount.form.ExpenditureForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="expenditure_info")
public class Expenditure {
	
	@Id
	@Column(name="expenditure_no")
	private ExpenditureNoVO expenditure_no;

	@Column(name="amount")
	private Integer amount;
	
	@Column(name="expenditure_expense_item_code")
	private ExpenditureExpenseItemCodeVO expenditureExpenseItemCode;
	
	@Column(name="expenditure_expense_item_name")
	private String expenditureExpenseItemName;
	
	@Column(name="expenditure_date")
	private Date expenditureDate;
	
	@Column(name="note")
	private String note;
	
	@Column(name="delete_frag")
	private boolean deleteFrag;
	
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
	
	private final SystemItemVO systemItemVO = new SystemItemVO();
	//final→一度値を設定したらその後は変更できないようにする
	//システムアイテムVO呼び出し
	
	//引数なしのコンストラクタ
	public Expenditure() {
	}
	
	public Expenditure(String expenditureNo,ExpenditureForm expenditureCommand,ExpenditureExpenseItemCodeVO expenditureExpenseItemCode) {
		this.expenditure_no = ExpenditureNoVO.of(expenditureNo);
		this.amount = expenditureCommand.getPrice();
		this.expenditureExpenseItemCode=expenditureExpenseItemCode;
		this.expenditureExpenseItemName = expenditureCommand.getSelectExpenditure();
		this.expenditureDate = expenditureCommand.getDate();
		this.note = expenditureCommand.getNote();
		this.deleteFrag=false;
//		this.initialCreateDateAndTime=LocalDateTime.now();
//		this.lastUpdateDateAndTime=LocalDateTime.now();
//		this.initialCreateUserCode="aa";
//		this.lastUpdateUserCode="bb";
		this.version=0;
	}
	
	public Expenditure(ExpenditureEditForm expenditureEditForm,ExpenditureExpenseItemCodeVO expenditureExpenseItemCode,Integer expenditureVersion) {
		this.expenditure_no = ExpenditureNoVO.of(expenditureEditForm.getBalanceNo());
		this.amount = expenditureEditForm.getAmount();
		this.expenditureExpenseItemCode=expenditureExpenseItemCode;
		this.expenditureExpenseItemName = expenditureEditForm.getExpenditureExpenseItemName();
		this.expenditureDate = expenditureEditForm.getBalanceDate();
		this.note = expenditureEditForm.getNote();
		this.deleteFrag=false;
		this.version=expenditureVersion;
	}
	
	//支出VO
	
	@ValueObject
	@Embeddable//クラスをEntityに埋め込む
	//クラス内のすべてのフィールドを引数にとるコンストラクタ(そのコンストラクタを呼び出す"of()を作成
	@AllArgsConstructor(staticName="of")
	//引数なしのコンストラクタを自動生成　force = true→強制的に初期化してコンストラクタ作成
	//access = AccessLevel.PROTECTED→作成されるコンストラクタの修飾子をPROTECTEDにする
	@NoArgsConstructor(force=true,access=AccessLevel.PROTECTED)
	//特定のフィールドを文字列として出力
	@JsonSerialize(using=ToStringSerializer.class)
	public static class ExpenditureNoVO implements Serializable,Identifier{
		
		@Column(nullable=false,length=10)
		private final String expenditureNo;
		//一度値を設定したらその後は変更できないようにする
		
		@Override
		public String toString() {
			return this.expenditureNo;
		//文字列として、expenditureNoの値を返す
			
		//ExpenditureNoVO型になっているが、String型として扱う
		}
	}

	//Setter Getter

	public ExpenditureNoVO getExpenditure_no() {
		return expenditure_no;
	}
	public void setExpenditure_no(ExpenditureNoVO expenditure_no) {
		this.expenditure_no = expenditure_no;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public ExpenditureExpenseItemCodeVO getExpenditureExpenseItemCode() {
		return expenditureExpenseItemCode;
	}
	public void setExpenditureExpenseItemCode(ExpenditureExpenseItemCodeVO expenditureExpenseItemCode) {
		this.expenditureExpenseItemCode = expenditureExpenseItemCode;
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
	public boolean isDeleteFrag() {
		return deleteFrag;
	}
	public void setDeleteFrag(boolean deleteFrag) {
		this.deleteFrag = deleteFrag;
	}
//	public LocalDateTime getInitialCreateDateAndTime() {
//		return initialCreateDateAndTime;
//	}
//	public void setInitialCreateDateAndTime(LocalDateTime initialCreateDateAndTime) {
//		this.initialCreateDateAndTime = initialCreateDateAndTime;
//	}
//	public LocalDateTime getLastUpdateDateAndTime() {
//		return lastUpdateDateAndTime;
//	}
//	public void setLastUpdateDateAndTime(LocalDateTime lastUpdateDateAndTime) {
//		this.lastUpdateDateAndTime = lastUpdateDateAndTime;
//	}
//	public String getInitialCreateUserCode() {
//		return initialCreateUserCode;
//	}
//	public void setInitialCreateUserCode(String initialCreateUserCode) {
//		this.initialCreateUserCode = initialCreateUserCode;
//	}
//	public String getLastUpdateUserCode() {
//		return lastUpdateUserCode;
//	}
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
