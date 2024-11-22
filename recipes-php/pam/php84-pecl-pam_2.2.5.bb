DESCRIPTION = "This extension provides PAM (Pluggable Authentication Modules) integration."
HOMEPAGE = "http://pecl.php.net/package/PAM"
SECTION = "console/network"
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7e571b888d585b31f9ef5edcc647fa30"
DEPENDS = "libpam"

#PR = "r1"

PHPVERSION = "84"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-pam:"

SRC_URI = " \
			http://pecl.php.net/get/pam-${PV}.tgz \
			file://40-pam.ini \
		"

SRC_URI[sha256sum] = "aa4e51a3f975b1462dd2cda82ce2288bd87efb5e01cd439ae086758b85e19b82"

S = "${UNPACKDIR}/pam-${PV}"

EXTRA_OECONF += "--with-pam=${STAGING_LIBDIR}/.."
