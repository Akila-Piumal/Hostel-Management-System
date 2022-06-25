package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.MakeReservationBO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.ReservationTM;

import java.util.List;

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
    public JFXComboBox<StudentDTO> cmbStudentID;
    public AnchorPane makeReservationFormContext;

    MakeReservationBO makeReservationBO = (MakeReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MAKERESERVATION);

    public void initialize(){
        lblReservationID.setText(generateNewReservationID());

        AnimationUtil.windowAnimation(makeReservationFormContext);

        clearFields();

        txtStudentId.setVisible(false);
        List<StudentDTO> allStudents = makeReservationBO.getAllStudents();
        for (StudentDTO studentDTO : allStudents) {
            cmbStudentID.getItems().add(studentDTO);
        }

        List<RoomDTO> allRooms = makeReservationBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {
            cmbRoomTypeID.getItems().add(roomDTO);
        }

        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("FeMale");

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtName.setDisable(false);
            txtAddress.setDisable(false);
            txtContactNo.setDisable(false);
            txtDob.setDisable(false);
            cmbGender.setDisable(false);
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtContactNo.setText(newValue.getContactNo());
            txtDob.setText(String.valueOf(newValue.getDob()));
            cmbGender.getSelectionModel().select(newValue.getGender());
        });

        cmbRoomTypeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            txtType.setDisable(false);
            txtKeyMoney.setDisable(false);
            txtQtyOnHand.setDisable(false);
            txtQty.setDisable(false);
            btnAddToList.setDisable(false);
            txtType.setText(newValue.getType());
            txtKeyMoney.setText(newValue.getKeyMoney());
            txtQtyOnHand.setText(String.valueOf(newValue.getQty()));

        });
    }

    public String generateNewReservationID(){
        return makeReservationBO.generateNewReservationID();
    }

    private void clearFields() {
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        txtDob.setDisable(true);
        cmbGender.setDisable(true);
        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtContactNo.setEditable(false);
        txtDob.setEditable(false);
        cmbGender.setEditable(false);
        txtType.setDisable(true);
        txtType.setEditable(false);
        txtKeyMoney.setDisable(true);
        txtKeyMoney.setEditable(false);
        txtQtyOnHand.setDisable(true);
        txtQtyOnHand.setEditable(false);
        txtQty.setDisable(true);
        btnAddToList.setDisable(true);
        btnReserve.setDisable(true);
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {

    }

    public void btnAddToListOnAction(ActionEvent actionEvent) {

    }

    public void btnReserveOnAction(ActionEvent actionEvent) {

    }
}
