package lhz.schoolhelper.model.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDo {

	private Integer id;
	private String title;
	private String content;
	private Integer tid;
	private Integer classid;
	private Date date;

}
