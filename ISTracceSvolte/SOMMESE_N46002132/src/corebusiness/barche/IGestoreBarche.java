/**
 * 
 */
package corebusiness.barche;

/**
 * @author Raffaele
 *
 */
public interface IGestoreBarche {
	/**
     * @param B
     */
    public void InserisciBarca(Barca B);

    /**
     * @param B
     */
    public void ModificaBarca(Barca B);

    /**
     * @param B
     */
    public void RimuoviBarca(Barca B);
}
