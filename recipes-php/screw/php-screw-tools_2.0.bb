require php-screw.inc

SRCREV = "d40f813e16033d5ce6d3762fab5db94e52cc9295"

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
