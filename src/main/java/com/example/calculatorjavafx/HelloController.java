package com.example.calculatorjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


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
            case "Equal" -> {
                double num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+" -> display.setText((num1 + num2) + "");
                    case "-" -> display.setText((num1 - num2) + "");
                    case "*" -> display.setText((num1 * num2) + "");
                    case "/" -> display.setText((num1 / num2) + "");
                }
                operator = ".";
            }
            case "AC" -> {
                display.setText(String.valueOf(0));
                operator = ".";
            }
            case "Switch" -> {
                double currentValue = Double.parseDouble(display.getText());
                display.setText(currentValue == 0 ? display.getText() : String.valueOf(-currentValue).replaceAll("\\.0*$", ""));
            }
            case "Percent" ->
                    display.setText(Double.parseDouble(display.getText()) == 0 ? display.getText() : String.valueOf(Double.parseDouble(display.getText()) * 0.01).replaceAll("\\.0*$", ""));
            case "Comma" -> {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            }
            default -> {
                switch (symbol) {
                    case "Plus" -> operator = "+";
                    case "Minus" -> operator = "-";
                    case "Multiply" -> operator = "*";
                    case "Divide" -> operator = "/";
                }
                num1 = Double.parseDouble(display.getText());
                display.setText(String.valueOf(0));
            }
        }
    }

}