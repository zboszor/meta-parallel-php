FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
	file://0001-Define-prototype-for-safe_flock.patch \
	file://0001-Do-not-build-mtest.patch \
	file://0002-tmail-Include-ctype.h-for-isdigit.patch \
	file://0001-Fix-Wincompatible-function-pointer-types.patch \
	file://uw-imap-newer-tls.patch \
	file://uw-imap-fix-incompatible-pointer-types.patch \
"

CFLAGS += "-Wno-error=incompatible-pointer-types"

BBCLASSEXTEND += "native"
