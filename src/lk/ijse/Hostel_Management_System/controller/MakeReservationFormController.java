package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.view.tdm.ReservationTM;

public class MakeReservationFormController {
    public Label lblReservationID;
    public Label lblDate;
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDob;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnNewStudent;
    public JFXComboBox<RoomDTO> cmbRoomTypeID;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;
    public JFXButton btnAddToList;
    public TableView<ReservationTM> tblReservationDetails;
    public Label lblTotal;
    public JFXTextField txtPaidKeyMoney;
    public JFXButton btnReserve;

    public void initialize(){

    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {

    }

    public void btnAddToListOnAction(ActionEvent actionEvent) {

    }

    public void btnReserveOnAction(ActionEvent actionEvent) {

    }
}
