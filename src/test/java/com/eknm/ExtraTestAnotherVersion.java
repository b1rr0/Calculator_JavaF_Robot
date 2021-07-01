package com.eknm;

import com.eknm.model.CalculationException;
import com.eknm.model.Calculator;
import com.eknm.model.Operator;
import org.junit.Test;

import java.math.BigDecimal;

/*
5,00000647e+9983
 1,e+9999
 1,e-14
 1,e-9999
 9,999999999999999e+9999

*/

public class ExtraTestAnotherVersion {

    @Test
    public void test() {

        BigDecimal bigDecimal;
        Calculator calculator = new Calculator(new BigDecimal(3));
        try {
            calculator.doNonStaticCalculation(new BigDecimal(2), Operator.MINUS);

            calculator.doNonStaticCalculation(Operator.SECOND_POWER);
            BigDecimal big2 = new BigDecimal(0);
            calculator.doNonStaticCalculation(big2, Operator.DIVISION);
            calculator.doNonStaticCalculation(Operator.SQRT);
            calculator.doNonStaticCalculation(BigDecimal.valueOf(7), Operator.MINUS);
            bigDecimal = calculator.doNonStaticCalculation(BigDecimal.valueOf(2), Operator.DIVISION);
            System.out.println("Result of operations = " + bigDecimal);
        } catch (CalculationException e) {
            System.out.println("Result of operations =  " + e.getMessage());
        }
        System.out.println("////////");

        try {
            calculator = new Calculator(new BigDecimal(10));
            BigDecimal res = calculator.doNonStaticCalculation(new BigDecimal(2), Operator.DIVISION);
            System.out.println("Result of operations = " + res);
        } catch (CalculationException e) {
            System.out.println("Result of operations =  " + e.getMessage());
        }

        System.out.println("////////");
        BigDecimal a = new BigDecimal(213);
        BigDecimal b = new BigDecimal(213);
        divisionAonB(a, b);

    }

    public void divisionAonB(BigDecimal a, BigDecimal b) {
        try {
            Calculator calculator = new Calculator(a);
            BigDecimal res = calculator.doNonStaticCalculation(b, Operator.DIVISION);
            System.out.println("Result of operations = " + res);
        } catch (ArithmeticException e) {
            System.out.println("Result of operations =  " + e.getMessage());
        }

    }


}
