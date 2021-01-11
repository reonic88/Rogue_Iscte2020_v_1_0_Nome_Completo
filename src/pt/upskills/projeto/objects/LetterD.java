package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class LetterD implements ImageTile {

    //atributos
    private Position position;

    //construtor
    public LetterD(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return "LetterD";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}