package lhz.schoolhelper.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhz.schoolhelper.mapper.NoticeMapper;
import lhz.schoolhelper.mapper.TeacherMapper;
import lhz.schoolhelper.model.domain.NoticeDo;
import lhz.schoolhelper.model.domain.TeacherDo;
import lhz.schoolhelper.model.dto.NoticeDto;
import lhz.schoolhelper.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private TeacherMapper tm;

	@Override
	public List<NoticeDto> getNoticeByClassid(Integer classid, Integer currentPage) {
		List<NoticeDo> noticeDoList = noticeMapper.getNoticeByClassid(classid, 5 * currentPage);

		if (noticeDoList.isEmpty() || noticeDoList == null) {
			return null;
		}

		List<NoticeDto> ndtlist = new ArrayList<>();
		Map<Integer, String> map = new HashMap<>();
		for (NoticeDo noticeDo : noticeDoList) {
			NoticeDto noticeDto = new NoticeDto();
			BeanUtils.copyProperties(noticeDo, noticeDto);
			Integer tid = noticeDo.getTid();
			if (map.containsKey(tid)) {
				noticeDto.setTeacherName(map.get(tid));
			} else {
				TeacherDo teacherDo = tm.getTeacherById(tid);
				String name = teacherDo == null ? "" : teacherDo.getName();
				map.put(tid, name);
				noticeDto.setTeacherName(name);
			}
			ndtlist.add(noticeDto);
		}
		return ndtlist;
	}

	@Override
	public List<NoticeDto> getNoticeByTid(Integer tid, Integer currentPage) {
		// TODO Auto-generated method stub
		List<NoticeDo> noticeDoList = noticeMapper.getNoticeByTid(tid, 5 * currentPage);

		if (noticeDoList.isEmpty() || noticeDoList == null) {
			return null;
		}

		List<NoticeDto> ndtlist = new ArrayList<>();
		for (NoticeDo noticeDo : noticeDoList) {
			NoticeDto noticeDto = new NoticeDto();
			BeanUtils.copyProperties(noticeDo, noticeDto);
			ndtlist.add(noticeDto);
		}
		return ndtlist;
	}

	@Override
	public Integer saveNotice(NoticeDo noticeDo) {
		// TODO Auto-generated method stub

		Integer i = noticeMapper.addNotice(noticeDo);

		return i;
	}

}
