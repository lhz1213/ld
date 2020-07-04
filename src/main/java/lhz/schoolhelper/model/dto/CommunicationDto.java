package lhz.schoolhelper.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommunicationDto {

	private Integer id;
	private String title;
	private String content;
	private String sName;
	private Date date;
	private Integer hot;

}
