DESCRIPTION = "Provides bindings to the functions of libssh2 which implements the SSH2 protocol."
HOMEPAGE = "http://pecl.php.net/package/ssh2"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab5215b391feaa5fb1ecc15a4caa2b47"
DEPENDS = "libssh2"

#PR = "r1"

PHPVERSION = "82"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-ssh2:"

SRC_URI = " \
			http://pecl.php.net/get/ssh2-${PV}.tgz \
			file://ssh2-detect-libssh2.patch \
			file://40-ssh2.ini \
		"

SRC_URI[sha256sum] = "988b52e0315bb5ed725050cb02de89b541034b7be6b94623dcb2baa33f811d87"

S = "${WORKDIR}/ssh2-${PV}"
