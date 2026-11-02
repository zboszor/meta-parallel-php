DESCRIPTION = "Timezone Database to be used with PHP's date and time functions"
HOMEPAGE = "http://pecl.php.net/package/timezonedb"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=423954b31818fc34cb3064236e2e44ce"

PV = "2026.6"
SRC_URI[sha256sum] = "17241d407602c04083b27d469b9c3362558bdac5e62b5940ac3dfdc430686d00"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-timezonedb:"

SRC_URI = " \
	https://pecl.php.net/get/timezonedb-${PV}.tgz \
	file://fix-poisoned-directory.patch \
	file://40-timezonedb.ini \
"

S = "${UNPACKDIR}/timezonedb-${PV}"
