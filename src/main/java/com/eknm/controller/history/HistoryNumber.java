package com.eknm.controller.history;

import com.eknm.controller.Refactor;

import java.math.BigDecimal;
/**
 * Interface to describe  historyData  with BigDecimal
 */
public class HistoryNumber implements HistoryData {
    /**
     * Data
     */
    private BigDecimal data;

    public HistoryNumber(BigDecimal data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Refactor.refactor(data);
    }

    @Override
    public String getView(String string) {
        return string + Refactor.refactor(data);
    }
}
