package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.Observer;

public abstract class MovableObject implements ImageTile, Observer {

    //atributos
    private int health;
    private int maxHealth;
    private int attack;
    private String name;
    private Position position;

    //construtor
    public MovableObject(String name, Position position, int health) {
        this.name = name;
        this.health = this.maxHealth = health;
        this.position = position;
    }

    //metodos
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void heal(int amount) {
        this.health += amount;
        if(health>maxHealth)
            this.health = this.maxHealth;
    }

    public void damage(int amount) {
        if(amount <= 0) amount = 1;
        this.health -= amount;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack += attack;
    }

}
