package jp.co.internous.dandelion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.dandelion.model.domain.MstUser;
import jp.co.internous.dandelion.model.form.UserForm;
import jp.co.internous.dandelion.model.mapper.MstUserMapper;
import jp.co.internous.dandelion.model.session.LoginSession;

@Controller
@RequestMapping("/dandelion/user")
public class UserController {
	
	@Autowired
	private MstUserMapper  userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("loginSession", loginSession);
		
		return "register_user";
	}
	
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UserForm form) {
		int count = userMapper.findCountByUserName(form.getUserName());

		return count > 0;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm form) {
		MstUser user = new MstUser(form);
		
		int count = userMapper.insert(user);
		
		return count > 0;
	}
	
}
