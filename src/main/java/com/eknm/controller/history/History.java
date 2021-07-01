package com.eknm.controller.history;

import com.eknm.model.Operator;
import java.util.ArrayDeque;

/**
 * Class to menage HistoryData
 */
public class History {
    /**
     * Deque with HistoryData
     */
    private ArrayDeque<HistoryData> historyDataArrayDeque = new ArrayDeque<>();

    /**
     * Add HistoryData
     */
    public void add(HistoryData historyData) {
        historyDataArrayDeque.addLast(historyData);
    }

    /**
     * Remove top HistoryData
     * * @param count of objects to remove
     */
    public void remove(int count) {
        while (count-- > 0) {
            historyDataArrayDeque.removeLast();
        }
    }


    /**
     * Get last operator from Deque ,
     *
     * @return last Operator or null if last HistoryData not HistoryOperator type
     */
    public Operator lastOperator() {
        if ((historyDataArrayDeque.peekLast() instanceof HistoryOperator)) {
            return ((HistoryOperator) historyDataArrayDeque.peekLast()).getData();
        }
        return null;
    }

    /**
     *  get String View of Deque
     */
    public String getView() {
        String res = "";
        for (HistoryData historyData : historyDataArrayDeque) {
            res = historyData.getView(res);
        }
        return res;
    }

    public boolean isEmpty() {
        return historyDataArrayDeque.isEmpty();
    }

    public void clear() {
        historyDataArrayDeque.clear();
    }

}
