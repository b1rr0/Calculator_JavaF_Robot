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

public class ExtraTest {

    @Test
    public void test() {

        BigDecimal bigDecimal;
        System.out.println("////////");
        try {
            bigDecimal = Calculator.calculate(new BigDecimal(3), new BigDecimal(2), Operator.MINUS);

            bigDecimal = Calculator.calculate(bigDecimal, Operator.SECOND_POWER);
            // BigDecimal big2 = new BigDecimal(4);
            BigDecimal big2 = new BigDecimal(0);
            //   Calculator.calculate(new BigDecimal(0),new BigDecimal(0),Operator.DIVISION);
            bigDecimal = Calculator.calculate(bigDecimal, big2, Operator.DIVISION);
            bigDecimal = Calculator.calculate(bigDecimal, Operator.SQRT);
            bigDecimal = Calculator.calculate(bigDecimal, BigDecimal.valueOf(7), Operator.MINUS);
            bigDecimal = Calculator.calculate(bigDecimal, BigDecimal.valueOf(2), Operator.DIVISION);
            System.out.println("Result of operations = " + bigDecimal);
        } catch (CalculationException e) {
            System.out.println("Result of operations =  " + e.getMessage());
        }

        System.out.println("////////");
        try {
            BigDecimal res = Calculator.calculate(new BigDecimal(10), new BigDecimal(2), Operator.DIVISION);
            System.out.println("Result of operations = " + res);
        } catch (CalculationException e) {
            System.out.println("Result of operations =  " + e.getMessage());
        }
        BigDecimal a = new BigDecimal(213);
        BigDecimal b = new BigDecimal(213);
        System.out.println("////////");
        divisionAonB(a, b);


    }

    public void divisionAonB(BigDecimal a, BigDecimal b) {

            BigDecimal res = Calculator.calculate(a, b, Operator.DIVISION);
            System.out.println("Result of operations = " + res);
    }

}
