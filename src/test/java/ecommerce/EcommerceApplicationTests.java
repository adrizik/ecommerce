package ecommerce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;





class EcommerceApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		//given
		int numberOne = 20;
		int numberTwo = 30;

		//when
		int actual = underTest.add(numberOne, numberTwo);

		//then
		int expected = 50;
		Assertions.assertEquals(expected, actual);
	}

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}
}
