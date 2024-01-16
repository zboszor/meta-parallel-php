DESCRIPTION = "Provides functions for function traces and profiling"
HOMEPAGE = "http://pecl.php.net/package/xdebug"
SECTION = "console/network"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d94a330d34ee6edc2638450736f119"

#PR = "r1"

PHPVERSION = "71"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-xdebug:"

SRC_URI = " \
			http://pecl.php.net/get/xdebug-${PV}.tgz \
			file://02-xdebug.ini \
		"

SRC_URI[sha256sum] = "f555b6cc58d96c9965af942d22e0f1818b7a477a410c76b1ab0eebe85a762f8a"

S = "${WORKDIR}/xdebug-${PV}"
