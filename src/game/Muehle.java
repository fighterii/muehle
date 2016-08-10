/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

/**
 *
 * @author Kai
 */
public class Muehle implements Runnable {

    /**
     * class Muehle describes the Game and all interactions with it
     * Every instance initializes its own Board instance, its own Stones instance and its own MoveValidator
     * Two IPlayerHandler (white & black) need to be assigned via setPlayer(1,IPlayerHandler) and setPlayer(2,IPlayerHandler)
     */
    private int step;
    private IPlayerHandler whitePlayer;
    private IPlayerHandler blackPlayer;
    private IPlayerHandler activePlayer;
    private boolean second_move;
    private final Board board;
    private final Stones stones;
    private MoveValidator moveValidator;
    private Move current_move;
    private boolean manual_stop;
    
    public Muehle() {
        System.out.println("Game initialized");
        this.board = new Board();
        System.out.println("create stones");
        this.stones = new Stones(board);
        this.current_move = null;
        this.manual_stop = false;
    }


    /**
     *
     * @return current GameStep
     */
    public int getStep() {
        return step;
    }

    /**
     *
     * is used to change the activePlayer
     */
    public void changeGameState() {
        this.step++;
        if(step%2==0){
            this.activePlayer= whitePlayer;
        }else{
            this.activePlayer= blackPlayer;
        }
    }
    
    /**
     * start main game flow
     */
    public void startGame(){
        // initialize game
        // check if all players are ready
        System.out.println("Waiting for players");
        while (this.blackPlayer == null || this.whitePlayer == null){
                // players are still missing
                try {Thread.sleep(1000);} catch (InterruptedException e) {}
        }
        //initialize moveValidator
        this.moveValidator = new MoveValidator(this);
        //initialize starting player to whitePlayer
        this.activePlayer = whitePlayer;
        // create stones
        //initialize second_move to false
        //this var is used to determine if a user has to defeat an enemy stone
        this.second_move = false;
        //initialize gamestep to 0
        this.step =0;
        System.out.println("starting game");


        while((!isGameEndConditionReached())&(!manual_stop)){
                waitForMove();
                if(second_move){
                    waitForMove();
                }
                this.second_move = false;
                changeGameState();
        }
        
        //Game End
        System.out.println("Game end");
        
    }
    private void waitForMove(){
        current_move = null;
        
        //wait for a valid move
        do{
                current_move = this.activePlayer.getMove();
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                if( current_move != null && this.moveValidator.isValidMove(current_move) ){
                        break;
                }else if( current_move != null && !this.moveValidator.isValidMove(current_move)){
                        System.out.println("provided move was invalid: "+current_move);
                        throw new IllegalStateException("provided move was invalid");
                }
        }while(current_move == null);
        
        //make the move
        this.moveStone(current_move);
    }
    /**
     * check if the games end condition is met: one player has 9 defeated_stones
     * 
     * @return true if the game end condition is met
     */
    private boolean isGameEndConditionReached() {
            return (stones.getSizeDefeatedStones(1)==9)|(stones.getSizeDefeatedStones(2)==9);
    }

    public IPlayerHandler getWhitePlayer() {
        return whitePlayer;
    }

    public IPlayerHandler getBlackPlayer() {
        return blackPlayer;
    }

    public void setPlayer(int color,IPlayerHandler player) {
        if(color==1){
            this.whitePlayer = player;
        }else{
            this.blackPlayer = player;           
        }
    }

    /**
     *
     * @return the current board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     *
     * @return boolean second_move
     * which indicates if a player has to make a second move (defeat enemy stone)
     * to proceed
     */
    public boolean isSecond_move() {
        return second_move;
    }

    /**
     *
     * @return Object with all Stones
     */
    public Stones getStones() {
        return stones;
    }

    /**
     *
     * @return the current MoveValidator
     */
    public MoveValidator getMoveValidator() {
        return moveValidator;
    }

    /**
     *
     * @return the current active player
     */
    public IPlayerHandler getActivePlayer() {
        return activePlayer;
    }
    
    

    @Override
    public void run() {
        this.startGame();
    }

    private void moveStone(Move move) {
        Stone stone = move.stone;
        Cell target = move.target;
        Cell old_cell = stone.getPos();
        //check if stone is already on the board or is set new
        //if old_cell == null stone is set new
        if(old_cell==null){
            stones.setStone(stone);
        }else{
            //set old cell to free
            old_cell.setStoneColor(0);    
        }
        //check if stone is defeated or moved to a new position
        //if target == null stone is defeated
        if(target==null){
            stones.defeatStone(stone);
        }else{
            //move stone to target cell
            stone.setPos(target);    
        }
        //check if stone creates a muehle
        //if yes, set second_move to true in order
        //to allow the user to defeat an enemy stone
        if(stones.iSStonePartOfMuehle(stone)){
            this.second_move = true;
        }
    }
    /**
     * stops the game
     */
    public void stopGame(){
        this.manual_stop = true;
    }

}
