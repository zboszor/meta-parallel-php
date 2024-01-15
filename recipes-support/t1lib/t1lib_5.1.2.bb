SUMMARY = "A Type1 Font Rastering Library"
SECTION = "libs"
DEPENDS = "virtual/libx11 libxaw"

LICENSE = "LGPL-2.0-only & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ca43cbc842c2336e835926c2166c28b \
                    file://LGPL;md5=6e29c688d912da12b66b73e32b03d812 \
"

SRC_URI = " \
	${DEBIAN_MIRROR}/main/t/t1lib/t1lib_${PV}.orig.tar.gz \
	file://configure.patch \
	file://t1lib_5.1.2-3.diff.gz \
	file://t1lib-5.1.2-segf.patch \
	file://t1lib-5.1.2-afm-fix.patch \
	file://t1lib-5.1.2-type1-inv-rw-fix.patch \
	file://t1lib-5.1.2-aarch64.patch \
	file://t1lib-5.1.2-format-security.patch \
	file://t1lib-5.1.2-t1.patch \
	file://t1lib-c99.patch \
"

SRC_URI[md5sum] = "a5629b56b93134377718009df1435f3c"
SRC_URI[sha256sum] = "821328b5054f7890a0d0cd2f52825270705df3641dbd476d58d17e56ed957b59"

inherit autotools-brokensep features_check
# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES = "x11"

# Fix GNU_HASH problem
TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OECONF = "--with-x --without-athena"
EXTRA_OEMAKE = "without_doc"

FILES:${PN} += " ${datadir}/t1lib/t1lib.config"
FILES:${PN}-doc = "${datadir}/t1lib/doc/t1lib_doc.pdf"

PARALLEL_MAKE = ""
