package com.example.householdaccount.repository.mybatis;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.householdaccount.entity.ExpenditureItems;
import com.example.householdaccount.entity.ExpenditureItems.ExpenditureExpenseItemCodeVO;

@Repository
public interface GetExpenditureItemsRepository extends JpaRepository<ExpenditureItems,ExpenditureExpenseItemCodeVO>{
														//ExpenditureItemsの主キーの型(String)
	//支出費目テーブルの中から、アイテムネームを使って検索し、該当する1列を取得する
	public ExpenditureItems findByExpenditureExpenseItemName(String expenditure_expense_item_name); 
	
}
