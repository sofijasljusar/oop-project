module com.plandiy.coursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.plandiy to javafx.fxml;
    exports com.plandiy;
    exports com.plandiy.controller;
    opens com.plandiy.controller to javafx.fxml;
    exports com.plandiy.model;
    opens com.plandiy.model to javafx.fxml;
}