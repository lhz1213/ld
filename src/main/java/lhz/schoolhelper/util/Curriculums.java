package lhz.schoolhelper.util;

import java.util.ArrayList;
import java.util.List;

import lhz.schoolhelper.model.dto.CourseDto;
import lhz.schoolhelper.model.vo.CourseVo;

public class Curriculums {

	private Curriculums() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static List<List<CourseVo>> convertToTimetable(List<CourseDto> courseList) {
		List<List<CourseVo>> schoolTimetable = init();
		for (CourseDto courseDto : courseList) {
			List<CourseVo> list = schoolTimetable.get(courseDto.getPeriod() - 1);
			CourseVo courseVo = list.get(courseDto.getDay() - 1);
			convertToCourseVo(courseDto, courseVo);
		}
		return schoolTimetable;
	}

	public static CourseVo convertToCourseVo(CourseDto courseDto, CourseVo courseVo) {
		courseVo.setLocate("地点: " + courseDto.getLocate());
		courseVo.setName("课程： " + courseDto.getName());
		courseVo.setTeacherName("老师： " + courseDto.getTeacherName());
		courseVo.setClassId("班级编号： " + courseDto.getClassid());
		courseVo.setInfo("周数： " + courseDto.getInfo());
		return courseVo;
	}

	private static List<List<CourseVo>> init() {
		List<List<CourseVo>> schoolTimetable = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			List<CourseVo> courseVoList = new ArrayList<>();
			for (int j = 1; j < 8; j++) {
				CourseVo cv = new CourseVo();
				cv.setId(10 * i + j);
				courseVoList.add(cv);
			}
			schoolTimetable.add(courseVoList);
		}
		return schoolTimetable;
	}

}
