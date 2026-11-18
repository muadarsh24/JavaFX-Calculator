package edu.psu.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private double firstNumber;
    private String operator;
    private double memory = 0;

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(borderPane());
        stage.setHeight(300);
        stage.setWidth(500);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();

    }


    private BorderPane borderPane() {

        BorderPane root = new BorderPane();

        VBox centerLayout = new VBox(15);


        TextField display = display();
        HBox editButtons = hBox(display);
        editButtons.setPadding(new Insets(0, 0, 0, 123.5));


        centerLayout.setPadding(new Insets(0, 0, 15, 0));
        centerLayout.setAlignment(Pos.BOTTOM_CENTER);
        root.setCenter(centerLayout);



        root.setTop(display);
        BorderPane.setMargin(root.getTop(), new Insets(20,10,0,10));
        centerLayout.getChildren().addAll(editButtons, gridPane(display));

        return root;

    }

    private TextField display(){

        TextField display = new TextField();

        display.setPrefHeight(30);
        display.setEditable(false);
        display.setMaxWidth(Double.MAX_VALUE);

        return display;
    }

    // hBox method
    private HBox hBox(TextField display) {

        Button button1 = new Button("Backspace");
        Button button2 = new Button("CE");
        Button button3 = new Button("C");

        button1.setPrefWidth(100);
        button1.setPrefHeight(30);
        button1.setMaxWidth(Double.MAX_VALUE);
        button1.setOnAction(event -> {
            if (display.getLength() > 0){
                display.setText(display.getText().substring(0, display.getLength() - 1));
            }
        });


        button2.setPrefWidth(100);
        button2.setPrefHeight(30);
        button2.setMaxWidth(Double.MAX_VALUE);
        button2.setOnAction(event -> {
            display.clear();
        });


        button3.setPrefWidth(100);
        button3.setPrefHeight(30);
        button3.setMaxWidth(Double.MAX_VALUE);
        button3.setOnAction(event -> {
            display.clear();
            firstNumber = 0;
            operator = null;
        });


        HBox.setHgrow(button1, Priority.ALWAYS);
        HBox.setHgrow(button2, Priority.ALWAYS);
        HBox.setHgrow(button3, Priority.ALWAYS);


        return new HBox(20, button1, button2, button3);
    }

    // helper method for grid Pane method and adding to text field
    private void addButton(TextField display, GridPane gridPane, String text, int column, int row){

        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setPrefSize(70, 30);
        gridPane.add(button, column, row);

        button.setOnAction(e ->{
            display.setText(display.getText() + button.getText());
        });

    }


    // grid Pane method
    private GridPane gridPane(TextField display) {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(8);


        // make columns and rows expand
        for (int i = 0; i < 7; i++){
            ColumnConstraints column = new ColumnConstraints();

            if (i == 1){
                column.setPrefWidth(20);
            } else{
                column.setPercentWidth(100.0/7);
                column.setHgrow(Priority.ALWAYS);
            }

            gridPane.getColumnConstraints().add(column);
        }


        // add the buttons to grid pane
        addButton(display, gridPane, "7", 2, 0);
        addButton(display, gridPane, "8", 3, 0);
        addButton(display, gridPane, "9", 4, 0);

        addButton(display, gridPane, "4", 2, 1);
        addButton(display, gridPane, "5", 3, 1);
        addButton(display, gridPane, "6", 4, 1);

        addButton(display, gridPane, "1", 2, 2);
        addButton(display, gridPane, "2", 3, 2);
        addButton(display, gridPane, "3", 4, 2);
        addButton(display, gridPane, ".", 5, 2);

        addButton(display, gridPane, "0", 2, 3);


// addition ------------------------------------------------------------------------------
        Button plusButton = new Button("+");
        plusButton.setMaxWidth(Double.MAX_VALUE);
        plusButton.setPrefWidth(70);
        plusButton.setPrefHeight(30);
        plusButton.setOnAction(e ->{
            firstNumber = Double.parseDouble(display.getText().replace(",", "."));
            operator = "+";
            display.clear();
        });
        gridPane.add(plusButton, 5, 3);
//addition--------------------------------------------------------------------------------

// multiplication-------------------------------------------------------------------------------
        Button multiplicationButton = new Button("*");
        multiplicationButton.setMaxWidth(Double.MAX_VALUE);
        multiplicationButton.setPrefWidth(70);
        multiplicationButton.setPrefHeight(30);
        multiplicationButton.setOnAction(e ->{
            firstNumber = Double.parseDouble(display.getText().replace(",", "."));
            operator = "*";
            display.clear();
        });
        gridPane.add(multiplicationButton, 5, 1);
// multiplication------------------------------------------------------------------------------


// division---------------------------------------------------------------------------------
        Button divisionButton = new Button("/");
        divisionButton.setMaxWidth(Double.MAX_VALUE);
        divisionButton.setPrefWidth(70);
        divisionButton.setPrefHeight(30);
        divisionButton.setOnAction(e->{
            firstNumber = Double.parseDouble(display.getText().replace(",", "."));
            operator = "/";
            display.clear();
        });
        gridPane.add(divisionButton, 5, 0);
// division---------------------------------------------------------------------



// sign button---------------------------------------------------------------------
        Button signButton = new Button("+/-");
        signButton.setMaxWidth(Double.MAX_VALUE);
        signButton.setPrefWidth(70);
        signButton.setPrefHeight(30);
        signButton.setOnAction(e->{
            display.setText(String.valueOf(Double.parseDouble(display.getText().replace(",", ".")) * -1));
        });
        gridPane.add(signButton, 3,3);

// sign button---------------------------------------------------------------------



// square root button---------------------------------------------------------------------
        Button squarerootButton = new Button("sqrt");
        squarerootButton.setMaxWidth(Double.MAX_VALUE);
        squarerootButton.setPrefWidth(70);
        squarerootButton.setPrefHeight(30);
        squarerootButton.setOnAction(e->{
            display.setText(String.valueOf(Math.sqrt(Double.parseDouble(display.getText().replace(",", ".")))));
        });
        gridPane.add(squarerootButton, 6, 0);
// square root button---------------------------------------------------------------------



// percentage button---------------------------------------------------------------------
        Button percentageButton = new Button("%");
        percentageButton.setMaxWidth(Double.MAX_VALUE);
        percentageButton.setPrefWidth(70);
        percentageButton.setPrefHeight(30);
        percentageButton.setOnAction(e->{
            if (operator == null){
                display.setText(String.valueOf(Double.parseDouble(display.getText().replace(",", ".")) / 100));
            } else{
                if (operator.equals("*")){
                    display.setText(String.valueOf(Double.parseDouble(display.getText().replace(",", ".")) / 100));
                }
                else if (operator.equals("+")){
                    display.setText(String.valueOf((firstNumber * Double.parseDouble(display.getText().replace(",", ".")) / 100)));
                }

                else if (operator.equals("/")){
                    double percentage = Double.parseDouble(display.getText().replace(",", ".")) / 100;
                    if (percentage == 0){
                        display.setText("Error");
                    } else {
                        display.setText(String.valueOf(percentage));
                    }
                }
            }

        });
        gridPane.add(percentageButton, 6, 1);
// percentage button---------------------------------------------------------------------




// reciprocal button---------------------------------------------------------------------
        Button reciprocalButton = new Button("1/x");
        reciprocalButton.setMaxWidth(Double.MAX_VALUE);
        reciprocalButton.setPrefWidth(70);
        reciprocalButton.setPrefHeight(30);
        reciprocalButton.setOnAction(e->{

            double doubleValue = Double.parseDouble(display.getText().replace(",", "."));

            if (doubleValue == 0){
                display.setText("Error");
            } else {
                double reciprocal = 1/doubleValue;
                String result = String.valueOf(reciprocal);
                display.setText(result);
            }

        });
        gridPane.add(reciprocalButton, 6, 2);
// reciprocal button---------------------------------------------------------------------


// comma button-------------------------------------------------------------------------
        Button commaButton = new Button(",");
        commaButton.setMaxWidth(Double.MAX_VALUE);
        commaButton.setPrefWidth(70);
        commaButton.setPrefHeight(30);

        commaButton.setOnAction(e -> {
            if (!display.getText().contains(",")) {
                display.setText(display.getText() + ",");
            }
        });

        gridPane.add(commaButton, 4, 3);
// comma button-------------------------------------------------------------------------





// equals button------------------------------------------------------------------------------
        Button equalsButton = new Button("=");
        equalsButton.setMaxWidth(Double.MAX_VALUE);
        equalsButton.setPrefWidth(70);
        equalsButton.setPrefHeight(30);
        equalsButton.setOnAction(e ->{
            double secondNumber = Double.parseDouble(display.getText().replace(",", "."));
            double result = 0;

            if (operator.equals("+")){
                result = firstNumber + secondNumber;
            }

            else if (operator.equals("*")){
                result = firstNumber * secondNumber;
            }

            else if (operator.equals("/")){
                if (secondNumber == 0) {
                    display.setText("Error");
                    return;
                }
                result = firstNumber / secondNumber;
            }

            display.setText(String.valueOf(result));

        });
        gridPane.add(equalsButton, 6,3);
//equals---------------------------------------------------------------------------------



// memory buttons-------------------------------------------------------------------------
// MS(mem save)
        Button msButton = new Button("MS");
        msButton.setMaxWidth(Double.MAX_VALUE);
        msButton.setPrefWidth(70);
        msButton.setPrefHeight(30);
        msButton.setOnAction(e->{
            memory = Double.parseDouble(display.getText().replace(",", "."));
        });
        gridPane.add(msButton, 0,2);

// MR(mem recall)
        Button mrButton = new Button("MR");
        mrButton.setMaxWidth(Double.MAX_VALUE);
        mrButton.setPrefWidth(70);
        mrButton.setPrefHeight(30);
        mrButton.setOnAction(e->{
            display.setText(String.valueOf(memory));
        });
        gridPane.add(mrButton, 0, 1);

// MC(mem clear)
        Button mcButton = new Button("MC");
        mcButton.setMaxWidth(Double.MAX_VALUE);
        mcButton.setPrefWidth(70);
        mcButton.setPrefHeight(30);
        mcButton.setOnAction(e->{
            memory = 0;
        });
        gridPane.add(mcButton, 0, 0);

// M+
        Button mplusButton = new Button("M+");
        mplusButton.setMaxWidth(Double.MAX_VALUE);
        mplusButton.setPrefWidth(70);
        mplusButton.setPrefHeight(30);
        mplusButton.setOnAction(e->{
            memory += Double.parseDouble(display.getText().replace(",", "."));
        });
        gridPane.add(mplusButton, 0, 3);



        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        return gridPane;

    }
}
