SUMMARY = "A server-side, HTML-embedded scripting language"
HOMEPAGE = "http://www.php.net"
SECTION = "console/network"

LICENSE = "PHP-3.0"

BBCLASSEXTEND = "native"

# The new PHP downloads server groups PHP releases by major version so find
# the major version of the PHP recipe.
PHP_MAJVER = "${@d.getVar('PV',1).split('.')[0]}"
PHP_MINVER = "${@d.getVar('PV',1).split('.')[1]}"
PHPVER = "${PHP_MAJVER}${PHP_MINVER}"
export PHPVER

DEPENDS:class-target = " \
	zlib bzip2 libxml2 libxslt virtual/libiconv php${PHPVER}-native lemon-native \
	openssl mariadb curl icu systemd libpcre libgcrypt \
	gd jpeg libpng freetype libpcre net-snmp freetds \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libxpm', '', d)} \
	rsync-native \
"

DEPENDS:class-native = " \
	zlib-native bzip2-native libxml2-native libxslt-native openssl-native curl-native \
	gmp-native gettext-native libpcre-native \
"

BETALEVEL ??= ""
BETAURL ??= ""

BB_CHECK_SSL_CERTS = "0"

ARCHIVE_COMPRESSION ??= "bz2"

def phpfile_url (d):
    beta = d.getVar('BETALEVEL', 1)
    if beta is None or beta == "":
        return "http://php.net/distributions/php-%s.tar.%s" % (d.getVar('PV',1), d.getVar('ARCHIVE_COMPRESSION',1))
    else:
        return "%s/php-%s%s.tar.%s" % (d.getVar('BETAURL', 1), d.getVar('PV', 1), beta, d.getVar('ARCHIVE_COMPRESSION',1))

def is_target_pkg(d):
    import re
    pn = d.getVar('PN', 1)
    if re.match("^.*-native$", pn):
        return 0
    return 1

SRC_URI = " \
	${@phpfile_url(d)} \
	file://php-config-fix-include_dir.patch \
	file://acinclude-xml2-config.patch \
	file://0001-php-don-t-use-broken-wrapper-for-mkdir.patch \
	file://0001-acinclude-use-pkgconfig-for-libxml2-config.patch \
	file://php-5.6.5-xslt-config.patch \
	file://0001-ext-xsl-use-pkgconfig-for-xslt-config.patch \
	file://phar-makefile.patch \
"

SRC_URI:append:class-target = " \
	file://iconv.patch \
	file://pear-makefile.patch \
	file://php_exec_native.patch \
	file://php-fpm.service \
	file://php-fpm.sysconfig \
"

S = "${WORKDIR}/php-${PV}${BETALEVEL}"

inherit autotools-brokensep pkgconfig python3native gettext systemd

# phpizeXX and php-configXX must be scanned for absolute paths

SSTATE_SCAN_FILES += "php-config${PHPVER}"
SSTATE_SCAN_FILES += "phpize${PHPVER}"
SSTATE_SCAN_FILES += "build-defs.h"

PHP_LIBDIR = "${libdir}/php${PHPVER}"

EXTENSION_DIR = "${PHP_LIBDIR}/modules"
export EXTENSION_DIR

PEAR_INSTALLDIR = "${datadir}/pear${PHPVER}"
export PEAR_INSTALLDIR

DEPSETTING1 = "${@ 'shared,%s/..' % (d.getVar('STAGING_LIBDIR') if is_target_pkg(d) else d.getVar('STAGING_LIBDIR_NATIVE'))}"
DEPSETTING2 = "${@ 'shared,%s%s' % (d.getVar('STAGING_DIR_TARGET') if is_target_pkg(d) else d.getVar('STAGING_DIR_NATIVE'), d.getVar('exec_prefix'))}"
DEPSETTING3 = "${@ '=shared' if is_target_pkg(d) else ''}"

# Common EXTRA_OECONF
COMMON_EXTRA_OECONF = " \
	--disable-rpath \
	--sysconfdir=${sysconfdir}/php${PHPVER} \
	--libdir=${PHP_LIBDIR} \
	--datadir=${datadir}/php${PHPVER} \
	--program-suffix=${PHPVER} \
	--with-config-file-path=${sysconfdir}/php${PHPVER} \
	--with-config-file-scan-dir=${sysconfdir}/php${PHPVER}/php.d \
	--with-pear=${PEAR_INSTALLDIR} \
	--enable-mbstring${DEPSETTING3} \
	--enable-mbregex \
	--with-gmp=${DEPSETTING1} \
	--enable-calendar${DEPSETTING3} \
	--enable-bcmath${DEPSETTING3} \
	--with-bz2=${DEPSETTING2} \
	--enable-ctype${DEPSETTING3} \
	--enable-exif${DEPSETTING3} \
	--enable-ftp${DEPSETTING3} \
	--with-gettext=${DEPSETTING1} \
	--enable-tokenizer${DEPSETTING3} \
	--with-openssl=${DEPSETTING1} \
	--with-curl=${DEPSETTING1} \
	--with-system-ciphers \
	--with-mhash${DEPSETTING3} \
	--enable-simplexml${DEPSETTING3} \
	--enable-xml${DEPSETTING3} \
	--with-zlib=${DEPSETTING1} \
	--with-xsl=${DEPSETTING1} \
	--enable-xmlreader${DEPSETTING3} \
	--enable-xmlwriter${DEPSETTING3} \
	--enable-phar${DEPSETTING3} \
	--enable-sockets${DEPSETTING3} \
	--enable-pcntl \
	--with-pic \
"

EXTRA_OECONF:class-target = " \
	--enable-opcache \
	--enable-phpdbg \
	--with-iconv=${DEPSETTING1} \
	--enable-dom${DEPSETTING3} \
	--with-fpm-systemd \
	--with-snmp${DEPSETTING3} \
	--enable-soap${DEPSETTING3} \
	--enable-pdo${DEPSETTING3} \
	--with-pdo-dblib=${DEPSETTING1} \
	--without-readline \
	--enable-sysvmsg${DEPSETTING3} \
	--enable-sysvshm${DEPSETTING3} \
	--enable-sysvsem${DEPSETTING3} \
	--enable-shmop${DEPSETTING3} \
	--enable-posix${DEPSETTING3} \
	--enable-fileinfo${DEPSETTING3} \
	--with-libdir=${baselib} \
	${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', 'ac_cv_c_bigendian_php=no', 'ac_cv_c_bigendian_php=yes', d)} \
	ac_cv_pwrite=yes \
	ac_cv_pread=yes \
	ac_cv_flush_io=yes \
	ac_cv_crypt_des=yes \
	ac_cv_crypt_ext_des=yes \
	ac_cv_crypt_md5=yes \
	ac_cv_crypt_blowfish=yes \
	ac_cv_crypt_sha512=yes \
	ac_cv_crypt_sha256=yes \
	--enable-shared \
	${COMMON_EXTRA_OECONF} \
	--with-system-tzdata \
"

EXTRA_OECONF:class-native = " \
	--with-zlib=${STAGING_LIBDIR_NATIVE}/.. \
	--without-iconv \
	--disable-shared \
	${COMMON_EXTRA_OECONF} \
"

PACKAGECONFIG = " \
	mysql sqlite3 \
	${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'ipv6', '', d)} \
"

MYSQLND = "'=shared,mysqlnd'"

PACKAGECONFIG[mysql] = " \
	--enable-mysqlnd${DEPSETTING3}  \
	--with-mysqli${MYSQLND} \
	--with-mysql-sock=/run/mariadb/mysql.sock \
	--with-pdo-mysql${MYSQLND} \
	,--disable-mysqlnd --without-mysqli --without-pdo-mysql \
	,mysql5"

PACKAGECONFIG[sqlite3] = " \
	--with-sqlite3=${DEPSETTING1} \
	--with-pdo-sqlite=${DEPSETTING1} \
	, \
	,sqlite3"
PGSQL = "=${DEPSETTING2}"
PACKAGECONFIG[pgsql] = "--with-pgsql${PGSQL},--without-pgsql,postgresql"
PACKAGECONFIG[soap] = "--enable-libxml --enable-soap, --disable-soap, libxml2"
PACKAGECONFIG[apache2] = "--with-apxs2=${STAGING_BINDIR_CROSS}/apxs,,apache2-native apache2"
PACKAGECONFIG[pam] = ",,libpam"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,"
PACKAGECONFIG[valgrind] = "--with-valgrind=${STAGING_DIR_TARGET}/usr,--with-valgrind=no,valgrind"

export PHP_NATIVE_DIR = "${STAGING_BINDIR_NATIVE}"
export PHP_PEAR_PHP_BIN = "${STAGING_BINDIR_NATIVE}/php${PHPVER}"
CFLAGS += " -D_GNU_SOURCE -g -DPTYS_ARE_GETPT -DPTYS_ARE_SEARCHED -I${STAGING_INCDIR}/apache2"
CFLAGS:append:aarch64 = " -O2"

EXTRA_OEMAKE = "INSTALL_ROOT=${D}"

acpaths = ""

do_configure:prepend () {
	rm -f ${S}/build/libtool.m4 ${S}/ltmain.sh ${S}/aclocal.m4
	find ${S} -name config.m4 | xargs -n1 sed -i 's!APXS_HTTPD=.*!APXS_HTTPD=${STAGING_BINDIR_NATIVE}/httpd!'
}

do_configure:append:class-target () {
	# No, libtool, we really don't want rpath set...
	sed -i 's|^hardcode_libdir_flag_spec=.*|hardcode_libdir_flag_spec=""|g' libtool
	sed -i 's|^runpath_var=LD_RUN_PATH|runpath_var=DIE_RPATH_DIE|g' libtool
	sed -i -e's@${RECIPE_SYSROOT}@@g' \
		-e's@-ffile-prefix-map=[^ ]*[ ]*@@g' \
		-e's@-fdebug-prefix-map=[^ ]*[ ]*@@g' \
		-e's@-ffile-prefix-map=[^ ]*[ ]*@@g' \
		-e's@-fmacro-prefix-map=[^ ]*[ ]*@@g' \
		${B}/main/build-defs.h \
		${B}/scripts/php-config
}

do_install:append:class-native () {
	# No, libtool, we really don't want rpath set...
	sed -i 's|^hardcode_libdir_flag_spec=.*|hardcode_libdir_flag_spec=""|g' libtool
	sed -i 's|^runpath_var=LD_RUN_PATH|runpath_var=DIE_RPATH_DIE|g' libtool
	sed -i -e's@${RECIPE_SYSROOT_NATIVE}@@g' \
		-e's@-ffile-prefix-map=[^ ]*[ ]*@@g' \
		-e's@-fdebug-prefix-map=[^ ]*[ ]*@@g' \
		-e's@-ffile-prefix-map=[^ ]*[ ]*@@g' \
		-e's@-fmacro-prefix-map=[^ ]*[ ]*@@g' \
		${B}/main/build-defs.h \
		${B}/scripts/php-config

	rm -rf ${D}/${datadir}/pear${PHPVER}/.registry
	rm -rf ${D}/${datadir}/pear${PHPVER}/.channels
	rm -rf ${D}/${datadir}/pear${PHPVER}/.[a-z]*
}

do_install:prepend() {
	cat ${ACLOCALDIR}/libtool.m4 ${ACLOCALDIR}/lt~obsolete.m4 ${ACLOCALDIR}/ltoptions.m4 \
		${ACLOCALDIR}/ltsugar.m4 ${ACLOCALDIR}/ltversion.m4 > ${S}/build/libtool.m4
}

do_install:prepend:class-target() {
	if ${@bb.utils.contains('PACKAGECONFIG', 'apache2', 'true', 'false', d)}; then
		# Install dummy config file so apxs doesn't fail
		install -d ${D}${sysconfdir}/apache2
		printf "\nLoadModule dummy_module modules/mod_dummy.so\n" > ${D}${sysconfdir}/apache2/httpd.conf
	fi

	# The native PHP executable is being run to create some files
	# for the target installation. E.g. pear.conf is installed into
	# ${D}${STAGING_BINDIR_NATIVE}/../../${sysconfdir}/php${PHPVER}
	# Let's make sure all elements on the path exists.
	# We'll remove them later in the installation process.
	mkdir -p ${D}${STAGING_BINDIR_NATIVE}
	mkdir -p ${D}${STAGING_ETCDIR_NATIVE}
}

install_php_module_ini () {
	# Generate files lists and stub .ini files for each subpackage
	for module in ${D}${EXTENSION_DIR}/*.so ; do
		mod0="`basename $module`"
		mod="${mod0%.so}"

		case $mod in
			opcache)
				continue;;
			dblib|pdo_*|mysql|mysqli|wddx|xmlreader|xmlrpc)
				# Extensions with dependencies on 20-*
				ini=30-${mod}.ini;;
			*)
				# Extensions with no dependency
				ini=20-${mod}.ini;;
		esac

		# some extensions may have their own config file
		if [ -f ${WORKDIR}/${ini} ]; then
			install -m644 ${WORKDIR}/${ini} ${D}${sysconfdir}/php${PHPVER}/php.d/${ini}
		else
			cat ${WORKDIR}/mod.ini | sed "s/@MOD@/${mod}/" > ${D}${sysconfdir}/php${PHPVER}/php.d/${ini}
		fi
	done

	# Install extra configuration
	install -m644 ${WORKDIR}/50-date.ini ${D}${sysconfdir}/php${PHPVER}/php.d/

	# Rename PHAR files
	if [ -f "${D}${bindir}/phar.phar" ]; then
		rm -f ${D}${bindir}/phar
		mv ${D}${bindir}/phar.phar ${D}${bindir}/phar.phar${PHPVER}
		ln -s phar.phar${PHPVER} ${D}${bindir}/phar${PHPVER}
		sed -i -e 's:${D}::g' ${D}${bindir}/phar.phar${PHPVER}
	else
		if [ -f "${D}${bindir}/phar" ]; then
			mv ${D}${bindir}/phar ${D}${bindir}/phar${PHPVER}
		fi
	fi

	if [ -f "${D}${mandir}/man1/phar.phar.1" ]; then
		mv ${D}${mandir}/man1/phar.1 ${D}${mandir}/man1/phar${PHPVER}.1
		mv ${D}${mandir}/man1/phar.phar.1 ${D}${mandir}/man1/phar.phar${PHPVER}.1
		sed -i 's:\.so man1/\(.*\)\.1$:.so man1/\1${PHPVER}.1:' ${D}${mandir}/man1/*.1
	else
		if [ -f "${D}${mandir}/man1/phar.1" ]; then
			mv ${D}${mandir}/man1/phar.1 ${D}${mandir}/man1/phar${PHPVER}.1
		fi
	fi
}

# fixme
do_install:append:class-target() {
	install -d ${D}${sysconfdir}/php${PHPVER}
	if [ -d ${D}${STAGING_ETCDIR_NATIVE}/php${PHPVER} ]; then
		cp -ar ${D}${STAGING_ETCDIR_NATIVE}/php${PHPVER}/* ${D}${sysconfdir}/php${PHPVER}/
	fi
	rm -rf ${D}/${TMPDIR}
	rm -rf ${D}/.registry
	rm -rf ${D}/.channels
	rm -rf ${D}/.[a-z]*
	rm -rf ${D}/${datadir}/pear${PHPVER}/.registry
	rm -rf ${D}/${datadir}/pear${PHPVER}/.channels
	rm -rf ${D}/${datadir}/pear${PHPVER}/.[a-z]*
	rm -rf ${D}/var
	if [ -f ${D}${sysconfdir}/php${PHPVER}/pear.conf ]; then
		sed -i -e 's:${STAGING_DIR_NATIVE}::g' -e 's:${D}::g' ${D}${sysconfdir}/php${PHPVER}/pear.conf
	fi

	rm -f ${D}/${sysconfdir}/php${PHPVER}/*.conf.default
	if [ ! -d "${D}/${sysconfdir}/php${PHPVER}/php-fpm.d" ]; then
		install -d -m0755 ${D}/${sysconfdir}/php${PHPVER}/php-fpm.d
	fi
	rm -f ${D}/${sysconfdir}/php${PHPVER}/php-fpm.d/*.conf.default

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)};then
		install -d ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/php-fpm.service ${D}${systemd_unitdir}/system/php-fpm${PHPVER}.service
		sed -i -e 's,@SYSCONFDIR@,${sysconfdir},g' \
			-e 's,@LOCALSTATEDIR@,${localstatedir},g' \
			-e 's,@PHPVER@,${PHPVER},g' \
			${D}${systemd_unitdir}/system/php-fpm${PHPVER}.service
	fi

	TMP=`dirname ${D}/${TMPDIR}`
	while test ${TMP} != ${D}; do
		if [ -d ${TMP} ]; then
			rmdir ${TMP}
		fi
		TMP=`dirname ${TMP}`;
	done

	if ${@bb.utils.contains('PACKAGECONFIG', 'apache2', 'true', 'false', d)}; then
		install -d ${D}${libdir}/apache2/modules
		install -d ${D}${sysconfdir}/apache2/modules.d
		install -d ${D}${sysconfdir}/php/apache2-php5
		install -m 755  libs/libphp5.so ${D}${libdir}/apache2/modules
		install -m 644  ${WORKDIR}/70_mod_php5.conf ${D}${sysconfdir}/apache2/modules.d
		sed -i s,lib/,${libdir}/, ${D}${sysconfdir}/apache2/modules.d/70_mod_php5.conf
		cat ${S}/php.ini-production | \
			sed -e 's,extension_dir = \"\./\",extension_dir = \"/usr/lib/extensions\",' \
			> ${D}${sysconfdir}/php/apache2-php5/php.ini
		rm -f ${D}${sysconfdir}/apache2/httpd.conf*
	fi

	install -d -m0755 ${D}${sysconfdir}/sysconfig
	install -m0644 ${WORKDIR}/php-fpm.sysconfig ${D}${sysconfdir}/sysconfig/php-fpm${PHPVER}
	install -d ${D}${sysconfdir}/php${PHPVER}
	install -d ${D}${sysconfdir}/php${PHPVER}/php.d
	mkdir -p ${D}${localstatedir}/lib/php${PHPVER}/session
	chown www-data:www-data ${D}${localstatedir}/lib/php${PHPVER}/session
	chmod 0775 ${D}${localstatedir}/lib/php${PHPVER}/session
	install_php_module_ini

	mv ${D}${includedir}/php ${D}${includedir}/php${PHPVER}

	for i in pear peardev pecl ; do
		if [[ -f "${D}${bindir}/$i" ]]; then
			mv "${D}${bindir}/$i" "${D}${bindir}/$i"${PHPVER}
		fi
	done
}

do_install:append:class-native () {
	install -m0755 -d ${D}${sysconfdir}/php${PHPVER}
	install -m0644 ${WORKDIR}/php.ini.native ${D}${sysconfdir}/php${PHPVER}/php.ini
	install -m0755 -d ${D}${sysconfdir}/php${PHPVER}/php.d

	install_php_module_ini

	mv ${D}${includedir}/php ${D}${includedir}/php${PHPVER}

	for i in pear peardev pecl ; do
		if [[ -f "${D}${bindir}/$i" ]]; then
			mv "${D}${bindir}/$i" "${D}${bindir}/$i"${PHPVER}
		fi
	done
}

SYSROOT_PREPROCESS_FUNCS += "php_sysroot_preprocess"

php_sysroot_preprocess () {
	install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}/
	install -m 755 ${D}${bindir}/phpize${PHPVER} ${SYSROOT_DESTDIR}${bindir_crossscripts}/
	install -m 755 ${D}${bindir}/php-config${PHPVER} ${SYSROOT_DESTDIR}${bindir_crossscripts}/

	sed -i -e 's!eval echo /!eval echo ${STAGING_DIR_HOST}/!' -e 's!^includedir="\(.*\)/php"!includedir="\1/php${PHPVER}"!' ${SYSROOT_DESTDIR}${bindir_crossscripts}/phpize${PHPVER}
	#sed -i -e 's!\(includedir=.*\)/php"!\1/php${PHPVER}"!' -e 's!eval echo /!eval echo ${STAGING_DIR_HOST}/!' ${SYSROOT_DESTDIR}${bindir_crossscripts}/phpize${PHPVER}
	sed -i 's!^include_dir=.*!include_dir=${STAGING_INCDIR}/php${PHPVER}!' ${SYSROOT_DESTDIR}${bindir_crossscripts}/php-config${PHPVER}
}

MODPHP_PACKAGE = "${@bb.utils.contains('PACKAGECONFIG', 'apache2', '${PN}-modphp', '', d)}"

PACKAGES = "${PN}-dbg ${PN}-cli ${PN}-cgi ${PN}-fpm ${PN}-fpm-apache2 ${PN}-pear ${PN}-phar ${MODPHP_PACKAGE} ${PN}-dev ${PN}-staticdev ${PN}-doc ${PN}"

# QA Issue: File /etc/phpNN/pear.conf in package phpNN-pear contains reference to TMPDIR
# This is harmless. /tmp will be used on the target machine.
INSANE_SKIP:${PN}-pear = "buildpaths"

RDEPENDS:${PN}-pear = "${PN}"
RDEPENDS:${PN}-phar = "${PN}-cli"
RDEPENDS:${PN}-cli = "${PN}"
RDEPENDS:${PN}-modphp = "${PN} apache2"
RDEPENDS:${PN}-dev = "${PN}"

INITSCRIPT_PACKAGES = "${PN}-fpm"
inherit update-rc.d

SYSTEMD_PACKAGES = "${PN}-fpm"
SYSTEMD_SERVICE:${PN}-fpm = "php-fpm${PHPVER}.service"

FILES:${PN}-dbg =+ "${bindir}/.debug \
					${libdir}/apache2/modules/.debug"
FILES:${PN}-doc += "${PHP_LIBDIR}/doc"
FILES:${PN}-cli = "${bindir}/php${PHPVER}"
FILES:${PN}-phar = "${bindir}/phar*"
FILES:${PN}-cgi = "${bindir}/php-cgi${PHPVER}"
FILES:${PN}-fpm = " \
	${sbindir}/php-fpm${PHPVER} \
	${datadir}/php${PHPVER}/fpm \
	${sysconfdir}/php${PHPVER}/php-fpm.d/ \
	${sysconfdir}/sysconfig/php-fpm${PHPVER} \
	${systemd_unitdir}/system/php-fpm${PHPVER}.service \
	${localstatedir}/lib/php${PHPVER}/session \
"
FILES:${PN}-fpm-apache2 = "${sysconfdir}/apache2/conf.d/php-fpm.conf"
CONFFILES:${PN}-fpm-apache2 = "${sysconfdir}/apache2/conf.d/php-fpm.conf"
INITSCRIPT_NAME:${PN}-fpm = "php-fpm"
INITSCRIPT_PARAMS:${PN}-fpm = "defaults 60"
FILES:${PN}-pear = "${bindir}/pear* ${bindir}/pecl* ${PHP_LIBDIR}/PEAR \
	${PHP_LIBDIR}/PEAR*.php ${PHP_LIBDIR}/System.php \
	${PHP_LIBDIR}/peclcmd.php ${PHP_LIBDIR}/pearcmd.php \
	${PHP_LIBDIR}/.channels ${PHP_LIBDIR}/.channels/.alias \
	${PHP_LIBDIR}/.registry ${PHP_LIBDIR}/Archive/Tar.php \
	${PHP_LIBDIR}/Console/Getopt.php ${PHP_LIBDIR}/OS/Guess.php \
	${PHP_LIBDIR}/data/PEAR \
	${sysconfdir}/php${PHPVER}/pear.conf"
FILES:${PN}-dev = " \
	${includedir}/php${PHPVER} \
	${PHP_LIBDIR}/build \
	${bindir}/phpize${PHPVER} \
	${bindir}/php-config${PHPVER} \
	${bindir}/phpdbg${PHPVER} \
	${datadir}/pear${PHPVER}/.depdb \
	${datadir}/pear${PHPVER}/.depdblock \
	${datadir}/pear${PHPVER}/.filemap \
	${datadir}/pear${PHPVER}/.lock \
	${datadir}/pear${PHPVER} \
	${PHP_LIBDIR}/test \
"
FILES:${PN}-staticdev += "${libdir}/php${PHPVER}/modules/*.a"
FILES:${PN} += " \
	${PHP_LIBDIR} \
	${bindir} \
	${sysconfdir}/php${PHPVER}/php.d \
	${localstatedir}/lib/php${PHPVER}/session/ \
"
FILES:${PN}:append:class-native = " \
	${sysconfdir}/php${PHPVER}/php.ini \
"

SUMMARY:${PN}-modphp = "PHP module for the Apache HTTP server"
FILES:${PN}-modphp = "${libdir}/apache2"

MODPHP_OLDPACKAGE = "${@bb.utils.contains('PACKAGECONFIG', 'apache2', 'modphp', '', d)}"
RPROVIDES:${PN}-modphp = "${MODPHP_OLDPACKAGE}"
RREPLACES:${PN}-modphp = "${MODPHP_OLDPACKAGE}"
RCONFLICTS:${PN}-modphp = "${MODPHP_OLDPACKAGE}"

do_install:append:class-native() {
	create_wrapper ${D}${bindir}/php${PHPVER} \
		PHP_PEAR_SYSCONF_DIR=${sysconfdir}/php${PHPVER}
}


# Fails to build with thumb-1 (qemuarm)
# | {standard input}: Assembler messages:
# | {standard input}:3719: Error: selected processor does not support Thumb mode `smull r0,r2,r9,r3'
# | {standard input}:3720: Error: unshifted register required -- `sub r2,r2,r0,asr#31'
# | {standard input}:3796: Error: selected processor does not support Thumb mode `smull r0,r2,r3,r3'
# | {standard input}:3797: Error: unshifted register required -- `sub r2,r2,r0,asr#31'
# | make: *** [ext/standard/math.lo] Error 1
ARM_INSTRUCTION_SET = "arm"
