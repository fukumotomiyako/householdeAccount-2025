package com.example.householdaccount.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.ExpenditureItems;
import com.example.householdaccount.entity.ExpenditureItems.ExpenditureExpenseItemCodeVO;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.SearchBalanceExpenditureInfo;
import com.example.householdaccount.entity.SearchBalanceIncomeInfo;
import com.example.householdaccount.entity.SearchResultExpenditure;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.form.ExpenditureEditForm;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeEditForm;
import com.example.householdaccount.form.IncomeForm;
import com.example.householdaccount.repository.mybatis.DetailSearchExpenditureRepository;
import com.example.householdaccount.repository.mybatis.DetailSearchIncomeRepository;
import com.example.householdaccount.repository.mybatis.ExpenditureRepository;
import com.example.householdaccount.repository.mybatis.GetExpenditureItemsRepository;
import com.example.householdaccount.repository.mybatis.IncomeRepository;
import com.example.householdaccount.repository.mybatis.SearchBalanceExpenditureRepository;
import com.example.householdaccount.repository.mybatis.SearchBalanceIncomeInfoRepository;
import com.example.householdaccount.repository.mybatis.SearchExpenditureHouseholdRepository;
import com.example.householdaccount.repository.mybatis.SearchIncomeHouseholdRepository;
import com.example.householdaccount.specification.DetailSearchExpenditureSpecification;
import com.example.householdaccount.specification.DetailSearchIncomeSpecification;

@Service
public class HouseholdService {

	@Autowired
	GetExpenditureItemsRepository expenditureItemRepository;
	@Autowired
	IncomeRepository incomeRepository;
	@Autowired
	ExpenditureRepository expenditureRepository;

	// 支出費目取得
	public List<ExpenditureItems> getExpenditureItems() {
		// リポジトリから取得した情報を返している
		return expenditureItemRepository.findAll();
	}

	// 収入登録
	public Income createIncomeInfo(IncomeForm incomeCommand) throws IllegalStateException {

		if (incomeCommand.getPrice() == null || incomeCommand.getDate() == null
				|| incomeCommand.getSelectIncome() == null) {
			throw new IllegalArgumentException("値がNULLです");

		} else if (!String.valueOf(incomeCommand.getPrice()).trim().matches("^\\d+$")
				|| !String.valueOf(incomeCommand.getDate()).matches("^\\d{4}-\\d{2}-\\d{2}")) {
			throw new IllegalArgumentException("数字のみ入力できます");

		} else if (incomeCommand.getNote() == null || incomeCommand.getNote() == "") {
			if (String.valueOf(incomeCommand.getPrice()).length() > 8) {
				throw new IllegalArgumentException("不正な桁数です");
			}

		} else if (incomeCommand.getNote() != null) {
			if (String.valueOf(incomeCommand.getPrice()).length() > 8 || incomeCommand.getNote().length() > 200) {
				throw new IllegalArgumentException("不正な桁数です");
			}
		}

		try {
			// incomeNo(I+YYMM登録時点の年月+00000)を作成する
			Calendar cl = Calendar.getInstance();

			// 現時点の年取得
			SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
			String strYear = sdfYear.format(cl.getTime());
			// 3番目の文字列から最後までを抽出
			String strfYear = strYear.substring(2);

			// 現時点の月取得
			SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
			String strMonth = sdfMonth.format(cl.getTime());

			// テーブルのデータ数をJPARepositoryで取得
			Long dateNumber = incomeRepository.count();
			// %→フォーマット指定の開始 0→ゼロ埋め 5→最小桁数 d→整数
			// 取得したデータ数＋1したものを返す
			String incomeNumber = String.format("%05d", dateNumber + 1);

			String incomeNo = "I" + strfYear + strMonth + incomeNumber;

			Income income = new Income(incomeNo, incomeCommand);
			// コンストラクタを呼び出し、引数にincomeNoなど不足しているものを入れる

			// Entity側で必要なものをセッターでデータを入れる
			// 不要なものは記述しない
			incomeRepository.save(income);
			return income;
		} catch (IllegalStateException e) {
			System.out.println("システムエラーが発生しました");
			throw new IllegalStateException("システムエラーが発生しました");
		}
	}

	// 支出登録
	public Expenditure createExpenditureInfo(ExpenditureForm expenditureCommand) throws Exception {

		if (expenditureCommand.getPrice() == null || expenditureCommand.getDate() == null
				|| expenditureCommand.getSelectExpenditure() == null
				|| expenditureCommand.getSelectExpenditure() == "") {
			throw new IllegalArgumentException("値がNULLです");

		} else if (!String.valueOf(expenditureCommand.getPrice()).trim().matches("^\\d+$")
				|| !String.valueOf(expenditureCommand.getDate()).matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			throw new IllegalArgumentException("数字のみ入力できます");

		} else if (expenditureCommand.getNote() == null || expenditureCommand.getNote() == "") {
			if (String.valueOf(expenditureCommand.getPrice()).length() > 8) {
				throw new IllegalArgumentException("不正な桁数です");
			}

		} else if (expenditureCommand.getNote() != null) {
			if (String.valueOf(expenditureCommand.getPrice()).length() > 8
					|| expenditureCommand.getNote().length() > 200) {
				throw new IllegalArgumentException("不正な桁数です");
			}
		}

		try {
			String expenditureItemName = expenditureCommand.getSelectExpenditure();
			ExpenditureItems expenditureItems = expenditureItemRepository
					.findByExpenditureExpenseItemName(expenditureItemName);
			ExpenditureExpenseItemCodeVO expenditureExpenseItemCode = expenditureItems
					.getExpenditure_expense_item_code();

			// String expenditureExpenseItemCode="EI001";

			Calendar cl = Calendar.getInstance();
			SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
			String strYear = sdfYear.format(cl.getTime());
			String strfYear = strYear.substring(2);
			SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
			String strMonth = sdfMonth.format(cl.getTime());
			Long dateNumber = expenditureRepository.count();
			String expenditureNumber = String.format("%05d", dateNumber + 1);
			String expenditureNo = "E" + strfYear + strMonth + expenditureNumber;

			Expenditure expenditure = new Expenditure(expenditureNo, expenditureCommand, expenditureExpenseItemCode);

			expenditureRepository.save(expenditure);
			return expenditure;
		} catch (Exception e) {
			throw new Exception("システムエラーが発生しました");
		}
	}

	// 収支データ取得(編集)
	@Autowired
	SearchBalanceIncomeInfoRepository serchBalanceIncomeRepository;
	@Autowired
	SearchBalanceExpenditureRepository serchBalanceExpenditureRepository;

	// 収入検索
	public SearchBalanceIncomeInfo getSearchIncomeInfo(String balanceNo) {

		SearchBalanceIncomeInfo serchBalanceIncomeInfo = serchBalanceIncomeRepository.findByBalanceNo(balanceNo);
		return serchBalanceIncomeInfo;
	}

	// 支出検索
	public SearchBalanceExpenditureInfo getSearchExpenditureInfo(String balanceNo) {
		SearchBalanceExpenditureInfo serchBalanceExpenditureInfo = serchBalanceExpenditureRepository
				.findByBalanceNo(balanceNo);
		return serchBalanceExpenditureInfo;
	}

	// 編集データ登録
	// 収入
	public Income incomeEdit(IncomeEditForm incomeEditForm) throws Exception {

		if (incomeEditForm.getAmount() == null || incomeEditForm.getBalanceDate() == null
				|| incomeEditForm.getIncomeType() == null) {
			throw new IllegalArgumentException("値がNULLです");

		} else if (!String.valueOf(incomeEditForm.getAmount()).trim().matches("^\\d+$")
				|| !String.valueOf(incomeEditForm.getBalanceDate()).matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			throw new IllegalArgumentException("数字のみ入力できます");

		} else if (incomeEditForm.getNote() == null || incomeEditForm.getNote() == "") {
			if (String.valueOf(incomeEditForm.getAmount()).length() > 8) {
				throw new IllegalArgumentException("不正な桁数です");
			}

		} else if (incomeEditForm.getNote() != null) {
			if (String.valueOf(incomeEditForm.getAmount()).length() > 8 || incomeEditForm.getNote().length() > 200) {
				throw new IllegalArgumentException("不正な桁数です");
			}
		}

		try {
			// バージョン取得して、＋１する
			String incomeNo = incomeEditForm.getBalanceNo();
			Integer incomeVersion = incomeRepository.findByIncomeNo(incomeNo);
			incomeVersion = incomeVersion + 1;

			Income income = new Income(incomeEditForm, incomeVersion);
			incomeRepository.save(income);
			return income;
		} catch (Exception e) {
			throw new Exception("システムエラーが発生しました");
		}
	}

//	支出
	public Expenditure expenditureEdit(ExpenditureEditForm expenditureEditForm) throws Exception {

		if (expenditureEditForm.getAmount() == null || expenditureEditForm.getBalanceDate() == null
				|| expenditureEditForm.getExpenditureExpenseItemName() == null
				|| expenditureEditForm.getExpenditureExpenseItemName() == "") {
			throw new IllegalArgumentException("値がNULLです");

		} else if (!String.valueOf(expenditureEditForm.getBalanceDate()).matches("^\\d{4}-\\d{2}-\\d{2}$")
				|| !String.valueOf(expenditureEditForm.getAmount()).trim().matches("^\\d+$")) {
			throw new IllegalArgumentException("数字のみ入力できます");

		} else if (expenditureEditForm.getNote() == null || expenditureEditForm.getNote() == "")
			if (String.valueOf(expenditureEditForm.getAmount()).length() > 8) {
				throw new IllegalArgumentException("不正な桁数です");

			} else if (expenditureEditForm.getNote() != null) {
				if (String.valueOf(expenditureEditForm.getAmount()).length() > 8
						|| expenditureEditForm.getNote().length() > 200) {
					throw new IllegalArgumentException("不正な桁数です");
				}
			}

		try {
			// アイテムコード取得
			String expenditureItemName = expenditureEditForm.getExpenditureExpenseItemName();
			ExpenditureItems expenditureItems = expenditureItemRepository
					.findByExpenditureExpenseItemName(expenditureItemName);
			ExpenditureExpenseItemCodeVO expenditureExpenseItemCode = expenditureItems
					.getExpenditure_expense_item_code();

			// バージョン取得して、＋１する
			String expenditureNo = expenditureEditForm.getBalanceNo();
			Integer expenditureVersion = expenditureRepository.findByExpenditureNo(expenditureNo);
			expenditureVersion = expenditureVersion + 1;

			Expenditure expenditure = new Expenditure(expenditureEditForm, expenditureExpenseItemCode,
					expenditureVersion);
			expenditureRepository.save(expenditure);
			return expenditure;
		} catch (Exception e) {
			throw new Exception("システムエラーが発生しました");
		}
	}

//削除
	// 収入
	public Income incomeDelete(String incomeNo) throws Exception {

		try {
			// incomeNoを引数に、テーブルの１行も取得するリポジトリ呼び出し
			Income incomeInfo = incomeRepository.findById(incomeNo);
			// コンストラクタ呼び出し
			Income income = new Income(incomeInfo);
			// 保存
			return incomeRepository.save(income);
		} catch (Exception e) {
			throw new Exception("システムエラーが発生しました");
		}
	}

	// 支出
	public Expenditure expenditureDelete(String expenditureNo) throws Exception {

		try {
			Expenditure expenditureInfo = expenditureRepository.findById(expenditureNo);
			Expenditure expenditure = new Expenditure(expenditureInfo);
			// 保存
			return expenditureRepository.save(expenditure);
		} catch (Exception e) {
			throw new Exception("システムエラーが発生しました");
		}
	}

//詳細検索

	@Autowired
	DetailSearchIncomeRepository detailSearchIncomeRepository;
	@Autowired
	DetailSearchExpenditureRepository detailSearchExpenditureRepository;

	// 収入検索
	public List<SearchResultIncome> getDetailSearchIncomeList(Date fromDate, Date toDate, Integer selectIncome,
			Integer fromAmount, Integer toAmount, String note) {
		// Specification生成
		DetailSearchIncomeSpecification<SearchResultIncome> spec = new DetailSearchIncomeSpecification<>();

		// 引数をもとに検索を行う
		List<SearchResultIncome> detailSearchIncomeInfo = detailSearchIncomeRepository.findAll(Specification
				.where(spec.dateGreaterThanLessThan(fromDate, toDate)).and(spec.incomeTypeMatch(selectIncome))
				.and(spec.amountGreaterThanLessThan(fromAmount, toAmount)).and(spec.noteLikeContains(note))
				.and(spec.deleteFlagCheck()));

		return detailSearchIncomeInfo;
	}

	// 支出検索
	public List<SearchResultExpenditure> getDetailSearchExpenditureList(Date fromDate, Date toDate,
			String selectExpenditure, Integer fromAmount, Integer toAmount, String note) {

		// Specification生成
		DetailSearchExpenditureSpecification<SearchResultExpenditure> spec = new DetailSearchExpenditureSpecification<SearchResultExpenditure>();

		// 引数をもとに検索を行う
		List<SearchResultExpenditure> detailSearchExpenditureInfo = detailSearchExpenditureRepository
				.findAll(Specification.where(spec.dateGreaterThanLessThan(fromDate, toDate))
						.and(spec.expenditureMatch(selectExpenditure))
						.and(spec.amountGreaterThanLessThan(fromAmount, toAmount)).and(spec.noteLikeContains(note))
						.and(spec.deleteFlagCheck()));

		return detailSearchExpenditureInfo;
	}

	// 検索
	@Autowired
	private SearchIncomeHouseholdRepository searchIncomeHouseholdRepository;

	@Autowired
	private SearchExpenditureHouseholdRepository searchExpenditureHouseholdRepository;

//収入データの通常検索
	public List<SearchResultIncome> getSearchIncomeInfoList(String balanceCode) {
		List<SearchResultIncome> searchIncomeInfoList = searchIncomeHouseholdRepository
				.findIncomeByBalanceCode(balanceCode);

		// 単体テスト用のNullPointerExceptionをthrowする処理
//		if(searchIncomeInfoList.isEmpty()) {
//			throw new NullPointerException("検索結果がありません");
//		}

		return searchIncomeInfoList;
	}

//支出データの通常検索
	public List<SearchResultExpenditure> getSearchExpenditureInfoList(String balanceCode) {
		List<SearchResultExpenditure> searchExpenditureInfoList = searchExpenditureHouseholdRepository
				.findExpenditureByBalanceCode(balanceCode);

//		System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
//		System.out.println(searchExpenditureInfoList);

		// 単体テスト用のNullPointerExceptionをthrowする処理
//		if(searchExpenditureInfoList.isEmpty()) {
//			throw new NullPointerException("検索結果がありません");
//		}

		return searchExpenditureInfoList;
	}

}
