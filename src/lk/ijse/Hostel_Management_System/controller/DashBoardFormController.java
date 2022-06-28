package lk.ijse.Hostel_Management_System.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane dashBoardFormContext;
    public AnchorPane childFormContext;
    public static String userName;
    public static String password;

    public void initialize() throws IOException {
        AnimationUtil.windowAnimation(dashBoardFormContext);

        setUi("home");
    }

    public void backToHomeOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("home");
    }

    public void manageStudentsFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("manageStudents");
    }

    public void manageRoomsFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("manageRoms");
    }

    public void reservationDetailsFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("reservationDetails");
    }

    public void makeReservationFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("makeReservation");
    }

    public void userManagementFormOnAction(ActionEvent actionEvent) throws IOException {
        UserManagementFormController.userName=userName;
        UserManagementFormController.password=password;
        setUi("userManagement");
    }

    public void keyMoneyRemainStudentsFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("keyMoneyRemainStudents");
    }

    public void setUi(String URI) throws IOException {
        childFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+ URI +"Form.fxml"));
        childFormContext.getChildren().add(parent);
    }
}
