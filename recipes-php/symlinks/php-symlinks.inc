HOMEPAGE = "https://github.com/zboszor/meta-parallel-php"
SECTION = "console/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c4870178db33f108e37cf55f70465f88"

PHPVERSION ??= "74"

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-symlinks:"

SRC_URI = "file://LICENSE"

S = "${UNPACKDIR}"

inherit allarch

BBCLASSEXTEND = "native"

do_install:class-target () {
	install -d -m0755 ${D}${localstatedir}/lib/php-fpm
}

pkg_postinst:${PN} () {
	for i in pear peardev pecl phar phar.phar php php-cgi php-config phpdbg phpize ; do
		update-alternatives --install "${bindir}/${i}" "${i}" "${i}${PHPVERSION}" "${PHPVERSION}"
	done
	update-alternatives --install "${sbindir}/php-fpm" php-fpm "php-fpm${PHPVERSION}" "${PHPVERSION}"
	update-alternatives --install "${localstatedir}/lib/php-fpm/php-fastcgi.socket" php-fastcgi.socket "../../../run/php-fpm/php${PHPVERSION}-fastcgi.socket" "${PHPVERSION}"
}

pkg_prerm:${PN} () {
	for i in pear peardev pecl phar phar.phar php php-cgi php-config phpdbg phpize ; do
		update-alternatives --remove $i "${i}${PHPVERSION}"
	done
	update-alternatives --remove php-fpm "php-fpm${PHPVERSION}"
	update-alternatives --remove php-fastcgi.socket "../../../run/php-fpm/php${PHPVERSION}-fastcgi.socket"
}

do_install:class-native () {
	install -d -m0755 ${D}${bindir}
	install -d -m0755 ${D}${sbindir}

	for i in pear peardev pecl phar phar.phar php php-cgi php-config phpdbg phpize ; do
		ln -s "${i}${PHPVERSION}" "${D}${bindir}/${i}"
	done
	ln -s "php-fpm${PHPVERSION}" "${D}${sbindir}/php-fpm"

	install -d -m0755 ${D}${localstatedir}/lib/php-fpm
}

PACKAGES = "${PN}"

FILES:${PN} += " \
			${sysconfdir}/lighttpd.d/php${PHPVERSION}.conf \
			${localstatedir}/lib/php-fpm \
		"
CONFFILES:${PN} += " \
			${sysconfdir}/lighttpd.d/php${PHPVERSION}.conf \
		"

DEPENDS += " \
			php${PHPVERSION} \
		"

RDEPENDS:${PN} = " \
			php${PHPVERSION} \
			php${PHPVERSION}-cli \
			php${PHPVERSION}-cgi \
			php${PHPVERSION}-fpm \
		"

RPROVIDES:${PN} = " \
			${bindir}/php \
		"

RDEPENDS:${PN}-dev = " \
			php${PHPVERSION}-dev \
		"

RDEPENDS:${PN}:class-native = "php${PHPVERSION}-native"
