DESCRIPTION = "PHP Screw is a PHP script encryption tool."
HOMEPAGE = "http://sourceforge.net/projects/php-screw/"
SECTION = "console/network"   
LICENSE = "BSD-3-Clause"
BBCLASSEXTEND = "native"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c41e344bb758f5bfaae2302677f013e9"
DEPENDS += "zlib"

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-screw:"

PV = "2.0"
SRCREV = "d40f813e16033d5ce6d3762fab5db94e52cc9295"
#PR = "r1"

SRC_URI = "git://github.com/zboszor/php-screw.git;protocol=https;nobranch=1"

S = "${WORKDIR}/git"
