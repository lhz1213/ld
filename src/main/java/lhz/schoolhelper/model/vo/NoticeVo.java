package lhz.schoolhelper.model.vo;

import lombok.Data;

@Data
public class NoticeVo {

	private String title;
	private String content;
	private String teacherName;
	private String dateString;
	private Integer classid;

}
