CREATE TABLE site_info (
  site_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  site_nm varchar(100) DEFAULT NULL COMMENT '사이트명',
  site_url varchar(100) DEFAULT NULL COMMENT '사이트 url',
  site_etc text COMMENT '기타',
  reg_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  upt_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (site_srl)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='사이트정보';


CREATE TABLE site_link (
  link_srl int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  site_srl int(10) unsigned DEFAULT NULL COMMENT 'site_info 참조번호',
  link_nm varchar(100) DEFAULT NULL COMMENT '링크명',
  link_url varchar(100) DEFAULT NULL COMMENT '링크 url',
  link_cls text COMMENT '실행클래스',
  link_mtd varchar(20) DEFAULT NULL COMMENT '실행메소드',
  link_cnt int(11) DEFAULT '0' COMMENT '링크수',
  use_yn char(1) DEFAULT 'Y' COMMENT '사용여부',
  reg_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  upt_dt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  exc_dt datetime  DEFAULT NULL COMMENT '실행일',
  PRIMARY KEY (link_srl),
  KEY site_link_idx (site_srl)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='사이트링크';



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
  KEY `site_link_log_idx` (`site_srl`,`link_srl`,`log_cd`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='사이트로그';




insert into site_info(site_srl,site_nm,site_url,site_etc,reg_dt,upt_dt) values (1,'도그마루','dogmaru.co.kr','강아지, 고양이 분양','2019-07-05 00:00:00','2019-07-05 00:00:00');

insert into site_link(link_srl,site_srl,link_nm,link_url,link_class,link_method,use_yn,reg_dt,upt_dt) values (1,1,'강아지 분양','https://dogmaru.co.kr/sdog','kr.pethub.site.DogmaruCoKr','getList','Y','2019-07-05 00:00:00','2019-07-05 00:00:00');



