DESCRIPTION = "Provides functions for function traces and profiling"
HOMEPAGE = "http://pecl.php.net/package/xdebug"
SECTION = "console/network"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=afd6ce4aa04fdc346e5b3c6e634bd75c"

PV = "3.4.7"
SRC_URI[sha256sum] = "7592923501e3fdb9741ad35b90e08fc6b3c5dd31eadc4c83b7b3c9dda0627b24"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-xdebug:"

SRC_URI = " \
	http://pecl.php.net/get/xdebug-${PV}.tgz \
	file://02-xdebug.ini \
"

S = "${UNPACKDIR}/xdebug-${PV}"
