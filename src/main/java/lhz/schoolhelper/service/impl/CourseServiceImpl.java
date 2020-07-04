package lhz.schoolhelper.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhz.schoolhelper.mapper.CourseMapper;
import lhz.schoolhelper.mapper.TeacherMapper;
import lhz.schoolhelper.model.domain.CourseDo;
import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.domain.TeacherDo;
import lhz.schoolhelper.model.dto.CourseDto;
import lhz.schoolhelper.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseMapper cm;
	@Autowired
	private TeacherMapper tm;

	@Override
	public List<CourseDto> getCourseByStudent(StudentDo sd) {

		List<CourseDo> cdlist = cm.getCourseByClassid(sd.getClassid());

		if (cdlist.isEmpty() || cdlist == null) {
			return null;
		}

		List<CourseDto> cdtlist = new ArrayList<>();
		Map<Integer, String> map = new HashMap<>();
		for (CourseDo courseDo : cdlist) {
			CourseDto courseDto = new CourseDto();
			BeanUtils.copyProperties(courseDo, courseDto);
			Integer tid = courseDo.getTid();
			if (map.containsKey(tid)) {
				courseDto.setTeacherName(map.get(tid));
			} else {
				TeacherDo teacherDo = tm.getTeacherById(tid);
				String name = teacherDo == null ? "" : teacherDo.getName();
				map.put(tid, name);
				courseDto.setTeacherName(name);
			}
			cdtlist.add(courseDto);
		}
		return cdtlist;
	}

	@Override
	public List<CourseDto> getCourseByTeacher(TeacherDo td) {
		// TODO Auto-generated method stub
		List<CourseDo> cdlist = cm.getCourseByTid(td.getId());

		if (cdlist.isEmpty() || cdlist == null) {
			return null;
		}

		List<CourseDto> cdtlist = new ArrayList<>();
		String name = td.getName();
		for (CourseDo courseDo : cdlist) {
			CourseDto courseDto = new CourseDto();
			BeanUtils.copyProperties(courseDo, courseDto);
			courseDto.setTeacherName(name);
			cdtlist.add(courseDto);
		}

		return cdtlist;
	}

	@Override
	public Integer save(CourseDo courseDo) {
		// TODO Auto-generated method stub
		Integer i = 0;
		Integer day = courseDo.getDay();
		Integer period = courseDo.getPeriod();
		if (day != null && period != null) {
			CourseDo cd = cm.getCourseByDayAndPeriodAndTid(courseDo.getDay(), courseDo.getPeriod(), courseDo.getTid());
			if (cd == null) {
				i = cm.addCourse(courseDo);
			} else {
				i = cm.updateCourse(courseDo);
			}
		}

		return i;
	}

}
