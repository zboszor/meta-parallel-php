require php.inc

DEPENDS:append:class-target = " libpcre2 libzip libsodium "
DEPENDS:append:class-native = " libpcre2-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=fb07bfc51f6d5e0c30b65d9701233b2e"

PR = "r3"

EXTRA_OECONF:append:class-target = " \
	--enable-fpm=shared \
	--enable-intl=shared \
	--with-pcre-regex=${STAGING_LIBDIR}/.. \
	--with-libxml-dir=${STAGING_BINDIR_CROSS} \
	--with-png-dir=${STAGING_LIBDIR}/.. \
	--with-jpeg-dir=${STAGING_LIBDIR}/.. \
	--with-xpm-dir=${STAGING_LIBDIR}/.. \
	--with-gd${DEPSETTING3} \
	--with-xmlrpc${DEPSETTING3} \
	--enable-wddx${DEPSETTING3} \
	--enable-zip \
	--with-sodium=shared,${STAGING_LIBDIR}/.. \
"

EXTRA_OECONF:append:class-native = " \
	--with-pcre-regex=${STAGING_LIBDIR}/.. \
	--with-libxml-dir=${STAGING_BINDIR_NATIVE} \
	--with-xmlrpc${DEPSETTING3} \
"

SRC_URI += " \
	file://php-7.3.9-pcre2-use-pkgconfig.patch \
	file://php-7.2.0-fix-phpize-for-parallel-installation.patch \
	file://php-7.3.3-fix-phar-build.patch \
	file://php.ini.native \
	file://mod.ini \
	file://20-openssl.ini \
	file://20-snmp.ini \
	file://50-date.ini \
	file://opcache-default.blacklist \
"

SRC_URI:append:class-native = " \
	file://readdir.patch \
"

SRC_URI:append:class-target = " \
	file://php-7.3.3-crosscompile.patch \
	file://php-5.6.9-install-pear.phar.patch \
	file://strcasestr-crosscompile.patch \
	file://php-7.3.3-httpd.patch \
	file://php-7.3.3-includedir.patch \
	file://php-7.3.3-embed.patch \
	file://php-7.3.3-recode.patch \
	file://php-7.3.3-libdb.patch \
	file://php-7.3.3-curl.patch \
	file://php-net-snmp.patch \
	file://php-7.3.3-dlopen.patch \
	file://php-7.3.3-systzdata-v19.patch \
	file://php-7.3.0-phpize.patch \
	file://php-7.3.3-ldap_r.patch \
	file://php-7.3.3-fixheader.patch \
	file://php-7.3.3-phpinfo.patch \
	file://php-7.3.3-pdooci.patch \
	file://php-7.3.3-oci8conf.patch \
	file://php-7.0.10-datetests.patch \
	file://php-5.6.3-PHP_CHECK_FUNC_LIB-no-run.patch \
	file://php-bug80682.patch \
	file://php-bug80783.patch \
	file://php-pcre1038.patch \
	file://php-bug81719.patch \
	file://php-bug81720.patch \
	file://php-bug81727.patch \
	file://php-bug81726.patch \
	file://php-bug81738.patch \
	file://php-bug81740.patch \
	file://php-bug81744.patch \
	file://php-bug81746.patch \
	file://php-cve-2023-0662.patch \
	file://php-cve-2023-3247.patch \
	file://php-cve-2023-3823.patch \
	file://php-cve-2023-3824.patch \
	file://php-7.3.3-icu-includes.patch \
	file://php-7.2.13-fnmatch-standard-check.patch \
	file://php-7.3.24-fpm.patch \
	file://php-7.3.3-reproducible-build.patch \
	file://php-cve-2024-2756.patch \
	file://php-cve-2024-3096.patch \
"

SRC_URI[sha256sum] = "f412487d7d953437e7978a0d7b6ec99bf4a85cf3378014438a8577b89535451a"

do_install:append:class-target() {
	install -m 644 ${WORKDIR}/opcache-default.blacklist ${D}${sysconfdir}/php${PHPVER}/php.d/
}

PACKAGES =+ "php${PHPVER}-json php${PHPVER}-sodium"

FILES:php${PHPVER}-json = ""
RPROVIDES:php${PHPVER}-json = "php${PHPVER}-pecl-json php${PHPVER}-pecl-jsonc"
ALLOW_EMPTY:php${PHPVER}-json = "1"

FILES:php${PHPVER}-sodium = " \
	${libdir}/php${PHPVER}/modules/sodium.so \
	${sysconfdir}/php${PHPVER}/php.d/20-sodium.ini \
"
RPROVIDES:php${PHPVER}-sodium = "php${PHPVER}-pecl-sodium"
