package com.example.householdaccount.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class TamesiTest {
	private Tamesi tamesi;
	
	@BeforeEach
	void setUp() {
		tamesi = new Tamesi();
	}
	
	@Test
	void testDivide() {
		assertEquals(2,tamesi.divide(4, 2));
	}
}
