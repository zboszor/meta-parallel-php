DESCRIPTION = "Provides functions for function traces and profiling"
HOMEPAGE = "http://pecl.php.net/package/xdebug"
SECTION = "console/network"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d94a330d34ee6edc2638450736f119"

#PR = "r1"

PHPVERSION = "82"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-xdebug:"

SRC_URI = " \
			http://pecl.php.net/get/xdebug-${PV}.tgz \
			file://fix-prototype.patch \
			file://02-xdebug.ini \
		"

SRC_URI[sha256sum] = "f48777371f90cbb315ea4ea082a1ede6765bcfb35d7d6356ab8f71fd6dfcc157"

S = "${UNPACKDIR}/xdebug-${PV}"
