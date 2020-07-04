package lhz.schoolhelper.mapper;

import org.apache.ibatis.annotations.Param;

import lhz.schoolhelper.model.domain.StudentDo;

public interface StudentMapper {

	StudentDo getStudentById(Integer id);

	StudentDo getStudentByIdentiferAndPassword(@Param("identifer") String identifer,
			@Param("password") String password);

	StudentDo getStudentByIdentifer(String identifer);

	Integer addStudent(StudentDo studentDo);

}
