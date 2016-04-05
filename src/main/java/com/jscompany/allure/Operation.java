package com.jscompany.allure;

public class Operation
{
    protected Integer fOperand;
    protected Integer sOperand;
    protected String mathOperation;
    protected Double calcResult;

    public Operation (String fOperand, String sOperand, String mathOperation) {
        try {
            this.fOperand = Integer.parseInt(fOperand);
        }catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Первый операнд " + fOperand + " не целое число");
        }
        try {
        this.sOperand = Integer.parseInt(sOperand);
        }catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Второй операнд " + sOperand + " не целое число");
        }
        try {
            this.mathOperation = String.valueOf(enumOperation.valueOf(enumOperation.getOperation(mathOperation)));

        }catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Операция " + mathOperation + " не из списка " + enumOperation.values());
        }
    }

    public Integer getfOperand() {
        return fOperand;
    }

    public Integer getsOperand() {
        return sOperand;
    }

    public String getMathOperation() {
        return mathOperation;
    }

    public Double getCalcResult() {
        if ("+".equals(mathOperation)) {calcResult = (double) fOperand + (double) sOperand;}
        else if ("-".equals(mathOperation)) {calcResult = (double) fOperand - (double) sOperand;}
        else if ("*".equals(mathOperation)) {calcResult = (double) fOperand * (double) sOperand;}
        else if ("/".equals(mathOperation)) {calcResult = (double) fOperand / (double) sOperand;}
        else calcResult = null;
        if (calcResult == null) throw new IllegalArgumentException("Данная операция " + mathOperation + " не определена");
        return calcResult;
    }
}
