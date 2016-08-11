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
                System.out.println("Failed to parse first part of Input");
                index = 100;
            }
            if(index>=0&index<24){
                source = muehleGame.getBoard().getListAllCells().get(index);
                stone = muehleGame.getStones().getStoneOnPos(source);
                if(stone==null){
                    System.out.println("Kein Stein auf Zelle gefunden");
                }
            }else{
                System.out.println("Ungültige Source-Zelle "+index);
            }
        }
        if(parts[1].contentEquals("defeat")){
            //defeating stone
            move = new Move(stone,null);
        }else{
            //finding target cell
            int index;
            try{
                index = Integer.parseInt(parts[1]);
            }catch(NumberFormatException e){
                System.out.println("Failed to parse second part of Input");
                index = 100;
            }
            if(index>=0&index<24){
                target = muehleGame.getBoard().getListAllCells().get(index);
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
        Cell[][] allCells = muehleGame.getBoard().getAllCells();
        //loop over rows first to print lines
        for(int j=0;j<7;j++){
            String string ="";
            for(int i=0;i<7;i++){
                Cell cell = allCells[i][j]; 
                if(((i==0)|(i==6))&(cell==null)){
                    string = string+" | ";
                }else if(((i==1)|(i==5))&(cell==null)&((j==2)|(j==4))){
                    string = string+" | ";
                }else if(cell==null){
                    string = string +"---";
                }else if(cell.getStoneColor()==0){
                    string = string + " o ";
                }else if(cell.getStoneColor()==1){
                    string = string + " W ";
                }else if(cell.getStoneColor()==2){
                    string = string + " B ";
                }
            }
            System.out.println(string);
        }
    }
    
}
