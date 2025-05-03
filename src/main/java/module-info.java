module com.plandiy.coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires static lombok;
    requires org.slf4j;
    requires java.desktop;


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
    exports com.plandiy.model.issue;
    opens com.plandiy.model.issue to javafx.fxml;
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
    exports com.plandiy.model.issue.task;
    opens com.plandiy.model.issue.task to javafx.fxml;
    exports com.plandiy.model.issue.subtask;
    opens com.plandiy.model.issue.subtask to javafx.fxml;
    exports com.plandiy.observer;
    exports com.plandiy.service.progress;
    exports com.plandiy.model.issue.task.factory;
    opens com.plandiy.model.issue.task.factory to javafx.fxml;
    exports com.plandiy.service.risk;
    opens com.plandiy.service.risk to javafx.fxml;
}