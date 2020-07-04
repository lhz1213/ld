package lhz.schoolhelper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lhz.schoolhelper.model.domain.CourseDo;

public interface CourseMapper {

	List<CourseDo> getCourseByClassid(Integer classid);

	List<CourseDo> getCourseByTid(Integer tid);

	Integer addCourse(CourseDo courseDo);

	CourseDo getCourseByDayAndPeriodAndTid(@Param("day") Integer day, @Param("period") Integer period,
			@Param("tid") Integer tid);

	Integer updateCourse(CourseDo courseDo);
}
