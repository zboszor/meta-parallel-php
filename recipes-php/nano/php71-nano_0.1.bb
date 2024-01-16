DESCRIPTION = "PHP POSIX realtime module"
HOMEPAGE = "https://github.com/treeleaf/php-nano/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f87c7f34a7baa3b636ae13f69a27588"
DEPENDS = "nanomsg"

#PR = "r1"

PHPVERSION = "71"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-nano:"

SRCBRANCH = "master"
SRCREV = "195ab371121ddeb5b8f2e3b38536d0c08b3746de"

SRC_URI = " \
			git://github.com/treeleaf/php-nano.git;protocol=https;branch=${SRCBRANCH} \
			file://20-nano.ini \
		"

S = "${WORKDIR}/git"
