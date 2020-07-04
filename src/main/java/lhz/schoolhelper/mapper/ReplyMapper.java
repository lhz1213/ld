package lhz.schoolhelper.mapper;

import java.util.List;

import lhz.schoolhelper.model.domain.ReplyDo;

public interface ReplyMapper {

	List<ReplyDo> getAllByComid(Integer comid);

	Integer addReply(ReplyDo replyDo);

}
