package lhz.schoolhelper.service;

import lhz.schoolhelper.model.domain.TeacherDo;

public interface TeacherService {

	/**
	 * 根据id查询教师
	 * 
	 * @param id
	 * @return TeacherDo
	 */
	TeacherDo getTeacherById(Integer id);

	/**
	 * 根据教师编号，密码查询教师
	 * 
	 * @param identifer
	 * @param password
	 * @return TeacherDo
	 */
	TeacherDo getTeacherByIdentiferAndPassword(String identifer, String password);

}
