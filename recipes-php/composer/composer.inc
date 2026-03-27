DESCRIPTION = "Composer"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "git-native php${PHPVERSION}-native php${PHPVERSION}-symlinks-native"

inherit allarch

PV = "2.8.12"
SRC_URI[sha256sum] = "f446ea719708bb85fcbf4ef18def5d0515f1f9b4d703f6d820c9c1656e10a2f2"

# We have just pull the pre-built composer
SRC_URI = "https://github.com/composer/composer/releases/download/${PV}/composer.phar;downloadfilename=composer-${PV}.phar"

do_install () {
	install -d -m755 ${D}${bindir}
	install -m755 ${WORKDIR}/composer-${PV}.phar ${D}${bindir}/composer
}

BBCLASSEXTEND = "native"
