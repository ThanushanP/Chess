import java.util.Scanner;

public class pawn implements piece{
    int x; 
    int y;
    boolean hasMoved;
    char sym;

    public pawn(int x, int y, char sym){
        this.x=x;
        this.y=y;
        this.sym=sym;
        this.hasMoved=true;
        if (sym<=122 && sym>=97){
            if (x==6){
                this.hasMoved=false;
            }
        }
        else {
            if (x==1){
                this.hasMoved=false;
            }
        }
    }
    public boolean getMoved(){
        return hasMoved;
    }
    public char getSym(){
        return sym;
    }
    public boolean isValidMove(char[][] board, int x, int y, piece[][] p){
        int[][] possibleMoves;

        possibleMoves = possibleMoves(board, p);

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
        int i;

        i=0;

        result = new int[board.length*board.length][2];

        if (sym<=122 && sym>=97){
            if (x-1!=-1 && y-1!=-1){
                if ((board[x-1][y-1]<=90 && board[x-1][y-1]>=65)){
                    result[i][0]=x-1;
                    result[i][1]=y-1;
                    i++;
                }
            }
            if (x-1!=-1 && y+1!=board.length){
                if ((board[x-1][y+1]<=90 && board[x-1][y+1]>=65)){
                    result[i][0]=x-1;
                    result[i][1]=y+1;
                    i++;
                }
            }
            if (x-1 == -1 || (board[x-1][y]<=122 && board[x-1][y]>=97) || (board[x-1][y]<=90 && board[x-1][y]>=65)){

            }
            else {
                result[i][0]=x-1;
                result[i][1]=y;
                i++;
            }//move one up
            if (hasMoved==false){
                if (x-2 == -1 || (board[x-2][y]<=122 && board[x-2][y]>=97) || (board[x-2][y]<=90 && board[x-2][y]>=65)){

                }
                else {
                    result[i][0]=x-2;
                    result[i][1]=y;
                    i++;
                }
            }//move two up if it hasnt been moved
            if (y-1!=-1 && x-1!=-1){
                if (board[x][y-1]<=90 && board[x][y-1]>=65){
                    result[i][0]=x-1;
                    result[i][1]=y-1;
                    i++;
                }
            }//Check if piece can move to the left diagonally
            if (y+1!=board.length && x-1!=-1){
                if (board[x][y+1]<=90 && board[x][y+1]>=65){
                    result[i][0]=x-1;
                    result[i][1]=y+1;
                    i++;
                }
            }//Check if piece can move to the right diagonally
        }//If piece is on lowercase team
        else{
            if (x+1!=board.length && y-1!=-1){
                if ((board[x+1][y-1]<=122 && board[x+1][y-1]>=97)){
                    result[i][0]=x+1;
                    result[i][1]=y-1;
                    i++;
                }
            }
            if (x+1!=board.length && y+1!=board.length){
                if ((board[x+1][y+1]<=122 && board[x+1][y+1]>=97)){
                    result[i][0]=x+1;
                    result[i][1]=y+1;
                    i++;
                }
            }
            if (x+1 == board.length || (board[x+1][y]<=122 && board[x+1][y]>=97) || (board[x+1][y]<=90 && board[x+1][y]>=65)){

            }
            else {
                result[i][0]=x+1;
                result[i][1]=y;
                i++;
            }//move one up
            if (hasMoved==false){
                if (x+2 == board.length || (board[x+2][y]<=122 && board[x+2][y]>=97) || (board[x+2][y]<=90 && board[x+2][y]>=65)){

                }
                else {
                    result[i][0]=x+2;
                    result[i][1]=y;
                    i++;
                }
            }//move two up if it hasnt been moved
            if (y-1!=-1 && x+1!=board.length){
                if (board[x][y-1]<=122 && board[x][y-1]>=97){
                    result[i][0]=x+1;
                    result[i][1]=y-1;
                    i++;
                }
            }//Check if piece can move to the left diagonally
            if (y+1!=board.length && x+1!=board.length){
                if (board[x][y+1]<=122 && board[x][y+1]>=97){
                    result[i][0]=x+1;
                    result[i][1]=y+1;
                    i++;
                }
            }//Check if piece can move to the right diagonally
        }//if piece is on uppercase team

        for (int j=i;j<result.length;j++){
            result[j][0]=-1;
        }

        return result;
    }

    public piece[][] move(char[][] board, int x, int y, piece[][] p, Scanner in){
        hasMoved = true;
        if (sym<=122 && sym>=97){
            if (y-1>-1 && board[x][y-1]<=90 && board[x][y-1]>=65 &&(this.x==x-1 && this.y==y-1 || this.x==x+1 && this.y==y-1)){
                p[x][y]= new pawn(x, y, sym);
                p[this.x][this.y]=null;
                p[this.x][this.y+1]=null;
            }
            else if (y+1<board.length && board[x][y+1]<=90 && board[x][y+1]>=65 &&(this.x==x-1 && this.y==y+1 || this.x==x+1 && this.y==y+1)){
                p[x][y]= new pawn(x, y, sym);
                p[this.x][this.y]=null;
                p[this.x][this.y-1]=null;
            }
            else {
                p[x][y]= new pawn(x, y, sym);
                p[this.x][this.y]=null;
            }
        }
        else {
            if (y-1>-1 && board[x][y-1]<=122 && board[x][y-1]>=97 &&(this.x==x-1 && this.y==y-1 || this.x==x+1 && this.y==y-1)){
                p[x][y]= new pawn(x, y, sym);
                p[this.x][this.y]=null;
                p[this.x][this.y+1]=null;
            }
            else if (y+1<board.length && board[x][y+1]<=122 && board[x][y+1]>=97 &&(this.x==x-1 && this.y==y+1 || this.x==x+1 && this.y==y+1)){
                p[x][y]= new pawn(x, y, sym);
                p[this.x][this.y]=null;
                p[this.x][this.y-1]=null;
            }
            else {
                p[x][y]= new pawn(x, y, sym);
                p[this.x][this.y]=null;
            }
        }
        promotion(x, y, board,p,in);

        return p;
    }

    private void promotion(int x, int y, char[][] board, piece[][] p, Scanner input){
        if (sym<=122 && sym>=97){
             if (x==0){
                if (input==null) {
                    p[x][y]= new queen(x, y, 'q');
                }
                else {
                    System.out.println("Promote to Queen(Type q), Rook(Type r), Bishop(Type b), or Knight(Type h): ");
                    this.sym=(input.next("[qrbh]")).charAt(0);
                    if (sym=='q'){
                        p[x][y]= new queen(x, y, 'q');
                    }
                    else if (sym=='r'){
                        p[x][y]= new rook(x, y, 'r');
                        
                    }
                    else if (sym=='b'){
                        p[x][y]= new bishop(x, y, 'b');
                        
                    }
                    else if (sym =='h'){
                        p[x][y]= new knight(x, y, 'h');
                    }
                }
            }
        }
        else {
            if (x==board.length-1){
                if (input==null) {
                    p[x][y]= new queen(x, y, 'Q');
                }
                else {
                    System.out.println("Promote to Queen(Type Q), Rook(Type R), Bishop(Type B), or Knight(Type H): ");
                    this.sym=(input.next("[QRBH]")).charAt(0);
                    if (sym=='Q'){
                        p[x][y]= new queen(x, y, 'Q');
                    }
                    else if (sym=='R'){
                        p[x][y]= new rook(x, y, 'R');
                        
                    }
                    else if (sym=='B'){
                        p[x][y]= new bishop(x, y, 'B');
                        
                    }
                    else if (sym =='H'){
                        p[x][y]= new knight(x, y, 'H');
                    }
                }
            }
        }
    }
}



