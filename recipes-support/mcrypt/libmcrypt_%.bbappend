FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
	file://libmcrypt-2.5.8-nolibltdl.patch \
	file://libmcrypt-2.5.8-uninitialized.patch \
	file://libmcrypt-configure-c99.patch \
	file://libmcrypt-configure-c99-2.patch \
"

BBCLASSEXTEND += "native"
