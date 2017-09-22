package jp.mzw.topse.lecture.testing_advanced;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorLogicTest {

	private Calculator calculator;

	@Before
	public void setup() {
		calculator = new Calculator();
	}

	@After
	public void teardown() {
		calculator.dispose();
	}

	@Test
	public void testAdd() {
		int actual = calculator.calculate(1, 2, Calculator.Operator.Add);
		assertEquals(3, actual);
	}

	@Test
	public void testSub() {
		int actual = calculator.calculate(3, 2, Calculator.Operator.Sub);
		assertEquals(1, actual);
	}

	@Test
	public void testMul() {
		int actual = calculator.calculate(2, 4, Calculator.Operator.Mul);
		assertEquals(8, actual);
	}

	@Test
	public void testDiv() {
		int actual = calculator.calculate(8, 4, Calculator.Operator.Div);
		assertEquals(2, actual);
	}

	@Test(expected = ArithmeticException.class)
	public void testException() {
		calculator.calculate(1, 0, Calculator.Operator.Div);
	}
}
