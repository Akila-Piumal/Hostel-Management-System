package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.custom.ReservationDetailsBO;
import lk.ijse.Hostel_Management_System.dto.ReservationDTO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.util.ValidationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.ReservationTM;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ReservationDetailsFormController {
    private final ReservationDetailsBO reservationDetailsBO = (ReservationDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATIONDETAILS);
    public TableView<ReservationTM> tblReservationDetails;
    public JFXTextField txtReservationID;
    public JFXTextField txtDate;
    public JFXTextField txtRoomID;
    public JFXTextField txtStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDob;
    public JFXTextField txtGender;
    public JFXTextField txtStatus;
    public JFXButton btnUpdate;
    public AnchorPane reservationDetailsFormContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        AnimationUtil.windowAnimation(reservationDetailsFormContext);

        disableFields();

        tblReservationDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("res_id"));
        tblReservationDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("date"));
        tblReservationDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("roomId"));
        tblReservationDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblReservationDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("status"));
        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservationDetails.getColumns().get(5);
        lastCol.setCellValueFactory(param -> {
            Button btnRemove = new Button("Remove");
            btnRemove.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure ?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)) {
                    if (reservationDetailsBO.removeReservation(param.getValue().getRes_id())) {
                        tblReservationDetails.getItems().remove(param.getValue());
                        new Alert(Alert.AlertType.CONFIRMATION, "Removed..!!").show();
                        RoomDTO roomDTO = reservationDetailsBO.getRoom(param.getValue().getRoomId());
                        reservationDetailsBO.updateRoomQty(roomDTO.getRoomTypeId(), roomDTO.getQty() + 1);

                        clearFields();

                        btnUpdate.setDisable(true);
                    }
                }
            });
            return new ReadOnlyObjectWrapper<>(btnRemove);
        });

        tblReservationDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtReservationID.setDisable(false);
                txtDate.setDisable(false);
                txtStatus.setDisable(false);
                txtRoomID.setDisable(false);
                txtStudentID.setDisable(false);
                txtReservationID.setEditable(false);
                txtDate.setEditable(false);
                txtStudentID.setEditable(false);
                txtRoomID.setEditable(false);
                txtName.setDisable(false);
                txtName.setEditable(false);
                txtAddress.setDisable(false);
                txtAddress.setEditable(false);
                txtContact.setDisable(false);
                txtContact.setEditable(false);
                txtDob.setDisable(false);
                txtDob.setEditable(false);
                txtGender.setDisable(false);
                txtGender.setEditable(false);
                txtReservationID.setText(newValue.getRes_id());
                txtDate.setText(String.valueOf(newValue.getDate()));
                txtRoomID.setText(newValue.getRoomId());
                txtStatus.setText(newValue.getStatus());

                StudentDTO student = reservationDetailsBO.getStudent(newValue.getStudentId());
                txtStudentID.setText(student.getStudentId());
                txtName.setText(student.getName());
                txtAddress.setText(student.getAddress());
                txtContact.setText(student.getContactNo());
                txtDob.setText(String.valueOf(student.getDob()));
                txtGender.setText(student.getGender());

                txtStatus.requestFocus();
            }
        });

        Pattern statusPattern = Pattern.compile("^paid|Paid|[0-9][0-9]{0,}(.[0-9]{2})?$");

        map.put(txtStatus, statusPattern);

        loadAllReservationDetails();
    }

    private void disableFields() {
        btnUpdate.setDisable(true);
        txtReservationID.setDisable(true);
        txtDate.setDisable(true);
        txtStudentID.setDisable(true);
        txtRoomID.setDisable(true);
        txtStatus.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtDob.setDisable(true);
        txtGender.setDisable(true);
    }

    private void clearFields() {
        tblReservationDetails.getSelectionModel().clearSelection();
        txtReservationID.clear();
        txtDate.clear();
        txtRoomID.clear();
        txtStatus.clear();
        txtStudentID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.clear();
        txtGender.clear();
    }

    private void loadAllReservationDetails() {
        List<ReservationDTO> allReservations = reservationDetailsBO.getAllReservations();
        for (ReservationDTO dto : allReservations) {
            tblReservationDetails.getItems().add(new ReservationTM(dto.getRes_id(), dto.getDate(), dto.getRoom().getRoomTypeId(), dto.getStudent().getStudentId(), dto.getStatus()));
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        updateReservation();

    }

    private void updateReservation() {
        ReservationTM selectedItem = tblReservationDetails.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (reservationDetailsBO.updateReservationStatus(selectedItem.getRes_id(), txtStatus.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                selectedItem.setStatus(txtStatus.getText());
                tblReservationDetails.refresh();
                clearFields();
                disableFields();
                tblReservationDetails.requestFocus();
            }
        }
    }

    public void text_Fields_key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map, btnUpdate);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, btnUpdate);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                updateReservation();
            }
        }
    }
}
