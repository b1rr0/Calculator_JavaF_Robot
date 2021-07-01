package com.eknm.controller.history;

import com.eknm.model.Operator;
/**
 * Interface to describe  historyData  with Operator
 */
public class HistoryOperator implements HistoryData {
    /**
     * Data
     */
    private Operator operator;

    public Operator getData() {
        return operator;
    }

    public HistoryOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator.toString();
    }

    @Override
    public String getView(String string) {
        string = "(" + string + ")";
        switch (operator) {
            case SECOND_POWER:
                string = string + "^2";
                break;
            case CHANGE_SIGN:
                string = "-" + string;
                break;
            case SQRT:
                string = "√" + string;
                break;
            case INVERSE:
                string = "1/" + string;
                break;
            case DIVISION:
                string = string + "/";
                break;
            case MINUS:
                string = string + "-";
                break;
            case MULTIPLY:
                string = string + "*";
                break;
            case PLUS:
                string = string + "+";
                break;
            default:
        }
        return string;
    }
}
