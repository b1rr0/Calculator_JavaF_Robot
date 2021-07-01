package com.eknm.model;

/**
 * Class to calculate data
 */
public class CalculationException extends ArithmeticException {
    /**
     * Type of Exception
     */
    private TypeOfCalculationException type;

    public TypeOfCalculationException getType() {
        return type;
    }

    public CalculationException(String message, TypeOfCalculationException type) {

        super(message);
        this.type = type;
    }
}
