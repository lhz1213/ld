package lhz.schoolhelper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhz.schoolhelper.mapper.TeacherMapper;
import lhz.schoolhelper.model.domain.TeacherDo;
import lhz.schoolhelper.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherMapper tm;

	@Override
	public TeacherDo getTeacherById(Integer id) {
		TeacherDo teacherDo = tm.getTeacherById(id);
		return teacherDo;
	}

	@Override
	public TeacherDo getTeacherByIdentiferAndPassword(String identifer, String password) {
		TeacherDo teacherDo = tm.getTeacherByIdentiferAndPassword(identifer, password);
		return teacherDo;
	}

}
