package com.example.calculatorjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class HelloController {
    @FXML
    private Label display;

    private double num1 = 0;
    private String operator = "+";


    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Button)event.getSource()).getId().replace("btn", ""));
        if (Double.parseDouble(display.getText()) <= 0 || display.getText().contains(".")) {
            if (display.getText().equals("0")) {
                display.setText(String.valueOf(value));
            } else {
                display.setText(display.getText() + value);
            }
        } else {
            display.setText(display.getText() + value);
        }
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Button)event.getSource()).getId().replace("btn","");
        switch (symbol) {
            case "Equal":
                try {
                    double result = getResult();
                    displayResult(result);
                    operator = ".";
                } catch (ArithmeticException e ) {
                    display.setText("ERROR");
                }
                break;
            case "AC":
                display.setText(String.valueOf(0));
                operator = ".";
                break;
            case "Switch":
                double currentValue = Double.parseDouble(display.getText());
                display.setText(currentValue == 0 ? display.getText() : String.valueOf(-currentValue).replaceAll("\\.0*$", ""));
                break;
            case "Percent":
                    display.setText(Double.parseDouble(display.getText()) == 0 ? display.getText() : String.valueOf(Double.parseDouble(display.getText()) * 0.01).replaceAll("\\.0*$", ""));
                break;
            case "Comma":
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
                break;
            default:
                switch (symbol) {
                    case "Plus" -> operator = "+";
                    case "Minus" -> operator = "-";
                    case "Multiply" -> operator = "*";
                    case "Divide" -> operator = "/";
                }
                num1 = Double.parseDouble(display.getText());
                display.setText(String.valueOf(0));
                break;
        }
    }

    private double getResult() {
        double num2 = Double.parseDouble(display.getText());
        double result = 0.0;
        switch (operator) {
            case "+" -> result = (num1 + num2);
            case "-" -> result = (num1 - num2);
            case "*" -> result = (num1 * num2);
            case "/" -> {
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by 0");
                }
                result = (num1 / num2);
            }
        }
        return result;
    }

    private void displayResult(double result) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.############", symbols);
        String formattedResult = decimalFormat.format(result);

        display.setText(formattedResult);
    }

}