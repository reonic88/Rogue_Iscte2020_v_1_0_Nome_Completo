package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class LetterA implements ImageTile {

    //atributos
    private Position position;

    //construtor
    public LetterA(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "LetterA";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
