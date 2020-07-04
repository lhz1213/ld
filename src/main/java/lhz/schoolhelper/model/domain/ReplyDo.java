package lhz.schoolhelper.model.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDo {

	private Integer id;
	private String content;
	private Integer sid;
	private Date date;
	private Integer comid;

}
