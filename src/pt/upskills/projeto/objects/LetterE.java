package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class LetterE implements ImageTile {

    //atributos
    private Position position;

    //construtor
    public LetterE(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "LetterE";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

