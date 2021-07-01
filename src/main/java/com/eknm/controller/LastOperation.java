package com.eknm.controller;

import com.eknm.model.Operator;

import java.math.BigDecimal;

/**
 * Class to store  previous operation
 */
public class LastOperation {
    /**
     * Number
     */
    private BigDecimal number;
    /**
     * Operator
     */
    private Operator sign;

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public Operator getSign() {
        return sign;
    }

    public void setSign(Operator sign) {
        this.sign = sign;
    }

    public boolean isEmpty() {
        return number == null && sign == null;
    }
}
