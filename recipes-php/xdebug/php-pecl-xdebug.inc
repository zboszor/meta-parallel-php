DESCRIPTION = "Provides functions for function traces and profiling"
HOMEPAGE = "http://pecl.php.net/package/xdebug"
SECTION = "console/network"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=afd6ce4aa04fdc346e5b3c6e634bd75c"

PV = "3.5.3"
SRC_URI[sha256sum] = "f073de91bea046106abf4d6071c963ea71e58571df6ce58948ceca89d121cb2d"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-xdebug:"

SRC_URI = " \
	https://pecl.php.net/get/xdebug-${PV}.tgz \
	file://02-xdebug.ini \
"

S = "${UNPACKDIR}/xdebug-${PV}"
