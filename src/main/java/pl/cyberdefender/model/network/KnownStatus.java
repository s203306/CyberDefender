package pl.cyberdefender.model.network;

/**
 * Status urządzenia znany graczowi.
 * <p>
 * Opisuje wiedzę gracza, a nie prawdziwy stan urządzenia: zainfekowane
 * urządzenie może mieć status {@link #UNKNOWN}, dopóki gracz go nie sprawdzi.
 */
public enum KnownStatus {
    /** Nic nie sprawdzono, nie ma alertów */
    UNKNOWN,
    /** Pojawił się alert, logi wykazują anomalię */
    SUSPICIOUS,
    /** Skan nie wykrył zagrożenia */
    CLEAN,
    /** Skan potwierdził infekcję */
    INFECTED
}
