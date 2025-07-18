DESCRIPTION = "PHP Screw is a PHP script encryption tool."
HOMEPAGE = "http://sourceforge.net/projects/php-screw/"
SECTION = "console/network"   
LICENSE = "BSD-3-Clause"
BBCLASSEXTEND = "native"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c41e344bb758f5bfaae2302677f013e9"
DEPENDS += "zlib"

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-screw:"

SRC_URI = " \
	git://github.com/zboszor/php-screw.git;protocol=https;nobranch=1 \
	file://01-php_screw.ini \
"

S = "${UNPACKDIR}/git"
