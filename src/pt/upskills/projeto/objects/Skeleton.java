package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Skeleton extends Monster {

    //construtor
    public Skeleton(Position position) {
        super("Skeleton", 2,1,true,position);
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
                //System.out.println("up");
                break;
            case 1:
                newPosition = getPosition().plus(Direction.RIGHT.asVector());
                //System.out.println("right");
                break;
            case 2:
                newPosition = getPosition().plus(Direction.DOWN.asVector());
                //System.out.println("down");
                break;
            case 3:
                newPosition = getPosition().plus(Direction.LEFT.asVector());
                //System.out.println("left");
                break;
        }
        moveMonster(newPosition);

        moveMonster(chasePlayer());

    }
}

