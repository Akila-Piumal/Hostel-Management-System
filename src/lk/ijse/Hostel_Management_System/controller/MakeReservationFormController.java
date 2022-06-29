package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.custom.MakeReservationBO;
import lk.ijse.Hostel_Management_System.dto.ReservationDTO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.entity.Room;
import lk.ijse.Hostel_Management_System.entity.Student;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.util.ValidationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.ReservationTM;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

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
    public TableView<ReservationTM> tblReservationDetails;
    public JFXTextField txtPaidKeyMoney;
    public JFXButton btnReserve;
    public JFXComboBox<StudentDTO> cmbStudentID;
    public AnchorPane makeReservationFormContext;

    MakeReservationBO makeReservationBO = (MakeReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MAKERESERVATION);

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    LinkedHashMap<TextField, Pattern> map2=new LinkedHashMap<>();

    public void initialize() {
        tblReservationDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("res_id"));
        tblReservationDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("date"));
        tblReservationDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("roomId"));
        tblReservationDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblReservationDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("status"));

        lblReservationID.setText(generateNewReservationID());

        lblDate.setText(LocalDate.now().toString());

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
            if (newValue!=null){
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
            }
        });

        cmbRoomTypeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                txtType.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtQtyOnHand.setDisable(false);
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                txtQtyOnHand.setText(String.valueOf(newValue.getQty()));
            }
        });

        Pattern idPattern = Pattern.compile("^S[0-9]{3,}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPatten = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern contactPattern = Pattern.compile("^(078|075|077|074|071|070|076|072|034)[0-9]{7}$");
        Pattern dobPattern=Pattern.compile("^(19[0-9]{2}|20[0-9]{2})-([11|12]{2}|0[1|2|3|4|5|6|7|8|9])-([1-2]{1}[0-9]{1}|0[1-9]{1}|3[1|0])$");
        Pattern paidKeyMoneyPattern = Pattern.compile("^[0-9][0-9]*(.[0-9]{2})?$");

        map.put(txtStudentId,idPattern);
        map.put(txtName,namePattern);
        map.put(txtAddress,addressPatten);
        map.put(txtContactNo,contactPattern);
        map.put(txtDob,dobPattern);

        map2.put(txtPaidKeyMoney,paidKeyMoneyPattern);

        loadAllReservationDetails();
    }

    private void loadAllReservationDetails() {
        List<ReservationDTO> allReservations = makeReservationBO.getAllReservations();
        for (ReservationDTO reservationDTO : allReservations) {
            tblReservationDetails.getItems().add(new ReservationTM(reservationDTO.getRes_id(),reservationDTO.getDate(),reservationDTO.getRoom().getRoomTypeId(),reservationDTO.getStudent().getStudentId(),reservationDTO.getStatus()));
        }
    }

    public String generateNewReservationID() {
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
        btnReserve.setDisable(true);
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        if (btnNewStudent.getText().equalsIgnoreCase("+New Student")) {
            cmbStudentID.setVisible(false);
            txtStudentId.setVisible(true);
            txtName.clear();
            txtAddress.clear();
            txtContactNo.clear();
            txtDob.clear();
            cmbGender.getSelectionModel().clearSelection();
            txtName.setDisable(false);
            txtName.setEditable(true);
            txtAddress.setDisable(false);
            txtAddress.setEditable(true);
            txtContactNo.setDisable(false);
            txtContactNo.setEditable(true);
            txtDob.setDisable(false);
            txtDob.setEditable(true);
            cmbGender.setDisable(false);
            txtStudentId.requestFocus();
            btnNewStudent.setText("Add Student");

        } else if (btnNewStudent.getText().equalsIgnoreCase("Add Student")) {
            if (makeReservationBO.checkTheStudentIsExist(txtStudentId.getText())) {
                new Alert(Alert.AlertType.WARNING, "Student Id Already Exist").show();

                ObservableList<StudentDTO> students = cmbStudentID.getItems();
                for (StudentDTO student : students) {
                    if (student.getStudentId().equalsIgnoreCase(txtStudentId.getText())) {
                        txtStudentId.clear();
                        txtStudentId.setVisible(false);
                        cmbStudentID.setVisible(true);
                        txtName.setEditable(false);
                        txtAddress.setEditable(false);
                        txtContactNo.setEditable(false);
                        txtDob.setEditable(false);
                        btnNewStudent.setText("+New Student");
                        cmbStudentID.getSelectionModel().select(student);
                        txtName.setText(student.getName());
                        txtAddress.setText(student.getAddress());
                        txtContactNo.setText(student.getContactNo());
                        txtDob.setText(String.valueOf(student.getDob()));
                        cmbGender.getSelectionModel().select(student.getGender());
                    }
                }
            } else {
                StudentDTO studentDTO = new StudentDTO(txtStudentId.getText(), txtName.getText(), txtAddress.getText(), txtContactNo.getText(), LocalDate.parse(txtDob.getText()), cmbGender.getValue());
                if (makeReservationBO.saveStudent(studentDTO)) {
                    txtStudentId.clear();
                    txtStudentId.setVisible(false);
                    cmbStudentID.setVisible(true);
                    cmbStudentID.getItems().add(studentDTO);
                    cmbStudentID.getSelectionModel().select(studentDTO);
                }
            }
        }
    }

    public void btnReserveOnAction(ActionEvent actionEvent) {
        ReserveRoom();
    }

    private void ReserveRoom() {
        double keyMoney=Double.parseDouble(txtKeyMoney.getText());
        double paidKeyMoney=Double.parseDouble(txtPaidKeyMoney.getText());
        String status="";
        if (keyMoney==paidKeyMoney){
            status="Paid";
        }else{
            double balanceToPay=keyMoney-paidKeyMoney;
            status=String.valueOf(balanceToPay);
        }
        StudentDTO studentDTO = cmbStudentID.getValue();
        RoomDTO roomDTO = cmbRoomTypeID.getValue();
        Student student=new Student(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContactNo(),studentDTO.getDob(),studentDTO.getGender());
        Room room=new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty()-1);

        if (makeReservationBO.saveReservation(new ReservationDTO(lblReservationID.getText(), LocalDate.parse(lblDate.getText()),status,student,room))) {
            tblReservationDetails.getItems().add(new ReservationTM(lblReservationID.getText(),LocalDate.parse(lblDate.getText()),room.getRoomTypeId(),student.getStudentId(),status));
            new Alert(Alert.AlertType.CONFIRMATION,"Reserved").show();
            makeReservationBO.updateRoomDetails(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
            txtPaidKeyMoney.clear();
            cmbStudentID.getSelectionModel().clearSelection();
            cmbRoomTypeID.getSelectionModel().clearSelection();
            btnReserve.setDisable(true);
            txtName.clear();
            txtAddress.clear();
            txtContactNo.clear();
            txtDob.clear();
            cmbGender.getSelectionModel().clearSelection();
            txtType.clear();
            txtKeyMoney.clear();
            txtQtyOnHand.clear();
            lblReservationID.setText(generateNewReservationID());
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnNewStudent);
        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map, btnNewStudent);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }
        }

        ValidationUtil.validate(map2,btnReserve);
        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map2, btnReserve);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){
                ReserveRoom();
            }
        }
    }
}
