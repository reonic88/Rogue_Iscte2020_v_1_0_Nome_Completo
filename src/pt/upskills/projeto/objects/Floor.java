package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Floor implements ImageTile {

    //atributos
    private Position position;

    //construtor
    public Floor(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "Floor";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
