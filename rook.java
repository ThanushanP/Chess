import java.util.Scanner;

public class rook implements piece{
    int x;
    int y;
    char sym;
    boolean hasMoved;
    public rook(int x, int y, char sym){
        this.x=x;
        this.y=y;
        this.sym=sym;
        this.hasMoved=true;
        if (sym<=122 && sym>=97){
            if (x==7 && y==7 || x==7 && y==0){
                hasMoved=false;
            }
        }
        else{
            if (x==0 && y==7 || x==0 && y==0){
                hasMoved=false;
            } 
        } 
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
            for (int i=1;i<board.length;i++){
                if (y+i==board.length || board[x][y+i]<=122 && board[x][y+i]>=97) break;
                else {
                    result[inc][0]=x;
                    result[inc][1]=y+i;
                    inc++;
                }
                if (board[x][y+i]<=90 && board[x][y+i]>=65) break;
            }
            for (int i=1;i<board.length;i++){
                if (y-i==-1 || board[x][y-i]<=122 && board[x][y-i]>=97) break;
                else {
                    result[inc][0]=x;
                    result[inc][1]=y-i;
                    inc++;
                }
                if (board[x][y-i]<=90 && board[x][y-i]>=65) break;
            }
            for (int i=1;i<board.length;i++){
                if (x+i==board.length || board[x+i][y]<=122 && board[x+i][y]>=97) break;
                else {
                    result[inc][0]=x+i;
                    result[inc][1]=y;
                    inc++;
                }
                if (board[x+i][y]<=90 && board[x+i][y]>=65) break;
            }
            for (int i=1;i<board.length;i++){
                if (x-i==-1 || board[x-i][y]<=122 && board[x-i][y]>=97) break;
                else {
                    result[inc][0]=x-i;
                    result[inc][1]=y;
                    inc++;
                }
                if (board[x-i][y]<=90 && board[x-i][y]>=65) break;
            }
        }
        else {
            for (int i=1;i<board.length;i++){
                if (y+i==board.length || board[x][y+i]<=90 && board[x][y+i]>=65) break;
                else {
                    result[inc][0]=x;
                    result[inc][1]=y+i;
                    inc++;
                }
                if (board[x][y+i]<=122 && board[x][y+i]>=97) break;
            }
            for (int i=1;i<board.length;i++){
                if (y-i==-1 || board[x][y-i]<=90 && board[x][y-i]>=65) break;
                else {
                    result[inc][0]=x;
                    result[inc][1]=y-i;
                    inc++;
                }
                if (board[x][y-i]<=122 && board[x][y-i]>=97) break;
            }
            for (int i=1;i<board.length;i++){
                if (x+i==board.length || board[x+i][y]<=90 && board[x+i][y]>=65) break;
                else {
                    result[inc][0]=x+i;
                    result[inc][1]=y;
                    inc++;
                }
                if (board[x+i][y]<=122 && board[x+i][y]>=97) break;
            }
            for (int i=1;i<board.length;i++){
                if (x-i==-1 || board[x-i][y]<=90 && board[x-i][y]>=65) break;
                else {
                    result[inc][0]=x-i;
                    result[inc][1]=y;
                    inc++;
                }
                if (board[x-i][y]<=122 && board[x-i][y]>=97) break;
            }
        }
        for (int j=inc;j<result.length;j++){
            result[j][0]=-1;
        }
        return result; 
    }

    public piece[][] move(char[][] board, int x, int y, piece[][] p, Scanner in){
        hasMoved=true;
        p[x][y]= new rook(x, y, sym);
        p[this.x][this.y]=null;
        return p;
    }
    public char getSym(){
        return sym;
    }
    public boolean getMoved(){
        return hasMoved;
    }
}
