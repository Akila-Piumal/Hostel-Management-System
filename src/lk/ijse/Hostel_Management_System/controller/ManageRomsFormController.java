package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.ManageRoomBO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.entity.Room;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.RoomTM;

import java.util.List;

public class ManageRomsFormController {
    public JFXButton btnNewRoom;
    public JFXComboBox<RoomDTO> cmbRoomTypeID;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<RoomTM> tblRoomDetails;
    public AnchorPane manageRoomsFormContext;

    ManageRoomBO manageRoomBO = (ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGEROOMS);

    public void initialize(){
        tblRoomDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("roomTypeId"));
        tblRoomDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("type"));
        tblRoomDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("keyMoney"));
        tblRoomDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("qty"));


        AnimationUtil.windowAnimation(manageRoomsFormContext);

        List<RoomDTO> allRooms = manageRoomBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {
            cmbRoomTypeID.getItems().add(roomDTO);
        }

        clearFields();

        cmbRoomTypeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtType.setText(newValue.getType());
            txtKeyMoney.setText(newValue.getKeyMoney());
            txtQty.setText(String.valueOf(newValue.getQty()));
        });

        loadAllRoomDetails();

    }

    private void loadAllRoomDetails() {
        List<RoomDTO> allRooms = manageRoomBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {
            tblRoomDetails.getItems().add(new RoomTM(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty()));
        }
    }

    private void clearFields() {
        cmbRoomTypeID.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtQty.clear();
        txtType.clear();
        txtKeyMoney.setDisable(true);
        txtType.setDisable(true);
        txtQty.setDisable(true);
        cmbRoomTypeID.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
    }

    public void btnNewRoomOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveRoomOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteRoomOnAction(ActionEvent actionEvent) {

    }
}
