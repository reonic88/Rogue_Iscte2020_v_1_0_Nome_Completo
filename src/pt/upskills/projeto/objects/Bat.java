package pt.upskills.projeto.objects;

import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.Observable;
import java.util.Random;

public class Bat extends Monster {

    //construtor
    public Bat(Position position) {
        super("Bat",4,1,false,position);
    }

    //metodos
    @Override
    public void update(Observable o, Object arg) {
        Position newPosition = null;
        Random random = new Random();
        int posRand = random.nextInt(2);
        switch (posRand) {
            case 0:
                newPosition = getPosition().plus(Direction.RIGHT.asVector());
                //System.out.println("right");
                break;
            case 1:
                newPosition = getPosition().plus(Direction.LEFT.asVector());
                //System.out.println("left");
                break;
        }
        moveMonster(newPosition);
    }
}