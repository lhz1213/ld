package lhz.schoolhelper.model.dto;

import lombok.Data;

@Data
public class CourseDto {

	private String name;
	private Integer day;
	private Integer period;
	private String locate;
	private String teacherName;
	private Integer classid;
	private String info;

}
