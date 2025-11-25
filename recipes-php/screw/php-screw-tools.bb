require php-screw.inc

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
