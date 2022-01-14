package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Login;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane Context2;
    public TextField username;
    public PasswordField password;
    public AnchorPane Context1;

    public void OpenEmployeeFromOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        Login login = new Login(username.getText(),password.getText());
        String userType = new LoginUserController().login(login);

        if(userType.equals("Employee")){
             Parent load = FXMLLoader.load(getClass().getResource("../View/EmployeeForm.fxml"));
             Scene scene = new Scene(load);
             Stage stage = (Stage) Context1.getScene().getWindow();
             stage.setScene(scene);
             stage.show();


        }else if (userType.equals("Admin")){
            Parent load = FXMLLoader.load(getClass().getResource("../View/AdminForm.fxml"));
            Scene scene = new Scene(load);
            Stage stage = (Stage) Context1.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }else if (userType.equals("Cheff")){
            Parent load = FXMLLoader.load(getClass().getResource("../View/CheffForm.fxml"));
            Scene scene = new Scene(load);
            Stage stage = (Stage) Context1.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Please check username & Password").show();
        }

    }
}
