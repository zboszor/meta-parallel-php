# This must be before require php.inc
do_configure:prepend () {
	libtoolize --force --copy
	cat $(aclocal --print-ac-dir)/{libtool,ltoptions,ltsugar,ltversion,lt~obsolete}.m4 >build/libtool.m4
}

require php.inc

ARCHIVE_COMPRESSION = "xz"

DEPENDS:append:class-target = " libpcre2 libzip libsodium onig "
DEPENDS:append:class-native = " libpcre2-native onig-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=fd469cce1a919f0cc95bab7afb28d19d"

#PR = "r1"

EXTRA_OECONF:append:class-target = " \
	ac_cv_func_fnmatch_works=yes \
	php_cv_func_ttyname_r=yes \
	php_cv_shm_ipc=yes \
	php_cv_shm_mmap_anon=yes \
	php_cv_shm_mmap_posix=yes \
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
	file://php-8.4.0-fix-phpize-for-parallel-installation.patch \
	file://php.ini.native \
	file://mod.ini \
	file://20-openssl.ini \
	file://20-snmp.ini \
	file://50-date.ini \
	file://opcache-default.blacklist \
"

SRC_URI:append:class-target = " \
	file://php-5.6.9-install-pear.phar.patch \
\
	file://php-8.4.0-httpd.patch \
	file://php-8.4.0-includedir.patch \
	file://php-8.4.0-embed.patch \
	file://php-8.4.0-libdb.patch \
	file://php-8.3.3-parser.patch \
	file://php-8.4.0-systzdata-v24.patch \
	file://php-8.4.0-phpize.patch \
	file://php-8.4.0-ldap_r.patch \
	file://php-8.0.7-argon2.patch \
	file://php-8.4.0-phpinfo.patch \
	file://php-7.4.0-datetests.patch \
"

SRC_URI[sha256sum] = "5c42173cbde7d0add8249c2e8a0c19ae271f41d8c47d67d72bdf91a88dcc7e4b"

LIBS:append:class-target = ""
LIBS:append:class-native = " -lpthread"
export LIBS

do_install:append:class-target() {
	install -m 644 ${UNPACKDIR}/opcache-default.blacklist ${D}${sysconfdir}/php${PHPVER}/php.d/
}

do_install:append:class-native() {
	install -m0755 ${B}/ext/opcache/jit/ir/minilua ${D}${bindir}/php-native-minilua
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
