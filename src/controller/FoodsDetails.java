package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Food;
import tm.FoodTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FoodsDetails {
    public JFXTextField txtId;
    public JFXTextField txtDescription;
    public JFXTextField txtqty;
    public JFXTextField txtUnitPrice;
    public TableView tblFood;
    public TableColumn colFoodId;
    public TableColumn colFoodtype;
    public TableColumn colqty;
    public TableColumn colUnitPrice;
    public JFXButton btnaddfood;
    public JFXButton btnremovefood;
    public JFXButton btnupdatefood;

    ObservableList<FoodTM>ObList = FXCollections.observableArrayList();

    public void initialize(){

        try{
            colFoodId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colFoodtype.setCellValueFactory(new PropertyValueFactory<>("description"));
            colqty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

            setFoodDetailstoTable(new FoodController().getAllFoods());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void SaveFoodOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        FoodTM foodTM = new FoodTM(txtId.getText(),txtDescription.getText(),Integer.parseInt(txtqty.getText()),Double.parseDouble(txtUnitPrice.getText()));
        ObList.add(foodTM);

        Food F1 = new Food (
                txtId.getText(),txtDescription.getText(),Integer.parseInt(txtqty.getText()),Double.parseDouble(txtUnitPrice.getText())
        );
        if(new FoodController().saveFood(F1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void DeleteFoodOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new FoodController().deleteFood(txtId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void UpdateFoodOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Food F1 = new Food(
                txtId.getText(),txtDescription.getText(),Integer.parseInt(txtqty.getText()),Double.parseDouble(txtUnitPrice.getText())
        );
        if(new FoodController().updateFood(F1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    private void setFoodDetailstoTable(ArrayList<Food> foods){
        ObservableList<FoodTM> obList = FXCollections.observableArrayList();
        foods.forEach(e->{
            obList.add(new FoodTM(e.getCode(),e.getDescription(),e.getQtyOnHand(),e.getUnitPrice()));
        });
      tblFood.setItems(obList);
    }

    public void FoodId_keyPress(KeyEvent keyEvent) {
        btnaddfood.setDisable(true);
        btnupdatefood.setDisable(true);
        btnremovefood.setDisable(true);

        String regEx="^[F]{1}[0-9]{0,50}$";
        String typeText = txtId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtId.setStyle("-fx-border-color: green");

        }else {
            txtId.setStyle("-fx-border-color: red");


        }
        if(keyEvent.getCode() == KeyCode.ENTER){
            if (matches){
                txtDescription.requestFocus();
            }
        }

    }
    public void Food_Name(KeyEvent keyEvent) {

        String regEx = "^[A-z]{3,20}$";
        String typeText = txtDescription.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if(matches){
            txtDescription.setStyle("-fx-border-color: green");


        }else {
            txtDescription.setStyle("-fx-border-color: red");

        }
        if (keyEvent.getCode() ==KeyCode.ENTER){
            if (matches){
                txtqty.requestFocus();
            }
        }

    }
    public void Food_QtyPress(KeyEvent keyEvent) {

        String regEx = "^[0-9]{1,4}?$";
        String typeText = txtqty.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtqty.setStyle("-fx-border-color: green");

        }else{
            txtqty.setStyle("-fx-border-color: red");

        }
        if (keyEvent.getCode() ==KeyCode.ENTER){
            if (matches){
                txtUnitPrice.requestFocus();
            }
        }

    }
    public void Food_SalaryPress(KeyEvent keyEvent) {
        String regEx = "^[1-9][0-9]*[0-9]{2}?$";
        String typeText = txtUnitPrice.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtUnitPrice.setStyle("-fx-border-color: green");
            btnaddfood.setDisable(false);
            btnremovefood.setDisable(false);
            btnupdatefood.setDisable(false);
        }else{
            txtUnitPrice.setStyle("-fx-border-color: red");

        }
    }
}
