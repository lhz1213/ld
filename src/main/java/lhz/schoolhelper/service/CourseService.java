package lhz.schoolhelper.service;

import java.util.List;

import lhz.schoolhelper.model.domain.CourseDo;
import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.domain.TeacherDo;
import lhz.schoolhelper.model.dto.CourseDto;

public interface CourseService {

	/**
	 * 查询学生的课程
	 * 
	 * @param StudentDo
	 * @return List<CourseDto>
	 */
	List<CourseDto> getCourseByStudent(StudentDo sd);

	List<CourseDto> getCourseByTeacher(TeacherDo td);

	/**
	 * 添加或更新
	 * 
	 * @param courseDo
	 * @return
	 */
	Integer save(CourseDo courseDo);

}
