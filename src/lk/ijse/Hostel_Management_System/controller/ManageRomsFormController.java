package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.ManageRoomBO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.entity.Room;
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

    ManageRoomBO manageRoomBO = (ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGEROOMS);

    public void initialize(){
        List<RoomDTO> allRooms = manageRoomBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {
            cmbRoomTypeID.getItems().add(roomDTO);
        }
    }

    public void btnNewRoomOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveRoomOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteRoomOnAction(ActionEvent actionEvent) {

    }
}
