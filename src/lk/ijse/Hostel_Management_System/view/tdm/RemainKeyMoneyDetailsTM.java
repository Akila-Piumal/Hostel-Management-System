package lk.ijse.Hostel_Management_System.view.tdm;

import lk.ijse.Hostel_Management_System.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemainKeyMoneyDetailsTM {
    String studentId;
    String name;
    String address;
    String con_No;
    String res_Id;
    LocalDate date;
    String roomId;
    String status;
}
