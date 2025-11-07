package com.example.householdaccount.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name="expenditure_item",schema="public" )
public class ExpenditureItems {
	
	@Id
	@Column(name="expenditure_expense_item_code")
	private ExpenditureExpenseItemCodeVO expenditureExpenseItemCode;

	@Column(name="expenditure_expense_item_name")
	private String expenditureExpenseItemName;
	
	@Column(name="expenditure_expense_item_name_kana")
	private String expenditureExpenseItemNameKana;
	
	//支出費目VO

	@ValueObject
	@Embeddable//クラスをEntityに埋め込む
	//クラス内のすべてのフィールドを引数にとるコンストラクタ(そのコンストラクタを呼び出す"of()を作成
	@AllArgsConstructor(staticName="of")
	//引数なしのコンストラクタを自動生成　force = true→強制的に初期化してコンストラクタ作成
	//access = AccessLevel.PROTECTED→作成されるコンストラクタの修飾子をPROTECTEDにする
	@NoArgsConstructor(force=true,access=AccessLevel.PROTECTED)
	//特定のフィールドを文字列として出力
	@JsonSerialize(using=ToStringSerializer.class)
	public static class ExpenditureExpenseItemCodeVO implements Serializable,Identifier{
		
		@Column(nullable=false,length=5)
		private final String ExpenditureExpenseItemCode;
		
		@Override
		public String toString() {
			return this.ExpenditureExpenseItemCode;
			
			
		}
	}
	
	//Getter Setter
	public ExpenditureExpenseItemCodeVO getExpenditure_expense_item_code() {
		return expenditureExpenseItemCode;
	}

	public void setExpenditure_expense_item_code(ExpenditureExpenseItemCodeVO expenditure_expense_item_code) {
		this.expenditureExpenseItemCode = expenditure_expense_item_code;
	}

	public String getExpenditure_expense_item_name() {
		return expenditureExpenseItemName;
	}

	public void setExpenditure_expense_item_name(String expenditure_expense_item_name) {
		this.expenditureExpenseItemName = expenditure_expense_item_name;
	}

	public String getExpenditure_expense_item_name_kana() {
		return expenditureExpenseItemNameKana;
	}

	public void setExpenditure_expense_item_name_kana(String expenditure_expense_item_name_kana) {
		this.expenditureExpenseItemNameKana = expenditure_expense_item_name_kana;
	}

	


}
