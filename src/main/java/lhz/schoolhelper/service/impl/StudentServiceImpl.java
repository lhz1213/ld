package lhz.schoolhelper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhz.schoolhelper.mapper.ClassMapper;
import lhz.schoolhelper.mapper.StudentMapper;
import lhz.schoolhelper.model.domain.ClassDo;
import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.dto.ClassDto;
import lhz.schoolhelper.model.dto.StudentDto;
import lhz.schoolhelper.service.ClassService;
import lhz.schoolhelper.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ClassMapper classMapper;
	@Autowired
	private ClassService classService;

	@Override
	public StudentDto getStudentById(Integer id) {
		StudentDo studentDo = studentMapper.getStudentById(id);
		return convertToStudentDto(studentDo);
	}

	@Override
	public StudentDto getStudentByIdentiferAndPassword(String identifer, String password) {
		StudentDo studentDo = studentMapper.getStudentByIdentiferAndPassword(identifer, password);
		return convertToStudentDto(studentDo);
	}

	private StudentDto convertToStudentDto(StudentDo studentDo) {
		if (studentDo == null) {
			return null;
		} else {
			StudentDto studentDto = new StudentDto();
			studentDto.setName(studentDo.getName());
			studentDto.setIdentifer(studentDo.getIdentifer());
			Integer classid = studentDo.getClassid();
			ClassDto classDto = classService.getClassById(classid);
			studentDto.setClassDto(classDto);
			return studentDto;
		}
	}

	@Override
	public String addStudent(StudentDo studentDo) {
		StudentDo studentByIdentifer = studentMapper.getStudentByIdentifer(studentDo.getIdentifer());
		if (studentByIdentifer != null) {
			return "学生已存在";
		} else {
			ClassDo classDo = classMapper.getClassById(studentDo.getClassid());
			if (classDo == null) {
				return "班级不存在";
			}
			studentMapper.addStudent(studentDo);
			return null;
		}
	}

}
