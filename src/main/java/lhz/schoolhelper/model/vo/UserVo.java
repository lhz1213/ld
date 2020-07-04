package lhz.schoolhelper.model.vo;

import lombok.Data;

@Data
public class UserVo {

	private String name;
	private String password;
	private Integer id; // 0学生1老师

}
