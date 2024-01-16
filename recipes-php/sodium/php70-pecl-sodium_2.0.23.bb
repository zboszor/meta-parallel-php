DESCRIPTION = "Wrapper for the Sodium cryptographic library"
HOMEPAGE = "http://pecl.php.net/package/libsodium"
SECTION = "console/network"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b3d3d5fa3a922980f441fe222945794"
DEPENDS = "libsodium"

#PR = "r1"

PHPVERSION = "70"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-sodium:"

SRC_URI = " \
	http://pecl.php.net/get/libsodium-${PV}.tgz \
	file://20-sodium.ini \
"

SRC_URI[sha256sum] = "f8c04533de8e4b48077f65de61b187e195c0919c41d4543e2263604fa0c50379"

S = "${WORKDIR}/libsodium-${PV}"
