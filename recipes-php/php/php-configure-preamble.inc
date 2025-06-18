do_configure:prepend () {
	libtoolize --force --copy
	cat $(aclocal --print-ac-dir)/{libtool,ltoptions,ltsugar,ltversion,lt~obsolete}.m4 >build/libtool.m4
}
