package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.custom.LoginBO;
import lk.ijse.Hostel_Management_System.entity.User;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;

import java.io.IOException;
import java.util.List;

public class LoginFormController {
    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public CheckBox cbShowPassword;
    public JFXTextField txtPassword;
    public AnchorPane LoginFormContext;

    public void initialize() {
        txtPassword.textProperty().bind(pwdPassword.textProperty());
        txtPassword.visibleProperty().bind(cbShowPassword.selectedProperty());
        pwdPassword.visibleProperty().bind(cbShowPassword.selectedProperty().not());

        AnimationUtil.windowAnimation(LoginFormContext);
    }

    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (txtUserName.getText() != null) {
                pwdPassword.requestFocus();
                if (pwdPassword.getText().length() > 0) {
                    try {
                        loginToSystem();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void loginToSystem() throws IOException {
        List<User> users = loginBO.getAllUsers();

        for (User user : users) {
            if (user.getUserName().equals(txtUserName.getText()) && user.getPassword().equals(txtPassword.getText())) {
                Stage stage = (Stage) LoginFormContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
                stage.show();
                return;
            }
        }
        new Alert(Alert.AlertType.WARNING,"Incorrect User name or Password !!!").show();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        try {
            loginToSystem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
