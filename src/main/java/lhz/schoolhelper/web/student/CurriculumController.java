package lhz.schoolhelper.web.student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lhz.schoolhelper.mapper.StudentMapper;
import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.dto.CourseDto;
import lhz.schoolhelper.model.vo.CourseVo;
import lhz.schoolhelper.model.vo.ResultVo;
import lhz.schoolhelper.model.vo.StudentVo;
import lhz.schoolhelper.service.CourseService;
import lhz.schoolhelper.util.Curriculums;

@RestController
public class CurriculumController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentMapper studentMapper;

	@GetMapping("/mycurriculum")
	public ResultVo mycurriculum(HttpSession session) {

		Integer classid = (Integer) session.getAttribute("classid");
		List<CourseDto> courseList = new ArrayList<>();

		if (classid == null) {
			StudentVo studentVo = (StudentVo) session.getAttribute("studentVo");
			StudentDo student = studentMapper.getStudentByIdentifer(studentVo.getIdentifer());
			session.setAttribute("classid", student.getClassid());
			courseList = courseService.getCourseByStudent(student);
		} else {
			StudentDo student = new StudentDo();
			student.setClassid(classid);
			courseList = courseService.getCourseByStudent(student);
		}

		if (courseList == null) {
			return ResultVo.error("暂无课程");
		}

		List<List<CourseVo>> timetable = Curriculums.convertToTimetable(courseList);
		return ResultVo.success(timetable);
	}

}
