DESCRIPTION = "The php-pecl-inotify module will add support for \
the kernel inotify service to PHP."
HOMEPAGE = "http://pecl.php.net/package/inotify"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0e964e9273c606c46afbd311b5ad4d77"

#PR = "r1"

PHPVERSION = "81"

inherit parallel-php-module

FILESPATH = "${FILE_DIRNAME}/php-pecl-inotify"

SRC_URI = " \
			http://pecl.php.net/get/inotify-${PV}.tgz \
			file://php-pecl-inotify-support-cross-compile.patch \
			file://40-inotify.ini \
		"

SRC_URI[sha256sum] = "c71b78644c0115579cc794f58e78441441751d78040b9d9d1a223d3e9c2d723d"

S = "${WORKDIR}/inotify-${PV}"
