package com.example.householdaccount.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

public class DetailSearchIncomeSpecification<T> {
	
	//日付(from以降の検索)
	public Specification<T> fromDateGreaterThanEqual(Date fromDate){
		return fromDate == null ? null : (root,query,builder) -> {
		//fromDateがnullの場合はnullを返す
		//root:Entityのルート Entityのフィールド
		//query:CriteriaQuery SQLのSELECT文を表す
		//builder:CriteriaBuilder　条件式(WHERE)や結合を作るためのもの
			return builder.greaterThanOrEqualTo(root.get("fromDate"),fromDate);
			//fromDateがnullの場合ではない場合は、fromDate以降の日付を検索する
		};	
	}
	
	//日付(to以前の検索)
	public Specification<T>
}
