DESCRIPTION = "PHP wrapper module over cracklib."
HOMEPAGE = "https://pecl.php.net/package/crack"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
BBCLASSEXTEND = "native"
LIC_FILES_CHKSUM = "file://crack.c;beginline=1;endline=18;md5=fd24615e1d33d3e88908fea46f121b8f"
DEPENDS += "cracklib"

#PR = "r1"

inherit parallel-php-module

PHPVERSION ??= "70"

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-crack:"

SRC_URI = " \
	git://github.com/zboszor/php-pecl-crack.git;protocol=https;nobranch=1 \
	file://20-crack.ini \
"

S = "${UNPACKDIR}/git"

EXTRA_OECONF = "--with-crack=${STAGING_LIBDIR}/.."
