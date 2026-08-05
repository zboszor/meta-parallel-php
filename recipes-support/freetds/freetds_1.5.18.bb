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

export LIBS = "-lgcrypt"

EXTRA_OECONF = " \
	--enable-sspi \
	--disable-odbc \
	--enable-msdblib \
	--enable-sybase-compat \
	--with-libiconv-prefix=${STAGING_LIBDIR}/.. \
	--with-tdsver=auto \
	--with-gnutls \
"

SRC_URI[sha256sum] = "6b2c8b93b9ee7c83855daf745de5878790032f14dbaee553d83a9d211b84dd4b"
