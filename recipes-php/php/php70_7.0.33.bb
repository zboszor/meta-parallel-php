require php.inc

# PHP 7.0 doesn't have --with-valgrind
PACKAGECONFIG[valgrind] = ""

DEPENDS:append:class-target = " libmcrypt "
DEPENDS:append:class-native = " libmcrypt-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=c0af599f66d0461c5837c695fcbc5c1e"

PR = "r20"

EXTRA_OECONF:append:class-target = " \
	--enable-crosscompiling \
	--with-flock-type=linux \
	--with-pcre-regex=${STAGING_LIBDIR}/.. \
	--enable-libxml \
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
	file://php-7.0.0-fix-phar-build.patch \
	file://0001-Sync-callback-signature-with-libxml2-2.9.8.patch \
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
	file://php-7.0.13-crosscompile.patch \
	file://php-5.6.9-install-pear.phar.patch \
	file://fix-fpm-cross-compile.patch \
	file://flock-type-crosscompile.patch \
	file://strcasestr-crosscompile.patch \
	file://php-7.0.0-includedir.patch \
	file://php-5.6.3-embed.patch \
	file://php-5.3.0-recode.patch \
	file://php-7.0.2-libdb.patch \
	file://php-7.0.17-dlopen.patch \
	file://php-7.0.0-systzdata-v14.patch \
	file://php-5.4.0-phpize.patch \
	file://php-5.6.3-ldap_r.patch \
	file://php-7.0.0-fixheader.patch \
	file://php-5.6.3-phpinfo.patch \
	file://php-5.6.3-datetests.patch \
	file://php-5.6.3-icu-includes.patch \
	file://php-7.0.17-fnmatch-standard-check.patch \
	file://1100-Utilize-the-recommended-way-to-handle-the-icu-namesp.patch \
	file://1101-Simplify-namespace-access.patch \
	file://php-7.0.33-fix-intl.patch \
	file://be50a72715c141befe6f34ece660745da894aaf3.patch \
	file://2ef8809ef3beb5f58b81dcff49bdcde4d2cb8426.patch \
	file://php-bug76846.patch \
	file://php-bug77242.patch \
	file://php-bug77247.patch \
	file://php-bug77370.patch \
	file://php-bug77371.patch \
	file://php-bug77380.patch \
	file://php-bug77381.patch \
	file://php-bug77369.patch \
	file://php-bug77418.patch \
	file://php-bug77396.patch \
	file://php-bug77431.patch \
	file://php-bug77540.patch \
	file://php-bug77563.patch \
	file://php-bug77586.patch \
	file://php-bug77630.patch \
	file://php-news.patch \
	file://php-sqlite3-defensive.patch \
	file://php-bug77753.patch \
	file://php-bug77831.patch \
	file://php-bug77950.patch \
	file://php-bug78069.patch \
	file://php-bug77988.patch \
	file://php-bug77967.patch \
	file://php-bug78222.patch \
	file://php-bug78256.patch \
	file://php-bug77919.patch \
	file://php-bug75457.patch \
	file://php-bug78380.patch \
	file://php-bug78599.patch \
	file://php-bug78878.patch \
	file://php-bug78862.patch \
	file://php-bug78863.patch \
	file://php-bug78793.patch \
	file://php-bug78910.patch \
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

SRC_URI[md5sum] = "6988ea64d0b32c9e1ab8aabe10b80dd1"
SRC_URI[sha256sum] = "4933ea74298a1ba046b0246fe3771415c84dfb878396201b56cb5333abe86f07"

PACKAGECONFIG[imap] = "--with-imap=${DEPSETTING1} --with-imap-ssl=${DEPSETTING1},--without-imap --without-imap-ssl,uw-imap"
PACKAGECONFIG += "imap"

PACKAGES =+ "php${PHPVER}-json"

RPROVIDES:php${PHPVER} += "${@bb.utils.contains('PACKAGECONFIG', 'imap', 'php${PHPVER}-pecl-imap', '', d)}"

FILES:php${PHPVER}-json = ""
RPROVIDES:php${PHPVER}-json = "php${PHPVER}-pecl-json php${PHPVER}-pecl-jsonc"
ALLOW_EMPTY:php${PHPVER}-json = "1"
