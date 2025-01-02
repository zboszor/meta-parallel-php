require php.inc

DEPENDS:append:class-target = " libmcrypt "
DEPENDS:append:class-native = " libmcrypt-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=fb07bfc51f6d5e0c30b65d9701233b2e"

PR = "r7"

# PHP 7.1 doesn't have --with-valgrind
PACKAGECONFIG[valgrind] = ""

EXTRA_OECONF:append:class-target = " \
	--enable-crosscompiling \
	--with-flock-type=linux \
	--with-pcre-regex=${STAGING_LIBDIR}/.. \
	--with-libxml-dir=${STAGING_BINDIR_CROSS} \
	--with-png-dir=${STAGING_LIBDIR}/.. \
	--with-jpeg-dir=${STAGING_LIBDIR}/.. \
	--with-xpm-dir=${STAGING_LIBDIR}/.. \
	--with-gd=shared \
	--with-xmlrpc${DEPSETTING3} \
	--enable-wddx${DEPSETTING3} \
	--enable-zip \
	--with-mcrypt=${DEPSETTING2} \
	--enable-fpm=shared \
	--enable-intl=shared \
	--with-zlib-dir=${STAGING_LIBDIR}/.. \
	--with-strcasestr=system \
"

EXTRA_OECONF:append:class-native = " \
	--with-pcre-regex=${STAGING_LIBDIR}/.. \
	--with-libxml-dir=${STAGING_BINDIR_NATIVE} \
	--with-xmlrpc${DEPSETTING3} \
	--with-zlib-dir=${STAGING_LIBDIR}/.. \
"

SRC_URI += " \
	file://imap-fix-autofoo.patch \
	file://php-7.0.0-fix-phpize-for-parallel-installation.patch \
	file://php-7.1.27-fix-phar-build.patch \
	file://readdir.patch \
	file://cookie-funcs-use-off64_t.patch \
	file://fix-func-prototypes-in-m4.patch \
	file://0001-Fix-bug-78823-add-zlib-library-to-mysqlnd.patch \
	file://php.ini.native \
	file://mod.ini \
	file://20-openssl.ini \
	file://20-snmp.ini \
	file://50-date.ini \
"

SRC_URI:append:class-target = " \
	file://php-7.1.27-crosscompile.patch \
	file://php-7.1.27-install-pear.phar.patch \
	file://flock-type-crosscompile.patch \
	file://strcasestr-crosscompile.patch \
	file://php-7.1.7-httpd.patch \
	file://php-7.1.27-includedir.patch \
	file://php-5.6.3-embed.patch \
	file://php-5.3.0-recode.patch \
	file://php-7.0.2-libdb.patch \
	file://php-7.0.7-curl.patch \
	file://php-net-snmp.patch \
	file://php-7.1.27-dlopen.patch \
	file://php-7.1.0-systzdata-v14.patch \
	file://php-7.1.27-phpize.patch \
	file://php-7.1.15-ldap_r.patch \
	file://php-7.1.16-fixheader.patch \
	file://php-7.1.27-phpinfo.patch \
	file://php-7.1.9-openssl-load-config.patch \
	file://php-7.1.24-getallheaders.patch \
	file://php-5.6.3-oci8conf.patch \
	file://php-bug78878.patch \
	file://php-bug78862.patch \
	file://php-bug78863.patch \
	file://php-bug78793.patch \
	file://php-bug78910.patch \
	file://php-bug79091.patch \
	file://php-bug79099.patch \
	file://php-bug79037.patch \
	file://php-bug77569.patch \
	file://php-bug79221.patch \
	file://php-bug79082.patch \
	file://php-bug79282.patch \
	file://php-bug79329.patch \
	file://php-bug79330.patch \
	file://php-bug79465.patch \
	file://php-bug78875.patch \
	file://php-bug78876.patch \
	file://php-bug79797.patch \
	file://php-bug79877.patch \
	file://php-bug79601.patch \
	file://php-bug79699.patch \
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
	file://php-bug81740.patch \
	file://php-bug81744.patch \
	file://php-bug81746.patch \
	file://php-cve-2023-0662.patch \
	file://php-cve-2023-3247.patch \
	file://php-cve-2023-3823.patch \
	file://php-cve-2023-3824.patch \
	file://php-5.6.3-datetests.patch \
	file://php-7.0.0-oldpcre.patch \
	file://php-openssl-cert.patch \
	file://php-7.1.27-icu-includes.patch \
	file://php-7.1.25-fnmatch-standard-check.patch \
	file://php-7.1.33-fix-intl.patch \
	file://php-cve-2024-2756.patch \
	file://php-cve-2024-3096.patch \
	file://php-cve-2024-5458.patch \
	file://php-cve-2024-8925.patch \
	file://php-cve-2024-8926.patch \
	file://php-cve-2024-8927.patch \
	file://php-cve-2024-11236.patch \
	file://php-cve-2024-11234.patch \
	file://php-cve-2024-8932.patch \
	file://php-cve-2024-11233.patch \
	file://php-ghsa-4w77-75f9-2c8w.patch \
"

SRC_URI[md5sum] = "01974aa2076c56d4c2730f2c2ec6d8aa"
SRC_URI[sha256sum] = "95a5e5f2e2b79b376b737a82d9682c91891e60289fa24183463a2aca158f4f4b"

PACKAGECONFIG[imap] = "--with-imap=${DEPSETTING1} --with-imap-ssl=${DEPSETTING1},--without-imap --without-imap-ssl,uw-imap"
PACKAGECONFIG += "imap"

PACKAGES =+ "php${PHPVER}-json"

RPROVIDES:php${PHPVER} += "${@bb.utils.contains('PACKAGECONFIG', 'imap', 'php${PHPVER}-pecl-imap', '', d)}"

FILES:php${PHPVER}-json = ""
RPROVIDES:php${PHPVER}-json = "php${PHPVER}-pecl-json php${PHPVER}-pecl-jsonc"
ALLOW_EMPTY:php${PHPVER}-json = "1"
