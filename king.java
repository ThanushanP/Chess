import java.util.Scanner;

public class king implements piece{
    int x;
    int y;
    char sym;
    boolean hasMoved;
    public king(int x, int y, char sym){
        this.x=x;
        this.y=y;
        this.sym=sym;
        this.hasMoved=true;
        if (sym<=122 && sym>=97){
            if (x==7 && y==4){
                hasMoved=false;
            }
        }
        else{
            if (x==0 && y==3){
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
        int i;

        i=0;
        result = new int[board.length*board.length][2];

        if (sym<=122 && sym>=97){
                if (p[7][0]!=null){
                    if (p[7][0].getSym()!='r' || p[7][0].getMoved()==true || this.hasMoved==true || board[7][3]!=0 ||
                    board[7][2]!=0){}
                    else {
                        result[i][0]=7;
                        result[i][1]=0;
                        i++;
                }
            }  
            if (p[7][7]!=null){ 
                if (p[7][7].getSym()!='r' || p[7][7].getMoved()==true || this.hasMoved==true || board[7][5]!=0 ||
                board[7][6]!=0){}
                else {
                    result[i][0]=7;
                    result[i][1]=7;
                    i++;
                }
            }
            if (x-1 == -1 || board[x-1][y]=='.'){}
            else if (p[x-1][y]==null){
                result[i][0]=x-1;
                result[i][1]=y;
                i++;
            }
            else if((p[x-1][y].getSym()<=122 && p[x-1][y].getSym()>=97)){}
            else {
                result[i][0]=x-1;
                result[i][1]=y;
                i++;
            }
            if (x+1 == p.length || board[x+1][y]=='.'){}
            else if (p[x+1][y]==null){
                result[i][0]=x+1;
                result[i][1]=y;
                i++;
            }
            else if (p[x+1][y].getSym()<=122 && p[x+1][y].getSym()>=97){}
            else {
                result[i][0]=x+1;
                result[i][1]=y;
                i++;
            }
            if (y-1 == -1 ||board[x][y-1]=='.'){}
            else if(p[x][y-1]==null){
                result[i][0]=x;
                result[i][1]=y-1;
                i++;
            }
            else if ((p[x][y-1].getSym()<=122 && p[x][y-1].getSym()>=97)){}
            else {
                result[i][0]=x;
                result[i][1]=y-1;
                i++;
            }
            if (y+1 == p.length || board[x][y+1]=='.'){

            }
            else if( p[x][y+1]==null) {
                result[i][0]=x;
                result[i][1]=y+1;
                i++;
            }
            else if((p[x][y+1].getSym()<=122 && p[x][y+1].getSym()>=97)) {}
            else {
                result[i][0]=x;
                result[i][1]=y+1;
                i++;
            }
            if (x-1 == -1 || y-1==-1 || board[x-1][y-1]=='.'){

            }
            else if (p[x-1][y-1]==null){
                result[i][0]=x-1;
                result[i][1]=y-1;
                i++;
            }
            else if ((p[x-1][y-1].getSym()<=122 && p[x-1][y-1].getSym()>=97)){}
            else {
                result[i][0]=x-1;
                result[i][1]=y-1;
                i++;
            }
            if (x-1 == -1 || y+1==p.length || board[x-1][y+1]=='.'){

            }
            else if (p[x-1][y+1]==null){
                result[i][0]=x-1;
                result[i][1]=y+1;
                i++;
            }
            else if ((p[x-1][y+1].getSym()<=122 && p[x-1][y+1].getSym()>=97)){}
            else {
                result[i][0]=x-1;
                result[i][1]=y+1;
                i++;
            }
            if (x+1 == p.length || y-1==-1 || board[x+1][y-1]=='.'){

            }
            else if (p[x+1][y+1]==null){
                result[i][0]=x+1;
                result[i][1]=y-1;
                i++;
            }
            else if ((p[x+1][y-1].getSym()<=122 && p[x+1][y-1].getSym()>=97)){}
            else {
                result[i][0]=x+1;
                result[i][1]=y-1;
                i++;
            }
            if (x+1 == p.length || y+1 == p.length || board[x+1][y+1]=='.'){

            }
            else if (p[x+1][y+1]==null){
                result[i][0]=x+1;
                result[i][1]=y+1;
                i++;
            }
            else if ((p[x+1][y+1].getSym()<=122 && p[x+1][y+1].getSym()>=97)){}
            else {
                result[i][0]=x+1;
                result[i][1]=y+1;
                i++;
            }
        }
        else{
            if (p[0][0]!=null){
                if (p[0][0].getSym()!='R' || p[0][0].getMoved()==true || this.hasMoved==true || board[0][1]!=0 ||
                board[0][2]!=0){}
                else {
                    result[i][0]=0;
                    result[i][1]=0;
                    i++;
                }
            }
            if (p[0][7]!=null){
                if (p[0][7].getSym()!='R' || p[0][7].getMoved()==true || this.hasMoved==true || board[0][5]!=0 ||
                board[0][4]!=0){}
                else {
                    result[i][0]=0;
                    result[i][1]=7;
                    i++;
                }
            }
            if (x-1 == -1 || board[x-1][y]=='.'){

            }
            else if (p[x-1][y]==null){
                result[i][0]=x-1;
                result[i][1]=y;
                i++;
            }
            else if ((p[x-1][y].getSym()<=90 && p[x-1][y].getSym()>=65)){}
            else {
                result[i][0]=x-1;
                result[i][1]=y;
                i++;
            }
            if (x+1 == p.length || board[x+1][y]=='.'){

            }
            else if (p[x+1][y]==null ){
                result[i][0]=x+1;
                result[i][1]=y;
                i++;
            }
            else if ((p[x+1][y].getSym()<=90 && p[x+1][y].getSym()>=65)){}
            else {
                result[i][0]=x+1;
                result[i][1]=y;
                i++;
            }
            if (y-1 == -1 ||board[x][y-1]=='.'){

            }
            else if (p[x][y-1]==null){
                result[i][0]=x;
                result[i][1]=y-1;
                i++;
            }
            else if ((p[x][y-1].getSym()<=90 && p[x][y-1].getSym()>=65)){}
            else {
                result[i][0]=x;
                result[i][1]=y-1;
                i++;
            }
            if (y+1 == p.length ||board[x][y+1]=='.'){

            }
            else if (p[x][y+1]==null){
                result[i][0]=x;
                result[i][1]=y+1;
                i++;
            }
            else if ((p[x][y+1].getSym()<=90 && p[x][y+1].getSym()>=65)){}
            else {
                result[i][0]=x;
                result[i][1]=y+1;
                i++;
            }
            if (x-1 == -1 || y-1==-1 || board[x-1][y-1]=='.'){

            }
            else if (p[x-1][y-1]==null){
                result[i][0]=x-1;
                result[i][1]=y-1;
                i++;
            }
            else if ( (p[x-1][y-1].getSym()<=90 && p[x-1][y-1].getSym()>=65)){}
            else {
                result[i][0]=x-1;
                result[i][1]=y-1;
                i++;
            }
            if (x-1 == -1 || y+1==p.length || board[x-1][y+1]=='.'){

            }
            else if ( p[x-1][y+1]==null ){
                result[i][0]=x-1;
                result[i][1]=y+1;
                i++;
            }
            else if ((p[x-1][y+1].getSym()<=90 && p[x-1][y+1].getSym()>=65)){}
            else {
                result[i][0]=x-1;
                result[i][1]=y+1;
                i++;
            }
            if (x+1 == p.length || y-1==-1 ||board[x+1][y-1]=='.'){

            }
            else if (p[x+1][y-1]==null ){
                result[i][0]=x+1;
                result[i][1]=y-1;
                i++;
            }
            else if ((p[x+1][y-1].getSym()<=90 && p[x+1][y-1].getSym()>=65)){}
            else {
                result[i][0]=x+1;
                result[i][1]=y-1;
                i++;
            }
            if (x+1 == p.length || y+1 == p.length ||board[x+1][y+1]=='.'){

            }
            else if (p[x+1][y+1]==null ){
                result[i][0]=x+1;
                result[i][1]=y+1;
                i++;
            }
            else if ((p[x+1][y+1].getSym()<=90 && p[x+1][y+1].getSym()>=65)){}
            else {
                result[i][0]=x+1;
                result[i][1]=y+1;
                i++;
            }
        }
        for (int j=i;j<result.length;j++){
            result[j][0]=-1;
        }
        return result;
    }
    public piece[][] move(char[][] board, int x, int y, piece[][] p, Scanner in){
        hasMoved=true;
        if (x==7 && y==0){
            p[7][2]=new king(7, 2, sym);
            p[7][3]=new rook(7, 3, 'r');
            p[7][0]=null;
            p[this.x][this.y]=null;
        }
        else if (x==7 && y==7){
            p[7][6]=new king(7, 6, sym);
            p[7][5]=new rook(7, 5, 'r');
            p[7][7]=null;
            p[this.x][this.y]=null;
        }
        else if (x==0 && y==0){
            p[0][1]=new king(0, 1, sym);
            p[0][2]=new rook(0, 2, 'R');
            p[0][0]=null;
            p[this.x][this.y]=null;
        }
        else if (x==0 && y==7){
            p[0][5]=new king(0, 5, sym);
            p[0][4]=new rook(0, 4, 'R');
            p[0][7]=null;
            p[this.x][this.y]=null;
        }
        else {
            p[x][y]= new king(x, y, sym);
            p[this.x][this.y]=null;
        }
        return p;
    }
    public char getSym(){
        return sym;
    }
    public boolean getMoved(){
        return hasMoved;
    }
}
