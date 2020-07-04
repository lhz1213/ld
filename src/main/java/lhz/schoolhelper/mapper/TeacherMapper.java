package lhz.schoolhelper.mapper;

import org.apache.ibatis.annotations.Param;

import lhz.schoolhelper.model.domain.TeacherDo;

public interface TeacherMapper {

	TeacherDo getTeacherById(Integer id);

	TeacherDo getTeacherByIdentiferAndPassword(@Param("identifer") String identifer,
			@Param("password") String password);

	TeacherDo getTeacherByIdentifer(String identifer);

}
