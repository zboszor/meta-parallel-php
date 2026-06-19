FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
	file://uw-imap-newer-tls.patch \
	file://uw-imap-fix-incompatible-pointer-types.patch \
"

CFLAGS += "-Wno-error=incompatible-pointer-types"

BBCLASSEXTEND += "native"
