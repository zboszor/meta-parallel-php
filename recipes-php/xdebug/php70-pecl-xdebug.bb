require php-pecl-xdebug.inc

PHPVERSION = "70"

PV = "2.8.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d94a330d34ee6edc2638450736f119"
SRC_URI[sha256sum] = "838be3974e2555bbbd796eb57c34840659815f23079417b5042e8b534fe61520"
SRC_URI += "file://fix-prototype.patch"
