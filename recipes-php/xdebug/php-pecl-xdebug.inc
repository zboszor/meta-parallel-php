DESCRIPTION = "Provides functions for function traces and profiling"
HOMEPAGE = "http://pecl.php.net/package/xdebug"
SECTION = "console/network"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=afd6ce4aa04fdc346e5b3c6e634bd75c"

PV = "3.5.1"
SRC_URI[sha256sum] = "0f26849a5edf3d9120edc100219854599d54f923a8a4d1cb4fe4403520e49678"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-xdebug:"

SRC_URI = " \
	http://pecl.php.net/get/xdebug-${PV}.tgz \
	file://02-xdebug.ini \
"

S = "${WORKDIR}/xdebug-${PV}"
