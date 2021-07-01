package com.eknm.controller.history;


/**
 * Interface to describe  historyData  with String
 */
public class HistoryString implements HistoryData {
    /**
     *  Data
     */
    private String data;


    public HistoryString(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public String getView(String string) {
        return string + data;
    }
}
