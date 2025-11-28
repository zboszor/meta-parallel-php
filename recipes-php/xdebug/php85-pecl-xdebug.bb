require php-pecl-xdebug.inc

PHPVERSION = "85"

SRC_URI:remove = "http://pecl.php.net/get/xdebug-${PV}.tgz"
SRC_URI:prepend = "git://github.com/xdebug/xdebug.git;protocol=https;branch=master "

PV = "3.5.0alpha3"
SRCREV = "eb6378f2feb65e6f26ab172cb42d478aa4b5e7f1"
S = "${UNPACKDIR}/git"
