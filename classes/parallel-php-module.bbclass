#
# Make it easy for PHP modules to use a specific PHP version.
# This class should be listed last for PHP module recipes
#

inherit autotools-brokensep binconfig pkgconfig gettext python3native

PHPVERSION ??= "74"

BBCLASSEXTEND = "native"

FILES:${PN} += " \
			${sysconfdir}/php${PHPVERSION}/php.d/*.ini \
			${libdir}/php${PHPVERSION}/modules/*.so \
		"

CONFFILES:${PN} += " \
			${sysconfdir}/php${PHPVERSION}/php.d/*.ini \
		"

FILES:${PN}-dbg += "${libdir}/php${PHPVERSION}/modules/.debug"

DEPENDS:append = " coreutils-native php${PHPVERSION}-native file-replacement-native "
DEPENDS:append:class-target = " php${PHPVERSION} "

EXTRANATIVEPATH:append = " file-native"

do_configure () {
	phpize${PHPVERSION}
	if [ -e ${S}/configure ]; then
		PYTHONPATH="${STAGING_DIR_NATIVE}${PYTHON_SITEPACKAGES_DIR}/.." oe_runconf php_cv_cc_rpath=no hardcode_libdir_flag_spec=''
	else
		bbnote "nothing to configure"
	fi
}

do_compile:append () {
	chrpath -d ${B}/modules/*.so
}

do_install  () {
	# Override do_install from e.g. autotools.bbclass
	:
}

phpmod_install_target () {
	cd ${B}

	oe_runmake install INSTALL_ROOT=${D}

	install -d -m0755 ${D}${sysconfdir}/php${PHPVERSION}/php.d

	for conf in ${UNPACKDIR}/*.ini ; do
		if [ -f "$conf" ]; then
			install -m0644 "$conf" "${D}${sysconfdir}/php${PHPVERSION}/php.d/"
		fi
	done

	install -d -m0755 ${D}${STAGING_DIR_TARGET}
	for i in ${D}${STAGING_DIR_TARGET}/../../../*/*/*${includedir} ; do
		if [ -d "$i" ]; then
			dir=$(realpath $i)
			if [ -d "$dir/php${PHPVERSION}" ]; then
				install -d -m0755 ${D}${includedir}

				mv "$dir/php${PHPVERSION}" ${D}${includedir}

				rmdir -p $dir || echo deleted $dir, stopped at the first non-empty dir
			fi
		fi
	done
	rmdir -p ${D}${STAGING_DIR_TARGET} || echo deleted ${D}${STAGING_DIR_TARGET}, stopped at the first non-empty dir
}

phpmod_install_native () {
	cd ${B}

	oe_runmake install INSTALL_ROOT=${D}

	install -d -m0755 ${D}${sysconfdir}/php${PHPVERSION}/php.d

	for conf in ${UNPACKDIR}/*.ini ; do
		if [ -f "$conf" ]; then
			install -m0644 "$conf" "${D}${sysconfdir}/php${PHPVERSION}/php.d/"
		fi
	done
}

def php_postinstallfunc(d):
    import bb, re
    pn = d.getVar('PN', True)
    pnregexp = 'php%s-.*-native' % d.getVar('PHPVERSION', True)
    if re.match(pnregexp, pn):
        return "phpmod_install_native"
    pnregexp = 'php%s-.*' % d.getVar('PHPVERSION', True)
    if re.match(pnregexp, pn):
        return "phpmod_install_target"
    bb.error('Invalid package name: %s' % pn)

do_install[postfuncs] += "${@php_postinstallfunc(d)}"

phpmod_copy_sysroot() {
	if [ -d "${SYSROOT_DESTDIR}${libdir}/php${PHPVER}" ]; then
		install -d -m0755 ${STAGING_LIBDIR}
		cp -arf ${SYSROOT_DESTDIR}${libdir}/php${PHPVER} ${STAGING_LIBDIR}/
	fi

	if [ -d "${SYSROOT_DESTDIR}${includedir}/php${PHPVER}" ]; then
		install -d -m0755 ${STAGING_INCDIR}
		cp -arf ${SYSROOT_DESTDIR}${includedir}/php${PHPVER} ${STAGING_INCDIR}/
	fi
}

phpmod_copy_sysroot:class-native() {
	if [ -d "${SYSROOT_DESTDIR}${STAGING_ETCDIR_NATIVE}/php${PHPVER}" ]; then
		install -d -m0755 ${STAGING_ETCDIR_NATIVE}
		cp -arf ${SYSROOT_DESTDIR}${STAGING_ETCDIR_NATIVE}/php${PHPVER} ${STAGING_ETCDIR_NATIVE}/
	fi

	if [ -d "${SYSROOT_DESTDIR}${STAGING_LIBDIR_NATIVE}/php${PHPVER}" ]; then
		install -d -m0755 ${STAGING_LIBDIR_NATIVE}
		cp -arf ${SYSROOT_DESTDIR}${STAGING_LIBDIR_NATIVE}/php${PHPVER} ${STAGING_LIBDIR_NATIVE}/
	fi

	if [ -d "${SYSROOT_DESTDIR}${STAGING_INCDIR_NATIVE}/php${PHPVER}" ]; then
		install -d -m0755 ${STAGING_INCDIR_NATIVE}
		cp -arf ${SYSROOT_DESTDIR}${STAGING_INCDIR_NATIVE}/php${PHPVER} ${STAGING_INCDIR_NATIVE}/
	fi
}

do_populate_sysroot[postfuncs] += " phpmod_copy_sysroot "
