package lk.ijse.Hostel_Management_System.controller;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.bo.custom.KeyMoneyRemainStudentsBO;
import lk.ijse.Hostel_Management_System.bo.custom.impl.KeyMoneyRemainStudentsBOImpl;
import lk.ijse.Hostel_Management_System.entity.Room;
import lk.ijse.Hostel_Management_System.util.FactoryConfiguration;
import lk.ijse.Hostel_Management_System.view.tdm.RemainKeyMoneyDetailsTM;


import java.time.LocalDate;
import java.util.List;

public class KeyMoneyRemainStudentsFormController {

    private final KeyMoneyRemainStudentsBO keyMoneyRemainStudentsBO = (KeyMoneyRemainStudentsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.KEYMONEYREMAINSTUDENTS);
    public TableView<RemainKeyMoneyDetailsTM> tblKeyMoneyRemainDetails;

    public void initialize() {
        tblKeyMoneyRemainDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblKeyMoneyRemainDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("name"));
        tblKeyMoneyRemainDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("address"));
        tblKeyMoneyRemainDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("con_No"));
        tblKeyMoneyRemainDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("res_Id"));
        tblKeyMoneyRemainDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("date"));
        tblKeyMoneyRemainDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("roomId"));
        tblKeyMoneyRemainDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory("status"));

        loadAllData();
    }

    private void loadAllData() {
        List<Object[]> list = keyMoneyRemainStudentsBO.getKeyMoneyAndStudentDetails();
        for (Object[] objects : list) {
            String stu_Id= (String) objects[0];
            String name= (String) objects[1];
            String address= (String) objects[2];
            String con_No= (String) objects[3];
            String res_Id= (String) objects[4];
            LocalDate date= (LocalDate) objects[5];
            String roomId= (String) objects[6];
            String status= (String) objects[7];
            tblKeyMoneyRemainDetails.getItems().add(new RemainKeyMoneyDetailsTM(stu_Id,name,address,con_No,res_Id,date,roomId,status));
        }
    }
}
