package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.ReservationDetailsBO;
import lk.ijse.Hostel_Management_System.dto.ReservationDTO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.ReservationTM;

import java.util.List;
import java.util.Optional;

public class ReservationDetailsFormController {
    public TableView<ReservationTM> tblReservationDetails;
    public JFXTextField txtReservationID;
    public JFXTextField txtDate;
    public JFXTextField txtRoomID;
    public JFXTextField txtStudentID;
    public JFXTextField txtStatus;
    public JFXButton btnUpdate;

    private final ReservationDetailsBO reservationDetailsBO = (ReservationDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATIONDETAILS);
    public AnchorPane reservationDetailsFormContext;

    public void initialize(){
        AnimationUtil.windowAnimation(reservationDetailsFormContext);

        tblReservationDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("res_id"));
        tblReservationDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("date"));
        tblReservationDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("roomId"));
        tblReservationDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblReservationDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("status"));
        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservationDetails.getColumns().get(5);
        lastCol.setCellValueFactory(param -> {
            Button btnRemove=new Button("Remove");
            btnRemove.setOnAction(event -> {
                Alert alert=new Alert(Alert.AlertType.WARNING,"Are You Sure ?", ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)){
                    if (reservationDetailsBO.removeReservation(param.getValue().getRes_id())) {
                        tblReservationDetails.getItems().remove(param.getValue());
                        new Alert(Alert.AlertType.CONFIRMATION,"Removed..!!").show();
                        RoomDTO roomDTO = reservationDetailsBO.getRoom(param.getValue().getRoomId());
                        reservationDetailsBO.updateRoomQty(roomDTO.getRoomTypeId(),roomDTO.getQty()+1);
                    }
                }
            });
            return new ReadOnlyObjectWrapper<>(btnRemove);
        });

        loadAllReservationDetails();
    }

    private void loadAllReservationDetails() {
        List<ReservationDTO> allReservations = reservationDetailsBO.getAllReservations();
        for (ReservationDTO dto : allReservations) {
            tblReservationDetails.getItems().add(new ReservationTM(dto.getRes_id(),dto.getDate(),dto.getRoom().getRoomTypeId(),dto.getStudent().getStudentId(),dto.getStatus()));
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }
}
