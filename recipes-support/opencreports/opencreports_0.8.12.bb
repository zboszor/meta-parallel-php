DESCRIPTION = "A report generator engine written in C"
HOMEPAGE = "https://github.com/zboszor/OpenCReports"
LICENSE = "LGPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=3000208d539ec061b899bce1d9ce9404"

DEPENDS = " \
	flex-native bison-native \
	libcsv libxml2 mpfr utf8proc yajl \
	libpaper cairo gdk-pixbuf librsvg pango \
"

SRC_URI = "git://github.com/zboszor/OpenCReports;protocol=https;branch=main"
SRCREV = "9ac8725e1cb89fca63047fde9ea8a2b1742e359c"

S = "${WORKDIR}/git"

inherit autotools pkgconfig python3-dir

PACKAGECONFIG ??= "mysql pgsql odbc pandas"

PACKAGECONFIG[mysql] = ",,mariadb"
PACKAGECONFIG[pgsql] = ",,postgresql"
PACKAGECONFIG[odbc] = ",,unixodbc,psqlodbc"
PACKAGECONFIG[pandas] = ",,python3 python3-numpy python3-native python3-numpy-native,python3 python3-pandas python3-xlrd python3-openpyxl python3-odfpy"

EXTRA_OECONF = "--disable-tests --disable-documentation"

CFLAGS += " \
	-I${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/numpy/core/include \
	-I${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/numpy/_core/include \
	-Wno-error=unused-function \
"
