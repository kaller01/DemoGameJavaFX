package game;

/**
 * Interface for players which have SpecialAbility
 */
public interface SpecialAbility {
    void shootCharge();
    void updateCharge(double deltaTime);
}
