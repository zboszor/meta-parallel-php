DESCRIPTION = "Timezone Database to be used with PHP's date and time functions"
HOMEPAGE = "http://pecl.php.net/package/timezonedb"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=423954b31818fc34cb3064236e2e44ce"

PV = "2026.1"
SRC_URI[sha256sum] = "ca53eeed51c21ff8ade4ed52cc4095a13eec4037682a3c3fb3947f61108f5091"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-timezonedb:"

SRC_URI = " \
	http://pecl.php.net/get/timezonedb-${PV}.tgz \
	file://fix-poisoned-directory.patch \
	file://40-timezonedb.ini \
"

S = "${WORKDIR}/timezonedb-${PV}"
