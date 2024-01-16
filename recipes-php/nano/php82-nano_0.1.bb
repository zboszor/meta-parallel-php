DESCRIPTION = "PHP POSIX realtime module"
HOMEPAGE = "https://github.com/adrianguenter/php-posix-realtime/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f87c7f34a7baa3b636ae13f69a27588"
DEPENDS = "nanomsg"

#PR = "r1"

PHPVERSION = "82"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-nano:"

SRCBRANCH = "master"
SRCREV = "231dc43f57ecf7bf0edb8a6e99d77ef74a4719b1"

SRC_URI = " \
			git://github.com/treeleaf/php-nano.git;protocol=https;branch=${SRCBRANCH} \
			file://0001-Allow-building-with-PHP-8.0.patch \
			file://20-nano.ini \
		"

S = "${WORKDIR}/git"
