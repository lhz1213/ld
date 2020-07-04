package lhz.schoolhelper.service;

import java.util.List;

import lhz.schoolhelper.model.domain.NoticeDo;
import lhz.schoolhelper.model.dto.NoticeDto;

public interface NoticeService {

	List<NoticeDto> getNoticeByClassid(Integer classid, Integer currentPage);

	List<NoticeDto> getNoticeByTid(Integer tid, Integer currentPage);

	Integer saveNotice(NoticeDo noticeDo);

}
