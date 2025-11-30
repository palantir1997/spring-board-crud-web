package com.springboot.springmyboard.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class boardVoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	public void insert(boardVo vo) {
//		String sql = "INSERT INTO boardvo (fkmember, title, content, name) VALUES (?, ?, ?, ?)";
//		jdbcTemplate.update(sql, vo.getFkmember(), vo.getTitle(), vo.getContent(), vo.getName());
//	}
	
	public void insert(boardVo vo) {
	    String sql = "INSERT INTO boardvo (fkmember, title, content, name, img_path) VALUES (?, ?, ?, ?, ?)";
	    jdbcTemplate.update(sql, vo.getFkmember(), vo.getTitle(), vo.getContent(), vo.getName(), vo.getImg_path());
	}


//	public void update(boardVo vo) {
//		String sql = "UPDATE boardvo SET title = ?, content = ?, viewcount = ?, name = ? WHERE pkid = ?";
//		jdbcTemplate.update(sql, vo.getTitle(), vo.getContent(), vo.getViewcount(), vo.getName(), vo.getPkid());
//	}
	
	public void update(boardVo vo) {
	    String sql = "UPDATE boardvo SET title = ?, content = ?, viewcount = ?, name = ?, img_path = ? WHERE pkid = ?";
	    jdbcTemplate.update(sql, vo.getTitle(), vo.getContent(), vo.getViewcount(), vo.getName(), vo.getImg_path(), vo.getPkid());
	}


	public void delete(int pkid) {
		String sql = "DELETE FROM boardvo WHERE pkid = ?";
		jdbcTemplate.update(sql, pkid);
	}

//	public boardVo getData(int pkid) {
//		String sql = "SELECT b.pkid, b.fkmember, b.title, b.content, b.viewcount, b.regdate, b.name "
//				+ "FROM boardvo b " + "WHERE b.pkid = ?";
//
//		List<boardVo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(boardVo.class), pkid);
//
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	public List<boardVo> getList(int start, String search_key) {
//		String sql = "SELECT b.pkid, b.fkmember, b.title, b.content, b.viewcount, b.regdate, b.name "
//				+ "FROM boardvo b " + "WHERE (b.title LIKE ? OR b.content LIKE ? OR b.name LIKE ?) "
//				+ "ORDER BY b.pkid DESC LIMIT ?, 10";
//
//		String like = "%" + search_key + "%";
//
//		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(boardVo.class), like, like, like, start);
//	}
	
	public boardVo getData(int pkid) {
	    String sql = "SELECT b.pkid, b.fkmember, b.title, b.content, b.viewcount, b.regdate, b.name, b.img_path "
	               + "FROM boardvo b WHERE b.pkid = ?";
	    List<boardVo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(boardVo.class), pkid);
	    return list.isEmpty() ? null : list.get(0);
	}

	public List<boardVo> getList(int start, String search_key) {
	    String sql = "SELECT b.pkid, b.fkmember, b.title, b.content, b.viewcount, b.regdate, b.name, b.img_path "
	               + "FROM boardvo b "
	               + "WHERE (b.title LIKE ? OR b.content LIKE ? OR b.name LIKE ?) "
	               + "ORDER BY b.pkid DESC LIMIT ?, 10";

	    String like = "%" + search_key + "%";
	    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(boardVo.class), like, like, like, start);
	}


	public int getTotalCount(String search_key) {
		String sql = "SELECT COUNT(*) AS cnt " + "FROM boardvo b "
				+ "WHERE (b.title LIKE ? OR b.content LIKE ? OR b.name LIKE ?)";

		String like = "%" + search_key + "%";

		return jdbcTemplate.queryForObject(sql, Integer.class, like, like, like);
	}

}
