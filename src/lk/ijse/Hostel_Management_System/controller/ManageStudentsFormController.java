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
import lk.ijse.Hostel_Management_System.bo.custom.ManageStudentsBO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.StudentTM;

import java.util.List;

public class ManageStudentsFormController {
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDob;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnAdd;
    public TableView<StudentTM> tblStudentDetails;
    public AnchorPane manageStudentsFormContext;

    ManageStudentsBO manageStudentsBO = (ManageStudentsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGESTUDENTS);

    public void initialize(){
        AnimationUtil.windowAnimation(manageStudentsFormContext);

        tblStudentDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblStudentDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("name"));
        tblStudentDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("address"));
        tblStudentDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("contactNo"));
        tblStudentDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("dob"));
        tblStudentDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("gender"));

        loadAllStudentDetails();
    }

    private void loadAllStudentDetails() {
        tblStudentDetails.getItems().clear();
        List<StudentDTO> allStudents = manageStudentsBO.getAllStudents();
        for (StudentDTO student : allStudents) {
            tblStudentDetails.getItems().add(new StudentTM(student.getStudentId(),student.getName(),student.getAddress(),student.getContactNo(),student.getDob(),student.getGender()));
        }
    }

    public void btnAddStudentOnAction(ActionEvent actionEvent) {

    }
}
