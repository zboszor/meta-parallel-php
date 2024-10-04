DESCRIPTION = "PHP POSIX realtime module"
HOMEPAGE = "https://github.com/adrianguenter/php-posix-realtime/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=730589c4d9e9ebcd342f99bfe073dd41"

PR = "r1"

PHPVERSION = "74"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-posix-realtime:"

SRCREV = "657dd0213efcfbedd02786bb5355b756a3a85326"

SRC_URI = " \
	git://github.com/adrianguenter/php-posix-realtime.git;protocol=https;nobranch=1 \
	file://20-posixrealtime.ini \
"

S = "${UNPACKDIR}/git"
