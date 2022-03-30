package com.sajjadamin.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class Calculator {
    GuiHelper guiHelper;
    Calculator(){
        guiHelper = new GuiHelper();
    }
    public BigDecimal calculate(String input){
        ArrayList<String> operator = new ArrayList<>();
        ArrayList<BigDecimal> operand = new ArrayList<>();
        StringBuilder digit = new StringBuilder();
        BigDecimal output = new BigDecimal("0");
        for(int i = 0; i < input.length(); i++){
            String c = guiHelper.filterSymbol(input.charAt(i)+"");
            if(c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("%")){
                operator.add(c);
                operand.add(new BigDecimal(digit.toString()));
                digit.setLength(0);
            } else {
                digit.append(c);
            }
        }
        operand.add(new BigDecimal(digit.toString().equals("") ? "0" : digit.toString()));
        for(int i = 0; i < operator.size(); i++){
            if(i == 0){
                output = doCalculate(operand.get(i),operand.get(i+1),operator.get(i));
            } else {
                output = doCalculate(output,operand.get(i+1),operator.get(i));
            }
        }
        return output;
    }

    private BigDecimal doCalculate(BigDecimal inp1, BigDecimal inp2, String operand){
        BigDecimal output = new BigDecimal("0");
        switch (operand){
            case "+":
                output = inp1.add(inp2);
                break;
            case "-":
                output = inp1.subtract(inp2);
                break;
            case "*":
                output = inp1.multiply(inp2);
                break;
            case "/":
                output = inp1.divide(inp2, new MathContext(10));
                break;
            case "%":
                output = inp1.divide(new BigDecimal("100"), new MathContext(10));
                break;
        }
        return output;
    }
}
