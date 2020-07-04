package lhz.schoolhelper.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SkipController {

	/**
	 * 学生/课程表
	 * 
	 * @return
	 */
	@GetMapping("student/curriculum")
	public String curriculum() {
		return "main/student/curriculum";
	}

	/**
	 * 学生/查看班级通知
	 * 
	 * @return
	 */
	@GetMapping("student/readnotice")
	public String readnotice() {
		return "main/student/readnotice";
	}

	/**
	 * 校园动态
	 * 
	 * @return
	 */
	@GetMapping("student/tieba")
	public String tieba() {
		return "main/student/tieba";
	}

	@GetMapping("detailed/{coid}")
	public String detailed() {
		return "main/student/detailed";
	}

	/**
	 * 老师/课程表
	 * 
	 * @return
	 */
	@GetMapping("teacher/tcurriculum")
	public String tcurriculum() {
		return "main/teacher/tcurriculum";
	}

	@GetMapping("teacher/writenotice")
	public String writenotice() {
		return "main/teacher/writenotice";
	}

	/**
	 * 登陆界面
	 * 
	 * @return
	 */
	@GetMapping("home")
	public String login() {
		return "loginPage";
	}

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@GetMapping("signup")
	public String signup() {
		return "signupPage";
	}

}
