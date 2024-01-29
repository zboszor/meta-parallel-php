require php.inc

# PHP 7.0 doesn't have --with-valgrind
PACKAGECONFIG[valgrind] = ""

DEPENDS:append:class-target = " libmcrypt "
DEPENDS:append:class-native = " libmcrypt-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=c0af599f66d0461c5837c695fcbc5c1e"

PR = "r12"

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
"

EXTRA_OECONF:append:class-native = " \
	--with-pcre-regex=${STAGING_LIBDIR}/.. \
	--with-libxml-dir=${STAGING_BINDIR_NATIVE} \
	--with-xmlrpc${DEPSETTING3} \
"

SRC_URI += " \
	file://php-7.0.0-fix-phpize-for-parallel-installation.patch \
	file://php-7.0.0-fix-phar-build.patch \
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
	file://php-7.0.0-systzdata-v13.patch \
	file://php-5.4.0-phpize.patch \
	file://php-5.6.3-ldap_r.patch \
	file://php-7.0.0-fixheader.patch \
	file://php-5.6.3-phpinfo.patch \
	file://php-5.6.3-datetests.patch \
	file://php-5.6.3-PHP_CHECK_FUNC_LIB-no-run.patch \
	file://php-5.6.3-icu-includes.patch \
	file://php-7.0.17-fnmatch-standard-check.patch \
	file://1100-Utilize-the-recommended-way-to-handle-the-icu-namesp.patch \
	file://1101-Simplify-namespace-access.patch \
	file://php-7.0.33-fix-intl.patch \
"

SRC_URI[md5sum] = "6988ea64d0b32c9e1ab8aabe10b80dd1"
SRC_URI[sha256sum] = "4933ea74298a1ba046b0246fe3771415c84dfb878396201b56cb5333abe86f07"

PACKAGES =+ "php${PHPVER}-json"
FILES:php${PHPVER}-json = ""
RPROVIDES:php${PHPVER}-json = "php${PHPVER}-pecl-json php${PHPVER}-pecl-jsonc"
ALLOW_EMPTY:php${PHPVER}-json = "1"
