do_configure:prepend () {
	libtoolize --force --copy
	rm -f build/libtool.m4
	for ltpart in libtool.m4 ltoptions.m4 ltsugar.m4 ltversion.m4 lt~obsolete.m4 ; do
		cat $(aclocal --print-ac-dir)/$ltpart >>build/libtool.m4
	done
}
