package lhz.schoolhelper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lhz.schoolhelper.model.domain.CommunicationDo;

public interface CommunicationMapper {

	List<CommunicationDo> getAllOrderByDate(@Param("currentPage") Integer currentPage);

	List<CommunicationDo> getAllOrderByHot(@Param("currentPage") Integer currentPage);

	Integer addCommunication(CommunicationDo communicationDo);

	CommunicationDo getById(Integer id);

}
