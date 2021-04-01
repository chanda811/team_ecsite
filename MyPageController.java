package jp.co.internous.dandelion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.dandelion.model.domain.MstUser;
import jp.co.internous.dandelion.model.mapper.MstUserMapper;
import jp.co.internous.dandelion.model.session.LoginSession;

@Controller
@RequestMapping("/dandelion/mypage")
public class MyPageController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private MstUserMapper userMapper;

	@RequestMapping("/")
	public String index(Model model) {
		MstUser user = userMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		model.addAttribute("user", user);
		model.addAttribute("loginSession", loginSession);
		return "my_page";
	}
}
