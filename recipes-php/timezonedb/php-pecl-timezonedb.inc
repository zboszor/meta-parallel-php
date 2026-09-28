DESCRIPTION = "Timezone Database to be used with PHP's date and time functions"
HOMEPAGE = "http://pecl.php.net/package/timezonedb"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=423954b31818fc34cb3064236e2e44ce"

PV = "2026.4"
SRC_URI[sha256sum] = "7e7e737043c3f85b144c3daeac839b0131484db0a517aa2b7a14fd9c91c0cdcc"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-timezonedb:"

SRC_URI = " \
	https://pecl.php.net/get/timezonedb-${PV}.tgz \
	file://fix-poisoned-directory.patch \
	file://40-timezonedb.ini \
"

S = "${WORKDIR}/timezonedb-${PV}"
