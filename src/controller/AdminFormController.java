package controller;

import com.jfoenix.controls.JFXButton;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AdminFormController {


    public AnchorPane AdminContext1;
    public AnchorPane AdminContext2;
    public AnchorPane AdminContext3;
    public AnchorPane adminloadcontext6;
    public JFXButton btnrentaljasper;
    public JFXButton btnRoomjasper;
    public JFXButton btnforder;
    public JFXButton btnrorder;

    public void OpenFoodOrderDetailsOnaction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/LoadAllFoodOrderDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminContext1.getChildren().add(load);


    }

    public void OpenRoomOrderDetailsOnaction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/LoadAllRoomOrderDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminContext2.getChildren().add(load);
    }

    public void OpenMonthlyPaymentDetails(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/MonthlyPayment.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminContext3.getChildren().add(load);

    }

    public void openLoginFormOnAction(ActionEvent actionEvent) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) adminloadcontext6.getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    public void Jasperevent(MouseEvent mouseEvent) {

        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/WelcomeReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);


        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void RoomJasperEvent(MouseEvent mouseEvent) {

        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/RoomDetail.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);


        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fdOrderrentalreport(MouseEvent mouseEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/foodorder.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);


        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void rdorderrentalreport(MouseEvent mouseEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/roomorder.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);


        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

 /*try {
         /*01 - Lets catch the file Report*/
        /*JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/WelcomeReport.jrxml"));
         /*02 - Lets compile the jasper design*/
         /*JasperReport compileReport = JasperCompileManager.compileReport(design);
         /*03 - Set the resources for the compiled report*/
         /*JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null,DbConnection.getInstance().getConnection());
         /*04 - Lets view the report*//*
         JasperViewer.viewReport(jasperPrint,false);
         } catch (JRException e) {
         e.printStackTrace();
         }*/

