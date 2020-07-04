package lhz.schoolhelper.web.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lhz.schoolhelper.model.dto.NoticeDto;
import lhz.schoolhelper.model.vo.NoticeVo;
import lhz.schoolhelper.model.vo.ResultVo;
import lhz.schoolhelper.service.NoticeService;

@RestController
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("/classnotice/{currentPage}")
	public ResultVo classnotice(HttpSession session, @PathVariable("currentPage") Integer currentPage) {
		currentPage = currentPage < 0 ? 0 : currentPage;
		Integer classid = (Integer) session.getAttribute("classid");
		List<NoticeDto> ndtlist = noticeService.getNoticeByClassid(classid, currentPage);

		if (ndtlist == null) {
			return ResultVo.error("无更多通知");
		}

		List<NoticeVo> nvolist = convertToNoticeVo(ndtlist);
		return ResultVo.success(nvolist);
	}

	private List<NoticeVo> convertToNoticeVo(List<NoticeDto> ndtlist) {
		List<NoticeVo> nvolist = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (NoticeDto noticeDto : ndtlist) {
			NoticeVo noticeVo = new NoticeVo();
			noticeVo.setContent(noticeDto.getContent());
			noticeVo.setTeacherName(noticeDto.getTeacherName());
			noticeVo.setTitle(noticeDto.getTitle());
			// noticeVo.setClassid(noticeDto.getClassid());
			noticeVo.setDateString(sdf.format(noticeDto.getDate()));
			nvolist.add(noticeVo);
		}
		return nvolist;
	}

}
