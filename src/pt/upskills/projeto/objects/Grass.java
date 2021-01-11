package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Grass implements ImageTile {

    //atributos
    private Position position;

    //construtor
    public Grass(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "Grass";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}