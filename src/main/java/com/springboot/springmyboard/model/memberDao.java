package com.springboot.springmyboard.model;


import java.util.List;

public interface memberDao {
	public void insert(memberVo vo);
	public void update(memberVo vo);
	public void delete(int pkid);
	public memberVo getData(String user_id, String user_pw);
	public memberVo getData(int pkid);
	public List<memberVo> getList(int start, String search_key);
	public int getTotalCount(String search_key);
	public int getIdCnt(String user_id);
}
