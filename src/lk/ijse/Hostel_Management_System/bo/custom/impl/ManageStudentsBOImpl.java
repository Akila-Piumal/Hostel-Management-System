package lk.ijse.Hostel_Management_System.bo.custom.impl;

import lk.ijse.Hostel_Management_System.bo.custom.ManageStudentsBO;
import lk.ijse.Hostel_Management_System.dao.DAOFactory;
import lk.ijse.Hostel_Management_System.dao.custom.StudentDAO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ManageStudentsBOImpl implements ManageStudentsBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Student student : all) {
            studentDTOList.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getContactNo(), student.getDob(), student.getGender()));
        }
        return studentDTOList;
    }

    @Override
    public boolean deleteStudent(String studentId) {
        return studentDAO.delete(studentId);
    }

    @Override
    public boolean checkStudentIsExists(String id) {
        return studentDAO.exist(id);
    }

}
