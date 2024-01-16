DESCRIPTION = "PHP Screw is a PHP script encryption tool."
HOMEPAGE = "http://sourceforge.net/projects/php-screw/"
SECTION = "console/network"   
LICENSE = "BSD-3-Clause"
BBCLASSEXTEND = "native"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3967a73344d01c72bdf5f02fb95ee421"
DEPENDS += "zlib"

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-screw:"

SRC_URI = " \
	git://github.com/zboszor/php-screw.git;protocol=https;nobranch=1 \
	file://01-php_screw.ini \
"

S = "${WORKDIR}/git"
