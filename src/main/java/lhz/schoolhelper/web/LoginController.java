package lhz.schoolhelper.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.domain.TeacherDo;
import lhz.schoolhelper.model.dto.ClassDto;
import lhz.schoolhelper.model.dto.StudentDto;
import lhz.schoolhelper.model.vo.ResultVo;
import lhz.schoolhelper.model.vo.StudentVo;
import lhz.schoolhelper.model.vo.TeacherVo;
import lhz.schoolhelper.model.vo.UserVo;
import lhz.schoolhelper.service.StudentService;
import lhz.schoolhelper.service.TeacherService;

@RestController
public class LoginController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;

	/**
	 * 用户登录
	 * 
	 * @param userVo
	 * @param session
	 * @return
	 */
	@PostMapping("userlogin")
	public ResultVo login(@RequestBody UserVo userVo, HttpSession session) {
		Integer id = userVo.getId();
		if (id == 0) {
			return studentLogin(userVo, session, id);
		} else if (id == 1) {
			return teacherLogin(userVo, session, id);
		} else {
			return ResultVo.error("用户名密码错误");
		}
	}

	private ResultVo studentLogin(UserVo userVo, HttpSession session, Integer id) {
		StudentDto studentDto = studentService.getStudentByIdentiferAndPassword(userVo.getName(), userVo.getPassword());

		if (studentDto == null) {
			return ResultVo.error("用户名密码错误");
		}

		StudentVo studentVo = new StudentVo();
		studentVo.setName(studentDto.getName());
		studentVo.setIdentifer(studentDto.getIdentifer());
		ClassDto classDto = studentDto.getClassDto();
		studentVo.setClassInfo(
				classDto.getYear() + classDto.getCollege() + classDto.getDepartment() + classDto.getName());

		if (session.getAttribute("studentVo") == null) {
			session.setAttribute("studentVo", studentVo);
		}

		return ResultVo.success("学生");
	}

	private ResultVo teacherLogin(UserVo userVo, HttpSession session, Integer id) {
		TeacherDo teacherDo = teacherService.getTeacherByIdentiferAndPassword(userVo.getName(), userVo.getPassword());

		if (teacherDo == null) {
			return ResultVo.error("用户名密码错误");
		}

		TeacherVo teacherVo = new TeacherVo();
		BeanUtils.copyProperties(teacherDo, teacherVo);

		if (session.getAttribute("teacherVo") == null) {
			session.setAttribute("teacherVo", teacherVo);
		}

		return ResultVo.success("老师");
	}

	@PostMapping("studentregister")
	public ResultVo register(@RequestBody StudentDo studentDo) {
		System.out.println(studentDo);
		String msg = studentService.addStudent(studentDo);
		if (msg == null) {
			return ResultVo.success("注册成功");
		} else {
			return ResultVo.success(msg);
		}
	}
}
