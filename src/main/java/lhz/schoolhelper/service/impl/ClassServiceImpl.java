package lhz.schoolhelper.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhz.schoolhelper.mapper.ClassMapper;
import lhz.schoolhelper.model.domain.ClassDo;
import lhz.schoolhelper.model.dto.ClassDto;
import lhz.schoolhelper.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassMapper classMapper;

	@Override
	public ClassDto getClassById(Integer id) {

		ClassDo classDo = classMapper.getClassById(id);

		if (classDo == null) {
			return null;
		}

		ClassDto classDto = new ClassDto();
		BeanUtils.copyProperties(classDo, classDto);
		return classDto;
	}

}
