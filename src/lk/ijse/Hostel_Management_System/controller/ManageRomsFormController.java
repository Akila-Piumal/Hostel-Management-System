package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.custom.ManageRoomBO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.util.ValidationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.RoomTM;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ManageRomsFormController {
    public JFXButton btnNewRoom;
    public JFXComboBox<RoomDTO> cmbRoomTypeID;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<RoomTM> tblRoomDetails;
    public AnchorPane manageRoomsFormContext;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnNewRoomType;
    public JFXTextField txtRoomTypeID;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    LinkedHashMap<TextField, Pattern> map2 = new LinkedHashMap<>();
    LinkedHashMap<TextField, Pattern> map3 = new LinkedHashMap<>();

    ManageRoomBO manageRoomBO = (ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGEROOMS);

    public void initialize() {
        tblRoomDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("roomTypeId"));
        tblRoomDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("type"));
        tblRoomDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("keyMoney"));
        tblRoomDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("qty"));

        AnimationUtil.windowAnimation(manageRoomsFormContext);

        List<RoomDTO> allRooms = manageRoomBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {
            cmbRoomTypeID.getItems().add(roomDTO);
        }

        txtRoomTypeID.setVisible(false);
        clearFields();

        cmbRoomTypeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                txtQtyOnHand.setText(String.valueOf(newValue.getQty()));
                txtQty.clear();
                txtQty.requestFocus();
            }
        });

        tblRoomDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtRoomTypeID.setVisible(false);
                cmbRoomTypeID.setVisible(true);
                cmbRoomTypeID.setDisable(true);
                txtQty.setDisable(true);
                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                cmbRoomTypeID.getSelectionModel().select(new RoomDTO(newValue.getRoomTypeId(), newValue.getType(), newValue.getKeyMoney(), newValue.getQty()));
                txtType.setDisable(false);
                txtQtyOnHand.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtKeyMoney.setEditable(true);
                txtQtyOnHand.setEditable(true);
            }
        });

        Pattern idPattern = Pattern.compile("^RM-[0-9]{4}$");
        Pattern typePattern = Pattern.compile("^[A-z/ 0-9]{2,15}$");
        Pattern keyMoneyPattern = Pattern.compile("^[0-9][0-9]{0,}(.[0-9]{2})?$");
        Pattern qtyOnHandPattern = Pattern.compile("^[0-9]+$");
        Pattern qtyPattern = Pattern.compile("^[0-9]+$");

        map.put(txtRoomTypeID, idPattern);
        map.put(txtType, typePattern);
        map.put(txtKeyMoney, keyMoneyPattern);
        map.put(txtQtyOnHand, qtyOnHandPattern);

        map2.put(txtType, typePattern);
        map2.put(txtKeyMoney, keyMoneyPattern);
        map2.put(txtQtyOnHand, qtyOnHandPattern);

        map3.put(txtQty, qtyPattern);

        loadAllRoomDetails();
    }

    private void loadAllRoomDetails() {
        List<RoomDTO> allRooms = manageRoomBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {
            tblRoomDetails.getItems().add(new RoomTM(roomDTO.getRoomTypeId(), roomDTO.getType(), roomDTO.getKeyMoney(), roomDTO.getQty()));
        }
    }

    private void clearFields() {
        cmbRoomTypeID.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtQtyOnHand.clear();
        txtQty.clear();
        txtType.clear();
        txtKeyMoney.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtType.setDisable(true);
        txtQty.setDisable(true);
        cmbRoomTypeID.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
    }

    public void btnNewRoomOnAction(ActionEvent actionEvent) {
        txtRoomTypeID.clear();
        txtQty.clear();
        txtKeyMoney.clear();
        txtType.clear();
        txtQtyOnHand.clear();
        txtRoomTypeID.setVisible(false);
        cmbRoomTypeID.setVisible(true);
        cmbRoomTypeID.setDisable(false);
        txtQty.setDisable(false);
        txtType.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtKeyMoney.setDisable(false);
        cmbRoomTypeID.requestFocus();
        btnSave.setText("Add");
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        txtType.setEditable(false);
        txtKeyMoney.setEditable(false);
        txtQtyOnHand.setEditable(false);
    }

    public void btnSaveRoomOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Add")) {
            updateQty();
        } else if (btnSave.getText().equalsIgnoreCase("Save")) {
            saveRoom();
        } else if (btnSave.getText().equalsIgnoreCase("Update")) {
            updateRoom();
        }
    }

    private void updateQty() {
        ObservableList<RoomTM> items = tblRoomDetails.getItems();
        for (RoomTM item : items) {
            if (item.getRoomTypeId().equalsIgnoreCase(cmbRoomTypeID.getValue().getRoomTypeId())) {
                int qty = item.getQty() + Integer.parseInt(txtQty.getText());
                if (manageRoomBO.updateQty(item.getRoomTypeId(), qty)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated..!").show();
                    item.setQty(qty);
                    txtQtyOnHand.setText(String.valueOf(item.getQty()));
                    clearFields();
                }
            }
        }
        tblRoomDetails.refresh();
    }

    private void updateRoom() {
        if (manageRoomBO.updateRoom(new RoomDTO(cmbRoomTypeID.getValue().getRoomTypeId(), txtType.getText(), txtKeyMoney.getText(), Integer.parseInt(txtQtyOnHand.getText())))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..!").show();
            RoomTM selectedItem = tblRoomDetails.getSelectionModel().getSelectedItem();
            selectedItem.setKeyMoney(txtKeyMoney.getText());
            selectedItem.setQty(Integer.parseInt(txtQtyOnHand.getText()));
            selectedItem.setType(txtType.getText());
            tblRoomDetails.getSelectionModel().clearSelection();
            cmbRoomTypeID.getSelectionModel().clearSelection();
            txtType.clear();
            txtType.setDisable(true);
            txtKeyMoney.clear();
            txtKeyMoney.setDisable(true);
            txtQtyOnHand.clear();
            txtQtyOnHand.setDisable(true);
            btnSave.setDisable(true);
            btnDelete.setDisable(true);
            tblRoomDetails.refresh();
        }
    }

    private void saveRoom() {
        if (manageRoomBO.saveRoom(new RoomDTO(txtRoomTypeID.getText(), txtType.getText(), txtKeyMoney.getText(), Integer.parseInt(txtQtyOnHand.getText())))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..!").show();
            tblRoomDetails.getItems().add(new RoomTM(txtRoomTypeID.getText(), txtType.getText(), txtKeyMoney.getText(), Integer.parseInt(txtQtyOnHand.getText())));
            tblRoomDetails.refresh();
            txtRoomTypeID.clear();
            txtType.clear();
            txtKeyMoney.clear();
            txtQtyOnHand.clear();
            btnSave.setDisable(true);
            txtRoomTypeID.setVisible(false);
            cmbRoomTypeID.setVisible(true);
            txtType.setDisable(true);
            txtKeyMoney.setDisable(true);
            txtQtyOnHand.setDisable(true);
        }
    }

    public void btnDeleteRoomOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure..?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            if (manageRoomBO.deleteRoom(cmbRoomTypeID.getValue().getRoomTypeId())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted..!!").show();
                tblRoomDetails.getItems().remove(new RoomTM(cmbRoomTypeID.getValue().getRoomTypeId(), txtType.getText(), txtKeyMoney.getText(), Integer.parseInt(txtQtyOnHand.getText())));
                tblRoomDetails.getSelectionModel().clearSelection();
                cmbRoomTypeID.getSelectionModel().clearSelection();
                txtType.clear();
                txtType.setDisable(true);
                txtKeyMoney.clear();
                txtKeyMoney.setDisable(true);
                txtQtyOnHand.clear();
                txtQtyOnHand.setDisable(true);
                btnSave.setDisable(true);
                btnDelete.setDisable(true);
            }
        }
    }

    public void btnNewRoomTypeOnAction(ActionEvent actionEvent) {
        cmbRoomTypeID.setVisible(false);
        cmbRoomTypeID.setDisable(true);
        txtRoomTypeID.setVisible(true);
        txtRoomTypeID.clear();
        txtType.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtType.setEditable(true);
        txtQtyOnHand.setEditable(true);
        txtKeyMoney.setEditable(true);
        txtType.clear();
        txtQtyOnHand.clear();
        txtQty.clear();
        txtKeyMoney.clear();
        txtQty.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setText("Save");
        btnSave.setDisable(false);
        txtRoomTypeID.requestFocus();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map, btnSave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, btnSave);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                if (btnSave.getText().equals("Save")) {
                    saveRoom();
                }
            }
        }


        ValidationUtil.validate(map2, btnSave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map2, btnSave);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                if (btnSave.getText().equals("Update")) {
                    updateRoom();
                }
            }
        }

        ValidationUtil.validate(map3, btnSave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map3, btnSave);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                if (btnSave.getText().equals("Add")) {
                    updateQty();
                }
            }
        }
    }
}
