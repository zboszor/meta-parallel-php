DESCRIPTION = "Timezone Database to be used with PHP's date and time functions"
HOMEPAGE = "http://pecl.php.net/package/timezonedb"
SECTION = "console/network"   
LICENSE = "PHP-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=423954b31818fc34cb3064236e2e44ce"

#PR = "r1"

PHPVERSION = "73"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-timezonedb:"

SRC_URI = " \
			http://pecl.php.net/get/timezonedb-${PV}.tgz \
			file://fix-poisoned-directory.patch \
			file://40-timezonedb.ini \
		"

SRC_URI[sha256sum] = "0ca91cabdd952a54ff9413337c374d4b157277f25d492c14a403ed526153e3bb"

S = "${WORKDIR}/timezonedb-${PV}"
