package com.eknm.model;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class to calculate data
 */
public class Calculator {
    private Calculator() {
    }

    /**
     * Max count of numbers after comma  that  calculator use in calculation
     */
    public static final int ACCURACY = 20_111;

    /**
     * Number 100
     */
    public static final BigDecimal N_100 = new BigDecimal(100);
    /**
     * Round mode for all my calculating
     */
    public static final RoundingMode ROUND_MODE = RoundingMode.HALF_UP;
    /**
     * Max value of calculating,the answer more - overflow
     */
    public static final BigDecimal MAX_VALUE = new BigDecimal("9.9999999999999995").multiply(new BigDecimal("10").pow(9999));
    /**
     * Max accuracy value of calculating,the answer nearer to zero - overflow
     */
    public static final BigDecimal MIN_VALUE = new BigDecimal("0.1").pow(99_99);
    /**
     * Number - border of rounding to   overflow
     */
    public static final BigDecimal EXTRA_MIN_VALUE = MIN_VALUE.multiply(new BigDecimal("0.9999999999999999"));

    /**
     * Data for nonstatic  calculation
     */
    private BigDecimal data;

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }

    public Calculator(BigDecimal data) {
        this.data = data;
    }

    /**
     * /**
     * Method for calculating  with data inside and another parameter with save res into inside Data
     *
     * @param bigDecimal   second param
     * @param operator sign of operation between numbers
     * @return result of this operation
     * @throws CalculationException if result of operation is invalid
     */
    public BigDecimal doNonStaticCalculation(BigDecimal bigDecimal, Operator operator) {
        data = calculate(data, bigDecimal, operator);
        return data;
    }

    /**
     * /**
     * Method for calculating  with data inside and operator with save res into inside Data
     *
     * @param operator sign of operation
     * @return result of this operation
     * @throws CalculationException if result of operation is invalid
     */
    public BigDecimal doNonStaticCalculation(Operator operator) {
        data = calculate(data, operator);
        return data;
    }

    /**
     * /**
     * Method for calculating  result
     *
     * @param big1     first number
     * @param big2     second number
     * @param operator sign of operation between numbers
     * @return result of this operation
     * @throws CalculationException if result of operation is invalid
     */


    public static BigDecimal calculate(BigDecimal big1, BigDecimal big2, Operator operator) throws CalculationException {

        BigDecimal res;
        switch (operator) {
            case PERCENT:
                res = percent(big1, big2);
                break;
            case DIVISION:
                res = division(big1, big2);
                break;
            case MULTIPLY:
                res = big1.multiply(big2).setScale(ACCURACY, ROUND_MODE);
                break;
            case MINUS:
                res = big1.subtract(big2);
                break;
            case PLUS:
                res = big1.add(big2);
                break;
            default:
                throw new ArithmeticException("unknown operator for this operation");
        }

        return checkForOverflow(res);
    }

    private static BigDecimal division(BigDecimal big1, BigDecimal big2) {
        if (big2.compareTo(BigDecimal.ZERO) == 0) {
            if (big1.compareTo(BigDecimal.ZERO) == 0) {
                throw new CalculationException("computational uncertainty", TypeOfCalculationException.UNCERTAINTY);
            }
            throw new CalculationException("division by zero", TypeOfCalculationException.DIVISION_BY_ZERO);
        }
        return big1.divide(big2, ACCURACY, ROUND_MODE);
    }

    /**
     * Method for calculating  result
     *
     * @param big1     number
     * @param operator sign of operation
     * @return result of this operation
     * @throws CalculationException if result of operation is invalid
     */
    public static BigDecimal calculate(BigDecimal big1, Operator operator) throws CalculationException {
        BigDecimal res;
        switch (operator) {
            case SQRT:
                if (big1.compareTo(BigDecimal.ZERO) < 0) {
                    throw new CalculationException("square root of a negative number", TypeOfCalculationException.SQRT_OF_NEGATIVE);
                }
                res = big1.sqrt(new java.math.MathContext(ACCURACY, ROUND_MODE));
                break;
            case INVERSE:
                res = inverse(big1);
                break;
            case CHANGE_SIGN:
                res = big1.multiply(BigDecimal.valueOf(-1));
                break;
            case SECOND_POWER:
                res = big1.pow(2).setScale(ACCURACY, ROUND_MODE);
                break;
            default:
                throw new ArithmeticException("unknown operator for this operation");
        }
        return checkForOverflow(res);
    }

    /**
     * Method for inverse number
     *
     * @param big1 number
     * @return result of this operation
     * @throws CalculationException if result of operation is invalid
     */
    public static BigDecimal inverse(BigDecimal big1) throws CalculationException {
        if ((big1.compareTo(BigDecimal.ZERO) == 0)) {
            throw
                    new CalculationException("division by zero", TypeOfCalculationException.DIVISION_BY_ZERO);
        } else {
            BigDecimal b = BigDecimal.ONE;
            return checkForOverflow(b.divide(big1, ACCURACY, ROUND_MODE));
        }
    }

    /**
     * Method for get percent
     *
     * @param big1 number
     * @param big2 number of percents that we wanna take
     * @return result of this operation
     * @throws CalculationException if result of operation is invalid
     */
    public static BigDecimal percent(BigDecimal big1, BigDecimal big2) throws CalculationException {
        BigDecimal tmp = big1.multiply(big2);
        tmp = tmp.divide(N_100);
        return checkForOverflow(tmp);

    }

    /**
     * Method for get percent
     *
     * @param big number
     * @return result of this operation
     * @throws CalculationException if result of operation is invalid
     */
    public static BigDecimal percentWithPower(BigDecimal big) throws CalculationException {
        big = (Calculator.calculate(big, BigDecimal.ONE, Operator.PERCENT));
        big = (Calculator.calculate(big, Operator.SECOND_POWER));
        big = Calculator.calculate(big, N_100, Operator.MULTIPLY);
        return checkForOverflow(big);

    }


    /**
     * Method to check data for overflow
     *
     * @param big number
     * @return BigDecimal if all is ok, or BigDecimal value of error
     * @throws CalculationException if result of operation is invalid
     */
    public static BigDecimal checkForOverflow(BigDecimal big) throws CalculationException {
        if (big.compareTo(BigDecimal.ZERO) == 0) {
            return big;
        }
        BigDecimal absoluteBig = big.abs();
        if (absoluteBig.compareTo(MIN_VALUE) <= 0 && absoluteBig.compareTo(EXTRA_MIN_VALUE) > 0) {
            return MIN_VALUE;
        }
        if (big.abs().compareTo(MAX_VALUE) >= 0 || absoluteBig.compareTo(MIN_VALUE) < 0) {
            throw new CalculationException("overflow of calculating", TypeOfCalculationException.OVERFLOW);
        }
        return big;
    }
}
