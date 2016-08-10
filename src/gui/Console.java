/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package gui;

import game.Cell;
import game.IPlayerHandler;
import game.Move;
import game.MoveValidator;
import game.Muehle;
import game.Stone;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kai
 */
public class Console implements IPlayerHandler{
    private Muehle muehleGame;
    private Scanner sc;
    private int color;
    private MoveValidator moveValidator;
    
    public Console(Muehle muehleGame){
        this.muehleGame = muehleGame;
        sc = new Scanner(System.in);
        //player is always white
        this.color = 1;
        System.out.println("Spiel gestartet...");
        System.out.println("Eingabe-Format move: #sourceCellX:#targetCellY");
        System.out.println("Z.b: 3:5");
        System.out.println("Eingabe-Format set: new:#targetCellY");
        System.out.println("Eingabe-Format defeat: #sourceCellX:defeat");
    }

    private Move getInput(){
        Move move = null;
        if(muehleGame.isSecond_move()){
            System.out.println("Schlage gegnerischen Stein...");
            System.out.println("Eingabe-Format defeat: #sourceCellX:defeat");
        }else if(muehleGame.getStones().getNotsetStones(color).size()>0){
            System.out.println("Setze neuen Stein aufs Spielfeld...");
            System.out.println("Eingabe-Format set: new:#targetCellY");
        }else{
            System.out.println("Bewege Stein...");
            System.out.println("Eingabe-Format move: #sourceCellX:#targetCellY");
        }

        System.out.print("Zug eingeben: ");
        String input  = sc.next();
        String[] parts = input.split(":");
        Stone stone = null;
        Cell target = null;
        Cell source = null;
        //System.out.println("Eingabe: "+input);
        //System.out.println("Part 0: "+parts[0]+" - Part 1: "+ parts[1]);
        if(parts[0].contentEquals("new")){
            //setting new stone
            System.out.println("Setze neuen Stein aufs Spielfeld");
            stone = muehleGame.getStones().getNextNotsetStone(color);
        }else{
            //finding stone on source
            int index;
            try{
                index = Integer.parseInt(parts[0]);
            }catch(NumberFormatException e){
                index = 100;
            }
            if(index>0&index<24){
                source = muehleGame.getBoard().getAllCells().get(index);
                stone = muehleGame.getStones().getStoneOnPos(source);
            }else{
                System.out.println("Ungültige Source-Zelle "+index);
            }
        }
        if(parts[1]=="defeat"){
            //defeating stone
            move = new Move(stone,null);
        }else{
            //finding target cell
            int index;
            try{
                index = Integer.parseInt(parts[1]);
            }catch(NumberFormatException e){
                index = 100;
            }
            if(index>0&index<24){
                target = muehleGame.getBoard().getAllCells().get(index);
            }else{
                System.out.println("Ungültige Ziel-Zelle "+index);
            }
            //moving stone
            move = new Move(stone,target);
        }
        return move;
    }
    @Override
    public Move getMove() {
        Move move=null;
        moveValidator = muehleGame.getMoveValidator();
        printBoard();
        do{
            move = getInput();
            if(!moveValidator.isValidMove(move)){
               System.out.println("Ungültiger Zug"); 
            }
        }while(!moveValidator.isValidMove(move));
        System.out.println("Gültiger Zug gefunden");
        return move;
    }

    private void printBoard() {
        ArrayList<Cell> allCells = muehleGame.getBoard().getAllCells();
        ArrayList<String> allCellsString = new ArrayList();
        for(int i=0;i<allCells.size();i++){
            if(allCells.get(i).getStoneColor()==0){
                //cell is free
                allCellsString.add("o");
            }else if(allCells.get(i).getStoneColor()==1){
                //white cell
                allCellsString.add("W");
            }else{
                allCellsString.add("B");
            }
        }
        System.out.println(allCellsString.get(0) +" - - "+allCellsString.get(1)+" - - "+allCellsString.get(2));
        System.out.println("| "+allCellsString.get(8)+" - "+allCellsString.get(9)+" - "+allCellsString.get(10)+ " |");
        System.out.println("| | "+allCellsString.get(16) + " " + allCellsString.get(17) + " " + allCellsString.get(18) + " | |");
        System.out.println(allCellsString.get(3) +" " + allCellsString.get(11) + " " + allCellsString.get(19) + "   "+ allCellsString.get(20) + " " + allCellsString.get(12) + " " + allCellsString.get(4));
        System.out.println("| | "+allCellsString.get(21) + " " + allCellsString.get(22) + " " + allCellsString.get(23) + " | |");
        System.out.println("| "+allCellsString.get(13)+" - "+allCellsString.get(14)+" - "+allCellsString.get(15)+ " |");
        System.out.println(allCellsString.get(5) +" - - "+allCellsString.get(6)+" - - "+allCellsString.get(7));
    }
    
}
