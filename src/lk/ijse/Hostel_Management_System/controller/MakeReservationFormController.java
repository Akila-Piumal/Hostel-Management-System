package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.custom.MakeReservationBO;
import lk.ijse.Hostel_Management_System.dto.ReservationDTO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.entity.Room;
import lk.ijse.Hostel_Management_System.entity.Student;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.ReservationTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

        cmbRoomTypeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtType.setDisable(false);
            txtKeyMoney.setDisable(false);
            txtQtyOnHand.setDisable(false);
            txtType.setText(newValue.getType());
            txtKeyMoney.setText(newValue.getKeyMoney());
            txtQtyOnHand.setText(String.valueOf(newValue.getQty()));
        });

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
        Room room=new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty());

        if (makeReservationBO.saveReservation(new ReservationDTO(lblReservationID.getText(), LocalDate.parse(lblDate.getText()),status,student,room))) {
            tblReservationDetails.getItems().add(new ReservationTM(lblReservationID.getText(),LocalDate.parse(lblDate.getText()),room.getRoomTypeId(),student.getStudentId(),status));
            new Alert(Alert.AlertType.CONFIRMATION,"Reserved").show();
        }

    }
}
