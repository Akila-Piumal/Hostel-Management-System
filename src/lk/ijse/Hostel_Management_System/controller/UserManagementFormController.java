package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.UserManagementBO;
import lk.ijse.Hostel_Management_System.dto.UserDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.util.ValidationUtil;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UserManagementFormController {
    public static String userName;
    public static String password;
    public TextField txtUserName;
    public TextField txtNewUserName;
    public TextField txtNewPassword;
    public TextField txtOldPassword;
    public JFXButton btnUpdate;
    public AnchorPane userManagementFormContext;
    private final UserManagementBO userManagementBO = (UserManagementBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERMANAGEMENT);

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();

    public void initialize(){
        AnimationUtil.windowAnimation(userManagementFormContext);

        txtUserName.setText(userName);

        btnUpdate.setDisable(true);
        txtNewUserName.requestFocus();

        Pattern userNamePattern = Pattern.compile("^[A-z@0-9]{5,}$");
        Pattern passwordPattern = Pattern.compile("^[A-z@0-9]{5,}$");

        map.put(txtNewPassword,userNamePattern);
        map.put(txtNewPassword,passwordPattern);
        map.put(txtOldPassword,passwordPattern);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        updateDetails();
    }

    private void updateDetails() {
        UserDTO user = userManagementBO.getUserFromUserNameAndPassword(userName, password);

        String newUserName="";
        if (txtNewUserName.getText().length()>0){
            newUserName=txtNewUserName.getText();
        }else{
            newUserName=userName;
        }

        if (txtOldPassword.getText().equals(password)){
            if (userManagementBO.updateUser(new UserDTO(user.getUserId(),newUserName,txtNewPassword.getText()))) {
                new Alert(Alert.AlertType.CONFIRMATION,"Updated..!").show();
                txtNewUserName.clear();
                txtNewPassword.clear();
                txtOldPassword.clear();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Old Password is Incorrect..!").show();
            txtOldPassword.clear();
            txtNewPassword.clear();
        }
    }

    public void textFields_key_released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnUpdate);
        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map, btnUpdate);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){
                updateDetails();
            }
        }
    }
}
