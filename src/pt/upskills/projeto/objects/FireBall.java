package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.FireTile;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.ArrayList;

public class FireBall implements FireTile {

    //atributos
    private Position position;

    //cosntrutor
    public FireBall(Position position) {
        this.position = position;
    }

    //metodos
    @Override
    public boolean validateImpact() {
        //return false quando tocar nalgum objecto
        for(ImageTile tile : Engine.getCurrentLevel()) {
            if(tile instanceof Wall) {
                if (tile.getPosition().equals(getPosition())) {
                    //remove-se a ela propria com o this
                    Engine.getCurrentLevel().remove(this);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void setPosition(Position position) {
        //this neste caso funciona como o proprio objecto
        this.position = position;
    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public Position getPosition() {
        return this.position;
    }


}
