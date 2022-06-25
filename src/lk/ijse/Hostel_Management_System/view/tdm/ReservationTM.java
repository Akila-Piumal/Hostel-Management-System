package lk.ijse.Hostel_Management_System.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationTM {
    String roomTypeID;
    String type;
    String keyMoney;
    int qtyOnHand;
    int qty;
    double total;
}
