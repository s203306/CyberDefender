package pl.cyberdefender.model.network;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    private static Device workstation() {
        return new Device("PC2", "PC2", DeviceType.WORKSTATION, false, "A. Nowak");
    }

    private static Device server() {
        return new Device("SRV", "Serwer", DeviceType.SERVER, true, null);
    }

    @Test
    void newDeviceStartsInInitialState() {
        Device device = workstation();

        assertEquals(KnownStatus.UNKNOWN, device.getKnownStatus());
        assertFalse(device.isInfected());
        assertFalse(device.isAccountCompromised());
        assertFalse(device.isIsolated());
        assertFalse(device.isInRepair());
        assertFalse(device.isAccountBlocked());
    }

    @Test
    void infectAndCleanseChangeInfection() {
        Device device = workstation();

        device.infect();
        assertTrue(device.isInfected());

        device.cleanse();
        assertFalse(device.isInfected());
    }

    @Test
    void workstationWithEmployeeHasAccountAndCanBeBlocked() {
        Device device = workstation();

        assertTrue(device.hasAccount());
        device.blockAccount();
        assertTrue(device.isAccountBlocked());
    }

    @Test
    void blockAccountOnServerWithoutEmployeeThrows() {
        Device device = server();

        assertFalse(device.hasAccount());
        assertThrows(IllegalStateException.class, device::blockAccount);
    }

    @Test
    void compromiseAccountOnDeviceWithoutAccountThrows() {
        assertThrows(IllegalStateException.class, server()::compromiseAccount);
    }

    @Test
    void constructorRejectsBlankId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Device("  ", "PC1", DeviceType.WORKSTATION, false, null));
    }

    @Test
    void constructorRejectsNullType() {
        assertThrows(IllegalArgumentException.class,
                () -> new Device("PC1", "PC1", null, false, null));
    }

    @Test
    void setKnownStatusRejectsNull() {
        Device device = workstation();

        assertThrows(IllegalArgumentException.class, () -> device.setKnownStatus(null));
        assertEquals(KnownStatus.UNKNOWN, device.getKnownStatus());
    }

    @Test
    void constructorRejectsBlankEmployee() {
        assertThrows(IllegalArgumentException.class,
                () -> new Device("PC1", "PC1", DeviceType.WORKSTATION, false, "  "));
    }

    @Test
    void deviceWithoutEmployeeHasNoAccount() {
        Device device = new Device("SRV", "Serwer", DeviceType.SERVER, true, null);

        assertFalse(device.hasAccount());
    }
}