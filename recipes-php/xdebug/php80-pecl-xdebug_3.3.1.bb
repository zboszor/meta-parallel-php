DESCRIPTION = "Provides functions for function traces and profiling"
HOMEPAGE = "http://pecl.php.net/package/xdebug"
SECTION = "console/network"
LICENSE = "Xdebug"
LIC_FILES_CHKSUM = "file://LICENSE;md5=afd6ce4aa04fdc346e5b3c6e634bd75c"

# This is only for pre-release versions, i.e. X.Y.0alphaZ.
# In other cases, wait for the proper point release (X.Y.1 and up)
BETALEVEL = ""

def xdebug_pr(d):
    import re
    p = re.compile('\.')
    beta = d.getVar('BETALEVEL', 1)
    pv = d.getVar('PV', 1)
    basepr = d.getVar('BASEPR', 1)
    pr = p.split(pv)
    if pr[len(pr) - 1] == '0':
        if beta == '':
            return str(int(basepr) + 1)
        else:
            return basepr + '.' + beta
    else:
        return basepr

BASEPR = "0"
PR = "r${@xdebug_pr(d)}"

PHPVERSION = "80"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-pecl-xdebug:"

SRC_URI = " \
			http://pecl.php.net/get/xdebug-${PV}${BETALEVEL}.tgz \
			file://02-xdebug.ini \
		"

SRC_URI[sha256sum] = "4eb4ee270bbcc5f14195c38f6ee58580e007cf4886ce32e11430318ab5bc2315"

S = "${WORKDIR}/xdebug-${PV}${BETALEVEL}"
