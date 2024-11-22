DESCRIPTION = "An extension to operate with the IMAP protocol, as well as the NNTP, POP3, and local mailbox access methods."
HOMEPAGE = "http://pecl.php.net/package/imap"
SECTION = "console/network"
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=91e78b2fb2a617bff81099e199b7ca66"
DEPENDS = "uw-imap"

#PR = "r1"

PHPVERSION = "84"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-imap:"

SRC_URI = " \
	https://pecl.php.net/get/imap-${PV}.tgz \
	file://40-imap.ini \
"

SRC_URI[sha256sum] = "0c2c0b1f94f299004be996b85a424e3d11ff65ac0a3c980db3213289a4a3faaf"

S = "${UNPACKDIR}/imap-${PV}"

EXTRA_OECONF += "--with-imap=shared,${STAGING_LIBDIR}/.. --with-imap-ssl"
