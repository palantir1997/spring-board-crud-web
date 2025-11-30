package com.springboot.springmyboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.springmyboard.model.boardVo;
import com.springboot.springmyboard.model.boardVoImpl;

public class boardVoServiceImpl implements boardVoService {

	@Autowired
    private boardVoImpl dao;

    public boardVoServiceImpl(boardVoImpl dao) {
        this.dao = dao;
    }

    @Override
    public void boardInsert(boardVo vo) {
        dao.insert(vo);
    }

    @Override
    public void boardUpdate(boardVo vo) {
        dao.update(vo);
    }

    @Override
    public void boardDelete(int pkid) {
        dao.delete(pkid);
    }

    @Override
    public boardVo boardGetData(int pkid) {
        return dao.getData(pkid);
    }

    @Override
    public List<boardVo> boardSearch(int page, String search_key) {
        int start = (page - 1) * 10; // 한 페이지 10개
        return dao.getList(start, search_key);
    }

    @Override
    public int totalCount(String search_key) {
        return dao.getTotalCount(search_key);
    }

}
