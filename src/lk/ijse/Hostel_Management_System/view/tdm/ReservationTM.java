package lk.ijse.Hostel_Management_System.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationTM {
    String res_id;
    LocalDate date;
    String roomId;
    String studentId;
    String status;
}
