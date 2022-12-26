import java.util.Scanner;
interface piece{
    public boolean isValidMove(char[][] board, int x, int y, piece[][] p); //Checks to see if x,y are valid moves
    public int[][] possibleMoves(char[][] board, piece[][] p);              //Returns all the possible moves of this.piece
    public piece[][] move(char[][] board, int x, int y, piece[][] p, Scanner in);   //Moves the piece to x,y
    public char getSym();   //Returns the symbol
    public boolean getMoved();  //Returns fi piece has moved
}
//lower case at the bottom moving up => upp case at the top moving down