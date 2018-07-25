#!/bin/bash

# yum -y install libxml2-devel
# yum -y install libjpeg-devel
# yum -y install libpng-devel
# yum -y install freetype-devel
# yum -y install openssl-devel
# yum -y install epel-release libmcrypt-devel
# yum -y install libcurl-devel



source=/usr/local/source
php=php-7.0.8
cifw=3.0.6
CI_version=3.0.6
CI_name=CodeIgniter
path=/usr/local/php

if [ ! -d $source ];then
	mkdir $source
fi

if [ ! -f $source/$php.tar.gz ];then
	wget http://kr1.php.net/get/$php.tar.gz/from/this/mirror -O $source/$php.tar.gz
fi

tar zxvf $source/$php.tar.gz -C $source

cd $source/$php && ./configure  \
--prefix=$path --with-config-file-path=/usr/local/php/etc \
--with-pdo-mysql=/usr/local/mysql --with-mysqli=/usr/local/mysql/bin/mysql_config --with-mysql-sock=/usr/local/mysql/mysql.sock \
--with-gd --with-jpeg-dir=/usr --with-freetype-dir=/usr --with-png-dir=/usr --with-xpm-dir=/usr \
--with-zlib --with-zlib-dir=/usr --with-gdbm --with-gettext --with-iconv --with-openssl \
--enable-gd-native-ttf --enable-exif --enable-sockets --enable-soap --enable-mbstring=all --enable-bcmath \
--with-libxml-dir=/usr/lib --enable-ftp --with-mcrypt --enable-opcache \
--enable-zip  --with-curl --enable-fpm 
	
cd $source/$php && make	
cd $source/$php && make install

cp -a $source/$php/php.ini-production $path/etc/php.ini
# vi  /usr/local/php/etc/php.ini
# date.timezon = Asia/Seoul

cp -a $source/$php/sapi/fpm/php-fpm.conf $path/etc/php-fpm.conf
chmod 755 $source/$php/sapi/fpm/init.d.php-fpm
cp -a $source/$php/sapi/fpm/init.d.php-fpm /etc/init.d/php-fpm



if [ ! -f $source/cifw.tar.gz ];then
wget --no-check-certificate https://github.com/bcit-ci/CodeIgniter/archive/$CI_version.tar.gz -O $source/$CI_name-$CI_version.tar.gz
fi

tar zxvf $source/$CI_name-$CI_version.tar.gz -C /usr/local/www
mv /usr/local/www/CodeIgniter-$CI_version /usr/local/www/php


rm -rf $source/$php
