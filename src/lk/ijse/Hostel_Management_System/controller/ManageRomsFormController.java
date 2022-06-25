package lk.ijse.Hostel_Management_System.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.custom.ManageRoomBO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.util.AnimationUtil;
import lk.ijse.Hostel_Management_System.view.tdm.RoomTM;

import java.util.List;
import java.util.Optional;

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
            txtType.setText(newValue.getType());
            txtKeyMoney.setText(newValue.getKeyMoney());
            txtQtyOnHand.setText(String.valueOf(newValue.getQty()));
            txtQty.clear();
        });

        tblRoomDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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
        });

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
            ObservableList<RoomTM> items = tblRoomDetails.getItems();
            for (RoomTM item : items) {
                if (item.getRoomTypeId().equalsIgnoreCase(cmbRoomTypeID.getValue().getRoomTypeId())) {
                    int qty = item.getQty() + Integer.parseInt(txtQty.getText());
                    if (manageRoomBO.updateQty(item.getRoomTypeId(), qty)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated..!").show();
                        item.setQty(qty);
                        txtQtyOnHand.setText(String.valueOf(item.getQty()));
                        txtQty.clear();
                    }
                }
            }
            tblRoomDetails.refresh();
        } else if (btnSave.getText().equalsIgnoreCase("Save")) {
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
        } else if (btnSave.getText().equalsIgnoreCase("Update")) {
            if (manageRoomBO.updateRoom(new RoomDTO(cmbRoomTypeID.getValue().getRoomTypeId(), txtType.getText(), txtKeyMoney.getText(), Integer.parseInt(txtQtyOnHand.getText())))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..!").show();
                RoomTM selectedItem = tblRoomDetails.getSelectionModel().getSelectedItem();
                selectedItem.setKeyMoney(txtKeyMoney.getText());
                selectedItem.setQty(Integer.parseInt(txtQtyOnHand.getText()));
                selectedItem.setType(txtType.getText());
                tblRoomDetails.refresh();
            }
        }
    }

    public void btnDeleteRoomOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"Are You Sure..?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)){
            if (manageRoomBO.deleteRoom(cmbRoomTypeID.getValue().getRoomTypeId())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted..!!").show();
                tblRoomDetails.getItems().remove(new RoomTM(cmbRoomTypeID.getValue().getRoomTypeId(),txtType.getText(),txtKeyMoney.getText(),Integer.parseInt(txtQtyOnHand.getText())));
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
}
