package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.game.FireBallThread;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Hero extends MovableObject {

    //atributos
    private Direction lastDir;

    //construtor
    public Hero(Position position) {
        super("Hero", position, 8);
        setAttack(1);
    }

    //metodos

    /**
     * This method is called whenever the observed object is changed. This function is called when an
     * interaction with the graphic component occurs {{@link ImageMatrixGUI}}
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Integer keyCode = (Integer) arg;
        Position newPosition = null;

        if (keyCode == KeyEvent.VK_DOWN) {
            if (getPosition().getY() <= 8) {
                newPosition = getPosition().plus(Direction.DOWN.asVector());
                //System.out.println("posX" + newPosition.getX() + "PosY" +  newPosition.getY());
                lastDir = Direction.DOWN;
            }
        }

        if (keyCode == KeyEvent.VK_UP) {
            if (getPosition().getY() >= 1) {
                newPosition = getPosition().plus(Direction.UP.asVector());
                lastDir = Direction.UP;
                //System.out.println("posX" + newPosition.getX() + "PosY" +  newPosition.getY());
            }
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            if (getPosition().getX() >= 1) {
                newPosition = getPosition().plus(Direction.LEFT.asVector());
                lastDir = Direction.LEFT;
                //System.out.println("posX" + newPosition.getX() + "PosY" +  newPosition.getY());
            }
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            if (getPosition().getX() <= 8) {
                newPosition = getPosition().plus(Direction.RIGHT.asVector());
                lastDir = Direction.RIGHT;
                //System.out.println("posX" + newPosition.getX() + "PosY" +  newPosition.getY());
            }
        }

        if (keyCode == KeyEvent.VK_SPACE) {
            FireBall fireBall = new FireBall(getPosition());
            FireBallThread fireBallthread = new FireBallThread(lastDir, fireBall);
            fireBallthread.start();

            Engine.getCurrentLevel().add(fireBall);
            ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
            gui.addImage(fireBall);
        }

        for (ImageTile tile : Engine.getCurrentLevel()) {
            if (tile.getPosition().equals(newPosition)) {

                if (tile instanceof StairsUp) {
                    Engine.goToNextLevel();
                }
                if (tile instanceof StairsDown) {
                    Engine.goToLowerLevel();
                }
                if (tile instanceof Sword) {
                    System.out.println("Before Excalibur power up damage : " + this.getAttack());

                    setAttack( ((Sword)tile).getIncreaseAttack());
                    System.out.println("I got the Excalibur: " + this.getAttack());
                    Engine.statusTiles.add(new Sword(new Position(7, 0)));                           //?????

                    Engine.hero = this;
                    Engine.getCurrentLevel().remove(tile);
                    Engine.gui.removeImage(tile);
                    // Engine.gui.deleteObserver((MovableObject)tile);
                    this.setPosition(newPosition);
                    return;
                }
                if (tile instanceof Monster) {
                    damageDone(((Monster) tile), getAttack());
                }
                if (tile instanceof Wall || tile instanceof MovableObject) {
                    return;
                }

                if (tile instanceof GoodMeat) {
                    System.out.println("Good Steak!");
                    Engine.getCurrentLevel().remove(tile);
                    Engine.gui.removeImage(tile);
                    setHealth(8);
                    System.out.println("life maxed to " + getHealth());
                    this.setPosition(newPosition);
                    Engine.updateStatus();
                    return;
                }
            }
        }

        if (newPosition != null) {
            this.setPosition(newPosition);
        }
    }

    public void damageTaken(int damage, int health) {

    }

    @Override
    public void damage(int amount) {
        super.damage(amount);
        Engine.updateStatus();
    }

    public void damageDone(Monster monster, int damage) {
        if (damage > 0) {
            monster.damage(damage);

        }
    }
}


