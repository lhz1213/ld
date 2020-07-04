package lhz.schoolhelper.model.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommunicationDo {

	private Integer id;
	private String title;
	private String content;
	private Integer sid;
	private Date date;
	private Integer hot;

}
