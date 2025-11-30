package com.springboot.springmyboard.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.springmyboard.common.pageHelper;
import com.springboot.springmyboard.model.BoardDaoSpring;
import com.springboot.springmyboard.model.boardVo;
import com.springboot.springmyboard.model.boardVoImpl;
import com.springboot.springmyboard.model.memberDaoImpl;
import com.springboot.springmyboard.model.memberVo;
import com.springboot.springmyboard.service.memberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardControllerSpring {

	@Autowired
	private BoardDaoSpring bdaoSpring;

	@Autowired
	private memberService memberService;

	@Autowired
	private boardVoImpl bdao;

	// ===========================================================
	// board
	// ===========================================================

	// main 연습 페이지 호출
	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession(false); // 기존 세션 가져오기
	    if (session != null && session.getAttribute("MEMBER_NAME") != null) {
	        String name = (String) session.getAttribute("MEMBER_NAME");
	        model.addAttribute("name", name);  // JSP에서 ${name}으로 사용 가능
	    } else {
	        model.addAttribute("name", "비회원"); // 로그인 안 된 경우
	    }

	    model.addAttribute("page", "메인 페이지"); // 페이지 이름도 넘기기

	    return "main";  // main.jsp 호출
	}

	@GetMapping("/board/list")
	public String boardList(@RequestParam(name = "page", defaultValue = "1") int page,
	        @RequestParam(defaultValue = "") String search_key, HttpServletRequest request, Model model) {

	    HttpSession session = request.getSession(false); // 기존 세션 가져오기
	    if (session == null || session.getAttribute("MEMBER_PKID") == null) {
	        model.addAttribute("msg", "로그인이 필요합니다.");
	        model.addAttribute("url", "/member/login");
	        return "common/alert";
	    }

	    int start = (page - 1) * 10;
	    List<boardVo> list = bdao.getList(start, search_key);
	    int totalcount = bdao.getTotalCount(search_key);

	    pageHelper pagging = new pageHelper(totalcount, page, "/board/list", "");
	    model.addAttribute("list", list);
	    model.addAttribute("page", page);
	    model.addAttribute("search_key", search_key);
	    model.addAttribute("pagging", pagging);

	    return "board/list";
	}


	// ===========================================================
	// 게시글 등록 폼
	// ===========================================================
	@GetMapping("/board/register")
	public String boardRegister(HttpServletRequest request, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String search_key) {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("MEMBER_PKID") == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			model.addAttribute("url", "/member/login");
			return "common/alert";
		}

		model.addAttribute("page", page);
		model.addAttribute("search_key", search_key);

		return "board/register";
	}

	// ===========================================================
	// 게시글 등록 처리
	// ===========================================================
	@PostMapping("/board/register")
	public String boardRegisterProc(HttpServletRequest request,
	        Model model,
	        @RequestParam("title") String title,
	        @RequestParam("content") String content,
	        @RequestParam("upload") MultipartFile upload,
	        @RequestParam(name = "page", defaultValue = "1") int page,
	        @RequestParam(defaultValue = "") String search_key) {

	    // 로그인 체크
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("MEMBER_PKID") == null) {
	        model.addAttribute("msg", "로그인이 필요합니다.");
	        model.addAttribute("url", "/member/login");
	        return "common/alert";
	    }

	    int memberPkid = (int) session.getAttribute("MEMBER_PKID");

	    /*1. 파일 업로드 처리 */
	    String fileName = null;

	    if (!upload.isEmpty()) {
	        String uploadDir = request.getServletContext().getRealPath("/upload/");
	        File dir = new File(uploadDir);
	        if (!dir.exists()) dir.mkdirs();

	        fileName = System.currentTimeMillis() + "_" + upload.getOriginalFilename();
	        File dest = new File(uploadDir + fileName);

	        try {
	            upload.transferTo(dest);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /*2. boardVo 에 데이터 넣기 */
	    boardVo vo = new boardVo();
	    vo.setFkmember(memberPkid);
	    vo.setTitle(title);
	    vo.setContent(content);
	    vo.setImg_path(fileName);   
	    
	    /*3. DB 저장 */
	    bdao.insert(vo);

	    model.addAttribute("msg", "등록되었습니다.");
	    model.addAttribute("url", "/board/list?page=" + page + "&search_key=" + search_key);

	    return "common/alert";
	}


	// ===========================================================
	// 게시글 상세 보기
	// ===========================================================
	@GetMapping("/board/view")
	public String boardView(@RequestParam Integer pkid, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String search_key, HttpServletRequest request, Model model) {

		if (pkid == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/board/list");
			return "common/alert";
		}

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("MEMBER_PKID") == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			model.addAttribute("url", "/member/login");
			return "common/alert";
		}

		boardVo vo = bdao.getData(pkid);
		if (vo == null) {
			model.addAttribute("msg", "해당 글을 찾을 수 없습니다.");
			model.addAttribute("url", "/board/list?page=" + page + "&search_key=" + search_key);
			return "common/alert";
		}

		 // 2. 이미지 로그 찍기
	    if (vo.getImg_path() != null && !vo.getImg_path().isEmpty()) {
	        // 실제 서버 파일 경로 확인
	        String realPath = request.getServletContext().getRealPath("/upload/" + vo.getImg_path());
	        File file = new File(realPath);

	        System.out.println("==== 이미지 디버깅 ====");
	        System.out.println("DB img_path: " + vo.getImg_path());
	        System.out.println("서버 realPath: " + realPath);
	        System.out.println("파일 존재 여부: " + file.exists());
	        System.out.println("=====================");
	    } else {
	        System.out.println("==== 이미지 디버깅 ====");
	        System.out.println("첨부 이미지 없음");
	        System.out.println("=====================");
	    }

		
		// 조회수 증가
		vo.setViewcount(vo.getViewcount() + 1);
		bdao.update(vo);

		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("search_key", search_key);

		return "board/view";
	}

	// ===========================================================
	// 수정 폼
	// ===========================================================
	@GetMapping("/board/modify")
	public String boardModify(@RequestParam Integer pkid, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String search_key, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("MEMBER_PKID") == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			model.addAttribute("url", "/member/login");
			return "common/alert";
		}

		int loginPkid = (int) session.getAttribute("MEMBER_PKID");

		boardVo vo = bdao.getData(pkid);
		if (vo == null || vo.getFkmember() != loginPkid) {
			model.addAttribute("msg", "수정 권한이 없습니다.");
			model.addAttribute("url", "/board/list");
			return "common/alert";
		}

		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("search_key", search_key);

		return "board/modify";
	}

	// ===========================================================
	// 수정 처리
	// ===========================================================
	@PostMapping("/board/modify")
	public String boardModifyProc(@RequestParam Integer pkid,
	                              String title,
	                              String content,
	                              @RequestParam("upload") MultipartFile upload,  // 파일 추가
	                              HttpServletRequest request,
	                              Model model,
	                              @RequestParam(name = "page", defaultValue = "1") int page,
	                              @RequestParam(defaultValue = "") String search_key) {

	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("MEMBER_PKID") == null) {
	        model.addAttribute("msg", "로그인이 필요합니다.");
	        model.addAttribute("url", "/member/login");
	        return "common/alert";
	    }

	    int loginPkid = (int) session.getAttribute("MEMBER_PKID");
	    boardVo vo = bdao.getData(pkid);
	    if (vo == null || vo.getFkmember() != loginPkid) {
	        model.addAttribute("msg", "수정 권한이 없습니다.");
	        model.addAttribute("url", "/board/list");
	        return "common/alert";
	    }

	    // 1. 이미지 업로드 처리
	    if (!upload.isEmpty()) {
	        String uploadDir = request.getServletContext().getRealPath("/upload/");
	        File dir = new File(uploadDir);
	        if (!dir.exists()) dir.mkdirs();

	        String fileName = System.currentTimeMillis() + "_" + upload.getOriginalFilename();
	        File dest = new File(uploadDir + fileName);
	        try {
	            upload.transferTo(dest);
	            vo.setImg_path(fileName); // 새 이미지로 교체
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // 2. 제목, 내용 수정
	    vo.setTitle(title);
	    vo.setContent(content);
	    bdao.update(vo);

	    model.addAttribute("msg", "수정되었습니다.");
	    model.addAttribute("url", "/board/view?pkid=" + pkid + "&page=" + page + "&search_key=" + search_key);
	    return "common/alert";
	}


	// ===========================================================
	// 삭제 처리
	// ===========================================================
	@GetMapping("/board/delete")
	public String boardDelete(@RequestParam Integer pkid, HttpServletRequest request, Model model,
	        @RequestParam(name = "page", defaultValue = "1") int page,
	        @RequestParam(defaultValue = "") String search_key) {

	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("MEMBER_PKID") == null) {
	        model.addAttribute("msg", "로그인이 필요합니다.");
	        model.addAttribute("url", "/member/login");
	        return "common/alert";
	    }

	    int loginPkid = (int) session.getAttribute("MEMBER_PKID");

	    boardVo vo = bdao.getData(pkid);
	    if (vo == null || vo.getFkmember() != loginPkid) {
	        model.addAttribute("msg", "삭제 권한이 없습니다.");
	        model.addAttribute("url", "/board/list");
	        return "common/alert";
	    }

	    // 1. 이미지 삭제
	    if (vo.getImg_path() != null && !vo.getImg_path().isEmpty()) {
	        String realPath = request.getServletContext().getRealPath("/upload/" + vo.getImg_path());
	        File file = new File(realPath);
	        if (file.exists()) {
	            file.delete();
	        }
	    }

	    // 2. DB 레코드 삭제
	    bdao.delete(pkid);

	    model.addAttribute("msg", "삭제되었습니다.");
	    model.addAttribute("url", "/board/list?page=" + page + "&search_key=" + search_key);
	    return "common/alert";
	}


	

	// ===========================================================
	// member
	// ===========================================================

	// member/list 초창기 연습
//	@RequestMapping(value = "/member/list")
//	public String memberlist() {
//		System.out.println("/member/list 연습");
//		return "member/list";
//	}

	// login 초창기 연습
//	@RequestMapping(value="/login")
//	public String login() {
//		System.out.println("login 연습");
//		return "member/login";
//	}

	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}

	@GetMapping("/member/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return "member/login";
	}

	@PostMapping("/member/login")
	public String login_proc(String username, String password, HttpServletRequest request, Model model) {

	    memberVo vo = memberService.memberLogin(username, password);

	    if (vo != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("MEMBER_PKID", vo.getPkid());
	        session.setAttribute("MEMBER_NAME", vo.getName());

	        // 로그인 성공 후 바로 게시판 리스트로 이동
	        return "redirect:/board/list";
	    } else {
	        model.addAttribute("msg", "아이디 또는 비밀번호가 맞지 않습니다.");
	        model.addAttribute("url", "/member/login");
	        return "common/alert";
	    }
	}


	// 회원가입 폼 페이지
	@GetMapping("/member/register")
	public String register() {
		// 로그인 체크 없이 회원가입 폼으로 바로 이동
		return "member/register";
	}

	// 회원가입 처리
	@PostMapping("/member/register")
	public String register_proc(String user_id, String user_pw, String user_name, Model model) {

		// memberVo 객체 생성 후 서비스 호출
		memberVo vo = new memberVo();
		vo.setUser_id(user_id);
		vo.setUser_pw(user_pw);
		vo.setName(user_name);

		memberService.memberInsert(vo); // service에서 DB insert 수행

		model.addAttribute("msg", "회원가입 완료!");
		model.addAttribute("url", "/member/login");

		return "common/alert";
	}

	@GetMapping("/member/list")
	public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String search_key,
			Model model) {

		int total = memberService.totalCount(search_key);
		List<memberVo> list = memberService.memberSearch(page, search_key);

		// paramStr 만들기
		String paramStr = "";
		if (!search_key.equals("")) {
			paramStr += "&search_key=" + search_key;
		}

		// 페이지 헬퍼 생성 (url은 페이징 링크가 이동할 주소)
		pageHelper pagging = new pageHelper(total, page, "/member/list", paramStr);

		model.addAttribute("list", list);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("search_key", search_key);

		// 이거 추가!!
		model.addAttribute("pagging", pagging);

		return "member/list";
	}

	// 운영자 상세 개인 페이지 정보 열람
	@GetMapping("/member/view")
	public String detail(@RequestParam int pkid, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String search_key, Model model) {

		memberVo vo = memberService.memberGetData(pkid); // Service에서 pkid로 데이터 조회
		if (vo == null) {
			model.addAttribute("msg", "회원 정보가 존재하지 않습니다.");
			model.addAttribute("url", "/member/list?page=" + page + "&search_key=" + search_key);
			return "common/alert";
		}

		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("search_key", search_key);

		return "member/view";
	}

	// 회원 수정 폼
	@GetMapping("/member/modify")
	public String edit(@RequestParam int pkid, HttpServletRequest request, Model model) {

	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("MEMBER_PKID") == null) {
	        model.addAttribute("msg", "로그인이 필요합니다.");
	        model.addAttribute("url", "/member/login");
	        return "common/alert";
	    }

	    int loginPkid = (int) session.getAttribute("MEMBER_PKID");

	    // 본인만 수정 가능하게
	    if (loginPkid != pkid) {
	        model.addAttribute("msg", "본인 정보만 수정할 수 있습니다.");
	        model.addAttribute("url", "/member/view?pkid=" + pkid);
	        return "common/alert";
	    }

	    memberVo vo = memberService.memberGetData(pkid);
	    model.addAttribute("vo", vo);

	    return "member/modify";
	}


	// 회원 수정 처리
	@PostMapping("/member/modify")
	public String edit_proc(memberVo vo, HttpServletRequest request,
	                        @RequestParam(defaultValue = "1") int page,
	                        @RequestParam(defaultValue = "") String search_key, Model model) {

	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("MEMBER_PKID") == null) {
	        model.addAttribute("msg", "로그인이 필요합니다.");
	        model.addAttribute("url", "/member/login");
	        return "common/alert";
	    }

	    int loginPkid = (int) session.getAttribute("MEMBER_PKID");

	    // 본인 아닌 경우 수정 불가
	    if (loginPkid != vo.getPkid()) {
	        model.addAttribute("msg", "본인 정보만 수정할 수 있습니다.");
	        model.addAttribute("url", "/member/view?pkid=" + vo.getPkid());
	        return "common/alert";
	    }

	    memberService.memberUpdate(vo);

	    return "redirect:/member/view?pkid=" + vo.getPkid() + "&page=" + page + "&search_key=" + search_key;
	}


	// 회원 삭제
	@GetMapping("/member/delete")
	public String delete(@RequestParam int pkid, HttpServletRequest request,
	                     @RequestParam int page,
	                     @RequestParam(defaultValue = "") String search_key, Model model) {

	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("MEMBER_PKID") == null) {
	        model.addAttribute("msg", "로그인이 필요합니다.");
	        model.addAttribute("url", "/member/login");
	        return "common/alert";
	    }

	    int loginPkid = (int) session.getAttribute("MEMBER_PKID");

	    // 본인만 삭제 가능
	    if (loginPkid != pkid) {
	        model.addAttribute("msg", "본인 계정만 삭제할 수 있습니다.");
	        model.addAttribute("url", "/member/view?pkid=" + pkid);
	        return "common/alert";
	    }

	    memberService.memberDelete(pkid);

	    model.addAttribute("msg", "계정이 삭제되었습니다.");
	    model.addAttribute("url", "/member/login");
	    return "common/alert";
	}

	
}
