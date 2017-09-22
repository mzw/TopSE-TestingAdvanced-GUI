package jp.mzw.topse.lecture.testing_advanced;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorViewTest {

	private FrameFixture window;

	@Before
	public void setup() {
		Calculator frame = GuiActionRunner.execute(new GuiQuery<Calculator>() {
			@Override
			protected Calculator executeInEDT() {
				return new Calculator();
			}

		});
		window = new FrameFixture(frame);
		window.show();
	}

	@After
	public void teardown() {
		window.cleanUp();
	}

	@Test
	public void testEmptyDisplay() {
		window.textBox("display").requireText("");
	}

	@Test
	public void testClickNumber0() {
		window.button("number0").click();
		window.textBox("display").requireText("");
	}

	@Test
	public void testClickNumber1() {
		window.button("number1").click();
		window.textBox("display").requireText("1");
	}

	@Test
	public void testClickOpeator() {
		window.button("add").click();
		window.textBox("display").requireText("");
	}

	@Test
	public void testDisplayResult() {
		window.button("number1").click();
		window.button("add").click();
		window.button("number2").click();
		window.button("eq").click();
		window.textBox("display").requireText("3");
	}

	@Test
	public void testSerialOperators() {
		window.button("number1").click();
		window.button("add").click();
		window.button("number2").click();
		window.button("add").click();
		window.button("eq").click();
		window.button("eq").click();
		window.button("eq").click();
		window.button("eq").click();
		window.textBox("display").requireText("3");
	}

	@Test
	public void testSerialCalc() {
		window.button("number1").click();
		window.button("add").click();
		window.button("number2").click();
		window.button("eq").click();
		window.textBox("display").requireText("3");

		window.button("ac").click();

		window.button("number3").click();
		window.button("add").click();
		window.button("number5").click();
		window.button("eq").click();
		window.textBox("display").requireText("8");
	}

	@Test
	public void testSerialCalcWithoutAC() {
		window.button("number1").click();
		window.button("add").click();
		window.button("number2").click();
		window.button("eq").click();
		window.textBox("display").requireText("3");

		window.button("number3").click();
		window.button("add").click();
		window.button("number5").click();
		window.button("eq").click();
		window.textBox("display").requireText("8");
	}

	@Test
	public void testAc() {
		window.button("number1").click();
		window.button("add").click();

		window.button("ac").click();

		window.button("number3").click();
		window.button("add").click();
		window.button("number5").click();
		window.button("eq").click();
		window.textBox("display").requireText("8");
	}

	@Test
	public void testChangeOperator() {
		window.button("number2").click();
		window.button("add").click();
		window.button("mul").click();
		window.button("number3").click();
		window.button("eq").click();
		window.textBox("display").requireText("6");
	}
}
