package com.jscompany.allure;

/**
 * Created by Silver on 04.04.2016.
 */
public enum enumOperation {
    addition("+"),
    subtraction("-"),
    multiplication("*"),
    division("/");

    private String symbolOperation;
    private static final enumOperation[] copyOfValues = values();

    private enumOperation(String symbolOperation){
        this.symbolOperation = symbolOperation;
    }

    public String toString() {
        return symbolOperation;
    }

    public static String getOperation(String name) {
        String result = null;
        for (enumOperation value : copyOfValues) {
            if (value.toString().equals(name)) {
                result = value.name().toString();
            }
        }
        if (result == null) throw new IllegalArgumentException("Math operation " + name + " not found");
        return result;
    }
}
