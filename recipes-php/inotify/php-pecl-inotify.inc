DESCRIPTION = "The php-pecl-inotify module will add support for \
the kernel inotify service to PHP."
HOMEPAGE = "http://pecl.php.net/package/inotify"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0e964e9273c606c46afbd311b5ad4d77"

PV = "3.0.1"
#PR = "r1"
SRC_URI[sha256sum] = "fbb7240abae502ea8f4b3c68264c6425fb46647402a8f93916482845394ad124"

PHPVERSION = "84"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-inotify:"

SRC_URI = " \
	http://pecl.php.net/get/inotify-${PV}.tgz \
	file://php-pecl-inotify-support-cross-compile.patch \
	file://40-inotify.ini \
"

S = "${UNPACKDIR}/inotify-${PV}"
