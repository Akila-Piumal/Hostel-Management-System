package lk.ijse.Hostel_Management_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Room {
    @Id
    @Column(name = "Room_Type_Id")
    String roomTypeId;
    @Column(nullable = false)
    String type;
    String keyMoney;
    @Column(name = "Rooms_Qty",nullable = false)
    int qty;
}
