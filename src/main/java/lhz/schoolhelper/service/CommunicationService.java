package lhz.schoolhelper.service;

import java.util.List;

import lhz.schoolhelper.model.domain.CommunicationDo;
import lhz.schoolhelper.model.dto.CommunicationDto;

public interface CommunicationService {

	/**
	 * 
	 * @param currentPage 当前页
	 * @param orderRule   排序方式
	 * @return 帖子集合
	 */
	List<CommunicationDto> getAll(Integer currentPage, String orderRule);

	/**
	 * 
	 * @param id
	 * @return 帖子信息
	 */
	CommunicationDto getById(Integer id);

	/**
	 * 
	 * @param communicationDo
	 * @return 是否插入成功
	 */
	Integer save(CommunicationDo communicationDo);

}
