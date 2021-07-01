package com.eknm.controller;

/**
 *
 */
public enum Data {
    /**
     * Data with number zero
     */
    ZERO("0"),
    /**
     * Data with number one
     */
    ONE("1"),
    /**
     * Data with number two
     */
    TWO("2"),
    /**
     * Data with number three
     */
    THREE("3"),
    /**
     * Data with number four
     */
    FOUR("4"),
    /**
     * Data with number five
     */
    FIVE(("5")),
    /**
     * Data with number six
     */
    SIX(("6")),
    /**
     * Data with number seven
     */
    SEVEN("7"),
    /**
     * Data with number eight
     */
    EIGHT("8"),
    /**
     * Data with number nine
     */
    NINE("9"),
    /**
     * Data with comma
     */
    COMMA(",");
    /**
     * String value of Data
     */
    private String value;

    Data(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }}
