package lhz.schoolhelper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lhz.schoolhelper.model.domain.NoticeDo;

public interface NoticeMapper {

	/**
	 * 根据班级id查询通知,一页5个
	 * 
	 * @param classid
	 * @return
	 */
	List<NoticeDo> getNoticeByClassid(@Param("classid") Integer classid, @Param("currentPage") Integer currentPage);

	List<NoticeDo> getNoticeByTid(@Param("tid") Integer classid, @Param("currentPage") Integer currentPage);

	Integer addNotice(NoticeDo noticeDo);
}
