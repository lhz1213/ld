package lhz.schoolhelper.service;

import java.util.List;

import lhz.schoolhelper.model.domain.ReplyDo;
import lhz.schoolhelper.model.dto.ReplyDto;

public interface ReplyService {
	/**
	 * 
	 * @param comid
	 * @return 回复集合
	 */
	List<ReplyDto> getAllByComid(Integer comid);

	/**
	 * 插入一条回复
	 * 
	 * @param replyDo
	 * @return
	 */
	Integer save(ReplyDo replyDo);

}
