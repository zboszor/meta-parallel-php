require php.inc

DEPENDS:append:class-target = " libsodium "

LIC_FILES_CHKSUM = "file://LICENSE;md5=fb07bfc51f6d5e0c30b65d9701233b2e"

PR = "r3"

EXTRA_OECONF:append:class-target = " \
	--enable-crosscompiling \
	--enable-fpm=shared \
	--enable-intl=shared \
	--with-pcre-regex=${STAGING_LIBDIR}/.. \
	--with-libxml-dir=${STAGING_BINDIR_CROSS} \
	--with-png-dir=${STAGING_LIBDIR}/.. \
	--with-jpeg-dir=${STAGING_LIBDIR}/.. \
	--with-xpm-dir=${STAGING_LIBDIR}/.. \
	--with-gd=shared \
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
	file://php-7.2.0-fix-phpize-for-parallel-installation.patch \
	file://php-7.2.16-fix-phar-build.patch \
	file://opcache-crosscompile.patch \
	file://php.ini.native \
	file://mod.ini \
	file://20-openssl.ini \
	file://20-snmp.ini \
	file://50-date.ini \
"

SRC_URI:append:class-target = " \
	file://php-7.2.16-crosscompile.patch \
	file://php-7.2.16-install-pear.phar.patch \
	file://strcasestr-crosscompile.patch \
		\
	file://php-7.1.7-httpd.patch \
	file://php-7.2.0-includedir.patch \
	file://php-5.6.3-embed.patch \
	file://php-5.3.0-recode.patch \
	file://php-7.2.0-libdb.patch \
	file://php-7.0.7-curl.patch \
	file://php-net-snmp.patch \
	file://php-7.2.16-dlopen.patch \
	file://php-7.2.16-systzdata-v17.patch \
	file://php-7.2.16-phpize.patch \
	file://php-7.2.3-ldap_r.patch \
	file://php-7.2.32-fixheader.patch \
	file://php-7.2.32-phpinfo.patch \
	file://php-7.2.8-getallheaders.patch \
	file://php-7.2.16-pdooci.patch \
	file://php-7.2.0-oci8conf.patch \
	file://php-bug77423.patch \
	file://php-bug80672.patch \
	file://php-bug80710.patch \
	file://php-bug81122.patch \
	file://php-bug76450.patch \
	file://php-bug81211.patch \
	file://php-bug81026.patch \
	file://php-bug79971.patch \
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
	file://php-7.0.10-datetests.patch \
	file://php-7.0.0-oldpcre.patch \
	file://php-5.6.3-PHP_CHECK_FUNC_LIB-no-run.patch \
	file://php-7.2.16-icu-includes.patch \
	file://php-7.2.13-fnmatch-standard-check.patch \
	file://php-7.2.34-fix-intl.patch \
"

SRC_URI[md5sum] = "4cc3868c4ab7a094bf15b02c2d953ccd"
SRC_URI[sha256sum] = "0e5816d668a2bb14aca68cef8c430430bd86c3c5233f6c427d1a54aac127abcf"

PACKAGES =+ "php${PHPVER}-json php${PHPVER}-sodium"

FILES:php${PHPVER}-json = ""
RPROVIDES:php${PHPVER}-json = "php${PHPVER}-pecl-json php${PHPVER}-pecl-jsonc"
ALLOW_EMPTY:php${PHPVER}-json = "1"

FILES:php${PHPVER}-sodium = " \
	${libdir}/php${PHPVER}/modules/sodium.so \
	${sysconfdir}/php${PHPVER}/php.d/20-sodium.ini \
"
RPROVIDES:php${PHPVER}-sodium = "php${PHPVER}-pecl-sodium"
