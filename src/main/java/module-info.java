module com.plandiy.coursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.plandiy to javafx.fxml;
    exports com.plandiy;
    exports com.plandiy.controller;
    opens com.plandiy.controller to javafx.fxml;
    exports com.plandiy.service.analytics;
    opens com.plandiy.service.analytics to javafx.fxml;
    exports com.plandiy.service.report;
    opens com.plandiy.service.report to javafx.fxml;
    exports com.plandiy.service;
    opens com.plandiy.service to javafx.fxml;
    exports com.plandiy.model.task;
    opens com.plandiy.model.task to javafx.fxml;
    exports com.plandiy.model.user;
    opens com.plandiy.model.user to javafx.fxml;
    exports com.plandiy.service.notification;
    opens com.plandiy.service.notification to javafx.fxml;
    exports com.plandiy.service.communication;
    opens com.plandiy.service.communication to javafx.fxml;
    exports com.plandiy.model.project;
    opens com.plandiy.model.project to javafx.fxml;
    exports com.plandiy.model.budget;
    opens com.plandiy.model.budget to javafx.fxml;
    exports com.plandiy.model.resource;
    opens com.plandiy.model.resource to javafx.fxml;
}