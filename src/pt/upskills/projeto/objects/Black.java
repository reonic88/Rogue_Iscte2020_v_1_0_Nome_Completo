package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Black implements ImageTile {

    //atributos
    private Position position;

    //cosntrutor
    public Black(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "Black";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
