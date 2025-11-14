package com.example.householdaccount.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.householdaccount.form.SearchBalanceInfo;
import com.example.householdaccount.repository.mybatis.ExpenditureRepository;
import com.example.householdaccount.repository.mybatis.GetExpenditureItemsRepository;
import com.example.householdaccount.repository.mybatis.IncomeRepository;
import com.example.householdaccount.repository.mybatis.SearchBalanceExpenditureRepository;
import com.example.householdaccount.repository.mybatis.SearchBalanceIncomeInfoRepository;
import com.example.householdaccount.repository.mybatis.SearchExpenditureHouseholdRepository;
import com.example.householdaccount.repository.mybatis.SearchIncomeHouseholdRepository;

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
	public Income createIncomeInfo(IncomeForm incomeCommand) {

		// 入力された日付をyyyy/mm/ddにしたい
//		Date date = incomeCommand.getDate();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		String strDate = sdf.format(date);
//		
//		System.out.println("mmmmmmmmmm");
//		System.out.println(strDate);

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
	}

	// 支出登録
	public Expenditure createExpenditureInfo(ExpenditureForm expenditureCommand) {

//		Date date = expenditureCommand.getDate();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		String strDate = sdf.format(date);

		String expenditureItemName = expenditureCommand.getSelectExpenditure();
		ExpenditureItems expenditureItems = expenditureItemRepository
				.findByExpenditureExpenseItemName(expenditureItemName);
		ExpenditureExpenseItemCodeVO expenditureExpenseItemCode = expenditureItems.getExpenditure_expense_item_code();

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
	}
	
	//支出データ取得(編集)
	@Autowired
	SearchBalanceIncomeInfoRepository serchBalanceIncomeRepository;
	@Autowired
	SearchBalanceExpenditureRepository serchBalanceExpenditureRepository;
	
		//収入検索
		public SearchBalanceIncomeInfo getSearchIncomeInfo(String balanceNo){
			
			SearchBalanceIncomeInfo serchBalanceIncomeInfo = serchBalanceIncomeRepository.findByBalanceNo(balanceNo);
			return serchBalanceIncomeInfo;
		}
		
		//支出検索
		public SearchBalanceExpenditureInfo getSearchExpenditureInfo(String balanceNo) {
			SearchBalanceExpenditureInfo serchBalanceExpenditureInfo = serchBalanceExpenditureRepository.findByBalanceNo(balanceNo);
			return serchBalanceExpenditureInfo;
		}
	
	//編集データ登録
		//収入
	public Income incomeEdit(IncomeEditForm incomeEditForm) {
		
		//バージョン取得して、＋１する
		String incomeNo = incomeEditForm.getBalanceNo(); 
		Integer incomeVersion = incomeRepository.findByIncomeNo(incomeNo);
		incomeVersion = incomeVersion+1;
		
		Income income = new Income(incomeEditForm,incomeVersion);
		incomeRepository.save(income);
		return income;
	}
	
//	支出
	public Expenditure expenditureEdit(ExpenditureEditForm expenditureEditForm) {
		//アイテムコード取得
		String expenditureItemName = expenditureEditForm.getExpenditureExpenseItemName();
		ExpenditureItems expenditureItems = expenditureItemRepository
				.findByExpenditureExpenseItemName(expenditureItemName);
		ExpenditureExpenseItemCodeVO expenditureExpenseItemCode = expenditureItems.getExpenditure_expense_item_code();
		
		//バージョン取得して、＋１する
		String expenditureNo = expenditureEditForm.getBalanceNo(); 
		Integer expenditureVersion = expenditureRepository.findByExpenditureNo(expenditureNo);
		expenditureVersion = expenditureVersion+1;
		
		Expenditure expenditure = new Expenditure(expenditureEditForm,expenditureExpenseItemCode,expenditureVersion);
		expenditureRepository.save(expenditure);
		return expenditure;
	}

	//検索
	@Autowired
	private SearchIncomeHouseholdRepository searchIncomeHouseholdRepository;

	@Autowired
	private SearchExpenditureHouseholdRepository searchExpenditureHouseholdRepository;

//収入データの通常検索
	public List<SearchResultIncome> getSearchIncomeInfoList(String balanceCode) {
		List<SearchResultIncome> searchIncomeInfoList = searchIncomeHouseholdRepository.findIncomeByBalanceCode(balanceCode);

		// 単体テスト用のNullPointerExceptionをthrowする処理
//		if(searchIncomeInfoList.isEmpty()) {
//			throw new NullPointerException("検索結果がありません");
//		}

		return searchIncomeInfoList;
	}

//支出データの通常検索
	public List<SearchResultExpenditure> getSearchExpenditureInfoList(String balanceCode) {
		List<SearchResultExpenditure> searchExpenditureInfoList = searchExpenditureHouseholdRepository.findExpenditureByBalanceCode(balanceCode);

//		System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
//		System.out.println(searchExpenditureInfoList);

		// 単体テスト用のNullPointerExceptionをthrowする処理
//		if(searchExpenditureInfoList.isEmpty()) {
//			throw new NullPointerException("検索結果がありません");
//		}

		return searchExpenditureInfoList;
	}

}
