package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Wall implements ImageTile {

    //atributos
    private Position position;

    //construtor
    public Wall(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "Wall";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
