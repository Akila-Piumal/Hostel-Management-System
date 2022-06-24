package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import lk.ijse.Hostel_Management_System.entity.Room;
import lk.ijse.Hostel_Management_System.view.tdm.RoomTM;

public class ManageRomsFormController {
    public JFXButton btnNewRoom;
    public JFXComboBox<Room> cmbRoomTypeID;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<RoomTM> tblRoomDetails;

    public void btnNewRoomOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveRoomOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteRoomOnAction(ActionEvent actionEvent) {
    }
}
