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

PV = "0.8.14"
SRCREV = "f864554cd06e225a124a5c45928e2d64170e9fef"

S = "${UNPACKDIR}/git/bindings/php"

inherit parallel-php-module
