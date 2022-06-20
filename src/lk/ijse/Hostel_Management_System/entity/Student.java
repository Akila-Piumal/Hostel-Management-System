package lk.ijse.Hostel_Management_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Student {
    @Id
    private String studentId;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String address;
    @Column(nullable = false)
    private String contactNo;
    @Column(nullable = false)
    private LocalDate dob;
    @Column(nullable = false)
    private String gender;

    //slkslksss
}
