package lk.ijse.Hostel_Management_System.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.HomeBO;
import lk.ijse.Hostel_Management_System.dto.ReservationDTO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class HomeFormController {
    public Label lblTotalRooms;
    public Label lblTotalReservations;
    public Label lblTotalStudents;
    public AnchorPane homeFormContext;

    private final HomeBO homeBO = (HomeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HOME);

    public void initialize(){
        AnimationUtil.windowAnimation(homeFormContext);

        List<StudentDTO> allStudents = homeBO.getAllStudents();
        int studentCount=0;
        for (StudentDTO student : allStudents) {
            studentCount++;
        }
        lblTotalStudents.setText(String.valueOf(studentCount));

        List<RoomDTO> allRooms = homeBO.getAllRooms();
        int roomCount=0;
        for (RoomDTO room : allRooms) {
            roomCount++;
        }
        lblTotalRooms.setText(String.valueOf(roomCount));

        List<ReservationDTO> allReservations = homeBO.getAllReservations();
        int reservationCount=0;
        for (ReservationDTO reservation : allReservations) {
            reservationCount++;
        }
        lblTotalReservations.setText(String.valueOf(reservationCount));
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = homeFormContext.getParent();
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();
    }
}
