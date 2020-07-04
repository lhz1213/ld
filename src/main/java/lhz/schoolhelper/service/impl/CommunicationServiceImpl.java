package lhz.schoolhelper.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhz.schoolhelper.mapper.CommunicationMapper;
import lhz.schoolhelper.mapper.StudentMapper;
import lhz.schoolhelper.model.domain.CommunicationDo;
import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.dto.CommunicationDto;
import lhz.schoolhelper.service.CommunicationService;

@Service
public class CommunicationServiceImpl implements CommunicationService {

	@Autowired
	private CommunicationMapper communicationMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<CommunicationDto> getAll(Integer currentPage, String orderRule) {
		// TODO Auto-generated method stub
		List<CommunicationDo> codolist = new ArrayList<>();
		if ("date".equals(orderRule)) {
			codolist = communicationMapper.getAllOrderByDate(currentPage);
		} else if ("hot".equals(orderRule)) {
			codolist = communicationMapper.getAllOrderByHot(currentPage);
		}

		if (codolist == null || codolist.isEmpty()) {
			return null;
		}

		Map<Integer, String> map = new HashMap<>();
		List<CommunicationDto> codtolist = new ArrayList<>();
		for (CommunicationDo communicationDo : codolist) {
			CommunicationDto communicationDto = new CommunicationDto();
			BeanUtils.copyProperties(communicationDo, communicationDto);
			Integer sid = communicationDo.getSid();
			if (map.containsKey(sid)) {
				communicationDto.setSName(map.get(sid));
			} else {
				StudentDo studentDo = studentMapper.getStudentById(sid);
				String sName = studentDo == null ? "" : studentDo.getName();
				map.put(sid, sName);
				communicationDto.setSName(sName);
			}
			codtolist.add(communicationDto);
		}
		return codtolist;
	}

	public Integer save(CommunicationDo communicationDo) {
		Integer i = communicationMapper.addCommunication(communicationDo);
		return i;
	}

	@Override
	public CommunicationDto getById(Integer id) {
		// TODO Auto-generated method stub
		CommunicationDto communicationDto = new CommunicationDto();
		CommunicationDo codo = communicationMapper.getById(id);
		Integer sid = codo.getSid();
		StudentDo studentDo = studentMapper.getStudentById(sid);
		String sName = studentDo == null ? "" : studentDo.getName();
		BeanUtils.copyProperties(codo, communicationDto);
		communicationDto.setSName(sName);
		return communicationDto;
	}

}
