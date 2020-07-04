package lhz.schoolhelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lhz.schoolhelper.mapper.CommunicationMapper;
import lhz.schoolhelper.mapper.NoticeMapper;
import lhz.schoolhelper.mapper.StudentMapper;
import lhz.schoolhelper.model.vo.ResultVo;
import lhz.schoolhelper.service.CommunicationService;
import lhz.schoolhelper.service.CourseService;
import lhz.schoolhelper.service.NoticeService;
import lhz.schoolhelper.service.ReplyService;
import lhz.schoolhelper.service.StudentService;
import lhz.schoolhelper.web.student.TiebaController;

@SpringBootTest
class SchoolhelperApplicationTests {

	@Autowired
	CourseService courseService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	StudentService studentService;
	@Autowired
	StudentMapper sm;
	@Autowired
	NoticeMapper nm;
	@Autowired
	private CommunicationMapper communicationMapper;
	@Autowired
	private CommunicationService communicationService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private TiebaController tc;

	@Test
	void contextLoads() {
		/*
		 * StudentDo sd = new StudentDo(); sd.setClassid(1); List<CourseDto> courseList
		 * = courseService.getCourseByStudent(sd); List<List<CourseVo>> list =
		 * Curriculums.convertToTimetable(courseList);
		 */
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// communicationService.getAll(0, null).forEach(System.out::println);
		// replyService.getAllByComid(1).forEach(System.out::print);
		ResultVo detailedpost = tc.detailedpost(1);
		System.out.println(detailedpost);
	}

}
