package com.springboot.springmyboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springmyboard.model.memberDaoImpl;
import com.springboot.springmyboard.model.memberVo;

@Service
public class memberServiceImpl implements memberService {
	@Autowired
	memberDaoImpl dao;

	public memberServiceImpl(memberDaoImpl dao) {
		this.dao = dao;
	}

	@Override
	public memberVo memberLogin(String user_id, String user_pw) {
		// TODO Auto-generated method stub
		return this.dao.getData(user_id, user_pw);
	}

	@Override
	public void memberInsert(memberVo vo) {
		int result = dao.insert(vo); // DAO insert 호출
		if (result == 0) {
			throw new RuntimeException("회원가입 실패");
		}

	}

	@Override
	public void memberUpdate(memberVo vo) {
		int result = dao.update(vo);
	    if(result == 0) throw new RuntimeException("회원정보 수정 실패");

	}

	@Override
	public void memberDelete(int pkid) {
		int result = dao.delete(pkid);
	    if(result == 0) throw new RuntimeException("회원 삭제 실패");

	}

	@Override
	public memberVo memberGetData(int pkid) {
		return dao.getData(pkid);
	}

	@Override
	public List<memberVo> memberSearch(int page, String search_key) {
		int start = (page - 1) * 10; // 한 페이지 10개
		return dao.getList(start, search_key);
	}

	@Override
	public int totalCount(String search_key) {
		// TODO Auto-generated method stub
		return dao.getTotalCount(search_key);
	}

	@Override
	public int memberIdCnt(String user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public void memberInsert(memberVo vo) {
//		this.dao.insert(vo);
//	}
//
//	@Override
//	public void memberUpdate(memberVo vo) {
//		this.dao.update(vo);
//	}
//
//	@Override
//	public void memberDelete(int pkid) {
//		this.dao.delete(pkid);
//	}
//	
//
//	@Override
//	public memberVo memberGetData(int pkid) {
//		return this.dao.getData(pkid);
//	}
//
//	@Override
//	public List<memberVo> memberSearch(int page, String search_key) {
//		int start = (page -1) * 10;
//		
//		return this.dao.getList(start, search_key);
//	}
//
//	@Override
//	public int totalCount(String search_key) {
//		return this.dao.getTotalCount(search_key);
//	}
//
//	@Override
//	public int memberIdCnt(String user_id) {
//		return this.dao.getIdCnt(user_id);
//	}
}
