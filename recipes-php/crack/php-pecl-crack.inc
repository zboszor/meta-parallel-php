DESCRIPTION = "PHP wrapper module over cracklib."
HOMEPAGE = "https://pecl.php.net/package/crack"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
BBCLASSEXTEND = "native"
LIC_FILES_CHKSUM = "file://crack.c;beginline=1;endline=18;md5=fd24615e1d33d3e88908fea46f121b8f"
DEPENDS += "cracklib"

PV = "1.1"
SRCREV = "dd0699635f389fd8d4875ed8b5bc37b99ab2fe58"
#PR = "r1"

inherit parallel-php-module

PHPVERSION ??= "70"

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-crack:"

SRC_URI = " \
	git://github.com/zboszor/php-pecl-crack.git;protocol=https;nobranch=1 \
	file://20-crack.ini \
"

EXTRA_OECONF = "--with-crack=${STAGING_LIBDIR}/.."
