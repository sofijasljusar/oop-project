module com.plandiy.coursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.plandiy.coursework to javafx.fxml;
    exports com.plandiy.coursework;
}