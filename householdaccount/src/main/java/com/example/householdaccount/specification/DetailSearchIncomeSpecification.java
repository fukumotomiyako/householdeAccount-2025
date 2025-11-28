package com.example.householdaccount.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

public class DetailSearchIncomeSpecification<SearchResultIncome> {

	// 日付(from~toの検索)
	public Specification<SearchResultIncome> dateGreaterThanLessThan(Date fromDate, Date toDate) {
		return (root, query, builder) -> {
			// root:Entityのルート Entityのフィールド
			// query:CriteriaQuery SQLのSELECT文を表す
			// builder:CriteriaBuilder 条件式(WHERE)や結合を作るためのもの
			if (fromDate != null && toDate != null) {
				return builder.and(
						// 複数条件を検索
						builder.greaterThanOrEqualTo(root.get("incomeDate"), fromDate),
						// fromDate以降の日付を検索する(entityで取得した値と、引数として渡された値を比較する)
						builder.lessThanOrEqualTo(root.get("incomeDate"), toDate)
				// toDate以降の日付を検索する
				);
			} else if (fromDate != null) {
				return builder.greaterThanOrEqualTo(root.get("incomeDate"), fromDate);
				// fromDate以降の日付を検索する
			} else if (toDate != null) {
				return builder.lessThanOrEqualTo(root.get("incomeDate"), toDate);
				// toDate以降の日付を検索する
			}
			return null;
			// fromDate,toDetaがともにnullの場合はnullを返す
		};
	}

	// 収入費目検索
	public Specification<SearchResultIncome> incomeTypeMatch(Integer selectIncome) {
		return selectIncome == null ? null : (root, query, builder) -> {
			return builder.equal(root.get("incomeType"), selectIncome);
		};
	}

	// 支出費目検索
//	public Specification<T> expenditureMatch(String selectExpenditure) {
//		return selectExpenditure == null ? null : (root, query, builder) -> {
//			return builder.equal(root.get("selectExpenditure"), selectExpenditure);
//		};
//	}

	// 金額(from~toの検索)
	public Specification<SearchResultIncome> amountGreaterThanLessThan(Integer fromAmount, Integer toAmount) {
		return (root, query, builder) -> {
			if (fromAmount != null && toAmount != null) {
				return builder.and(builder.greaterThanOrEqualTo(root.get("amount"), fromAmount),
						builder.lessThanOrEqualTo(root.get("amount"), toAmount));
			} else if (fromAmount != null) {
				return builder.greaterThanOrEqualTo(root.get("amount"), fromAmount);
				// from以降の検索
			} else if (toAmount != null) {
				return builder.lessThanOrEqualTo(root.get("amount"), toAmount);
				// to以前の検索
			}
			return null;
		};
	}

	// 備考検索(部分一致)
	public Specification<SearchResultIncome> noteLikeContains(String note) {
		return (root, equal, builder) -> {
			if(note == null||note == "") {
				return null;
			}
			String containNote = "%" + note + "%";
			//noteを含む文字列
			return builder.like(root.get("note"), containNote);
		};
	}
	
	//deleteFlagチェック
	public Specification<SearchResultIncome> deleteFlagCheck(){
		return (root,equal,builder) -> {
			return builder.equal(root.get("deleteFrag"), false);
		};
	}

}
