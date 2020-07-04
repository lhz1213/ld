package lhz.schoolhelper.service;

import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.dto.StudentDto;

public interface StudentService {

	/**
	 * 根据id查询学生信息
	 * 
	 * @param id
	 * @return StudentDto
	 */
	StudentDto getStudentById(Integer id);

	/**
	 * 根据学号，密码查询学生
	 * 
	 * @param identifer
	 * @param password
	 * @return StudentDto
	 */
	StudentDto getStudentByIdentiferAndPassword(String identifer, String password);

	/**
	 * 添加学生
	 * 
	 * @param studentDo
	 * @return 失败信息，null:成功
	 */
	String addStudent(StudentDo studentDo);

}
