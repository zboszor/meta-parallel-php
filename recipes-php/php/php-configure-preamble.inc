do_configure:prepend () {
	libtoolize --force --copy
	rm -f build/libtool.m4
	for ltpart in libtool ltoptions ltsugar ltversion lt~obsolete ; do
		cat $(aclocal --print-ac-dir)/$ltpart >>build/libtool.m4
	done
}
