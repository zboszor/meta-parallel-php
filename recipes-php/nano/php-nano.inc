DESCRIPTION = "PHP POSIX realtime module"
HOMEPAGE = "https://github.com/treeleaf/php-nano/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f87c7f34a7baa3b636ae13f69a27588"
DEPENDS = "nanomsg"

PV = "0.1"
#PR = "r1"
SRCREV = "195ab371121ddeb5b8f2e3b38536d0c08b3746de"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-nano:"

SRC_URI = " \
	git://github.com/treeleaf/php-nano.git;protocol=https;branch=master \
	file://20-nano.ini \
"

S = "${WORKDIR}/git"
