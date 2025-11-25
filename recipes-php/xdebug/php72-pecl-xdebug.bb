require php-pecl-xdebug.inc

PHPVERSION = "72"

PV = "3.1.6"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d94a330d34ee6edc2638450736f119"
SRC_URI[sha256sum] = "554eca0b4d5b7b93cb2258fab0b0bd84cc8721e74322a2255c14e137cbcad5d2"
SRC_URI += "file://fix-prototype.patch"
