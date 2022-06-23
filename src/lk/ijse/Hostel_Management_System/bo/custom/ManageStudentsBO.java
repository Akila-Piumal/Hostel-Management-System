package lk.ijse.Hostel_Management_System.bo.custom;

import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.entity.Student;

import java.util.List;

public interface ManageStudentsBO extends SuperBO {
    List<StudentDTO> getAllStudents();

    boolean deleteStudent(String studentId);

    boolean checkStudentIsExists(String id);

    boolean saveStudent(StudentDTO dto);

    boolean updateStudent(StudentDTO dto);
}
