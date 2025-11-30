package com.springboot.springmyboard.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.springmyboard.model.loginMember;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component("webHelper")
public class webHelper {
	@Autowired
	private HttpServletRequest request;
	
	public loginMember checkLogin() {
		HttpSession session = this.request.getSession();
		Integer loginPkid = (Integer) session.getAttribute("MEMBER_PKID");
		String loginName = (String) session.getAttribute("MEMBER_NAME");
		
		if(loginPkid == null) {
			return null;
		} else {
			loginMember loginInfo = new loginMember();
			loginInfo.setPkid(loginPkid);
			loginInfo.setName(loginName);
			
			return loginInfo;
		}
	}	
}
