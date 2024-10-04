DESCRIPTION = "Terminal screen handling and optimization package for PHP"
HOMEPAGE = "http://pecl.php.net/package/ncurses"
SECTION = "console/network"
LICENSE = "PHP-3.01"
LIC_FILES_CHKSUM = "file://ncurses.c;beginline=2;endline=16;md5=ec10d477b64fbda817c3e433ebec2fb8"
DEPENDS += "ncurses"

PV = "1.0.4"
#PR = "r1"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-ncurses:"

SRCREV = "9d41187a23fbd239262e4682fe1718e117a6bda1"

SRC_URI = " \
			git://github.com/OOPS-ORG-PHP/mod_ncurses.git;protocol=https;nobranch=1 \
			file://40-ncurses.ini \
		"

S = "${UNPACKDIR}/git"

EXTRA_OECONF = "--with-ncurses=${STAGING_LIBDIR}/.. --enable-ncursesw"
