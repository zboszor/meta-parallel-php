DESCRIPTION = "Provides bindings to the functions of libssh2 which implements the SSH2 protocol."
HOMEPAGE = "http://pecl.php.net/package/ssh2"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab5215b391feaa5fb1ecc15a4caa2b47"
DEPENDS = "libssh2"

#PR = "r1"

PHPVERSION = "83"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-ssh2:"

SRC_URI = " \
			http://pecl.php.net/get/ssh2-${PV}.tgz \
			file://ssh2-detect-libssh2.patch \
			file://40-ssh2.ini \
		"

SRC_URI[sha256sum] = "7bca5b23f731db9d8ed0aea5db9bb15da8ff133b0fbba96102b82e95da4d8764"

S = "${UNPACKDIR}/ssh2-${PV}"
