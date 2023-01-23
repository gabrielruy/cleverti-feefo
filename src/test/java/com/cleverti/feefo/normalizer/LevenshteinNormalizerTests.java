package com.cleverti.feefo.normalizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LevenshteinNormalizerTests {

    private static final String ARCHITECT = "Architect";
    private static final String SOFTWARE_ENGINEER = "Software Engineer";
    private static final String QUANTITY_SURVEYOR = "Quantity Surveyor";
    private static final String ACCOUNTANT = "Accountant";

    @Autowired
    private LevenshteinNormalizer normalizer;

    @Test
    void testSeniorArchitectNormalize(){
        Assertions.assertEquals(ARCHITECT,
                normalizer.normalize("Senior Architect"));
    }

    @Test
    void testArchitectPartnerNormalize(){
        Assertions.assertEquals(ARCHITECT,
                normalizer.normalize("Architect Partner"));
    }

    @Test
    void testJavaEngineerNormalize(){
        Assertions.assertEquals(SOFTWARE_ENGINEER,
                normalizer.normalize("Java Engineer"));
    }

    @Test
    void testCEngineerNormalize(){
        Assertions.assertEquals(SOFTWARE_ENGINEER,
                normalizer.normalize("C# Engineer"));
    }

    @Test
    void testQuantitySurveyorNormalize(){
        Assertions.assertEquals(QUANTITY_SURVEYOR,
                normalizer.normalize("Quantity surveyor"));
    }

    @Test
    void testQuantitySurveyorAnalystNormalize(){
        Assertions.assertEquals(QUANTITY_SURVEYOR,
                normalizer.normalize("Quantity surveyor Analyst"));
    }

    @Test
    void testAccountantNormalize(){
        Assertions.assertEquals(ACCOUNTANT,
                normalizer.normalize("Accountant"));
    }

    @Test
    void testChiefAccountantNormalize(){
        Assertions.assertEquals(ACCOUNTANT,
                normalizer.normalize("Chief Accountant"));
    }

    @Test
    void testNoMatchNormalize(){
        Assertions.assertEquals("BZL",
                normalizer.normalize("BZL"));
    }
}
