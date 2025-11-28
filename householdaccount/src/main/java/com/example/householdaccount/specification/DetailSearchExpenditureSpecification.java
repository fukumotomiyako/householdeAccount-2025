package com.example.householdaccount.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

public class DetailSearchExpenditureSpecification<SearchResultExpenditure> {
	
	// 日付(from~toの検索)
		public Specification<SearchResultExpenditure> dateGreaterThanLessThan(Date fromDate, Date toDate) {
			return (root, query, builder) -> {
				// root:Entityのルート Entityのフィールド
				// query:CriteriaQuery SQLのSELECT文を表す
				// builder:CriteriaBuilder 条件式(WHERE)や結合を作るためのもの
				if (fromDate != null && toDate != null) {
					return builder.and(
							// 複数条件を検索
							builder.greaterThanOrEqualTo(root.get("expenditureDate"), fromDate),
							// fromDate以降の日付を検索する(entityで取得した値と、引数として渡された値を比較する)
							builder.lessThanOrEqualTo(root.get("expenditureDate"), toDate)
					// toDate以降の日付を検索する
					);
				} else if (fromDate != null) {
					return builder.greaterThanOrEqualTo(root.get("expenditureDate"), fromDate);
					// fromDate以降の日付を検索する
				} else if (toDate != null) {
					return builder.lessThanOrEqualTo(root.get("expenditureDate"), toDate);
					// toDate以降の日付を検索する
				}
				return null;
				// fromDate,toDetaがともにnullの場合はnullを返す
			};
		}

		// 支出費目検索
		public Specification<SearchResultExpenditure> expenditureMatch(String selectExpenditure) {
			return (root, query, builder) -> {
				if (selectExpenditure == null||selectExpenditure=="")  {
					return null;
				}
				return builder.equal(root.get("expenditureExpenseItemName"), selectExpenditure);
			};
		}

		// 金額(from~toの検索)
		public Specification<SearchResultExpenditure> amountGreaterThanLessThan(Integer fromAmount, Integer toAmount) {
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
		public Specification<SearchResultExpenditure> noteLikeContains(String note) {
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
		public Specification<SearchResultExpenditure> deleteFlagCheck(){
			return (root,equal,builder) -> {
				return builder.equal(root.get("deleteFrag"), false);
			};
		}

}
