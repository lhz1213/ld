package lhz.schoolhelper.service;

import lhz.schoolhelper.model.dto.ClassDto;

public interface ClassService {

	/**
	 * 根据班级id查询班级信息
	 * 
	 * @param Integer
	 * @return ClassDto
	 */
	ClassDto getClassById(Integer id);

}
