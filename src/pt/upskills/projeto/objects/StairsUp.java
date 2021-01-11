package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class StairsUp implements ImageTile {

    //atributos
    private Position position;

    //construtor
    public StairsUp(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "StairsUp";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
