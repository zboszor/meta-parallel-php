DESCRIPTION = "PHP POSIX realtime module"
HOMEPAGE = "https://github.com/treeleaf/php-nano/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f87c7f34a7baa3b636ae13f69a27588"
DEPENDS = "nanomsg"

PV = "0.1"
#PR = "r1"
SRCREV = "58b3529c5b84ced623bb668e2c8f68fc866aeb90"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-nano:"

SRC_URI = " \
	git://github.com/zboszor/php-nano.git;protocol=https;branch=master \
	file://20-nano.ini \
"
