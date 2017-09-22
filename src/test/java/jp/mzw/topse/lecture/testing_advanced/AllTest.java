package jp.mzw.topse.lecture.testing_advanced;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculatorViewTest.class, CalculatorLogicTest.class })
public class AllTest {
	// NOP
}
