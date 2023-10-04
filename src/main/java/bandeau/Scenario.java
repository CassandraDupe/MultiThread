package bandeau;
import java.util.List;
import java.util.LinkedList;

/**
 * Classe utilitaire pour représenter la classe-association UML
 */
class ScenarioElement {

    Effect effect;
    int repeats;

    ScenarioElement(Effect e, int r) {
        effect = e;
        repeats = r;
    }
}
/**
 * Un scenario mémorise une liste d'effets, et le nombre de repetitions pour chaque effet
 * Un scenario sait se jouer sur un bandeau.
 */
public class Scenario {

    private final List<ScenarioElement> myElements = new LinkedList<>();

    /**
     * Ajouter un effect au scenario.
     *
     * @param e l'effet à ajouter
     * @param repeats le nombre de répétitions pour cet effet
     */
    public void addEffect(Effect e, int repeats) {
        myElements.add(new ScenarioElement(e, repeats));
    }

    /**
     * Jouer ce scenario sur un bandeau
     *
     * @param b le bandeau ou s'afficher.
     */
    public void playOn(SousBandeau b){
        //On crée un thread
        Thread t = new Thread(
                () -> {
                    //On bloque le bandeau au lancement du scénario
                    b.verrouille();
                    for (ScenarioElement element : myElements) {
                        for (int repeats = 0; repeats < element.repeats; repeats++) {
                            element.effect.playOn(b);
                        }
                    }
                    //Apres avoir fini le scenario on deverrouille le bandeau
                    b.deverrouille();
                }
        );
        t.start();
    }
}
