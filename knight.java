import java.util.Scanner;

public class knight implements piece{
    int x;
    int y;
    char sym;
    public knight(int x, int y, char sym){
        this.x=x;
        this.y=y;
        this.sym=sym;
    }
    public boolean isValidMove(char[][] board, int x, int y, piece[][] p){
        int[][] possibleMoves;

        possibleMoves = possibleMoves(board,p);

        for (int i=0;i<possibleMoves.length;i++){
            if (possibleMoves[i][0]==-1) break;
            if (possibleMoves[i][0]==x && possibleMoves[i][1]==y){
                return true;
            }
        }

        return false;
    }
    public int[][] possibleMoves(char[][] board, piece[][] p){
        int [][] result;
        int inc;

        inc=0;
        result = new int[board.length*board.length][2];

        if (sym<=122 && sym>=97){
            if (x-2<0 || y-1<0 || board[x-2][y-1]<=122 && board[x-2][y-1]>=97){

            }
            else{
                result[inc][0]=x-2;
                result[inc][1]=y-1;
                inc++;
            }
            if (x-1<0 || y-2<0 || board[x-1][y-2]<=122 && board[x-1][y-2]>=97){

            }
            else{
                result[inc][0]=x-1;
                result[inc][1]=y-2;
                inc++;
            }
            if (x+1>=board.length || y-2<0 || board[x+1][y-2]<=122 && board[x+1][y-2]>=97){

            }
            else{
                result[inc][0]=x+1;
                result[inc][1]=y-2;
                inc++;
            }
            if (x+2>=board.length || y-1<0 || board[x+2][y-1]<=122 && board[x+2][y-1]>=97){

            }
            else{
                result[inc][0]=x+2;
                result[inc][1]=y-1;
                inc++;
            }
            if (x+2>=board.length || y+1>=board.length || board[x+2][y+1]<=122 && board[x+2][y+1]>=97){

            }
            else{
                result[inc][0]=x+2;
                result[inc][1]=y+1;
                inc++;
            }
            if (x+1>=board.length || y+2>=board.length || board[x+1][y+2]<=122 && board[x+1][y+2]>=97){

            }
            else{
                result[inc][0]=x+1;
                result[inc][1]=y+2;
                inc++;
            }
            if (x-1<0 || y+2>=board.length || board[x-1][y+2]<=122 && board[x-1][y+2]>=97){

            }
            else{
                result[inc][0]=x-1;
                result[inc][1]=y+2;
                inc++;
            }
            if (x-2<0 || y+1>=board.length || board[x-2][y+1]<=122 && board[x-2][y+1]>=97){

            }
            else{
                result[inc][0]=x-2;
                result[inc][1]=y+1;
                inc++;
            }
        }
        else{
            if (x-2<0 || y-1<0 || board[x-2][y-1]<=90 && board[x-2][y-1]>=65){

            }
            else{
                result[inc][0]=x-2;
                result[inc][1]=y-1;
                inc++;
            }
            if (x-1<0 || y-2<0 || board[x-1][y-2]<=90 && board[x-1][y-2]>=65){

            }
            else{
                result[inc][0]=x-1;
                result[inc][1]=y-2;
                inc++;
            }
            if (x+1>=board.length || y-2<0 || board[x+1][y-2]<=90 && board[x+1][y-2]>=65){

            }
            else{
                result[inc][0]=x+1;
                result[inc][1]=y-2;
                inc++;
            }
            if (x+2>=board.length || y-1<0 || board[x+2][y-1]<=90 && board[x+2][y-1]>=65){

            }
            else{
                result[inc][0]=x+2;
                result[inc][1]=y-1;
                inc++;
            }
            if (x+2>=board.length || y+1>=board.length || board[x+2][y+1]<=90 && board[x+2][y+1]>=65){

            }
            else{
                result[inc][0]=x+2;
                result[inc][1]=y+1;
                inc++;
            }
            if (x+1>=board.length || y+2>=board.length || board[x+1][y+2]<=90 && board[x+1][y+2]>=65){

            }
            else{
                result[inc][0]=x+1;
                result[inc][1]=y+2;
                inc++;
            }
            if (x-1<0 || y+2>=board.length || board[x-1][y+2]<=90 && board[x-1][y+2]>=65){

            }
            else{
                result[inc][0]=x-1;
                result[inc][1]=y+2;
                inc++;
            }
            if (x-2<0 || y+1>=board.length || board[x-2][y+1]<=90 && board[x-2][y+1]>=65){

            }
            else{
                result[inc][0]=x-2;
                result[inc][1]=y+1;
                inc++;
            }
        }
        for (int j=inc;j<result.length;j++){
            result[j][0]=-1;
        }
        return result;
    }
    public piece[][] move(char[][] board, int x, int y, piece[][] p, Scanner in){
        p[x][y]= new knight(x, y, sym);
        p[this.x][this.y]=null;
        return p;
    }
    public char getSym(){
        return sym;
    }
    public boolean getMoved(){
        boolean hasMoved=true;
        return hasMoved;
    }
}
