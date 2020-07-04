package lhz.schoolhelper.web.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lhz.schoolhelper.mapper.StudentMapper;
import lhz.schoolhelper.model.domain.CommunicationDo;
import lhz.schoolhelper.model.domain.ReplyDo;
import lhz.schoolhelper.model.domain.StudentDo;
import lhz.schoolhelper.model.dto.CommunicationDto;
import lhz.schoolhelper.model.dto.ReplyDto;
import lhz.schoolhelper.model.vo.CommunicationVo;
import lhz.schoolhelper.model.vo.ResultVo;
import lhz.schoolhelper.model.vo.StudentVo;
import lhz.schoolhelper.model.vo.TieBodyVo;
import lhz.schoolhelper.model.vo.TieInfoVo;
import lhz.schoolhelper.service.CommunicationService;
import lhz.schoolhelper.service.ReplyService;

@RestController
public class TiebaController {

	@Autowired
	private CommunicationService communicationService;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ReplyService replyService;

	/**
	 * 校园动态，逛贴吧
	 * 
	 * @param currentPage
	 * @param orderRule
	 * @return
	 */
	@GetMapping("/schoolpost/{currentPage}")
	public ResultVo schoolpost(@PathVariable("currentPage") Integer currentPage, String orderRule) {

		currentPage = currentPage < 0 ? 0 : currentPage;
		List<CommunicationDto> codtolist = communicationService.getAll(5 * currentPage, orderRule);

		if (codtolist == null) {
			return ResultVo.error("无更多动态");
		}

		List<CommunicationVo> list = convertToCommunicationVo(codtolist);
		return ResultVo.success(list);
	}

	/**
	 * 帖子详情
	 * 
	 * @param comid
	 * @return
	 */
	@GetMapping("/detailedpost")
	public ResultVo detailedpost(Integer comid) {

		List<ReplyDto> relist = replyService.getAllByComid(comid);

		if (relist == null) {
			return ResultVo.error("贴子不存在");
		}

		CommunicationDto communicationDto = communicationService.getById(comid);
		TieInfoVo tie = convertToTie(communicationDto, relist);

		return ResultVo.success(tie);
	}

	/**
	 * 发帖
	 * 
	 * @param communicationDo
	 * @param session
	 * @return
	 */
	@PostMapping("/fatie")
	public ResultVo fatie(@RequestBody CommunicationDo communicationDo, HttpSession session) {

		Integer studentid = (Integer) session.getAttribute("stuentid");

		if (studentid == null) {
			StudentVo studentVo = (StudentVo) session.getAttribute("studentVo");
			StudentDo student = studentMapper.getStudentByIdentifer(studentVo.getIdentifer());
			Integer sid = student.getId();
			session.setAttribute("studentid", sid);
			communicationDo.setSid(sid);
		} else {
			communicationDo.setSid(studentid);
		}
		communicationDo.setDate(new Date());
		communicationDo.setHot(0);

		Integer i = communicationService.save(communicationDo);
		if (i != 1) {
			return ResultVo.error("发布失败");
		}
		return ResultVo.success("发布成功");
	}

	/**
	 * 回帖
	 * 
	 * @param replyDo
	 * @param session
	 * @return
	 */
	@PostMapping("/reply")
	public ResultVo reply(@RequestBody ReplyDo replyDo, HttpSession session) {

		Integer studentid = (Integer) session.getAttribute("stuentid");

		if (studentid == null) {
			StudentVo studentVo = (StudentVo) session.getAttribute("studentVo");
			StudentDo student = studentMapper.getStudentByIdentifer(studentVo.getIdentifer());
			Integer sid = student.getId();
			session.setAttribute("studentid", sid);
			replyDo.setSid(sid);
		} else {
			replyDo.setSid(studentid);
		}
		replyDo.setDate(new Date());
		Integer i = replyService.save(replyDo);
		if (i != 1) {
			return ResultVo.error("发布失败");
		}
		return ResultVo.success("发布成功");
	}

	private TieInfoVo convertToTie(CommunicationDto communicationDto, List<ReplyDto> relist) {
		// TODO Auto-generated method stub
		TieInfoVo tie = new TieInfoVo();
		tie.setTitle(communicationDto.getTitle());
		ReplyDto redto = new ReplyDto();
		BeanUtils.copyProperties(communicationDto, redto);
		relist.add(redto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<TieBodyVo> bodylist = new ArrayList<>();
		for (ReplyDto replyDto : relist) {
			TieBodyVo body = new TieBodyVo();
			BeanUtils.copyProperties(replyDto, body);
			body.setDateString(sdf.format(replyDto.getDate()));
			bodylist.add(body);
		}
		bodylist.sort(Comparator.comparing(TieBodyVo::getDateString));
		tie.setBody(bodylist);
		Integer totalPage = bodylist.size() / 5 + 1;
		tie.setTotalPage(totalPage.toString());
		return tie;
	}

	private List<CommunicationVo> convertToCommunicationVo(List<CommunicationDto> codtolist) {
		List<CommunicationVo> covolist = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (CommunicationDto communicationDto : codtolist) {
			CommunicationVo communicationVo = new CommunicationVo();
			BeanUtils.copyProperties(communicationDto, communicationVo);
			communicationVo.setDateString(sdf.format(communicationDto.getDate()));
			covolist.add(communicationVo);
		}
		return covolist;
	}

}
