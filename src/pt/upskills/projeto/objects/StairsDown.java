package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class StairsDown implements ImageTile {

    //atributos
    private Position position;

    //construtor
    public StairsDown(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "StairsDown";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

