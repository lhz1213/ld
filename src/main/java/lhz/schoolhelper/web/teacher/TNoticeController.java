package lhz.schoolhelper.web.teacher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lhz.schoolhelper.model.domain.NoticeDo;
import lhz.schoolhelper.model.dto.NoticeDto;
import lhz.schoolhelper.model.vo.NoticeVo;
import lhz.schoolhelper.model.vo.ResultVo;
import lhz.schoolhelper.service.NoticeService;

@RestController
public class TNoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("/teachernotice/{currentPage}")
	public ResultVo classnotice(HttpSession session, @PathVariable("currentPage") Integer currentPage) {
		currentPage = currentPage < 0 ? 0 : currentPage;
		Integer tid = (Integer) session.getAttribute("tid");
		List<NoticeDto> ndtlist = noticeService.getNoticeByTid(tid, currentPage);

		if (ndtlist == null) {
			return ResultVo.error("无更多通知");
		}

		List<NoticeVo> nvolist = convertToNoticeVo(ndtlist);
		return ResultVo.success(nvolist);
	}

	@PostMapping("/announcement")
	public ResultVo announcement(@RequestBody NoticeDo noticeDo, HttpSession session) {
		Integer tid = (Integer) session.getAttribute("tid");
		noticeDo.setTid(tid);
		noticeDo.setDate(new Date());
		Integer i = noticeService.saveNotice(noticeDo);

		if (i != 1) {
			return ResultVo.error("操作失败");
		}

		return ResultVo.success("1");
	}

	private List<NoticeVo> convertToNoticeVo(List<NoticeDto> ndtlist) {
		List<NoticeVo> nvolist = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (NoticeDto noticeDto : ndtlist) {
			NoticeVo noticeVo = new NoticeVo();
			noticeVo.setContent(noticeDto.getContent());
			// noticeVo.setTeacherName(noticeDto.getTeacherName());
			noticeVo.setTitle(noticeDto.getTitle());
			noticeVo.setClassid(noticeDto.getClassid());
			noticeVo.setDateString(sdf.format(noticeDto.getDate()));
			nvolist.add(noticeVo);
		}
		return nvolist;
	}
}
