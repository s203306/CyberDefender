package pl.cyberdefender.model.network;

/** Rodzaj urządzenia w symulowanej sieci firmowej. */
public enum DeviceType {
    /** Router łączący sieć firmy z internetem */
    ROUTER,
    /** PC pracownika */
    WORKSTATION,
    /** Serwer firmowy */
    SERVER,
    /** Baza danych z ważnymi danymi */
    DATABASE
}
