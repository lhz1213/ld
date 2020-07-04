package lhz.schoolhelper.model.vo;

import java.util.List;

import lombok.Data;

@Data
public class TieInfoVo {
	private String title;
	List<TieBodyVo> body;
	private String totalPage;

}
