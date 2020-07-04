package lhz.schoolhelper.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhz.schoolhelper.mapper.ReplyMapper;
import lhz.schoolhelper.mapper.StudentMapper;
import lhz.schoolhelper.model.domain.ReplyDo;
import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.dto.ReplyDto;
import lhz.schoolhelper.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<ReplyDto> getAllByComid(Integer comid) {
		// TODO Auto-generated method stub

		List<ReplyDo> replylist = replyMapper.getAllByComid(comid);

		if (replylist == null || replylist.isEmpty()) {
			return null;
		}

		Map<Integer, String> map = new HashMap<>();
		List<ReplyDto> redtolist = new ArrayList<>();
		for (ReplyDo replyDo : replylist) {
			ReplyDto replyDto = new ReplyDto();
			BeanUtils.copyProperties(replyDo, replyDto);
			Integer sid = replyDo.getSid();
			if (map.containsKey(sid)) {
				replyDto.setSName(map.get(sid));
			} else {
				StudentDo studentDo = studentMapper.getStudentById(sid);
				String sName = studentDo == null ? "" : studentDo.getName();
				map.put(sid, sName);
				replyDto.setSName(sName);
			}
			redtolist.add(replyDto);
		}
		return redtolist;
	}

	@Override
	public Integer save(ReplyDo replyDo) {
		// TODO Auto-generated method stub
		Integer i = replyMapper.addReply(replyDo);

		return i;
	}

}
