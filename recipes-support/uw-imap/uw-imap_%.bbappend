FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
	file://uw-imap-newer-tls.patch \
	file://uw-imap-fix-incompatible-pointer-types.patch \
	file://0001-Define-prototype-for-safe_flock.patch \
	file://0001-Fix-Wincompatible-function-pointer-types.patch \
	file://0001-Do-not-build-mtest.patch \
"

CFLAGS += "-Wno-error=incompatible-pointer-types"

BBCLASSEXTEND += "native"
