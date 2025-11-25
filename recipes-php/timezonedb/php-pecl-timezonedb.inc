DESCRIPTION = "Timezone Database to be used with PHP's date and time functions"
HOMEPAGE = "http://pecl.php.net/package/timezonedb"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=423954b31818fc34cb3064236e2e44ce"

PV = "2025.2.2"
#PR = "r1"
SRC_URI[sha256sum] = "315b3f9672d88bdf8209056cb11bce65889c65ec242cffd86beeb81f2c2a3fc1"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-timezonedb:"

SRC_URI = " \
	http://pecl.php.net/get/timezonedb-${PV}.tgz \
	file://fix-poisoned-directory.patch \
	file://40-timezonedb.ini \
"

S = "${UNPACKDIR}/timezonedb-${PV}"
