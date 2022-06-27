package lk.ijse.Hostel_Management_System.bo.custom;

import lk.ijse.Hostel_Management_System.bo.SuperBO;

import java.util.List;

public interface KeyMoneyRemainStudentsBO extends SuperBO {
    List<Object[]> getKeyMoneyAndStudentDetails();
}
