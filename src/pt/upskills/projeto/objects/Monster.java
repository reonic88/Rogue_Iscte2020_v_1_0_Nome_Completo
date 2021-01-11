package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.ArrayList;
import java.util.logging.Level;

public abstract class Monster extends MovableObject {

    //atributos
    private int attack;
    private boolean chasePlayer;
    private String name;

    //construtor
    public Monster (String name, int hp, int attack, boolean chasePlayer, Position position) {
        super(name, position, hp);
        this.attack = attack;
        this.chasePlayer = chasePlayer;
    }

    //metodos
    public void moveMonster(Position newPosition)
    {
        for (ImageTile tile : Engine.getCurrentLevel()) {
            if (tile.getPosition().equals(newPosition)) {
                if (tile instanceof Hero) {
                    ((Hero) tile).damage(1);
                }
                if (tile instanceof Wall || tile instanceof MovableObject || tile instanceof StairsUp || tile instanceof StairsDown || tile instanceof Sword) {
                    return;
                }
            }
        }
        this.setPosition(newPosition);
    }

    public Position chasePlayer() {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        int heroXPosition = Engine.hero.getPosition().getX();
        int heroYPosition = Engine.hero.getPosition().getY();
        if (x < heroXPosition) {
            x++;
        } else if (x > heroXPosition) {
            x--;
        }
        if (y < heroYPosition) {
            y++;
        } else if (y > heroYPosition) {
            y--;
        }
        return new Position(x, y);
    }

    @Override
    public void damage(int damage) {
        super.damage(damage);
        System.out.println("Monster was hit!");
        if(getHealth() <= 0) {
            //die!
            System.out.println("Monster died");
            Engine.getCurrentLevel().remove(this);
            Engine.gui.removeImage(this);
            Engine.gui.deleteObserver(this);
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
    }
    public boolean getChasePlayer() {
        return chasePlayer;
    }

    public void setChasePlayer(boolean chasePlayer) {
    }

}



