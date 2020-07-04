package lhz.schoolhelper.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDto {

	private String title;
	private String content;
	private String teacherName;
	private Integer classid;
	private Date date;

}
