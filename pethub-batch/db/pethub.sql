-- clean install -DskipTests package -P local


CREATE TABLE auth_member (
  user_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  user_id varchar(20) NOT NULL COMMENT '유저아이디',
  password varchar(200) DEFAULT NULL COMMENT '패스워드',
  user_nm varchar(50) DEFAULT NULL COMMENT '이름',
  email varchar(50) DEFAULT NULL COMMENT '메일',
  use_yn char(1) DEFAULT 'N' COMMENT '사용여부(Y:사용,N:비사용)',
  reg_date datetime DEFAULT NULL COMMENT '등록일',
  PRIMARY KEY (user_srl),
  UNIQUE KEY auth_member_uk (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1 COMMENT='유저정보';

insert into auth_member(user_srl,user_id,password,user_nm,email,use_yn,reg_date) values (1,'admin','$2a$10$3pfLylekH08DS6IO7RY4s.oHHhq5V1pOpiLeq3lKXz.jbfSzqNvZC','관리','','Y',SYSDATE());


CREATE TABLE auth_member_role (
  user_role_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  user_id varchar(20) DEFAULT NULL COMMENT '유저아이디',
  role_cd varchar(10) DEFAULT NULL COMMENT '권한코드',
  PRIMARY KEY (user_role_srl)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='유저별 권한';

insert into auth_member_role(user_role_srl,user_id,role_cd) values (1,'admin','ADM');

CREATE TABLE auth_menu (
  menu_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  parent_srl int(10) DEFAULT NULL COMMENT '참조번호',
  menu_nm varchar(100) DEFAULT NULL COMMENT '메뉴명',
  menu_url varchar(100) DEFAULT NULL COMMENT '경로',
  menu_stp int(4) DEFAULT NULL COMMENT '순서',
  use_yn char(1) CHARACTER SET latin1 DEFAULT 'Y' COMMENT '사용여부',
  PRIMARY KEY (menu_srl)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메뉴관리';

insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (1,0,'사이트관리','',1,'Y');
insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (2,1,'권한관리','/adm/role/list',1,'Y');
insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (3,1,'사용자관리','/adm/user/list',2,'Y');
insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (4,1,'메뉴관리','',3,'Y');
insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (5,4,'메뉴등록','/adm/menu/list',1,'Y');
insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (6,4,'메뉴설정','/adm/menu/role/list',2,'Y');
insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (7,0,'게시판관리','',2,'Y');
insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (8,7,'자료실','/adm/board/01/list',1,'Y');
insert into auth_menu(menu_srl,parent_srl,menu_nm,menu_url,menu_stp,use_yn) values (9,1,'캐쉬관리','/adm/cache/list',4,'Y');

CREATE TABLE auth_menu_role (
  role_cd varchar(10) DEFAULT NULL COMMENT '권한코드',
  menu_srl int(11) DEFAULT NULL COMMENT '참조번호'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='권한별메뉴';


insert into auth_menu_role(role_cd,menu_srl) values ('ADM',1);
insert into auth_menu_role(role_cd,menu_srl) values ('ADM',2);
insert into auth_menu_role(role_cd,menu_srl) values ('ADM',3);
insert into auth_menu_role(role_cd,menu_srl) values ('ADM',4);
insert into auth_menu_role(role_cd,menu_srl) values ('ADM',5);
insert into auth_menu_role(role_cd,menu_srl) values ('ADM',6);
insert into auth_menu_role(role_cd,menu_srl) values ('ADM',7);

CREATE TABLE auth_role (
  role_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  role_cd varchar(10) DEFAULT NULL COMMENT '권한코드',
  role_nm varchar(50) DEFAULT NULL COMMENT '권한',
  use_yn char(1) CHARACTER SET latin1 DEFAULT 'Y',
  PRIMARY KEY (role_srl)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='권한코드';

insert into auth_role(role_srl,role_cd,role_nm,use_yn) values (1,'ADM','관리자','Y');
insert into auth_role(role_srl,role_cd,role_nm,use_yn) values (2,'USR','사용자','Y');

CREATE TABLE auth_login_log (
  log_srl int(10) unsigned NOT NULL AUTO_INCREMENT,
  try_id varchar(100) DEFAULT NULL,
  success_yn char(1) DEFAULT NULL,
  reg_date datetime DEFAULT NULL,
  remote_addr varchar(20) DEFAULT NULL,
  comment varchar(100) DEFAULT NULL,
  PRIMARY KEY (log_srl)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='로그인 이력';

CREATE TABLE com_board (
  board_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  fid int(10) unsigned NOT NULL DEFAULT '0' COMMENT '그룹아이디',
  lev int(4) unsigned NOT NULL DEFAULT '0' COMMENT '깊이',
  stp int(4) unsigned NOT NULL DEFAULT '0' COMMENT '순서',
  user_id varchar(30) NOT NULL COMMENT '아이디',
  title varchar(200) DEFAULT NULL COMMENT '제목',
  content text COMMENT '내용',
  ip varchar(15) DEFAULT NULL COMMENT '아이피',
  reg_date datetime DEFAULT NULL COMMENT '등록일',
  read_cnt int(10) unsigned DEFAULT '0' COMMENT '조회수',
  flag varchar(10) NOT NULL COMMENT '구분',
  PRIMARY KEY (board_srl),
  KEY flag (flag)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시판';

CREATE TABLE com_board_file (
  board_file_srl int(10) unsigned NOT NULL AUTO_INCREMENT,
  board_srl int(10) unsigned DEFAULT '0',
  file_sys_nm varchar(50) DEFAULT NULL,
  file_real_nm varchar(100) DEFAULT NULL,
  file_ext varchar(10) DEFAULT NULL,
  file_size varchar(10) DEFAULT NULL,
  PRIMARY KEY (board_file_srl),
  KEY IDX_BOARD_FILE (board_srl)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='파일목록';

CREATE TABLE com_board_reply (
  board_reply_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  board_srl int(10) unsigned DEFAULT '0' COMMENT '참조번호',
  user_id varchar(20) DEFAULT NULL COMMENT '유저아이디',
  content text COMMENT '내용',
  ip varchar(20) DEFAULT NULL COMMENT '아이피',
  reg_date datetime DEFAULT NULL COMMENT '등록일',
  PRIMARY KEY (board_reply_srl)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------------------------------------------------------------------------------


CREATE TABLE site_info (
  site_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  site_nm varchar(100) DEFAULT NULL COMMENT '사이트명',
  site_url varchar(100) DEFAULT NULL COMMENT '사이트 url',
  site_etc text COMMENT '기타',
  reg_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  upt_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (site_srl)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사이트정보';


CREATE TABLE site_link (
  link_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  site_srl int(10) unsigned DEFAULT NULL COMMENT 'site_info 참조번호',
  link_cd varchar(10) DEFAULT NULL COMMENT '링크코드',
  link_nm varchar(100) DEFAULT NULL COMMENT '링크명',
  link_url varchar(100) DEFAULT NULL COMMENT '링크 url',
  link_cls varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '실행클래스',
  link_mtd_lst varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '목록 실행메소드',
  link_mtd_cts varchar(50) DEFAULT NULL COMMENT '내용 실행메소드',
  link_cnt int(11) DEFAULT '0' COMMENT '링크수',
  batch_itv varchar(20) DEFAULT NULL COMMENT '배치 간격',
  use_yn char(1) DEFAULT 'Y' COMMENT '사용여부',
  reg_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  upt_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  exc_dt datetime DEFAULT NULL COMMENT '실행일',
  PRIMARY KEY (link_srl),
  KEY site_link_idx (site_srl,link_cd)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사이트링크';



CREATE TABLE site_link_data (
  data_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  site_srl int(10) unsigned COMMENT 'site_info 참조번호',
  link_srl int(10) unsigned COMMENT 'site_link 참조번호',
  data_id varchar(50) DEFAULT NULL COMMENT '아이디',
  data_title varchar(400) DEFAULT NULL COMMENT '제목',
  data_link varchar(200) DEFAULT NULL COMMENT '링크',
  data_img varchar(100) DEFAULT NULL COMMENT '이미지',
  data_content text COMMENT '내용',
  reg_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  upt_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (data_srl),
  KEY site_link_data_idx (site_srl, link_srl, data_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='사이트데이터';



CREATE TABLE site_link_log (
  log_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  site_srl int(10) unsigned DEFAULT NULL COMMENT 'site_info 참조번호',
  link_srl int(10) unsigned DEFAULT NULL COMMENT 'site_link 참조번호',
  link_cnt int(10) unsigned DEFAULT NULL COMMENT '링크수',
  log_cd varchar(10) DEFAULT NULL COMMENT '에러제목',
  log_msg text COMMENT '에러내용',
  reg_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  PRIMARY KEY (log_srl),
  KEY site_link_log_idx (site_srl,link_srl,log_cd)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='사이트로그';





insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (1,'도그마루','dogmaru.co.kr','강아지, 고양이 분양','2019-07-05 00:00:00','2019-07-05 00:00:00');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (2,'I love dog','www.theilovedog.com','강이지, 고양이 분양','2019-07-06 00:00:00','2019-07-06 00:00:00');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (3,'주세요닷컴','www.zooseyo.com','강아지, 고양이 분양','2019-07-06 00:00:00','2019-07-06 00:00:00');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (4,'도그짱','www.dog-zzang.co.kr','강아지, 고양이 분양','2019-07-09 13:36:27','2019-07-09 13:36:27');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (5,'우리펫','www.우리펫.com','강아지, 고양이 분양','2019-07-10 00:00:00','2019-07-10 00:00:00');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (6,'착한애견분양','chdog.co.kr','강아지 분양','2019-07-11 00:00:00','2019-07-11 00:00:00');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (7,'유기견보호센터','www.animal.or.kr','강아지 분양','2019-07-11 00:00:00','2019-07-11 00:00:00');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (8,'도그시장','www.dogsijang.co.kr','강아지 분양','2019-07-12 00:00:00','2019-07-12 00:00:00');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (9,'강아지분양카페','www.puppycafe.co.kr','강아지 분양','2019-07-13 00:00:00','2019-07-13 00:00:00');
insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (10,'KaKao Dog ','www.kakaodog.co.kr','강아지 분','2019-07-13 00:00:00','2019-07-13 00:00:00');



insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (1,1,'dog','강아지 분양','https://dogmaru.co.kr/sdog','kr.pethub.site.DogmaruCoKr','getDogList','getDogContent',20,'8','Y','2019-07-05 00:00:00','2019-07-06 13:20:07','2019-07-12 21:17:01');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (2,1,'cat','고양이 분양','https://dogmaru.co.kr/cat','kr.pethub.site.DogmaruCoKr','getCatList','getCatContent',20,'8','Y','2019-07-06 00:00:00','2019-07-06 00:00:00','2019-07-12 21:17:14');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (3,2,'dog','강아지 분양','http://www.theilovedog.com/dog/list.php?category=1','kr.pethub.site.TheilovedogCom','getDogList','getDogContent',18,'8','Y','2019-07-06 00:00:00','2019-07-06 00:00:00','2019-07-12 21:17:15');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (4,3,'dog','강아지 분양','http://www.zooseyo.com/sale/sale_list.php?animal=dog','kr.pethub.site.ZooseyoCom','getDogList','getDogContent',25,'1','Y','2019-07-06 00:00:00','2019-07-06 00:00:00','2019-07-12 21:17:23');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (5,3,'cat','고양이 분양','http://www.zooseyo.com/sale/sale_list.php?animal=cat','kr.pethub.site.ZooseyoCom','getCatList','getCatContent',27,'1','Y','2019-07-06 00:00:00','2019-07-06 00:00:00','2019-07-12 21:17:30');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (6,4,'dog','강이지 안심 분양','http://www.dog-zzang.co.kr/dog_sale/safe_list.php','kr.pethub.site.DogZzangCoKr','getDogList1','getDogContent1',50,'1','Y','2019-07-09 13:43:36','2019-07-09 13:43:36','2019-07-13 11:58:49');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (7,5,'dog','강이지 분양','http://www.xn--oy2b11v46j.com/shop/shop/listtype.php?type=4','kr.pethub.site.WooripetCom','getDogList','getDogContent',15,'6','Y','2019-07-10 00:00:00','2019-07-10 00:00:00','2019-07-13 11:33:15');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (8,6,'dog','강이지 분양','http://chdog.co.kr/goods/goods_list.php?cateCd=001&pageNum=60','kr.pethub.site.ChdogCoKr','getDogList',null,60,'8','Y','2019-07-11 00:00:00','2019-07-11 00:00:00','2019-07-12 21:17:34');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (9,7,'dog','강이지 분양','http://www.animal.or.kr/bbs/board.php?bo_table=commu_08','kr.pethub.site.AnimalOrKr','getDogList','getDogContent',25,'8','Y','2019-07-11 00:00:00','2019-07-11 00:00:00','2019-07-12 21:17:40');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (10,8,'dog','강아지 분양','http://www.dogsijang.co.kr/board_dog/list.php?tb=board_sale','kr.pethub.site.DogsijangCoKr','getDogList','getDogContent',121,'1','Y','2019-07-12 00:00:00','2019-07-12 00:00:00','2019-07-12 21:18:10');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (11,5,'dog','강이지 할인','http://www.xn--oy2b11v46j.com/shop/shop/listtype.php?type=5','kr.pethub.site.WooripetCom','getDogList','getDogContent',15,'8','Y','2019-07-13 00:00:00','2019-07-13 00:00:00','2019-07-13 11:33:15');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (12,5,'dog','강이지 프리미엄 분양','http://www.xn--oy2b11v46j.com/shop/shop/listtype.php?type=3','kr.pethub.site.WooripetCom','getDogList','getDogContent',15,'8','Y','2019-07-13 00:00:00','2019-07-13 00:00:00','2019-07-13 11:33:18');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (13,4,'dog','강이지 무료 분양','http://www.dog-zzang.co.kr/dog_sale/free_sale_list.php','kr.pethub.site.DogZzangCoKr','getDogList2','getDogContent2',52,'1','Y','2019-07-13 00:00:00','2019-07-13 00:00:00','2019-07-13 11:59:07');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (14,9,'dog','강아지 분양','http://www.puppycafe.co.kr/gnu/index.php','kr.pethub.site.PuppycafeCoKr','getDogList','getDogContent',70,'1','Y','2019-07-13 00:00:00','2019-07-13 00:00:00','2019-07-13 14:32:18');
insert into site_link(link_srl,site_srl,link_cd,link_nm,link_url,link_cls,link_mtd_lst,link_mtd_cts,link_cnt,batch_itv,use_yn,reg_dt,upt_dt,exc_dt) values (15,10,'dog','강아지 분양','http://www.kakaodog.co.kr/gnu/index.php','kr.pethub.site.KakaodogCoKr','getDogList','getDogContent',70,'1','Y','2019-07-13 00:00:00','2019-07-13 00:00:00','2019-07-13 14:31:52');


