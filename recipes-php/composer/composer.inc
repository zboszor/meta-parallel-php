DESCRIPTION = "Composer"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "git-native php${PHPVERSION}-native php${PHPVERSION}-symlinks-native"

inherit allarch

PV = "2.8.9"
SRC_URI[sha256sum] = "8e8829ec2b97fcb05158236984bc252bef902e7b8ff65555a1eeda4ec13fb82b"

# We have just pull the pre-built composer
SRC_URI = "https://github.com/composer/composer/releases/download/${PV}/composer.phar;downloadfilename=composer-${PV}.phar"

do_install () {
	install -d -m755 ${D}${bindir}
	install -m755 ${WORKDIR}/composer-${PV}.phar ${D}${bindir}/composer
}

BBCLASSEXTEND = "native"
