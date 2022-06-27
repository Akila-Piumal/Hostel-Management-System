package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.UserManagementBO;
import lk.ijse.Hostel_Management_System.dto.UserDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;

public class UserManagementFormController {
    public static String userName;
    public static String password;
    public TextField txtUserName;
    public TextField txtNewUserName;
    public TextField txtNewPassword;
    public TextField txtOldPassword;
    public JFXButton btnUpdate;

    private final UserManagementBO userManagementBO = (UserManagementBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERMANAGEMENT);
    public AnchorPane userManagementFormContext;

    public void initialize(){
        AnimationUtil.windowAnimation(userManagementFormContext);

        txtUserName.setText(userName);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
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
}
