PHPVERSION ??= "74"

DEPENDS:append = " php${PHPVERSION}-native "

export PHPRC="${STAGING_ETCDIR_NATIVE}/php${PHPVERSION}/php.ini"
export PHP_INI_SCAN_DIR="${STAGING_ETCDIR_NATIVE}/php${PHPVERSION}/php.d"

php_native_set_ext_dir() {
	if [ -f ${PHPRC} ]; then
		echo "extension_dir = \"${STAGING_LIBDIR_NATIVE}/php${PHPVERSION}/modules\"" >>${PHPRC}
	fi
}

do_prepare_recipe_sysroot[postfuncs] += " php_native_set_ext_dir "
