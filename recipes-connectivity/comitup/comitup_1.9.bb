inherit setuptools3 systemd

SUMMARY = "Comitup"
DESCRIPTION = "Wifi booststrapping using wifi"

SECTION = "devel/scripts"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"


RDEPENDS_${PN} = "networkmanager iw avahi-daemon avahi-autoipd iputils-arping wpa-supplicant-cli \
                  python3-jinja2 python3-pygobject python3-dbus python3-networkmanager python3-pyinotify python3-flask coreutils" 

SRC_URI = "git://github.com/davesteele/comitup \
           file://web_path_hack.patch \
           file://comitup.service \
           file://comitup-web.service "
SRCREV = "b0bf19a13afa5ae9df38693ea66c2c95d9b08ab1"


FILES_${PN} += "/lib/ /sbin/"

SYSTEMD_SERVICE_${PN} = " comitup.service"

do_install_append(){
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/comitup.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/comitup-web.service ${D}${systemd_unitdir}/system
    
    #Hack for different location of wpa_cli  
    install -d ${D}${base_sbindir}
    cd ${D}${base_sbindir}
    ln -s ../usr/sbin/wpa_cli wpa_cli
}


S = "${WORKDIR}/git/"
