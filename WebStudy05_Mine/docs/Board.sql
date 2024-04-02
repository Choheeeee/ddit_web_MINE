--기본(계층)형 게시판

--게시글 FREEBOARD
--글번호 BO_NO NUMBER(6) PK
--제목 BO_TITLE VARCHAR2(50 CHAR) NOT NULL
--작성자명 BO_WRITER VHARCHAR2(20 CHAR) NOT NULL
--IP BO_IP VARCHAR2(50) NOT NULL
--email BO_MAIL VARCHAR2(50 CHAR)
--비번 BO_PASS VARCHAR2(200) NOT NULL
--내용 BO_CONTENT CLOB
--작성일 BO_DATE DATE DEFAULT SYSDATE
--조회수 BO_HIT NUMBER(4) DEFAULT 0

CREATE SEQUENCE FREEBOARD_SEQ;
CREATE TABLE FREEBOARD(
    BO_NO NUMBER(6) not null, --글번호 
    BO_TITLE VARCHAR2(50 CHAR) NOT NULL, --제목 
    BO_WRITER VARCHAR2(20 CHAR) NOT NULL, --작성자명 
    BO_IP VARCHAR2(50) NOT NULL , --IP 
    BO_MAIL VARCHAR2(50 CHAR), --email 
    BO_PASS VARCHAR2(200) NOT NULL, --비번 
    BO_CONTENT CLOB, --내용 
    BO_DATE DATE DEFAULT SYSDATE, --작성일 
    BO_HIT NUMBER(4) DEFAULT 0, --조회수 
    BO_PARENT NUMBER(5), -- 계층형 게시판 구현용
    CONSTRAINT PK_FREEBOARD PRIMARY KEY(BO_NO),
    CONSTRAINT FK_FREEBOARD FOREIGN KEY(BO_PARENT)
        REFERENCES FREEBOARD(BO_NO)
);

--dummy data
INSERT INTO freeboard (
    bo_no,    bo_title,    bo_writer,
    bo_ip,    bo_mail,    bo_pass,
    bo_content,  
    bo_hit
) 
SELECT FREEBOARD_SEQ.NEXTVAL, 
    MEM_NAME||'이 남기는 글', MEM_NAME,
    '192.168.43.'||MOD(NVL(MEM_MILEAGE, 1), 256),
    MEM_MAIL, MEM_PASS,
    MEM_NAME||'이 글을 남겼는데, 자기 직업은 '||MEM_JOB||
    '이고, <br /> 취미는 '||MEM_LIKE||'라네.',
    MOD(NVL(MEM_MILEAGE, 23), 1000)
FROM MEMBER;
SELECT COUNT(*) FROM FREEBOARD;
COMMIT;
SELECT BO_NO FROM FREEBOARD ORDER BY BO_NO DESC;



COMMENT ON COLUMN FREEBOARD.BO_NO IS '글번호';
COMMENT ON COLUMN FREEBOARD.BO_TITLE IS '제목';
COMMENT ON COLUMN FREEBOARD.BO_WRITER IS '작성자';
COMMENT ON COLUMN FREEBOARD.BO_IP IS '아이피';
COMMENT ON COLUMN FREEBOARD.BO_MAIL IS '이메일';
COMMENT ON COLUMN FREEBOARD.BO_PASS IS '비밀번호';
COMMENT ON COLUMN FREEBOARD.BO_CONTENT IS '내용';
COMMENT ON COLUMN FREEBOARD.BO_DATE IS '작성일';
COMMENT ON COLUMN FREEBOARD.BO_HIT IS '조회수';
COMMENT ON COLUMN FREEBOARD.BO_PARENT IS '상위글번호';

-- 한 게시글에 복수의 파일 첨부 가능.

--첨부파일(ATTATCH)
--파일번호 ATT_NO  NUMBER(4) PK
--글번호 BO_NO NUMBER(6) FK
--파일명 ATT_FILENAME VARCHAR2(150) NOT NULL
--저장명 ATT_SAVENAME VARCHAR2(70) NOT NULL
--MIME ATT_MIME VARCHAR2(100) 
--크기 ATT_FILESIZE NUMBER(10) NOT NULL
--팬시크기 ATT_FANCYSIZE VARCAHR2(10) NOT NULL
--다운로드수 ATT_DOWNLOAD NUMBER(4) DEFAULT 0

CREATE TABLE ATTATCH(
     ATT_NO  NUMBER(4), --파일번호
     BO_NO NUMBER(6), --글번호
     ATT_FILENAME VARCHAR2(150) NOT NULL, --파일명
     ATT_SAVENAME VARCHAR2(70) NOT NULL, --저장명
     ATT_MIME VARCHAR2(100) , --MIME
     ATT_FILESIZE NUMBER(10) NOT NULL, --크기
     ATT_FANCYSIZE VARCHAR2(10) NOT NULL, --팬시크기
     ATT_DOWNLOAD NUMBER(4) DEFAULT 0, --다운로드수
     CONSTRAINT PK_ATTATCH PRIMARY KEY(ATT_NO),
     CONSTRAINT FK_ATTATCH_BOARD FOREIGN KEY(BO_NO)
        REFERENCES FREEBOARD(BO_NO)
);

COMMENT ON COLUMN ATTATCH.ATT_NO IS '파일번호';
COMMENT ON COLUMN ATTATCH.BO_NO IS '글번호';
COMMENT ON COLUMN ATTATCH.ATT_FILENAME IS '파일명';
COMMENT ON COLUMN ATTATCH.ATT_SAVENAME IS '저장명';
COMMENT ON COLUMN ATTATCH.ATT_MIME IS '파일MIME';
COMMENT ON COLUMN ATTATCH.ATT_FILESIZE IS '파일크기';
COMMENT ON COLUMN ATTATCH.ATT_FANCYSIZE IS '팬시크기';
COMMENT ON COLUMN ATTATCH.ATT_DOWNLOAD IS '다운로드수';


-- 200자 미만의 계층형 덧글 지원

--FREEREPLY
--덧글번호 REP_NO NUMBER(4) PK
--글번호 BO_NO NUMBER(6) FK
--덧글내용 REP_CONTENT VARCHAR2(200 CHAR)
--덧글작성자명 REP_WRITER VARCHAR2(20 CHAR) NOT NULL
--이메일 REP_MAIL VARCHAR2(50 CHAR)
--비번 REP_PASS VARCHAR2(200) NOT NULL
--덧글작성일 REP_DATE DATE DEFAULT SYSDATE
--부모덧글 REP_PARENT NUMBER(4) FK

CREATE TABLE FREEREPLY(
     REP_NO NUMBER(4), --덧글번호
     BO_NO NUMBER(6)NOT NULL , --글번호
     REP_CONTENT VARCHAR2(200 CHAR), --덧글내용
     REP_WRITER VARCHAR2(20 CHAR) NOT NULL, --덧글작성자명
     REP_MAIL VARCHAR2(50 CHAR), --이메일
     REP_PASS VARCHAR2(200) NOT NULL, --비번
     REP_DATE DATE DEFAULT SYSDATE, --덧글작성일
     REP_PARENT NUMBER(4), --부모덧글
     CONSTRAINT PK_FREEREPLY PRIMARY KEY(REP_NO),
     CONSTRAINT FK_REPLY_BOARD FOREIGN KEY(BO_NO)
        REFERENCES FREEBOARD(BO_NO)
);

COMMENT ON COLUMN FREEREPLY.REP_NO IS '덧글번호';
COMMENT ON COLUMN FREEREPLY.BO_NO IS '글번호';
COMMENT ON COLUMN FREEREPLY.REP_CONTENT IS '내용';
COMMENT ON COLUMN FREEREPLY.REP_WRITER IS '작성자';
COMMENT ON COLUMN FREEREPLY.REP_MAIL IS '이메일';
COMMENT ON COLUMN FREEREPLY.REP_PASS IS '비밀번호';
COMMENT ON COLUMN FREEREPLY.REP_DATE IS '작성일';
COMMENT ON COLUMN FREEREPLY.REP_PARENT IS '원글번호';