require php-screw.inc

SRCREV = "0929fface981feeae1a8fce599723308e4d04165"

do_compile () {
	make -C tools
}

do_install () {
	install -d -m755 ${D}${bindir}
	install -m755 tools/screw ${D}${bindir}
}

FILES:${PN} += "${bindir}/screw"
