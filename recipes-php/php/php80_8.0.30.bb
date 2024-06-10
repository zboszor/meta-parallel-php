# This must be before require php.inc
do_configure:prepend () {
	libtoolize --force --copy
	cat $(aclocal --print-ac-dir)/{libtool,ltoptions,ltsugar,ltversion,lt~obsolete}.m4 >build/libtool.m4
}

require php.inc

ARCHIVE_COMPRESSION = "xz"

DEPENDS:append:class-target = " libpcre2 libzip libsodium onig "
DEPENDS:append:class-native = " libpcre2-native onig-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=99532e0f6620bc9bca34f12fadaee33c"

PR = "r5"

EXTRA_OECONF:append:class-target = " \
	ac_cv_func_fnmatch_works=yes \
	--with-external-pcre \
	--with-pcre-jit \
	--enable-fpm${DEPSETTING3} \
	--enable-intl${DEPSETTING3} \
	--enable-gd${DEPSETTING3} \
	--with-jpeg \
	--with-sodium=shared,${STAGING_LIBDIR}/.. \
"

SRC_URI:remove = " \
	file://acinclude-xml2-config.patch \
	file://0001-acinclude-use-pkgconfig-for-libxml2-config.patch \
	file://php-5.6.5-xslt-config.patch \
	file://0001-ext-xsl-use-pkgconfig-for-xslt-config.patch \
"

SRC_URI += " \
	file://php-8.0.0-fix-phpize-for-parallel-installation.patch \
	file://php-8.0.21-openssl3.patch \
	file://php-8.0.13-crypt.patch \
	file://php-8.0.30-libxml212.patch \
	file://php.ini.native \
	file://mod.ini \
	file://20-openssl.ini \
	file://20-snmp.ini \
	file://50-date.ini \
	file://opcache-default.blacklist \
"

SRC_URI:append:class-target = " \
	file://php-8.0.5-crosscompile.patch \
	file://php-5.6.9-install-pear.phar.patch \
	file://strcasestr-crosscompile.patch \
	file://php-7.4.0-httpd.patch \
	file://php-7.2.0-includedir.patch \
	file://php-8.0.0-embed.patch \
	file://php-7.4.0-libdb.patch \
	file://php-8.0.6-deprecated.patch \
	file://php-7.0.7-curl.patch \
	file://php-8.0.0-parser.patch \
	file://php-8.0.10-systzdata-v21.patch \
	file://php-7.4.0-phpize.patch \
	file://php-7.4.0-ldap_r.patch \
	file://php-8.0.7-argon2.patch \
	file://php-8.0.0-phpinfo.patch \
	file://php-8.0.10-snmp-sha.patch \
	file://php-8.0.10-phar-sha.patch \
	file://php-7.2.0-oci8conf.patch \
	file://php-8.0.28-intlinfo.patch \
	file://php-7.4.0-datetests.patch \
	file://php-8.0.0-PHP_CHECK_FUNC_LIB-no-run.patch \
	file://php-8.0.0-run-installed-minilua.patch \
	file://php-8.0.30-zlib-tests.patch \
	file://php-cve-2024-2756.patch \
	file://php-cve-2024-3096.patch \
	file://php-cve-2024-5458.patch \
"

SRC_URI[sha256sum] = "216ab305737a5d392107112d618a755dc5df42058226f1670e9db90e77d777d9"

do_install:append:class-target() {
	install -m 644 ${WORKDIR}/opcache-default.blacklist ${D}${sysconfdir}/php${PHPVER}/php.d/
}

do_install:append:class-native() {
	install -m0755 ${B}/ext/opcache/minilua ${D}${bindir}/php-native-minilua
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
