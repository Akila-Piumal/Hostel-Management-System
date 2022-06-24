package lk.ijse.Hostel_Management_System.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomTM {
    private String roomTypeId;
    private String type;
    private String keyMoney;
    private int qty;
}
