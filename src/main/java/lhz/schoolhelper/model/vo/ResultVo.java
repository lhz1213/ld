package lhz.schoolhelper.model.vo;

import lombok.Data;

@Data
public class ResultVo {

	private Integer code;
	private Object model;
	private String msg;

	public static ResultVo success(Object model) {
		ResultVo rv = new ResultVo();
		rv.setCode(200);
		rv.setModel(model);
		return rv;
	}

	public static ResultVo success(Object model, String msg) {
		ResultVo rv = new ResultVo();
		rv.setCode(200);
		rv.setModel(model);
		rv.setMsg(msg);
		return rv;
	}

	public static ResultVo success(String msg) {
		ResultVo rv = new ResultVo();
		rv.setCode(200);
		rv.setMsg(msg);
		return rv;
	}

	public static ResultVo error(String msg) {
		ResultVo rv = new ResultVo();
		rv.setCode(500);
		rv.setMsg(msg);
		return rv;
	}

}
