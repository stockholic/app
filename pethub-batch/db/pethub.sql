CREATE TABLE `site_info` (
  `site_srl` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `site_nm` varchar(100) DEFAULT NULL COMMENT '사이트명',
  `site_url` varchar(100) DEFAULT NULL COMMENT '사이트 url',
  `site_etc` text COMMENT '기타',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `upt_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`site_srl`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='사이트정보';



CREATE TABLE `site_link` (
  `link_srl` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `site_srl` int(10) unsigned COMMENT 'site_info 참조번호',
  `link_nm` varchar(100) DEFAULT NULL COMMENT '링크명',
  `link_url` varchar(100) DEFAULT NULL COMMENT '링크 url',
  `link_class` text  COMMENT '실행클래스',
  `link_method` text  COMMENT '실행메소드',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `upt_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`link_srl`),
  KEY site_srl (site_srl)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사이트링크';
;

CREATE TABLE `site_link_data` (
  `data_srl` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `site_srl` int(10) unsigned COMMENT 'site_info 참조번호',
  `link_srl` int(10) unsigned COMMENT 'site_link 참조번호',
  `data_id` varchar(50) DEFAULT NULL COMMENT '아이디',
  `data_title` varchar(400) DEFAULT NULL COMMENT '제목',
  `data_link` varchar(200) DEFAULT NULL COMMENT '링크',
  `data_img` varchar(100) DEFAULT NULL COMMENT '이미지',
  `data_content` text COMMENT '내용',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `upt_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`data_srl`),
  KEY data_id (site_srl, link_srl, data_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사이트데이터';
;
