package lhz.schoolhelper.model.domain;

import lombok.Data;

@Data
public class CourseDo {

	private Integer id;
	private String name;
	private Integer day;
	private Integer period;
	private String locate;
	private Integer tid;
	private Integer classid;
	private String info;

}
