package pt.upskills.projeto.objects;

import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.Observable;
import java.util.Random;

public class Thief extends Monster{

    //construtor
    public Thief(Position position) {
        super("Thief", 17,2,false,position);
    }

    //metodos
    @Override
    public void update(Observable o, Object arg) {
        Position newPosition = null;
        Random random = new Random();
        int posRand = random.nextInt(4);
        switch (posRand) {
            case 0:
                newPosition = getPosition().plus(Direction.UP.asVector());
                break;
            case 1:
                newPosition = getPosition().plus(Direction.RIGHT.asVector());
                break;
            case 2:
                newPosition = getPosition().plus(Direction.DOWN.asVector());
                break;
            case 3:
                newPosition = getPosition().plus(Direction.LEFT.asVector());
                break;
        }
        moveMonster(newPosition);

        moveMonster(chasePlayer());

    }
}

