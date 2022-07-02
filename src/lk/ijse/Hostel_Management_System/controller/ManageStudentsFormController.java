package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.ManageStudentsBO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.util.ValidationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.StudentTM;
import org.apache.commons.lang3.Validate;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ManageStudentsFormController {
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDob;
    public JFXComboBox<String> cmbGender;
    public TableView<StudentTM> tblStudentDetails;
    public AnchorPane manageStudentsFormContext;
    public JFXButton btnDelete;
    public JFXButton btnAddStudent;
    public JFXButton btnSave;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    ManageStudentsBO manageStudentsBO = (ManageStudentsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGESTUDENTS);

    public void initialize() {
        AnimationUtil.windowAnimation(manageStudentsFormContext);

        tblStudentDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblStudentDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("name"));
        tblStudentDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("address"));
        tblStudentDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("contactNo"));
        tblStudentDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("dob"));
        tblStudentDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("gender"));

        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("FeMale");

        clearFields();

        tblStudentDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");

            if (newValue != null) {
                txtStudentId.setText(newValue.getStudentId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContactNo.setText(newValue.getContactNo());
                txtDob.setText(String.valueOf(newValue.getDob()));
                cmbGender.getSelectionModel().select(newValue.getGender());

                txtName.setDisable(false);
                txtStudentId.setDisable(false);
                txtAddress.setDisable(false);
                txtDob.setDisable(false);
                txtContactNo.setDisable(false);
                cmbGender.setDisable(false);
            }
        });

        Pattern idPattern = Pattern.compile("^S[0-9]{3,}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPatten = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern contactPattern = Pattern.compile("^(078|075|077|074|071|070|076|072|034)[0-9]{7}$");
        Pattern dobPattern = Pattern.compile("^(19[0-9]{2}|20[0-9]{2})-([11|12]{2}|0[1|2|3|4|5|6|7|8|9])-([1-2]{1}[0-9]{1}|0[1-9]{1}|3[1|0])$");

        map.put(txtStudentId, idPattern);
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPatten);
        map.put(txtContactNo, contactPattern);
        map.put(txtDob, dobPattern);

        loadAllStudentDetails();

    }

    private void clearFields() {
        txtStudentId.clear();
        txtName.clear();
        txtContactNo.clear();
        txtAddress.clear();
        txtDob.clear();
        cmbGender.getSelectionModel().clearSelection();
        txtName.setDisable(true);
        txtStudentId.setDisable(true);
        txtAddress.setDisable(true);
        txtDob.setDisable(true);
        txtContactNo.setDisable(true);
        cmbGender.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadAllStudentDetails() {
        tblStudentDetails.getItems().clear();
        List<StudentDTO> allStudents = manageStudentsBO.getAllStudents();
        for (StudentDTO student : allStudents) {
            tblStudentDetails.getItems().add(new StudentTM(student.getStudentId(), student.getName(), student.getAddress(), student.getContactNo(), student.getDob(), student.getGender()));
        }
    }

    public void btnDeleteStudentOnAction(ActionEvent actionEvent) {
        String studentId = tblStudentDetails.getSelectionModel().getSelectedItem().getStudentId();
        if (!existStudent(studentId)) {
            new Alert(Alert.AlertType.WARNING, "Student is not Exists !!").show();
        }
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            if (manageStudentsBO.deleteStudent(studentId)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted..!").show();
                tblStudentDetails.getItems().remove(tblStudentDetails.getSelectionModel().getSelectedItem());
                tblStudentDetails.getSelectionModel().clearSelection();
                clearFields();
            }
        }
    }

    private boolean existStudent(String studentId) {
        return manageStudentsBO.checkStudentIsExists(studentId);
    }

    public void btnAddNewStudentOnAction(ActionEvent actionEvent) {
        txtStudentId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtDob.setDisable(false);
        cmbGender.setDisable(false);
        txtStudentId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtDob.clear();
        cmbGender.getSelectionModel().clearSelection();
        tblStudentDetails.getSelectionModel().clearSelection();
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        txtStudentId.requestFocus();
    }

    public void btnSaveStudentOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Save")) {
            if (existStudent(txtStudentId.getText())) {
                new Alert(Alert.AlertType.WARNING, "Student Already Exists").show();
            }
            manageStudentsBO.saveStudent(new StudentDTO(txtStudentId.getText(), txtName.getText(), txtAddress.getText(), txtContactNo.getText(), LocalDate.parse(txtDob.getText()), cmbGender.getValue()));
            tblStudentDetails.getItems().add(new StudentTM(txtStudentId.getText(), txtName.getText(), txtAddress.getText(), txtContactNo.getText(), LocalDate.parse(txtDob.getText()), cmbGender.getValue()));
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..!").show();
            txtStudentId.clear();
            txtName.clear();
            txtAddress.clear();
            txtDob.clear();
            txtContactNo.clear();
            cmbGender.getSelectionModel().clearSelection();
        } else {
            if (!existStudent(txtStudentId.getText())) {
                new Alert(Alert.AlertType.ERROR, "Student Not Exists").show();
            }
            manageStudentsBO.updateStudent(new StudentDTO(txtStudentId.getText(), txtName.getText(), txtAddress.getText(), txtContactNo.getText(), LocalDate.parse(txtDob.getText()), cmbGender.getValue()));
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..!").show();

            StudentTM selectedItem = tblStudentDetails.getSelectionModel().getSelectedItem();
            selectedItem.setStudentId(txtStudentId.getText());
            selectedItem.setName(txtName.getText());
            selectedItem.setAddress(txtAddress.getText());
            selectedItem.setContactNo(txtContactNo.getText());
            selectedItem.setDob(LocalDate.parse(txtDob.getText()));
            selectedItem.setGender(cmbGender.getValue());
            tblStudentDetails.refresh();

            clearFields();
            tblStudentDetails.getSelectionModel().clearSelection();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map, btnSave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, btnSave);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }
        }
    }
}
