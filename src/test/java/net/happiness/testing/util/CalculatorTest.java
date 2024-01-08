package net.happiness.testing.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void sum() {
        int sum = calculator.sum(10, 15);
        Assertions.assertEquals(25, sum);
    }

    @Test
    void evenOrOdd_evenNumber() {
        boolean even = calculator.evenOrOdd(10);
        Assertions.assertTrue(even);
    }

    @Test
    void evenOrOdd_oddNumber() {
        boolean odd = calculator.evenOrOdd(15);
        Assertions.assertFalse(odd);
    }

    @Test
    void divide_byZero() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

}
