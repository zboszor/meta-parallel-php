DESCRIPTION = "Composer"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "git-native php${PHPVERSION}-native php${PHPVERSION}-symlinks-native"

inherit allarch

PV = "2.10.3"
SRC_URI[sha256sum] = "7a2d379d5b8ffdaa028580ef26494c36d2feef4b178d3dd1473a4dbc5e17c8d6"

# We have just pull the pre-built composer
SRC_URI = "https://github.com/composer/composer/releases/download/${PV}/composer.phar;downloadfilename=composer-${PV}.phar"

do_install () {
	install -d -m755 ${D}${bindir}
	install -m755 ${WORKDIR}/composer-${PV}.phar ${D}${bindir}/composer
}

BBCLASSEXTEND = "native"
