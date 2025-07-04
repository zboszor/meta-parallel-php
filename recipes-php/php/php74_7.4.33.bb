# This must be before require php.inc
require php-configure-preamble.inc

require php.inc

ARCHIVE_COMPRESSION = "xz"

DEPENDS:append:class-target = " libpcre2 libzip libsodium onig "
DEPENDS:append:class-native = " libpcre2-native onig-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=99532e0f6620bc9bca34f12fadaee33c"

PR = "r10"

EXTRA_OECONF:append:class-target = " \
	ac_cv_func_fnmatch_works=yes \
	--with-external-pcre \
	--with-pcre-jit \
	--with-libxml \
	--enable-fpm${DEPSETTING3} \
	--enable-intl${DEPSETTING3} \
	--enable-gd${DEPSETTING3} \
	--with-jpeg \
	--with-xmlrpc${DEPSETTING3} \
	--with-sodium=shared,${STAGING_LIBDIR}/.. \
	--with-zlib-dir=${STAGING_LIBDIR}/.. \
	--with-strcasestr=system \
"

EXTRA_OECONF:append:class-native = " \
	--with-xmlrpc${DEPSETTING3} \
	--with-zlib-dir=${STAGING_LIBDIR}/.. \
"

SRC_URI:remove = " \
	file://acinclude-xml2-config.patch \
	file://0001-acinclude-use-pkgconfig-for-libxml2-config.patch \
	file://php-5.6.5-xslt-config.patch \
	file://0001-ext-xsl-use-pkgconfig-for-xslt-config.patch \
"

SRC_URI += " \
	file://imap-fix-autofoo.patch \
	file://php-7.4.11-fix-phpize-for-parallel-installation.patch \
	file://php-7.4.26-openssl3.patch \
	file://php-7.4.26-openssl3-fixup.patch \
	file://php-7.4.33-libxml212.patch \
	file://php.ini.native \
	file://mod.ini \
	file://20-openssl.ini \
	file://20-snmp.ini \
	file://50-date.ini \
	file://opcache-default.blacklist \
	file://php-7.4.33-gcc14.patch \
	file://php-7.4.33-proto.patch \
	file://0001-Fix-cookie_seek_function_t-signature-under-musl-1389.patch \
	file://0002-ext-intl-level-up-c-runtime-std-for-icu-74-and-onwar.patch \
"

SRC_URI:append:class-target = " \
	file://php-7.4.11-crosscompile.patch \
	file://php-5.6.9-install-pear.phar.patch \
	file://strcasestr-crosscompile.patch \
	file://php-7.4.0-httpd.patch \
	file://php-7.2.0-includedir.patch \
	file://php-7.4.0-embed.patch \
	file://php-7.2.0-libdb.patch \
	file://php-7.0.7-curl.patch \
	file://php-net-snmp.patch \
	file://php-7.3.3-systzdata-v19.patch \
	file://php-7.4.0-phpize.patch \
	file://php-7.4.0-ldap_r.patch \
	file://php-7.4.20-argon2.patch \
	file://php-7.4.8-phpinfo.patch \
	file://php-7.0.10-datetests.patch \
	file://php-7.4.11-PHP_CHECK_FUNC_LIB-no-run.patch \
	file://php-7.4.11-reproducible-build.patch \
	file://php-bug81740.patch \
	file://php-bug81744.patch \
	file://php-bug81746.patch \
	file://php-cve-2023-0662.patch \
	file://php-cve-2023-3247.patch \
	file://php-cve-2023-3823.patch \
	file://php-cve-2023-3824.patch \
	file://php-cve-2024-2756.patch \
	file://php-cve-2024-3096.patch \
	file://php-7.4.33-zlib-tests.patch \
	file://php-cve-2024-5458.patch \
	file://php-cve-2024-8925.patch \
	file://php-cve-2024-8926.patch \
	file://php-cve-2024-8927.patch \
	file://php-cve-2024-9026.patch \
	file://php-cve-2024-11236.patch \
	file://php-cve-2024-11234.patch \
	file://php-cve-2024-8932.patch \
	file://php-cve-2024-11233.patch \
	file://php-ghsa-4w77-75f9-2c8w.patch \
	file://php-cve-2024-8929.patch \
"

SRC_URI[sha256sum] = "924846abf93bc613815c55dd3f5809377813ac62a9ec4eb3778675b82a27b927"

do_install:append:class-target() {
	install -m 644 ${UNPACKDIR}/opcache-default.blacklist ${D}${sysconfdir}/php${PHPVER}/php.d/
}

PACKAGECONFIG[imap] = "--with-imap=${DEPSETTING1} --with-imap-ssl=${DEPSETTING1},--without-imap --without-imap-ssl,uw-imap"
PACKAGECONFIG += "imap"

PACKAGES =+ "php${PHPVER}-json php${PHPVER}-sodium"

RPROVIDES:php${PHPVER} += "${@bb.utils.contains('PACKAGECONFIG', 'imap', 'php${PHPVER}-pecl-imap', '', d)}"

FILES:php${PHPVER}-json = ""
RPROVIDES:php${PHPVER}-json = "php${PHPVER}-pecl-json php${PHPVER}-pecl-jsonc"
ALLOW_EMPTY:php${PHPVER}-json = "1"

FILES:php${PHPVER}-sodium = " \
	${libdir}/php${PHPVER}/modules/sodium.so \
	${sysconfdir}/php${PHPVER}/php.d/20-sodium.ini \
"
RPROVIDES:php${PHPVER}-sodium = "php${PHPVER}-pecl-sodium"
