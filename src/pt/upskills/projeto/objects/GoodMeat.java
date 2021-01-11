package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GoodMeat implements ImageTile {

    //atributos
    private Position position;
    private int increaseHealth;

    //construtor
    public GoodMeat(Position position) {
        this.position = position;
        increaseHealth = 8;
    }

    //metodos
    @Override
    public String getName() {
        return "GoodMeat";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
