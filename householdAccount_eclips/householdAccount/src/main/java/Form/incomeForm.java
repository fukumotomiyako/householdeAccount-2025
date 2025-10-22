package Form;

import java.util.Date;

public class incomeForm {
	
	private String radioName;
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
	public String getSelectIncome() {
		return selectIncome;
	}
	public void setSelectIncome(String selectIncome) {
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
	private Date date;
	private String selectIncome;
	private  Integer price;
	private String note;

}
