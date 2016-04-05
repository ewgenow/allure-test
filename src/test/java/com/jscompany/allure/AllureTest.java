package com.jscompany.allure;

import com.jscompany.allure.utils.ReadData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Title("Тест сьют для арифметических операций")
@Description("Проверяем операции сложения, вычитания, деления и умножения")
@RunWith(Parameterized.class)
public class AllureTest
{
    String fOperand;
    String sOperand;
    String mathOperation;
    String expResult;
    public AllureTest(String fOperand, String sOperand, String mathOperation, String expResult )
    {
        this.fOperand = fOperand;
        this.sOperand = sOperand;
        this.mathOperation = mathOperation;
        this.expResult = expResult;
    }

    public void setfOperand(String fOperand) {
        this.fOperand = fOperand;
    }

    public void setsOperand(String sOperand) {
        this.sOperand = sOperand;
    }

    public void setMathOperation(String mathOperation) {
        this.mathOperation = mathOperation;
    }

    public void setExpResult(String expResult) {
        this.expResult = expResult;
    }

    @Step("Тест на деление")
    public void divisionSimpleTest() {
        checkResult(getDivisionResult(fOperand, sOperand), Double.parseDouble(expResult));
    }

    @Step("Тест на сложение")
    public void additionSimpleTest() {
        checkResult(getAdditionResult(fOperand, sOperand), Double.parseDouble(expResult));
    }

    @Step("Тест на вычитание")
    public void subtractionSimpleTest() {
        checkResult(getSubtractionResult(fOperand, sOperand), Double.parseDouble(expResult));
    }

    @Step("Тест на умножение")
    public void multiplicationSimpleTest() {
        checkResult(getMultiplicationResult(fOperand, sOperand), Double.parseDouble(expResult));
    }

    @Step("Проверка результата на совпадение")
    private void checkResult(double actualResult, double expectedResult) {
        log("Расчётное значение: " + actualResult + "; ожидаемое значение: " + expectedResult);
        assertTrue("Актуальный результат ("+ actualResult+") не равен ожидаемоиу("+expectedResult+")", actualResult == expectedResult);
    }

    @Step("Шаг для логирования")
    @Attachment(value = "{0}", type = "text/plain")
    private String log(String value) {
        return value;
    }

    @Step("Проверка делителя на 0")
    private void checkNotZero(int intValue) {
        log("Значение делителя равно: " + intValue);
        assertTrue("Делитель не равен 0", intValue != 0);
    }

    @Step("Проверка операнда на целочисленность")
    private int checkIsInteger(String intValue) {
        int resValue;
        log("Значение операнда: " + intValue);
        try {
            resValue = Integer.parseInt(intValue);
            assertTrue("Операнд число", 0 == 0);
        }catch (NumberFormatException e)
        {
            resValue = 0;
            assertFalse("Операнд не число", 0 != 0);
        }
        return resValue;
    }

    @Step("Получение результата от делимого на делитель")
    private double getDivisionResult(String firstInt, String secondInt) {
        checkNotZero(checkIsInteger(secondInt));
        log("Значение делимого: " + firstInt + "; значение делителя: " + secondInt);
        return (double)checkIsInteger(firstInt) / (double)checkIsInteger(secondInt);
    }

    @Step("Получение результата сложения двух слагаемых")
    private double getAdditionResult(String firstInt, String secondInt) {
        log("Значение первого слагаемого: " + firstInt + "; значение второго слагаемого: " + secondInt);
        return (double)checkIsInteger(firstInt) + (double)checkIsInteger(secondInt);
    }

    @Step("Получение результата вычитания вычитаемого из уменьшаемого")
    private double getSubtractionResult(String firstInt, String secondInt) {
        log("Значение уменьшаемого: " + firstInt + "; значение вычитаемого: " + secondInt);
        return (double)checkIsInteger(firstInt) - (double)checkIsInteger(secondInt);
    }

    @Step("Получение результата умножения двух множителей")
    private double getMultiplicationResult(String firstInt, String secondInt) {
        log("Значение первого множителя: " + firstInt + "; значение второго множителя: " + secondInt);
        return (double)checkIsInteger(firstInt) * (double)checkIsInteger(secondInt);
    }

    @Description("Определение типа операции")
    @Test
    public void simpleTestWithSteps() throws Exception{
        Operation operation = new Operation(fOperand, sOperand, mathOperation);
        if ("/".equals(mathOperation)) {divisionSimpleTest();}
        else if ("+".equals(mathOperation)) {additionSimpleTest();}
        else if ("-".equals(mathOperation)) {subtractionSimpleTest();}
        else if ("*".equals(mathOperation)) {multiplicationSimpleTest();}
        else throw new IllegalArgumentException("Арифметическая операция " + mathOperation + " не определена");
    }

    @Description("Чтение параметров из файла")
    @Parameterized.Parameters
    public static Collection<String[]> getTestData() {
        ArrayList<String[]> arrList = ReadData.get("sample.csv");
        Collection<String[]> arr = arrList;
        /*Collection<String[]> arr = Arrays.asList(new String[][]{
                {"2", "2", "+", "4.0"},
                {"2", "2", "-", "0.0"},
                {"2", "2", "*", "4.0"},
                {"2", "2", "/", "1.0"},
        });*/
        return arr;
    }
}
