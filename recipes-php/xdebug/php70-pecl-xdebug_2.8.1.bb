DESCRIPTION = "Provides functions for function traces and profiling"
HOMEPAGE = "http://pecl.php.net/package/xdebug"
SECTION = "console/network"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d94a330d34ee6edc2638450736f119"

#PR = "r1"

PHPVERSION = "70"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-xdebug:"

SRC_URI = " \
			http://pecl.php.net/get/xdebug-${PV}.tgz \
			file://02-xdebug.ini \
		"

SRC_URI[md5sum] = "4170605efa503e1f116db0ab7b020848"
SRC_URI[sha256sum] = "838be3974e2555bbbd796eb57c34840659815f23079417b5042e8b534fe61520"

S = "${WORKDIR}/xdebug-${PV}"
