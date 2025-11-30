//package com.springboot.springmyboard.service;
//
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.mysql.cj.protocol.ServerSessionStateController.ServerSessionStateChanges;
//
//import common.dbHelper;
//
//@Component
//public class memberDaoImpl implements memberDao {
//	@Autowired
//	private dbHelper helper;
//	private Connection dbcon;
//	private PreparedStatement stmt;
//	private ResultSet rs;
//
//	@Override
//	public void insert(memberVo vo) {
//		try {
//			dbcon = helper.getConnection();
//			String sql = "insert into member(user_id, user_pw, name) values (?, ?, ?)";
//			stmt = dbcon.prepareStatement(sql);
//			
//			stmt.setString(1, vo.getUser_id());
//			stmt.setString(2, vo.getUser_pw());
//			stmt.setString(3, vo.getName());
//			stmt.execute();
//			System.out.println(stmt);
//			
//			helper.close(dbcon, stmt);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void update(memberVo vo) {
//		try {
//			dbcon = helper.getConnection();
//			String sql = "update member set user_id = ?, user_pw = ?, name = ? where pkid = ?";
//			stmt = dbcon.prepareStatement(sql);
//			
//			stmt.setString(1, vo.getUser_id());
//			stmt.setString(2, vo.getUser_pw());
//			stmt.setString(3, vo.getName());
//			stmt.setInt(4, vo.getPkid());
//			stmt.execute();
//			System.out.println(stmt);
//			helper.close(dbcon, stmt);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void delete(int pkid) {
//		try {
//			dbcon = helper.getConnection();
//			String sql = "delete from member where pkid = ?";
//			stmt = dbcon.prepareStatement(sql);
//			
//			stmt.setInt(1, pkid);
//			stmt.execute();
//			System.out.println(stmt);
//			
//			helper.close(dbcon, stmt);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//
//	@Override
//	public memberVo getData(String user_id, String user_pw) {
//		memberVo vo = null;
//		
//		try {
//			dbcon = helper.getConnection();
//			String sql = "select pkid, user_id, user_pw, name, regdate from member where user_id = ? and user_pw = ?";
//			stmt = dbcon.prepareStatement(sql);
//			
//			stmt.setString(1, user_id);
//			stmt.setString(2, user_pw);
//			rs = stmt.executeQuery();
//			System.out.println(stmt);
//			
//			if(rs.next()) {
//				vo = new memberVo();
//				
//				vo.setPkid(rs.getInt("pkid"));
//				vo.setUser_id(rs.getString("user_id"));
//				vo.setUser_pw(rs.getString("user_pw"));
//				vo.setName(rs.getString("name"));
//				vo.setRegdate(rs.getString("regdate"));
//			}
//			helper.close(dbcon, stmt, rs);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return vo;
//	}
//
//	@Override
//	public memberVo getData(int pkid) {
//		memberVo vo = null;
//		
//		try {
//			dbcon = helper.getConnection();
//			String sql = "select pkid, user_id, user_pw, name, regdate from member where pkid = ?";
//			stmt = dbcon.prepareStatement(sql);
//			
//			stmt.setInt(1, pkid);
//			rs = stmt.executeQuery();
//			System.out.println(stmt);
//			
//			if(rs.next()) {
//				vo = new memberVo();
//				
//				vo.setPkid(rs.getInt("pkid"));
//				vo.setUser_id(rs.getString("user_id"));
//				vo.setUser_pw(rs.getString("user_pw"));
//				vo.setName(rs.getString("name"));
//				vo.setRegdate(rs.getString("regdate"));
//			}
//			helper.close(dbcon, stmt, rs);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return vo;
//	}
//
//	@Override
//	public List<memberVo> getList(int start, String search_key) {
//		List<memberVo> list = new ArrayList<memberVo>();
//		
//		try {
//			dbcon = helper.getConnection();
//			String sql = "select pkid, user_id, user_pw, name, regdate "
//					+ " from member"
//					+ " where (user_id like ?  or name like ?)"
//					+ " order by pkid desc limit ?, 10 ";
//			stmt = dbcon.prepareStatement(sql);
//			
//			stmt.setString(1, "%" + search_key + "%" );
//			stmt.setString(2, "%" + search_key + "%" );
//			stmt.setInt(3, start);
//			
//			rs = stmt.executeQuery();
//			System.out.println(stmt);
//			
//			while(rs.next()) {
//				memberVo vo = new memberVo();
//				
//				vo.setPkid(rs.getInt("pkid"));
//				vo.setUser_id(rs.getString("user_id"));
//				vo.setUser_pw(rs.getString("user_pw"));
//				vo.setName(rs.getString("name"));
//				vo.setRegdate(rs.getString("regdate"));
//				
//				list.add(vo);
//			}
//			
//			helper.close(dbcon, stmt, rs);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return list;
//	}
//
//	@Override
//	public int getTotalCount(String search_key) {
//		int total = 0;
//		
//		try {
//			dbcon = helper.getConnection();
//			String sql = "select count(pkid) as cnt "
//					+ " from member"
//					+ " where (user_id like ?  or name like ?)";
//			stmt = dbcon.prepareStatement(sql);
//			
//			stmt.setString(1, "%" + search_key + "%" );
//			stmt.setString(2, "%" + search_key + "%" );
//			
//			rs = stmt.executeQuery();
//			System.out.println(stmt);
//			
//			if(rs.next()) {
//				total = rs.getInt("cnt");
//			}
//			
//			helper.close(dbcon, stmt, rs);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return total;
//	}
//
//	@Override
//	public int getIdCnt(String user_id) {
//		int total = 0;
//		
//		try {
//			dbcon = helper.getConnection();
//			String sql = "select count(pkid) as cnt from member where user_id = ?";
//			stmt = dbcon.prepareStatement(sql);
//			stmt.setString(1, user_id);
//			
//			rs = stmt.executeQuery();
//			System.out.println(stmt);
//			
//			if(rs.next()) {
//				total = rs.getInt("cnt");
//			}
//			
//			helper.close(dbcon, stmt, rs);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return total;
//	}
//}

package com.springboot.springmyboard.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class memberDaoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 로그인 메소드 (기존 getData(user_id, user_pw) 역할)
	public memberVo getData(String user_id, String user_pw) {
		String sql = "SELECT pkid, user_id, user_pw, name, regdate FROM member WHERE user_id = ? AND user_pw = ?";
		Object[] args = { user_id, user_pw };

		try {
			return jdbcTemplate.queryForObject(sql, args, new RowMapper<memberVo>() {
				@Override
				public memberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					memberVo vo = new memberVo();
					vo.setPkid(rs.getInt("pkid"));
					vo.setUser_id(rs.getString("user_id"));
					vo.setUser_pw(rs.getString("user_pw"));
					vo.setName(rs.getString("name"));
					vo.setRegdate(rs.getString("regdate"));
					return vo;
				}
			});
		} catch (Exception e) {
			return null; // 로그인 실패 시 null 반환
		}
	}

	// pkid로 회원 정보 가져오기
	public memberVo getData(int pkid) {
		String sql = "SELECT pkid, user_id, user_pw, name, regdate FROM member WHERE pkid = ?";
		Object[] args = { pkid };

		try {
			return jdbcTemplate.queryForObject(sql, args, new RowMapper<memberVo>() {
				@Override
				public memberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					memberVo vo = new memberVo();
					vo.setPkid(rs.getInt("pkid"));
					vo.setUser_id(rs.getString("user_id"));
					vo.setUser_pw(rs.getString("user_pw"));
					vo.setName(rs.getString("name"));
					vo.setRegdate(rs.getString("regdate"));
					return vo;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	// 회원가입 메소드
	public int insert(memberVo vo) {
		String sql = "INSERT INTO member(user_id, user_pw, name) VALUES (?, ?, ?)";
		Object[] args = { vo.getUser_id(), vo.getUser_pw(), vo.getName() };

		try {
			return jdbcTemplate.update(sql, args); // 성공 시 1 반환
		} catch (Exception e) {
			e.printStackTrace();
			return 0; // 실패 시 0 반환
		}
	}

	public List<memberVo> getList(int start, String search_key) {
		String sql = "SELECT pkid, user_id, user_pw, name, regdate " + "FROM member "
				+ "WHERE user_id LIKE ? OR name LIKE ? " + "ORDER BY pkid DESC LIMIT ?, 10";
		Object[] args = { "%" + search_key + "%", "%" + search_key + "%", start };

		return jdbcTemplate.query(sql, args, new RowMapper<memberVo>() {
			@Override
			public memberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				memberVo vo = new memberVo();
				vo.setPkid(rs.getInt("pkid"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setUser_pw(rs.getString("user_pw"));
				vo.setName(rs.getString("name"));
				vo.setRegdate(rs.getString("regdate"));
				return vo;
			}
		});
	}

	public int getTotalCount(String search_key) {
		String sql = "SELECT COUNT(pkid) FROM member WHERE user_id LIKE ? OR name LIKE ?";
		Object[] args = { "%" + search_key + "%", "%" + search_key + "%" };

		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	// 회원 정보 수정
	public int update(memberVo vo) {
	    String sql = "UPDATE member "
	               + "SET user_id = ?, "
	               + "    user_pw = COALESCE(?, user_pw), "
	               + "    name = ? "
	               + "WHERE pkid = ?";
	    Object[] args = { vo.getUser_id(), vo.getUser_pw(), vo.getName(), vo.getPkid() };

	    try {
	        return jdbcTemplate.update(sql, args);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}


	// 회원 정보 삭제
	public int delete(int pkid) {
		String sql = "DELETE FROM member WHERE pkid = ?";
		Object[] args = { pkid };

		try {
			return jdbcTemplate.update(sql, args); // 성공 시 1 반환
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
