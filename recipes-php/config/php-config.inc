PV = "1.0"

S = "${WORKDIR}"

inherit allarch

PACKAGES = "${PN}"

LICENSE = "PHP-3.01"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3363e286b5882ec667a6ebd86e0d9d91"

SRC_URI = " \
	file://LICENSE \
	file://php-fpm.tmpfiles \
	file://98-shmmax.conf \
	file://10-opcache.ini.${PHPVERSION} \
	file://php-fpm.conf.${PHPVERSION} \
	file://php.ini.${PHPVERSION} \
	file://www.conf.${PHPVERSION} \
"

do_install () {
	install -d -m0755 ${D}${sysconfdir}/tmpfiles.d
	install -m0644 ${S}/php-fpm.tmpfiles ${D}${sysconfdir}/tmpfiles.d/php-fpm.conf

	install -d -m0755 ${D}${sysconfdir}/php${PHPVERSION}
	install -m644 ${S}/php.ini.${PHPVERSION} ${D}${sysconfdir}/php${PHPVERSION}/php.ini
	install -m644 ${S}/php-fpm.conf.${PHPVERSION} ${D}${sysconfdir}/php${PHPVERSION}/php-fpm.conf

	install -d -m0755 ${D}${sysconfdir}/php${PHPVERSION}/php-fpm.d
	install -m0644 ${S}/www.conf.${PHPVERSION} ${D}${sysconfdir}/php${PHPVERSION}/php-fpm.d/www.conf

	install -d -m0755 ${D}${sysconfdir}/php${PHPVERSION}/php.d
	install -m0644 ${S}/10-opcache.ini.${PHPVERSION} ${D}${sysconfdir}/php${PHPVERSION}/php.d/10-opcache.ini
}
