package com.springboot.springmyboard.service;

import java.util.List;

import com.springboot.springmyboard.model.boardVo;

public interface boardVoService {
	void boardInsert(boardVo vo);
    void boardUpdate(boardVo vo);
    void boardDelete(int pkid);
    boardVo boardGetData(int pkid);
    List<boardVo> boardSearch(int page, String search_key);
    int totalCount(String search_key);
}
