package com.example.mobile_client;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationUtilsTest {

    @Test
    public void supplierNameCheck() throws Exception {

        String input = "Kasun";
        String output;
        boolean expected = true;

        ValidationUtils validationUtils = new ValidationUtils();
        output = validationUtils.supplierNameCheck(input);

    }

}