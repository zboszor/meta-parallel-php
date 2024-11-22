DESCRIPTION = "Bindings for the libmcrypt library"
HOMEPAGE = "https://pecl.php.net/package/mcrypt"
SECTION = "console/network"
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=727d8e538931300e934e754c47398710"
DEPENDS += "libmcrypt"

#PR = "r1"

PHPVERSION = "84"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-mcrypt:"

SRC_URI = " \
	http://pecl.php.net/get/mcrypt-${PV}.tgz \
	file://20-mcrypt.ini \
"

SRC_URI[sha256sum] = "12ea2fbbf2e2efbe790a12121f77bf096c8b84cef81d0216bec00d56e5badef4"

S = "${UNPACKDIR}/mcrypt-${PV}"

EXTRA_OECONF = "--with-mcrypt=${STAGING_LIBDIR}/.."
