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
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
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
    public JFXTextField txtQty;
    public JFXButton btnAddToList;
    public TableView<ReservationTM> tblReservationDetails;
    public Label lblTotal;
    public JFXTextField txtPaidKeyMoney;
    public JFXButton btnReserve;
    public JFXComboBox<StudentDTO> cmbStudentID;
    public AnchorPane makeReservationFormContext;

    MakeReservationBO makeReservationBO = (MakeReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MAKERESERVATION);

    public void initialize() {
        tblReservationDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("roomTypeID"));
        tblReservationDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("type"));
        tblReservationDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("keyMoney"));
        tblReservationDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        tblReservationDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("qty"));
        tblReservationDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("total"));

        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservationDetails.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnRemove = new Button("Remove");
            btnRemove.setOnAction(event -> {
                tblReservationDetails.getItems().remove(param.getValue());
                tblReservationDetails.getSelectionModel().clearSelection();
                cmbRoomTypeID.getSelectionModel().clearSelection();
                txtType.clear();
                txtKeyMoney.clear();
                txtQtyOnHand.clear();
                cmbRoomTypeID.setDisable(false);
                cmbRoomTypeID.requestFocus();

                if (tblReservationDetails.getItems().isEmpty()) {
                    btnNewStudent.setDisable(false);
                    cmbStudentID.setDisable(false);
                }
//                calculateTotal();
            });
            return new ReadOnlyObjectWrapper<>(btnRemove);
        });

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
            txtQty.setDisable(false);
            btnAddToList.setDisable(false);
            if (newValue != null) {
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                Optional<ReservationTM> optReservationDetail = tblReservationDetails.getItems().stream().filter(detail -> detail.getRoomTypeID().equals(newValue.getRoomTypeId())).findFirst();
                txtQtyOnHand.setText((optReservationDetail.isPresent() ? newValue.getQty() - optReservationDetail.get().getQty() : newValue.getQty()) + "");
            }

        });

        tblReservationDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cmbRoomTypeID.getSelectionModel().select(new RoomDTO(newValue.getRoomTypeID(), newValue.getType(), newValue.getKeyMoney(), newValue.getQtyOnHand()));
                cmbRoomTypeID.setDisable(true);
//                cmbRoomTypeID.setDisable(true);
                btnAddToList.setText("Update");
                txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
                txtQty.setText(newValue.getQty() + "");
                txtQty.requestFocus();
            } else {
                btnAddToList.setText("Add to List");
                cmbRoomTypeID.getSelectionModel().clearSelection();
                txtQty.clear();
            }
        });
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
        txtQty.setDisable(true);
        btnAddToList.setDisable(true);
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

    public void btnAddToListOnAction(ActionEvent actionEvent) {
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = Double.parseDouble(txtKeyMoney.getText()) * qty;

        boolean exists = tblReservationDetails.getItems().stream().anyMatch(detail -> detail.getRoomTypeID().equalsIgnoreCase(cmbRoomTypeID.getValue().getRoomTypeId()));

        if (exists) {
            ReservationTM reservationTM = tblReservationDetails.getItems().stream().filter(detail -> detail.getRoomTypeID().equals(cmbRoomTypeID.getValue().getRoomTypeId())).findFirst().get();

            if (btnAddToList.getText().equalsIgnoreCase("Update")) {
                reservationTM.setQty(qty);
                reservationTM.setTotal(total);
                cmbRoomTypeID.setDisable(false);
                tblReservationDetails.getSelectionModel().clearSelection();
            } else {
                reservationTM.setQty(reservationTM.getQty() + qty);
                total = (Double.parseDouble(txtKeyMoney.getText()) * reservationTM.getQty());
                reservationTM.setTotal(total);
            }
            tblReservationDetails.refresh();

        } else {
            tblReservationDetails.getItems().add(new ReservationTM(cmbRoomTypeID.getValue().getRoomTypeId(), txtType.getText(), txtKeyMoney.getText(), qtyOnHand, qty, total));
        }
        txtType.clear();
        txtKeyMoney.clear();
        txtQtyOnHand.clear();
        txtQty.clear();
        btnAddToList.setDisable(true);
        btnNewStudent.setDisable(true);
        cmbStudentID.setDisable(true);
        cmbRoomTypeID.getSelectionModel().clearSelection();
        btnAddToList.setDisable(true);
    }

    public void btnReserveOnAction(ActionEvent actionEvent) {

    }
}
