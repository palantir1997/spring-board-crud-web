package com.springboot.springmyboard.service;
import java.util.List;

import com.springboot.springmyboard.model.memberVo;



public interface memberService {
	public void memberInsert(memberVo vo);
	public void memberUpdate(memberVo vo);
	public void memberDelete(int pkid);
	
	public memberVo memberLogin(String user_id, String user_pw);
	public memberVo memberGetData(int pkid);
	public List<memberVo> memberSearch(int page, String search_key);
	public int totalCount(String search_key);
	public int memberIdCnt(String user_id);
}
