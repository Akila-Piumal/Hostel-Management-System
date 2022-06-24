package lk.ijse.Hostel_Management_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
    private String roomTypeId;
    private String type;
    private String keyMoney;
    private int qty;
}
