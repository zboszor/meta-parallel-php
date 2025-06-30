require php-screw.inc

SRCREV = "fb482a7c4aad09a8129c6b21382fd08a8887f1e6"

do_compile () {
	make -C tools
}

do_install () {
	install -d -m755 ${D}${bindir}
	install -m755 tools/screw tools/unscrew ${D}${bindir}
}

PACKAGES =+ "${PN}-unscrew"

FILES:${PN}-unscrew = "${bindir}/unscrew"
RPROVIDES:${PN}-unscrew = "php-unscrew"
