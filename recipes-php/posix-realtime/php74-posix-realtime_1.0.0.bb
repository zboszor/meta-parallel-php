DESCRIPTION = "PHP POSIX realtime module"
HOMEPAGE = "https://github.com/adrianguenter/php-posix-realtime/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=730589c4d9e9ebcd342f99bfe073dd41"

# This is the GitHub pre-release version
PR0 = "beta.1"
# This is the package release string.
# Only increase the last number if we're still using betas
PR = "r0.${PR0}.10"

PHPVERSION = "74"

inherit parallel-php-module

FILESEXTRAPATHS:prepend = "${FILE_DIRNAME}/php-posix-realtime:"

SRCREV = "d214e3c4dc8597145512fa94d0f850e1d4547906"

SRC_URI = " \
			git://github.com/adrianguenter/php-posix-realtime.git;protocol=https;nobranch=1 \
			file://posixrealtime-link-to-libm.patch \
			file://fix-build-with-php7.patch \
			file://20-posixrealtime.ini \
		"

S = "${WORKDIR}/git"
