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

PV = "0.8.17"
SRCREV = "2d0bcfa9631039f6867a572d7f5d7a7a71621407"

S = "${WORKDIR}/git/bindings/php"

inherit parallel-php-module
