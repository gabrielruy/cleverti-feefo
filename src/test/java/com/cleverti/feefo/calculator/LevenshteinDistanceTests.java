package com.cleverti.feefo.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.cleverti.feefo.calculator.LevenshteinDistance.calculate;

@SpringBootTest
class LevenshteinDistanceTests {

    private static final String EMPTY = "";
    private static final String JAVA = "Java";
    private static final String JAVAX = "Javax";
    private static final String ENGINEER = "Engineer";

    @Test
    void testEmptyToWordDistance() {
        Assertions.assertEquals(4,
                calculate(EMPTY, JAVA));
    }

    @Test
    void testWordToEmptyDistance() {
        Assertions.assertEquals(4,
                calculate(JAVA, EMPTY));
    }

    @Test
    void testSameWordDistance() {
        Assertions.assertEquals(0,
                calculate(JAVA, JAVA));
    }

    @Test
    void testNoNegativeDistance() {
        Assertions.assertTrue(calculate(JAVA, ENGINEER) > 0);
    }

    @Test
    void testOneOperationDistance() {
        Assertions.assertEquals(1,
                calculate(JAVA, JAVAX));
    }

    @Test
    void testSymmetryDistance() {
        Assertions.assertEquals(calculate(JAVA, ENGINEER),
                calculate(ENGINEER, JAVA));
    }
}
