DESCRIPTION = "PHP module for OpenCReports"
HOMEPAGE = "https://github.com/zboszor/OpenCReports"
LICENSE = "PHP-3.01"
LIC_FILES_CHKSUM = "file://../../COPYING.PHP;md5=7e571b888d585b31f9ef5edcc647fa30"

DEPENDS = "opencreports"

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-opencreports:"

SRC_URI = " \
	git://github.com/zboszor/OpenCReports;protocol=https;branch=main \
	file://40-opencreports.ini \
"

PV = "0.8.12"
SRCREV = "d117369c71ca3c2a3f00d1702c651b2aa196d269"

S = "${WORKDIR}/git/bindings/php"

inherit parallel-php-module
