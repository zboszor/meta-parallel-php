DESCRIPTION = "Free implementation of Microsoft/Sybase wire protocol for databases"
HOMEPAGE = "http://www.freetds.org"
SECTION = "console/network"
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING_LIB.txt;md5=5f30f0716dfdd0d91eb439ebec522ec2"
DEPENDS = "gnutls libgcrypt"

BBCLASSEXTEND = "native"

SRC_URI = "http://ftp.freetds.org/pub/freetds/stable/freetds-${PV}.tar.bz2"

#PR = "r1"

inherit autotools pkgconfig

export LIBS="-lgcrypt"

EXTRA_OECONF = " \
	--enable-sspi \
	--disable-odbc \
	--enable-msdblib \
	--enable-sybase-compat \
	--with-libiconv-prefix=${STAGING_LIBDIR}/.. \
	--with-tdsver=auto \
	--with-gnutls \
"

SRC_URI[sha256sum] = "a2cb8993a46417e0503e7b8488463d91b8c5d9a4601d6ee2484d28f56d3a7075"
