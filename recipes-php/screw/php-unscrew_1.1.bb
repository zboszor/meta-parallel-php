DESCRIPTION = "PHP UnScrew is a too to decrypt PHP scripts encrypted with PHP Screw."
SECTION = "console/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://unscrew.c;md5=30987cce86f12f5c2d45740690c6d0ae"
DEPENDS += "zlib libphpscrew"

#PR = "r1"

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-screw:"

SRC_URI = " \
			file://unscrew.c \
			file://my_screw.h \
		"

S = "${WORKDIR}"

do_compile () {
	${CC} ${CFLAGS} ${LDFLAGS} -o unscrew unscrew.c -lphpscrew
}

do_install () {
	install -d -m755 ${D}${bindir}
	install -m755 unscrew ${D}${bindir}
}
