package jp.mzw.topse.lecture.testing_advanced;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloWorldFrameTest {

	private FrameFixture window;

	@Before
	public void setup() {
		HelloWorldFrame frame = GuiActionRunner.execute(new GuiQuery<HelloWorldFrame>() {
			@Override
			protected HelloWorldFrame executeInEDT() {
				return new HelloWorldFrame();
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
	public void testEmptyText() {
		window.textBox("textField").requireText("");
	}

	@Test
	public void testClick() {
		window.button("button").click();
		window.textBox("textField").requireText("Hello World!");
	}

	@Test
	public void testClickTwice() {
		window.button("button").click();
		window.button("button").click();
		window.textBox("textField").requireText("Hello World!Hello World!");
	}

	@Test
	public void testEnterTextAndClick() {
		window.textBox("textField").enterText("aaa");
		window.button("button").click();
		window.textBox("textField").requireText("aaaHello World!");
	}

}
