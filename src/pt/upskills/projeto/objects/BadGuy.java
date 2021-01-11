package pt.upskills.projeto.objects;

import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;

import java.util.Observable;
import java.util.Random;

public class BadGuy extends Monster {

    //construtor
    public BadGuy(Position position) {
        super("BadGuy",9,1,false ,position);
    }

    //metodos
    @Override
    public void update(Observable o, Object arg) {
        Position newPosition = null;
        Random random = new Random();
        int posRand = random.nextInt(4);
        switch (posRand) {
            case 0:
                newPosition = getPosition().plus(Direction.UP.asVector2());
                //System.out.println("up");
                break;
            case 1:
                newPosition = getPosition().plus(Direction.RIGHT.asVector2());
                //System.out.println("right");
                break;
            case 2:
                newPosition = getPosition().plus(Direction.DOWN.asVector2());
                //System.out.println("down");
                break;
            case 3:
                newPosition = getPosition().plus(Direction.LEFT.asVector2());
                //System.out.println("left");
                break;
        }
        moveMonster(newPosition);

    }
}