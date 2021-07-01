package com.eknm.controller;

import com.eknm.controller.history.*;
import com.eknm.model.*;
import com.eknm.view.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;


/**
 * Class that  manages calculator
 */
public class Controller {

    /**
     * Instance for Singleton
     */
    private static Controller instance;

    public Controller() {
        instance = this;
    }

    /**
     * Method for Singleton
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Data for overflow exception
     */
    public static final String DATA_OVERFLOW = "Переполнение";

    /**
     * Data for zero division exception
     */
    public static final String DATA_ZERO_DIVISION = "Деление на ноль невозможно";

    /**
     * Data for  incorrect data  exception
     */
    public static final String DATA_SQRT = "Введены неверные данные";

    /**
     * Data for  uncertainty   exception
     */
    public static final String DATA_UNCERTAINTY = "Результат не определен";

    /**
     * Map with rus EQUIVALENTS of exceptions
     */
    public static final Map<TypeOfCalculationException, String> MAP_OF_EXCEPTION_EQUIVALENTS = initMap();

    /**
     * Zero
     */
    public static final String ZERO = "0";


    /**
     * Max count symbols in label
     */
    public static final int MAX_COUNT = 16;

    /**
     * Is last pressed Button was equals
     */
    private boolean lastPressEqually = false;

    /**
     * First number of calculations
     */
    private BigDecimal firstData;

    /**
     * Second number of calculations
     */
    private BigDecimal secondData;

    /**
     * Data stored in memory
     */
    private BigDecimal memData = null;

    /**
     * Last sign  to repeat
     */
    private Operator lastSign;

    /**
     * Last pressed sign
     */
    private Operator lastPressedSign;

    /**
     * Flag  defining state
     */
    private Flag flag = Flag.DEFAULT;

    /**
     * Storage last sign + last number
     */
    private LastOperation lastOperation = new LastOperation();

    private boolean lastChangePrevious = false;
    /**
     * AnchorPane for buttons
     */
    @FXML
    private AnchorPane anchorPane;

    /**
     * AnchorPane for menu
     */
    @FXML
    private AnchorPane anchorPaneMenu;

    /**
     * AnchorPane for menu Fon
     */
    @FXML
    private AnchorPane anchorPaneMenuFon;

    /**
     * GridPane for buttons with out memory
     */
    @FXML
    private GridPane gridPaneWithData;

    /**
     * GridPane for buttons with  memory
     */
    @FXML
    private GridPane gridPaneWithM;

    /**
     * Label for input data
     */
    @FXML
    private Label field;

    /**
     * Scroll pane list of different type of calculators
     */
    @FXML
    private ScrollPane scrollPane;

    /**
     * Label  for history of calculating
     */
    @FXML
    private Label fieldPrevious;

    /**
     * Button  memory clean
     */
    @FXML
    private Button buttonMC;

    /**
     * Button  memory read
     */
    @FXML
    private Button buttonMR;

    private History history = new History();

    public ScrollPane getScrollPane() {
        return scrollPane;
    }


    /**
     * Method for  initialise data
     */
    @FXML
    void initialize() {
        clean();
        scale();
        ButtonsMemoryAnim.buttonDisable(buttonMC);
        ButtonsMemoryAnim.buttonDisable(buttonMR);
    }

    /**
     * Click handler for square  button
     */
    @FXML
    void buttonPowSquareOnClick(MouseEvent event) {
        calcWith1Param(Operator.SECOND_POWER);
    }


    /**
     * Click handler for C button
     */
    @FXML
    void buttonCOnClick(MouseEvent event) {
        Scaling.fontSizeScale(gridPaneWithData, gridPaneWithM, anchorPane, field, ZERO);

        if (lastSign != null && fieldPrevious.getText().length() > 1) {
            fieldPrevious.setText(fieldPrevious.getText().substring(0, fieldPrevious.getText().length() - 1));
        }

        if (firstData == null) {
            clean();
        }

        if (firstData != null && lastSign != null) {
            lastSign = null;
        } else {
            fieldPrevious.setText(history.getView());
            firstData = null;
        }
        flag = Flag.CALCULATING;
    }

    /**
     * Click handler for CE button
     */
    @FXML
    void buttonCEOnClick(MouseEvent event) {
        clean();
    }

    /**
     * Click handler for "." button
     */
    @FXML
    void buttonDotOnClick(MouseEvent event) {
        if (!field.getText().contains(Data.COMMA.getValue()) && !field.getText().isEmpty())
            addData(Data.COMMA);
    }

    /**
     * Click handler for dell button
     */
    @FXML
    void buttonDellOnCLick(MouseEvent event) {
        if (flag == Flag.OVERFLOW) {
            clean();
            return;
        }
        String txtForScale = "";
        String fieldText = field.getText();

        if (flag == Flag.ANSWER) {
            fieldPrevious.setText(history.getView());
            history.clear();
            firstData = secondData;
            secondData = null;
        } else if (fieldText.length() == 1) {
            txtForScale = ZERO;
        } else {
            txtForScale = fieldText.substring(0, fieldText.length() - 1);
        }
        fontScale(txtForScale);
        initFirsData();

    }

    /**
     * Click handler for division button
     */
    @FXML
    void buttonDivisionOnClick(MouseEvent event) {
        buttonSignOnClick(Operator.DIVISION);
    }

    /**
     * Click handler for equally button
     */
    @FXML
    void buttonEquallyOnClick(MouseEvent event) {
        history.clear();
        if (flag == Flag.OVERFLOW) {
            clean();
            return;
        }

        lastPressEqually = true;
        if (lastPressedSign != null) {
            secondData = null;
        }

        if (lastSign != null && firstData != null && secondData != null) {
            calcWith2Param();
            lastPressedSign = null;
        } else if (firstData != null && lastPressedSign != null && lastPressedSign != Operator.PERCENT) {
            secondData = firstData;
            lastSign = lastPressedSign;
            calcWith2Param();
            lastPressedSign = null;
        } else if (!lastOperation.isEmpty() && firstData != null) {
            percentLogWithEqually();
        } else if (firstData != null && firstData.compareTo(BigDecimal.ZERO) == 0 && lastSign != null) {
            secondData = firstData;
            initFirsData();
            buttonEquallyOnClick(null);
        }
    }

    private void percentLogWithEqually() {
        if (lastPressedSign == Operator.PERCENT) {
            BigDecimal tmp = firstData;
            firstData = lastOperation.getNumber();
            lastOperation.setNumber(tmp);
        }
        if (lastOperation.getNumber() != null) {
            initFirstDataAsResCalculation(firstData, lastOperation.getNumber(), lastOperation.getSign());
        }
        if (firstData != null) {
            fontScale(Refactor.refactor(firstData));
        }
        lastPressedSign = null;
    }

    /**
     * Click handler for minus button
     */
    @FXML
    void buttonMinusOnClick(MouseEvent event) {
        buttonSignOnClick(Operator.MINUS);
    }

    /**
     * Click handler for multiply button
     */
    @FXML
    void buttonMultiplyOnClick(MouseEvent event) {
        buttonSignOnClick(Operator.MULTIPLY);
    }

    /**
     * Click handler for division by X  button
     */
    @FXML
    void buttonOneDivisionByXOnClick(MouseEvent event) {
        calcWith1Param(Operator.INVERSE);
    }

    /**
     * Click handler for percent   button
     */
    @FXML
    void buttonPercentOnClick(MouseEvent event) {
        lastPressedSign = Operator.PERCENT;
        if (flag == Flag.OVERFLOW || ((flag == Flag.DEFAULT) && lastSign == null)) {
            clean();
            return;
        }

        if (firstData == null) {
            initFirsData();
        }

        if (flag != Flag.OVERFLOW) {
            if (secondData != null) {
                percentLogic();
            } else {
                percentLogicWithOne();
            }
            if (flag != Flag.OVERFLOW) {
                flag = Flag.CALCULATING;
                fontScale(Refactor.refactor(firstData));
            }
        }
    }

    /**
     * Click handler for  change sign  button
     */
    @FXML
    void buttonPlusMinusOnClick(MouseEvent event) {
        if (flag == Flag.OVERFLOW) {
            clean();
            return;
        }

        if (firstData.compareTo(BigDecimal.ZERO) != 0) {
            initFirstDataAsResCalculation(firstData, null, Operator.CHANGE_SIGN);
            fontScale(Refactor.refactor(firstData));
        }
    }

    /**
     * Click handler for  plus sign  button
     */
    @FXML
    void buttonPlusOnClick(MouseEvent event) {
        buttonSignOnClick(Operator.PLUS);
    }

    /**
     * Click handler for  sqrt sign  button
     */
    @FXML
    void buttonSqrtOnClick(MouseEvent event) {
        calcWith1Param(Operator.SQRT);
    }

    /**
     * Click handler for menu  button
     */
    @FXML
    void buttonMenuOnClick(MouseEvent event) {
        if (anchorPaneMenuFon.isVisible()) {
            MenuAnim.animClose(anchorPaneMenu, anchorPaneMenuFon);
        } else {
            MenuAnim.animOpen(anchorPaneMenu, anchorPaneMenuFon);
        }
    }

    /**
     * Click handler for anchor pane menu  button
     */
    @FXML
    void buttonDefOnClick(MouseEvent event) {
        if (anchorPaneMenuFon.isVisible()) {
            MenuAnim.animClose(anchorPaneMenu, anchorPaneMenuFon);
        } else {
            MenuAnim.animOpen(anchorPaneMenu, anchorPaneMenuFon);
        }
    }

    /**
     * Click handler for MC  button
     */
    @FXML
    void buttonMCOnClick(MouseEvent event) {
        memData = null;
        ButtonsMemoryAnim.buttonDisable(buttonMC);
        ButtonsMemoryAnim.buttonDisable(buttonMR);
    }

    /**
     * Click handler for plus  button
     */
    @FXML
    void buttonMPlusOnClick(MouseEvent event) {
        mPlusMinusOnClick(Operator.PLUS);
    }

    /**
     * Click handler for Memory minus   button
     */
    @FXML
    void buttonMMinusOnClick(MouseEvent event) {
        mPlusMinusOnClick(Operator.MINUS);
    }

    /**
     * Click handler for Memory plus   button
     */
    void mPlusMinusOnClick(Operator operation) {
        if (firstData != null) {
            if (memData == null) {
                memData = BigDecimal.ZERO;
                ButtonsMemoryAnim.buttonAvailable(buttonMR);
                ButtonsMemoryAnim.buttonAvailable(buttonMC);
            }
            try {
                memData = Calculator.calculate(memData, firstData, operation);
            } catch (CalculationException e) {
                setExceptionToField(e);
            }
        }
    }

    /**
     * Click handler for Memory save   button
     */
    @FXML
    void buttonMSOnClick(MouseEvent event) {
        if (firstData != null) {
            memData = firstData;
            flag = Flag.ANSWER;
            ButtonsMemoryAnim.buttonAvailable(buttonMR);
            ButtonsMemoryAnim.buttonAvailable(buttonMC);
        }
    }

    /**
     * Click handler for Memory reset   button
     */
    @FXML
    void buttonMROnClick(MouseEvent event) {

        if (memData == null) {
            return;
        }

        fontScale(Refactor.refactor(memData));
        if (flag == Flag.CALCULATING) {
            secondData = firstData;
            flag = Flag.DEFAULT;
        }

        if (flag == Flag.ANSWER) {
            clean();
        }

        firstData = memData;
        flag = Flag.ANSWER;
        lastPressedSign = null;
    }

    /**
     * Click handler for "0"   button
     */
    @FXML
    void buttonZeroOnClick(MouseEvent event) {
        addData(Data.ZERO);
    }

    /**
     * Click handler for "1"   button
     */
    @FXML
    void buttonOneOnClick(MouseEvent event) {
        addData(Data.ONE);
    }

    /**
     * Click handler for "2"   button
     */
    @FXML
    void buttonTwoOnClick(MouseEvent event) {
        addData(Data.TWO);
    }

    /**
     * Click handler for "3"   button
     */
    @FXML
    void buttonThreeOnClick(MouseEvent event) {
        addData(Data.THREE);
    }

    /**
     * Click handler for "4"   button
     */
    @FXML
    void buttonFourOnClick(MouseEvent event) {
        addData(Data.FOUR);
    }

    /**
     * Click handler for "5"   button
     */
    @FXML
    void buttonFiveOnClick(MouseEvent event) {
        addData(Data.FIVE);
    }

    /**
     * Click handler for "6"   button
     */
    @FXML
    void buttonSixOnClick(MouseEvent event) {
        addData(Data.SIX);
    }

    /**
     * Click handler for "7"   button
     */
    @FXML
    void buttonSevenOnClick(MouseEvent event) {
        addData(Data.SEVEN);
    }

    /**
     * Click handler for "8"   button
     */
    @FXML
    void buttonEightOnClick(MouseEvent event) {
        addData(Data.EIGHT);
    }

    /**
     * Click handler for "9"   button
     */
    @FXML
    void buttonNineOnClick(MouseEvent event) {
        addData(Data.NINE);
    }

    /**
     * Scroll handler finish scroll
     */
    @FXML
    void onScrollFinished(ScrollEvent event) {
        SliderAnim.scrollFinish(scrollPane);
    }

    /**
     * Scroll handler start scroll
     */
    @FXML
    void onScrollStarted(ScrollEvent event) {
        SliderAnim.scrollStart(scrollPane);
    }

    /**
     * Click handler for fon needed  to close menu
     */
    @FXML
    void onClickAnchorPaneMenuFon(MouseEvent event) {
        MenuAnim.animClose(anchorPaneMenu, anchorPaneMenuFon);
    }

    /**
     * Method to scale size of cell of gridPane with data, gridPane with memory , anchor pane and field
     */
    public void scale() {
        Scaling.scaleData(gridPaneWithData, gridPaneWithM, anchorPane, field);
    }

    /**
     * Method to scale font of gridPane with data, gridPane with memory , anchor pane and field
     */
    public void fontScale(String tmp) {
        Scaling.fontSizeScale(gridPaneWithData, gridPaneWithM, anchorPane, field, tmp);
    }

    private void addData(Data data) {
        lastPressedSign = null;
        lastChangePrevious = false;
        if (flag == Flag.OVERFLOW) {
            clean();
            return;
        }
        String txtToField = "";
        if (flag == Flag.CALCULATING) {
            secondData = firstData;
            txtToField = ZERO;
            firstData = BigDecimal.ZERO;
            flag = Flag.DEFAULT;
        }

        if (flag == Flag.ANSWER) {
            clean();
        }

        int countCharactersAllowed = 0;
        if (firstData.abs().compareTo(BigDecimal.ONE) < 0) {
            countCharactersAllowed++;
        }
        txtToField = field.getText();
        if (hasDefaultComma(dataWithOutExtraSymbols(firstData), countCharactersAllowed, data)) {
            if (firstData.equals(BigDecimal.ZERO) && !data.equals(Data.COMMA)) {
                txtToField = "";
                firstData = null;
            }

            txtToField += data.getValue();
        }
        fontScale(txtToField);
        initFirsData();
    }

    private String dataWithOutExtraSymbols(BigDecimal data) {
        return Refactor.refactor(data).replaceAll(".", "").replaceAll("-", "")
                .replaceAll(" ", "");
    }

    private boolean hasDefaultComma(String dataSimbols, int count, Data data) {
        return !(dataSimbols.length() >= count + MAX_COUNT && !data.equals(Data.COMMA)) && flag == Flag.DEFAULT;
    }

    private void buttonSignOnClick(Operator sign) {

        if (flag == Flag.OVERFLOW) {
            clean();
            return;
        }
        lastPressedSign = sign;
        flag = Flag.CALCULATING;

        if (lastChangePrevious) {
            history.remove(2);
        }
        if (firstData.equals(BigDecimal.ZERO)) {
            history.clear();
            history.add(new HistoryNumber(BigDecimal.ZERO));
        } else {
            history.add(new HistoryNumber(firstData));
            history.add(new HistoryOperator(sign));
        }

        if (lastSign != null && secondData != null) {
            calcWith2ParamByAnotherSign();
        }

        fieldPrevious.setText(history.getView());
        lastChangePrevious = true;
        lastSign = sign;
    }


    private void calcWith2ParamByAnotherSign() {
        if (flag == Flag.OVERFLOW) {
            clean();
            return;
        }

        flag = Flag.CALCULATING;
        initFirstDataAsResCalculation(secondData, firstData, lastSign);
        if (firstData != null) {
            fontScale(Refactor.refactor(firstData));
        }
        secondData = null;
    }


    private void calcWith2Param() {
        lastChangePrevious = false;
        lastPressEqually = false;
        if (flag == Flag.OVERFLOW) {
            clean();
            return;
        }

        lastOperation.setNumber(firstData);
        lastOperation.setSign(lastSign);
        flag = Flag.ANSWER;
        initFirstDataAsResCalculation(secondData, firstData, lastSign);
        if (firstData != null) {
            fontScale(Refactor.refactor(firstData));
        }

        secondData = null;
        lastSign = null;
        fieldPrevious.setText(history.getView());
    }

    private void calcWith1Param(Operator sign) {
        lastPressedSign = null;
        lastChangePrevious = false;
        if (flag == Flag.OVERFLOW) {
            clean();
            return;
        }

        if (!field.getText().isEmpty()) {
            if (firstData == null) {
                initFirsData();
            }
            if (lastSign != null && secondData == null) {
                secondData = firstData;
            }

            HistoryData historyData;
            if (lastSign == null) {
                if (history.isEmpty()) {
                    history.add(new HistoryNumber(firstData));
                }
                historyData = new HistoryOperator(sign);

            } else {
                if (history.lastOperator() != null && history.lastOperator().equals(lastSign)) {
                    historyData = new HistoryOperator(sign);
                } else {
                    String tmsString = (new HistoryNumber(firstData).getView(""));
                    tmsString = new HistoryOperator(sign).getView(tmsString);
                    historyData = new HistoryString(tmsString);
                }
            }


            if (secondData != null) {
                flag = Flag.CALCULATING;
            } else {
                flag = Flag.ANSWER;
            }

            initFirstDataAsResCalculation(firstData, null, sign);
            if (flag != Flag.OVERFLOW) {
                fontScale(Refactor.refactor(firstData));
            }
            history.add(historyData);
        }
        fieldPrevious.setText(history.getView());

    }

    private void percentLogic() {
        lastChangePrevious = false;
        if (lastSign != null) {
            percentLogicSignComb(lastSign);
            lastOperation.setNumber(secondData);
            lastOperation.setSign(lastSign);
            lastSign = null;
        } else if (lastOperation.getSign() != null) {
            percentLogicSignComb(lastOperation.getSign());
        }
    }

    private void percentLogicSignComb(Operator sign) {
        BigDecimal dataForCalc = BigDecimal.ONE;
        String dataForField = " % 1";

        if (!sign.equals(Operator.DIVISION) && !sign.equals(Operator.MULTIPLY)) {
            history.clear();
            dataForField = "(" + Refactor.refactor(secondData).trim() + ")% " + Refactor.refactor(firstData).trim();
            dataForCalc = secondData;
        }
        HistoryString historyString = new HistoryString(dataForField);
        history.add(historyString);
        fieldPrevious.setText(dataForField);
        initFirstDataAsResCalculation(firstData, dataForCalc, Operator.PERCENT);
    }

    private void percentLogicWithOne() {
        lastChangePrevious = false;
        if (lastSign != null) {
            percentLogicWithOneSignComb(lastSign);
            lastOperation.setNumber(secondData);
            lastOperation.setSign(lastSign);
            lastSign = null;
        } else if (lastOperation.getSign() != null) {
            percentLogicWithOneSignComb(lastOperation.getSign());
        } else {
            clean();
        }
    }

    private void percentLogicWithOneSignComb(Operator sign) {
        if (sign.equals(Operator.DIVISION) || sign.equals(Operator.MULTIPLY)) {
            fieldPrevious.setText(field.getText() + " % 1");
        } else {
            secondData = firstData;
            initWithCalcPercentWithPower(firstData);
        }
        initFirstDataAsResCalculation(firstData, BigDecimal.ONE, Operator.PERCENT);
    }

    private void initWithCalcPercentWithPower(BigDecimal bigDecimal) {
        try {
            BigDecimal res = Calculator.percentWithPower(bigDecimal);
            firstData = res;
        } catch (CalculationException e) {
            setExceptionToField(e);
        }
    }

    private void clean() {
        Scaling.fontSizeScale(gridPaneWithData, gridPaneWithM, anchorPane, field, ZERO);
        lastPressEqually = false;
        lastPressedSign = null;
        lastChangePrevious = false;
        lastSign = null;
        firstData = BigDecimal.ZERO;
        secondData = null;
        lastOperation = new LastOperation();
        fieldPrevious.setText("");
        history.clear();
        flag = Flag.DEFAULT;
    }

    private void initFirstDataAsResCalculation(BigDecimal bigDecimal1, BigDecimal bigDecimal2, Operator operator) {
        try {
            BigDecimal res;
            if (bigDecimal2 != null) {
                res = Calculator.calculate(bigDecimal1, bigDecimal2, operator);
            } else {
                res = Calculator.calculate(bigDecimal1, operator);
            }
            firstData = res;
        } catch (CalculationException e) {
            setExceptionToField(e);
        }
    }

    private void setExceptionToField(CalculationException e) {
        firstData = null;
        flag = Flag.OVERFLOW;
        Scaling.setInvalidData(field, MAP_OF_EXCEPTION_EQUIVALENTS.get(e.getType()));
    }

    private void initFirsData() {
        String text = field.getText();
        firstData = Refactor.bigDecimalFromString(text);
    }

    private static Map<TypeOfCalculationException, String> initMap() {
        Map<TypeOfCalculationException, String> map = new HashMap<>();
        map.put(TypeOfCalculationException.UNCERTAINTY, DATA_UNCERTAINTY);
        map.put(TypeOfCalculationException.OVERFLOW, DATA_OVERFLOW);
        map.put(TypeOfCalculationException.DIVISION_BY_ZERO, DATA_ZERO_DIVISION);
        map.put(TypeOfCalculationException.SQRT_OF_NEGATIVE, DATA_SQRT);


        return map;
    }
}
