DESCRIPTION = "libphpscrew - php-screw core functions in a library"
HOMEPAGE = "https://github.com/zboszor/libphpscrew"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=cc437efa0395fbb3b9d40e3ccf342ea3"
DEPENDS = "zlib"
BBCLASSEXTEND = "native"

PR = "r1"

SRCREV = "df66d1c8cdea548880f61c60057cf7585aeda73b"

SRC_URI = "git://github.com/zboszor/libphpscrew.git;protocol=https;nobranch=1"

S = "${WORKDIR}/git"

inherit pkgconfig autotools-brokensep
