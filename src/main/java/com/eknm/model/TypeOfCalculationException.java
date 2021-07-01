package com.eknm.model;

/**
 * Class to distinguish  types of CalculatingExceptions
 */
public enum TypeOfCalculationException {
    /**
     * Overflow Exception
     */
    OVERFLOW,
    /**
     * Data zero division Exception
     */
    DIVISION_BY_ZERO,
    /**
     * Data sqrt from negative number Exception
     */
    SQRT_OF_NEGATIVE,
    /**
     * Res of division zero by zero
     */
    UNCERTAINTY;

}
