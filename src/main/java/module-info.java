module edu.psu.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.psu.calculator to javafx.fxml;
    exports edu.psu.calculator;
}