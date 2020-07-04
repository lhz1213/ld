package lhz.schoolhelper.web.teacher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lhz.schoolhelper.mapper.TeacherMapper;
import lhz.schoolhelper.model.domain.CourseDo;
import lhz.schoolhelper.model.domain.TeacherDo;
import lhz.schoolhelper.model.dto.CourseDto;
import lhz.schoolhelper.model.vo.CourseVo;
import lhz.schoolhelper.model.vo.ResultVo;
import lhz.schoolhelper.model.vo.TeacherVo;
import lhz.schoolhelper.service.CourseService;
import lhz.schoolhelper.util.Curriculums;

@RestController
public class TCurriculumController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private TeacherMapper teacherMapper;

	@GetMapping("/ttcurriculum")
	public ResultVo mycurriculum(HttpSession session) {

		Integer tid = (Integer) session.getAttribute("tid");
		List<CourseDto> courseList = new ArrayList<>();
		TeacherVo teacherVo = (TeacherVo) session.getAttribute("teacherVo");

		if (tid == null) {
			TeacherDo teacher = teacherMapper.getTeacherByIdentifer(teacherVo.getIdentifer());
			session.setAttribute("tid", teacher.getId());
			courseList = courseService.getCourseByTeacher(teacher);
		} else {
			TeacherDo teacher = new TeacherDo();
			teacher.setId(tid);
			teacher.setName(teacherVo.getName());
			courseList = courseService.getCourseByTeacher(teacher);
		}

		if (courseList == null) {
			return ResultVo.error("暂无课程");
		}

		List<List<CourseVo>> timetable = Curriculums.convertToTimetable(courseList);
		return ResultVo.success(timetable);
	}

	@PostMapping("/addCourse")
	public ResultVo addCourse(@RequestBody CourseDo courseDo, HttpSession session) {
		Integer tid = (Integer) session.getAttribute("tid");
		courseDo.setTid(tid);
		Integer i = courseService.save(courseDo);

		if (i != 1) {
			return ResultVo.error("操作失败");
		}

		return ResultVo.success("success");
	}

}
