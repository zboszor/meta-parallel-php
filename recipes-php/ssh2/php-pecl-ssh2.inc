DESCRIPTION = "Provides bindings to the functions of libssh2 which implements the SSH2 protocol."
HOMEPAGE = "http://pecl.php.net/package/ssh2"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab5215b391feaa5fb1ecc15a4caa2b47"
DEPENDS = "libssh2"

PV ?= "1.5.0"
SRC_URI[sha256sum] ?= "a943427fae39a5503c813179018ae6c500323c8c9fb434e1a9a665fb32a4d7b4"

#PR = "r1"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-ssh2:"

SRC_URI = " \
	http://pecl.php.net/get/ssh2-${PV}.tgz \
	file://ssh2-detect-libssh2.patch \
	file://40-ssh2.ini \
"

S = "${UNPACKDIR}/ssh2-${PV}"
