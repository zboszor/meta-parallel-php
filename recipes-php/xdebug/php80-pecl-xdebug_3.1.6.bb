DESCRIPTION = "Provides functions for function traces and profiling"
HOMEPAGE = "http://pecl.php.net/package/xdebug"
SECTION = "console/network"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d94a330d34ee6edc2638450736f119"

#PR = "r1"

PHPVERSION = "80"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-xdebug:"

SRC_URI = " \
			http://pecl.php.net/get/xdebug-${PV}.tgz \
			file://fix-prototype.patch \
			file://02-xdebug.ini \
		"

SRC_URI[sha256sum] = "554eca0b4d5b7b93cb2258fab0b0bd84cc8721e74322a2255c14e137cbcad5d2"

S = "${UNPACKDIR}/xdebug-${PV}"
