# 소통 자유게시판

대학생들을 위한 자유게시판 웹 애플리케이션입니다.  
회원가입 후 글 작성, 이미지 업로드 등 다양한 기능을 통해 학생들이 자유롭게 소통할 수 있도록 CRUD 기능을 구현하였습니다.

<br>

<img width="367" height="671" alt="Image" src="https://github.com/user-attachments/assets/333f8793-d075-49ec-b967-b09c42667808" />
<img width="373" height="666" alt="Image" src="https://github.com/user-attachments/assets/bd222b4e-e8d9-4ded-ac23-3f6be69d6805" />

## 프로젝트 소개

에브리타임과 같은 대학 커뮤니티 플랫폼을 참고하여 개발한 게시판 웹 애플리케이션입니다.  
Spring Boot와 JSP, JQUERY를 활용하여 회원 관리, 게시글 관리 등의 기본적인 CRUD 기능을 구현했습니다.

---

## 기술 스택

### Backend
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring JDBC** - 데이터베이스 연동
- **MyBatis** - SQL 매핑

### Frontend
- **JSP / JSTL** - 서버 사이드 렌더링
- **CSS** - 스타일링
- **JavaScript** - 클라이언트 인터랙션

### Database
- **MySQL 8** - 데이터 저장소
- **HikariCP** - 커넥션 풀 관리

### Build Tool
- **Maven** - 의존성 관리 및 빌드

### Server
- **Embedded Tomcat** - 내장 서버
- **Tomcat Jasper** - JSP 엔진

---

## 주요 기능

### 회원 관리
- 회원가입 / 로그인 / 로그아웃
- 회원 정보 수정 / 삭제
- 회원 목록 조회 및 검색
- 세션 기반 인증

### 게시판 관리
- 게시글 작성 / 조회 / 수정 / 삭제 (CRUD)
- 이미지 파일 업로드 및 첨부
- 조회수 자동 증가
- 페이징 처리 (10개씩)
- 제목/내용/작성자 통합 검색

### 권한 관리
- 로그인 사용자만 게시판 접근 가능
- 본인이 작성한 글만 수정/삭제 가능
- 본인 정보만 수정/삭제 가능

---

## 프로젝트 구조

```
src/main/java/com/springboot/springmyboard/
│
├── controller/
│   └── BoardControllerSpring.java    # 게시판 & 회원 컨트롤러
│
├── service/
│   ├── memberService.java            # 회원 서비스 인터페이스
│   ├── memberServiceImpl.java        # 회원 서비스 구현체
│   ├── boardVoService.java           # 게시판 서비스 인터페이스
│   └── boardVoServiceImpl.java       # 게시판 서비스 구현체
│
├── model/
│   ├── memberVo.java                 # 회원 VO
│   ├── memberDao.java                # 회원 DAO 인터페이스
│   ├── memberDaoImpl.java            # 회원 DAO 구현체
│   ├── boardVo.java                  # 게시판 VO
│   └── boardVoImpl.java              # 게시판 DAO 구현체
│
├── common/
│   ├── pageHelper.java               # 페이징 헬퍼
│   └── webHelper.java                # 세션 체크 헬퍼
│
└── SpringmyboardApplication.java     # 메인 애플리케이션
```

---

## 데이터베이스 구조

### member 테이블
```sql
CREATE TABLE member (
    pkid INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    user_pw VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### boardvo 테이블
```sql
CREATE TABLE boardvo (
    pkid INT PRIMARY KEY AUTO_INCREMENT,
    fkmember INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    viewcount INT DEFAULT 0,
    img_path VARCHAR(255),
    name VARCHAR(50),
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fkmember) REFERENCES member(pkid)
);
```

---

## 실행 방법

### 1. 사전 요구사항
- JDK 21 이상
- MySQL 8 이상
- Maven
- IntelliJ IDEA (권장)

### 2. 데이터베이스 설정
```sql
-- 데이터베이스 생성
CREATE DATABASE springboard;

-- 테이블 생성 (위의 SQL 참고)
```

### 3. application.properties 설정
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springboard
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

### 4. 프로젝트 실행
```bash
# Maven 빌드
mvn clean install

# 애플리케이션 실행
mvn spring-boot:run
```

### 5. 접속
브라우저에서 `http://localhost:8080/main` 접속

---

## 주요 화면

### 메인 페이지
- 로그인/비로그인 상태에 따른 메시지 표시

### 로그인/회원가입
- 세션 기반 로그인 처리
- 회원가입 시 유효성 검증

### 게시판 목록
- 페이징 처리 (10개씩)
- 검색 기능 (제목, 내용, 작성자)
- 조회수 표시

### 게시글 작성/수정
- 제목, 내용 입력
- 이미지 파일 업로드
- 본인 글만 수정/삭제 가능

---

## 주요 구현 내용

### 1. Spring JDBC 기반 데이터 접근
- JdbcTemplate을 활용한 SQL 실행
- RowMapper를 통한 객체 매핑
- Connection Pool 자동 관리

### 2. 세션 기반 인증
- HttpSession을 활용한 로그인 상태 유지
- 인터셉터 패턴으로 권한 체크
- 로그인 필수 페이지 접근 제어

### 3. 파일 업로드
- MultipartFile을 통한 이미지 업로드
- 타임스탬프 기반 파일명 생성으로 중복 방지
- 서버 로컬 저장소에 파일 저장

### 4. 페이징 처리
- pageHelper 클래스로 페이지네이션 로직 구현
- 이전/다음 버튼 표시
- 검색 조건 유지

---

## API 엔드포인트

### 회원 관련
- `GET /member/login` - 로그인 폼
- `POST /member/login` - 로그인 처리
- `GET /member/logout` - 로그아웃
- `GET /member/register` - 회원가입 폼
- `POST /member/register` - 회원가입 처리
- `GET /member/list` - 회원 목록 (검색 가능)
- `GET /member/view?pkid={id}` - 회원 상세
- `GET /member/modify?pkid={id}` - 회원 수정 폼
- `POST /member/modify` - 회원 수정 처리
- `GET /member/delete?pkid={id}` - 회원 삭제

### 게시판 관련
- `GET /main` - 메인 페이지
- `GET /board/list` - 게시글 목록 (검색, 페이징)
- `GET /board/register` - 게시글 작성 폼
- `POST /board/register` - 게시글 등록 처리
- `GET /board/view?pkid={id}` - 게시글 상세
- `GET /board/modify?pkid={id}` - 게시글 수정 폼
- `POST /board/modify` - 게시글 수정 처리
- `GET /board/delete?pkid={id}` - 게시글 삭제

---

## 향후 개선 사항

- [ ] 댓글 기능 추가
- [ ] 좋아요/싫어요 기능
- [ ] 파일 다운로드 기능
- [ ] 공지사항 게시판 분리
- [ ] Spring Security 적용
- [ ] RESTful API 전환
- [ ] 프론트엔드 분리 (React/Vue)
- [ ] 이미지 리사이징 및 썸네일 생성
- [ ] 실시간 알림 기능

## 개발자

**palantir1997**
- GitHub: [@palantir1997](https://github.com/palantir1997)

## 라이선스

이 프로젝트는 개인 학습 목적으로 제작되었습니다.

---

⭐ 이 프로젝트가 도움이 되었다면 Star를 눌러주세요!
