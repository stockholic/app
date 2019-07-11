-- clean install -DskipTests package -P local

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





insert into `site_info`(`site_srl`,`site_nm`,`site_url`,`site_etc`,`reg_dt`,`upt_dt`) values (1,'도그마루','dogmaru.co.kr','강아지, 고양이 분양','2019-07-05 00:00:00','2019-07-05 00:00:00');
insert into `site_info`(`site_srl`,`site_nm`,`site_url`,`site_etc`,`reg_dt`,`upt_dt`) values (2,'I love dog','www.theilovedog.com','강이지, 고양이 분양','2019-07-06 00:00:00','2019-07-06 00:00:00');
insert into `site_info`(`site_srl`,`site_nm`,`site_url`,`site_etc`,`reg_dt`,`upt_dt`) values (3,'주세요닷컴','www.zooseyo.com','강아지, 고양이 분양','2019-07-06 00:00:00','2019-07-06 00:00:00');
insert into `site_info`(`site_srl`,`site_nm`,`site_url`,`site_etc`,`reg_dt`,`upt_dt`) values (4,'도그짱','www.dog-zzang.co.kr','강아지, 고양이 분양','2019-07-09 13:36:27','2019-07-09 13:36:27');
insert into `site_info`(`site_srl`,`site_nm`,`site_url`,`site_etc`,`reg_dt`,`upt_dt`) values (5,'우리펫','www.우리펫.com','강아지, 고양이 분양','2019-07-10 00:00:00','2019-07-10 00:00:00');
insert into `site_info`(`site_srl`,`site_nm`,`site_url`,`site_etc`,`reg_dt`,`upt_dt`) values (6,'착한애견분양','chdog.co.kr','강아지 분양','2019-07-11 00:00:00','2019-07-11 00:00:00');
insert into `site_info`(`site_srl`,`site_nm`,`site_url`,`site_etc`,`reg_dt`,`upt_dt`) values (7,'유기견보호센터','www.animal.or.kr','강아지 분양','2019-07-11 00:00:00','2019-07-11 00:00:00');



insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (1,1,'dog','강아지 분양','https://dogmaru.co.kr/sdog','kr.pethub.site.DogmaruCoKr','getDogList','getDogContent',20,'8','Y','2019-07-05 00:00:00','2019-07-06 13:20:07','2019-07-09 15:04:40');
insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (2,1,'cat','고양이 분양','https://dogmaru.co.kr/cat','kr.pethub.site.DogmaruCoKr','getCatList','getCatContent',20,'8','Y','2019-07-06 00:00:00','2019-07-06 00:00:00','2019-07-09 15:05:01');
insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (3,2,'dog','강아지 분양','http://www.theilovedog.com/dog/list.php?category=1','kr.pethub.site.TheilovedogCom','getDogList','getDogContent',18,'8','Y','2019-07-06 00:00:00','2019-07-06 00:00:00','2019-07-09 15:05:04');
insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (4,3,'dog','강아지 분양','http://www.zooseyo.com/sale/sale_list.php?animal=dog','kr.pethub.site.ZooseyoCom','getDogList','getDogContent',29,'1','Y','2019-07-06 00:00:00','2019-07-06 00:00:00','2019-07-09 15:05:22');
insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (5,3,'cat','고양이 분양','http://www.zooseyo.com/sale/sale_list.php?animal=cat','kr.pethub.site.ZooseyoCom','getCatList','getCatContent',29,'1','Y','2019-07-06 00:00:00','2019-07-06 00:00:00','2019-07-09 15:05:39');
insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (6,4,'dog','강이지 분양','http://www.dog-zzang.co.kr/dog_sale/safe_list.php','kr.pethub.site.DogZzangCoKr','getDogList','getDogContent',50,'1','Y','2019-07-09 13:43:36','2019-07-09 13:43:36','2019-07-09 15:06:01');
insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (7,5,'dog','강이지 분양','http://www.xn--oy2b11v46j.com/shop/shop/listtype.php?type=4','kr.pethub.site.WooripetCom','getDogList','getDogContent',15,'6','Y','2019-07-10 00:00:00','2019-07-10 00:00:00','2019-07-11 13:41:11');
insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (8,6,'dog','강이지 분양','http://chdog.co.kr/goods/goods_list.php?cateCd=001&pageNum=60','kr.pethub.site.ChdogCoKr','getDogList',null,60,'8','Y','2019-07-11 00:00:00','2019-07-11 00:00:00','2019-07-11 13:51:23');
insert into `site_link`(`link_srl`,`site_srl`,`link_cd`,`link_nm`,`link_url`,`link_cls`,`link_mtd_lst`,`link_mtd_cts`,`link_cnt`,`batch_itv`,`use_yn`,`reg_dt`,`upt_dt`,`exc_dt`) values (9,7,'dog','강이지 분양','http://www.animal.or.kr/bbs/board.php?bo_table=commu_08','kr.pethub.site.AnimalOrKr','getDogList','getDogContent',25,'8','Y','2019-07-11 00:00:00','2019-07-11 00:00:00','2019-07-11 14:54:46');

