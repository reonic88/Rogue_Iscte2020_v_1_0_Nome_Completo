package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Sword implements ImageTile {

    //atributos
    private Position position;
    private int increaseAttack;

    //construtor
    public Sword(Position position) {
        this.position = position;
        increaseAttack = 3;
    }

    //metodos
    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    //se no image tile já estiver algum com este nome fica com override
    public int getIncreaseAttack() {
        return increaseAttack;
    }
}

