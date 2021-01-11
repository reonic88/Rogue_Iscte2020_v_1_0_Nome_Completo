package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.FireTile;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.*;
import pt.upskills.projeto.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Engine {

    public static List<ImageTile> statusTiles = new ArrayList<>();
    public static HashMap<Integer, List<ImageTile>> levelToGo = new HashMap();
    private static List<ImageTile> currentLevel = new ArrayList<>();
    private static List<ImageTile> level0 = new ArrayList<>(), level1 = new ArrayList<>(), level2 = new ArrayList<>();
    public static int currentLevelIndex = 0;
    public static  ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    public static Hero hero;

    public void init() {

        readMapFile();
        currentLevel = levelToGo.get(currentLevelIndex);
        //System.out.println(currentLevelIndex);
        for (ImageTile tile : currentLevel) {
            if (tile instanceof MovableObject) {
               gui.addObserver((MovableObject) tile);
            }
            if (tile instanceof Hero) {
                hero = (Hero) tile;
            }
        }

        updateStatus();

        gui.newImages(currentLevel);
        gui.go();


        while (true){
            gui.update();
        }
    }

    public static void updateStatus() {
        gui.clearStatus();
        statusTiles.clear();
        for(int i=0; i<10; i++) {
            statusTiles.add(new Black(new Position(i,0)));
        }
        statusTiles.add(new FireBall(new Position(0,0)));
        statusTiles.add(new FireBall(new Position(1,0)));
        statusTiles.add(new FireBall(new Position(2,0)));


        switch (hero.getHealth()) {
            case 8:
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(3, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(4, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(5, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(6, 0)));
                break;
            case 7:
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(3, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(4, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(5, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN_RED, new Position(6, 0)));
                break;
            case 6:
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(3, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(4, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(5, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(6, 0)));
                break;
            case 5:
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(3, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(4, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN_RED, new Position(5, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(6, 0)));
                break;
            case 4:
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(3, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(4, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(5, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(6, 0)));
                break;
            case 3:
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(3, 0)));
                statusTiles.add(new Life(Life.LifeType.GREEN_RED, new Position(4, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(5, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(6, 0)));
                break;
            case 2:
                statusTiles.add(new Life(Life.LifeType.GREEN, new Position(3, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(4, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(5, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(6, 0)));
                break;
            case 1:
                statusTiles.add(new Life(Life.LifeType.GREEN_RED, new Position(3, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(4, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(5, 0)));
                statusTiles.add(new Life(Life.LifeType.RED, new Position(6, 0)));
                break;
            default:
                System.out.println("  ____ \n |   o\n |  /|\n |   |\n |  /|\n_|____");
                System.out.println("----------K.O.----------");
                System.out.println("-----YOU... LOSE...-----");
                statusTiles.add(new Thief(new Position(0, 0)));
                statusTiles.add(new Thief(new Position(1, 0)));
                statusTiles.add(new Thief(new Position(2, 0)));
                statusTiles.add(new LetterD(new Position(3, 0)));
                statusTiles.add(new LetterE(new Position(4, 0)));
                statusTiles.add(new LetterA(new Position(5, 0)));
                statusTiles.add(new LetterD(new Position(6, 0)));
                statusTiles.add(new Thief(new Position(7, 0)));
                statusTiles.add(new Thief(new Position(8, 0)));
                statusTiles.add(new Thief(new Position(9, 0)));
        }

        //statusTiles.add(new Sword(new Position(8,0)));


        gui.newStatusImages(statusTiles);
    }

    public static List<ImageTile> getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int posicao) {
        currentLevel = levelToGo.get(posicao);
    }

    //ver o nivel em que estamos
    public void readMapFile() {

        //implementaçao do metodo...
        //ler o ficheiro rooms/room + level . txt


        int level = 0;
        try {
            while (true) {

                //decidir em que camada aparecem as coisas, assim os esqueletos sobrepoem-se a relva por exemplo
                List<ImageTile> tiles = new ArrayList<>();
                level0 = new ArrayList<>();
                level1 = new ArrayList<>();
                level2 = new ArrayList<>();

                for(int i=0; i<10; i++){
                    for(int j=0; j<10; j++){
                        //percorrer todos os numeros para ver com este sout
                        //System.out.println(i + ", " + j);
                        level0.add(new Floor(new Position(i, j)));
                    }
                }
                Scanner fileScanner = new Scanner(new File("rooms/room" + level + ".txt"));
                int i = 0;
                while (fileScanner.hasNextLine()) {
                    String nextLine = fileScanner.nextLine();
                    String[] separar = nextLine.split("");
                    //etc...
                    //escolher em que camada aparecem os items, floor, movable objects
                    for (int j = 0; j < separar.length; j++) {
                        if (separar[0].equals("#")) {
                            i--;
                            break;
                        }
                        if (separar[j].equals("W")) {
                            level0.add(new Wall(new Position(j, i)));
                        }

                        if (separar[j].equals("h")) {
//                            System.out.println("posição do heroi " + hero.getPosition());
                            if (hero == null) {
                                level2.add(new Hero(new Position(j, i)));
                            } else {
                                hero.setPosition(new Position(j,i));
                                level2.add(hero);
                            }
                        }

                        if (separar[j].equals("S")) {
                            level2.add(new Skeleton(new Position(j, i)));
                        }

                        if (separar[j].equals("B")) {
                            level2.add(new Bat(new Position(j, i)));
                        }

                        if (separar[j].equals("0")) {
                            level0.add(new StairsUp(new Position(j, i)));
                        }

                        if (separar[j].equals("M")) {
                            level2.add(new BadGuy(new Position(j, i)));
                        }

                        if (separar[j].equals("9")) {
                            level0.add(new StairsDown(new Position(j, i)));
                        }

                        if (separar[j].equals("G")) {
                            level0.add(new Grass(new Position(j, i)));
                        }

                        if (separar[j].equals("T")) {
                            level2.add(new Thief(new Position(j, i)));
                        }

                        if (separar[j].equals("R")) {
                            level0.add(new GoodMeat(new Position(j, i)));
                        }

                        if (separar[j].equals("A")) {
                            level1.add(new Sword(new Position(j, i)));
                        }


                    }
                    i++;
                }
                tiles.addAll(level0);
                tiles.addAll(level1);
                tiles.addAll(level2);

                levelToGo.put(level,tiles);
                level++;
                fileScanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("os mapas foram carregados com sucesso !!!");
        }
    }

    //mudar de andares
    public static void goToNextLevel() {
        currentLevelIndex++;
        //System.out.println("current level" + currentLevelIndex);
        refreshWindow();
    }

    private static void refreshWindow() {
        gui.clearImages();
        gui.deleteObservers();

        System.out.println("observers" + gui.countObservers());
        currentLevel = levelToGo.get(currentLevelIndex);
        //referencia para a posicao dos bonecos
        for (ImageTile tile : currentLevel) {
            if(hero != null && tile instanceof Hero){
        //        ((Hero)tile).setAttack(hero.getAttack() - 1);
            }

            if (tile instanceof MovableObject) {
                gui.addObserver((MovableObject) tile);
            }
        }
        //gui.newStatusImages(statusTiles);
        gui.newImages( currentLevel);
    }

    public static void goToLowerLevel() {
        currentLevelIndex--;
        refreshWindow();
    }


    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }
}


