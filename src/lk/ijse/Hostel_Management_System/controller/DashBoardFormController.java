package lk.ijse.Hostel_Management_System.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane dashBoardFormContext;
    public AnchorPane childFormContext;

    public void backToHomeOnAction(MouseEvent mouseEvent) {

    }

    public void manageStudentsFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("manageStudents");
    }

    public void manageRoomsFormOnAction(ActionEvent actionEvent) {

    }

    public void reservationDetailsFormOnAction(ActionEvent actionEvent) {

    }

    public void makeReservationFormOnAction(ActionEvent actionEvent) {

    }

    public void setUi(String URI) throws IOException {
        childFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+ URI +"Form.fxml"));
        childFormContext.getChildren().add(parent);
    }
}
