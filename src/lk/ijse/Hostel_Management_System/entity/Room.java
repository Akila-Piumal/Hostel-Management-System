package lk.ijse.Hostel_Management_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Room {
    @Id
    @Column(name = "Room_Type_Id")
    private String roomTypeId;

    @Column(nullable = false)
    private String type;

    private String keyMoney;

    @Column(name = "Rooms_Qty",nullable = false)
    private int qty;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservationList=new ArrayList<>();

}
