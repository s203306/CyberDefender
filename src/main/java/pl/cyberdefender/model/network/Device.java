package pl.cyberdefender.model.network;

/**
 * Urządzenie w symulowanej sieci firmowej.
 * <p>
 * Przechowuje trzy rodzaje informacji:
 * <ul>
 *   <li><b>Prawdę</b> – rzeczywisty stan znany tylko silnikowi gry (infekcja, przejęte konto),</li>
 *   <li><b>Wiedzę</b> – status widoczny dla gracza ({@link KnownStatus}),</li>
 *   <li><b>Znaczniki</b> – skutki działań gracza (izolacja, naprawa, blokada konta).</li>
 * </ul>
 * Klasa pilnuje tylko spójności własnego stanu; reguły dostępności akcji
 * należą do akcji gracza.
 */
public class Device {

    private final String id;
    private final String name;
    private final DeviceType type;
    private final boolean critical;
    private final String employee;

    private boolean infected;
    private boolean accountCompromised;

    private KnownStatus knownStatus = KnownStatus.UNKNOWN;

    private boolean isolated;
    private boolean inRepair;
    private boolean accountBlocked;

    /**
     * Tworzy urządzenie w stanie początkowym: czyste, nieodizolowane,
     * o statusie {@link KnownStatus#UNKNOWN}.
     *
     * @param id       unikalny identyfikator urządzenia, np. {@code "PC2"}
     * @param name     nazwa wyświetlana graczowi
     * @param type     rodzaj urządzenia
     * @param critical czy urządzenie jest krytyczne dla firmy
     * @param employee pracownik przypisany do stacji roboczej lub {@code null},
     *                 jeśli urządzenie nie ma konta
     * @throws IllegalArgumentException jeśli {@code id} lub {@code name} jest
     *         {@code null} albo puste, {@code type} jest {@code null} lub
     *         {@code employee} jest pustym tekstem
     */
    public Device(String id, String name, DeviceType type, boolean critical, String employee) {
        this.id = requireNotBlank(id, "id");
        this.name = requireNotBlank(name, "name");
        if (type == null) {
            throw new IllegalArgumentException("type nie może być null");
        }
        if (employee != null && employee.isBlank()) {
            throw new IllegalArgumentException("employee nie może być pusty (użyj null dla braku konta)");
        }
        this.type = type;
        this.critical = critical;
        this.employee = employee;
    }

    private static String requireNotBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " nie może być null ani puste");
        }
        return value;
    }

    // ---------- dane stałe ----------

    /** @return unikalny identyfikator urządzenia */
    public String getId() {
        return id;
    }

    /** @return nazwa wyświetlana graczowi */
    public String getName() {
        return name;
    }

    /** @return rodzaj urządzenia */
    public DeviceType getType() {
        return type;
    }

    /** @return {@code true}, jeśli urządzenie jest krytyczne dla firmy */
    public boolean isCritical() {
        return critical;
    }

    /** @return pracownik przypisany do urządzenia lub {@code null}, jeśli go nie ma */
    public String getEmployee() {
        return employee;
    }

    /** @return {@code true}, jeśli do urządzenia jest przypisany pracownik z kontem */
    public boolean hasAccount() {
        return employee != null;
    }

    // ---------- prawda ----------

    /** @return {@code true}, jeśli urządzenie jest naprawdę zainfekowane */
    public boolean isInfected() {
        return infected;
    }

    /** Infekuje urządzenie. */
    public void infect() {
        infected = true;
    }

    /** Usuwa infekcję z urządzenia. */
    public void cleanse() {
        infected = false;
    }

    /** @return {@code true}, jeśli konto pracownika zostało przejęte przez atakującego */
    public boolean isAccountCompromised() {
        return accountCompromised;
    }

    /**
     * Oznacza konto pracownika jako przejęte.
     *
     * @throws IllegalStateException jeśli urządzenie nie ma konta
     */
    public void compromiseAccount() {
        requireAccount();
        accountCompromised = true;
    }

    // ---------- wiedza gracza ----------

    /** @return status urządzenia znany graczowi */
    public KnownStatus getKnownStatus() {
        return knownStatus;
    }

    /**
     * Ustawia status urządzenia znany graczowi.
     *
     * @param knownStatus nowy status
     * @throws IllegalArgumentException jeśli {@code knownStatus} jest {@code null}
     */
    public void setKnownStatus(KnownStatus knownStatus) {
        if (knownStatus == null) {
            throw new IllegalArgumentException("knownStatus nie może być null");
        }
        this.knownStatus = knownStatus;
    }

    // ---------- znaczniki ----------

    /** @return {@code true}, jeśli urządzenie jest odizolowane od sieci */
    public boolean isIsolated() {
        return isolated;
    }

    /**
     * Ustawia izolację urządzenia.
     *
     * @param isolated {@code true}, aby odizolować urządzenie od sieci
     */
    public void setIsolated(boolean isolated) {
        this.isolated = isolated;
    }

    /** @return {@code true}, jeśli urządzenie jest w trakcie naprawy */
    public boolean isInRepair() {
        return inRepair;
    }

    /**
     * Ustawia stan naprawy urządzenia.
     *
     * @param inRepair {@code true}, jeśli urządzenie jest naprawiane
     */
    public void setInRepair(boolean inRepair) {
        this.inRepair = inRepair;
    }

    /** @return {@code true}, jeśli konto pracownika jest zablokowane */
    public boolean isAccountBlocked() {
        return accountBlocked;
    }

    /**
     * Blokuje konto pracownika przypisanego do urządzenia.
     *
     * @throws IllegalStateException jeśli urządzenie nie ma konta
     */
    public void blockAccount() {
        requireAccount();
        accountBlocked = true;
    }

    private void requireAccount() {
        if (!hasAccount()) {
            throw new IllegalStateException("Urządzenie " + id + " nie ma konta");
        }
    }

    @Override
    public String toString() {
        return "Device[" + id + ", " + type + "]";
    }
}