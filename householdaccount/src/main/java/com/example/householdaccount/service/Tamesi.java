package com.example.householdaccount.service;

public class Tamesi {

	//単体テスト動くかテスト
	public double divide(int dividend, int divisor) throws ArithmeticException {
		try {
			double quotient = dividend / divisor;
			return quotient;
		} catch (ArithmeticException e) {
			System.out.println("ゼロ除算です");
			throw e;
		}
	}
}
