DESCRIPTION = "Bindings for the libmcrypt library"
HOMEPAGE = "https://pecl.php.net/package/mcrypt"
SECTION = "console/network"
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=727d8e538931300e934e754c47398710"
DEPENDS += "libmcrypt"

PV = "1.0.9"
#PR = "r1"
SRC_URI[sha256sum] = "2a9ef0817d3bf677f6d7baf8e325629a2758974735d8abad6566384788d424a5"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-mcrypt:"

SRC_URI = " \
	http://pecl.php.net/get/mcrypt-${PV}.tgz \
	file://20-mcrypt.ini \
"

S = "${UNPACKDIR}/mcrypt-${PV}"

EXTRA_OECONF = "--with-mcrypt=${STAGING_LIBDIR}/.."
