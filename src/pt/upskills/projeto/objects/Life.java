package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Life implements ImageTile {

    //atributos
    private LifeType lifeType;
    private Position position;

    //construtor
    public Life(LifeType lifeType, Position position) {
        this.lifeType = lifeType;
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        if (lifeType == LifeType.RED) {
            return "Red";
        }

        if (lifeType == LifeType.GREEN_RED) {
            return "RedGreen";
        }

        return "Green";

    }

    @Override
    public Position getPosition() {
        return position;
    }

    public enum LifeType {
        GREEN, GREEN_RED, RED
    }
}
