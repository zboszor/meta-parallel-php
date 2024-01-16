DESCRIPTION = "The php-pecl-inotify module will add support for \
the kernel inotify service to PHP."
HOMEPAGE = "http://pecl.php.net/package/inotify"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0e964e9273c606c46afbd311b5ad4d77"

#PR = "r1"

PHPVERSION = "71"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-inotify:"

SRC_URI = " \
			http://pecl.php.net/get/inotify-${PV}.tgz \
			file://php-pecl-inotify-support-cross-compile.patch \
			file://40-inotify.ini \
		"

SRC_URI[md5sum] = "f7a951b3c66d08f5e7889479f5fc7564"
SRC_URI[sha256sum] = "d044a89d26bbaa110f34f182b1c92ab5eb09768096bacf3d837d7e49ac045107"

S = "${WORKDIR}/inotify-${PV}"
