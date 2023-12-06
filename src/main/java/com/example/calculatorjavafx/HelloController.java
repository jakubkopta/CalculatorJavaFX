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
        display.setText(Double.parseDouble(display.getText())<=0?String.valueOf((double)value):String.valueOf(Double.parseDouble(display.getText())*10+value));
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Button)event.getSource()).getId().replace("btn","");
        if(symbol.equals("Equal")) {
            double num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+" -> display.setText((num1+num2) + "");
                case "-" -> display.setText((num1-num2) + "");
                case "*" -> display.setText((num1*num2) + "");
                case "/" -> display.setText((num1/num2) + "");
            }
            operator = ".";
        }
        else if (symbol.equals("AC")) {
            display.setText(String.valueOf(0));
            operator = ".";
        }
        else if (symbol.equals("Switch")) {
            display.setText(Double.parseDouble(display.getText())==0? display.getText(): String.valueOf(Double.parseDouble(display.getText())*(-1)));
        }
        else if (symbol.equals("Percent")) {
            display.setText(Double.parseDouble(display.getText())==0? display.getText(): String.valueOf(Double.parseDouble(display.getText())*0.01));
        }
//        else if (symbol.equals("Coma")) {
//            display.setText(display.getText() + ".");
//        }
        else {
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