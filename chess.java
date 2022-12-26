import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class chess {
    Scanner in;
    int[] upperKingPos;
    int[] lowerKingPos;
    FileWriter out;
    public chess(int n){

    }
    public chess(){
        int temp;
        piece[][] p;
        char[][] board={
            {'R','H','B','K','Q','B','H','R'},
            {'P','P','P','P','P','P','P','P'},
            { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0,},
            { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0,},
            { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0,},
            { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0,},
            {'p','p','p','p','p','p','p','p'},
            {'r','h','b','q','k','b','h','r'}
        };  //Edit this board to edit the the game

        try {
            out = new FileWriter("result.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        upperKingPos = new int[2];
        lowerKingPos = new int[2]; 
        p=createPieces(board, upperKingPos, lowerKingPos);
        System.out.println("2 Player or 1 Player(Type the number): ");
        in = new Scanner(System.in);
        temp = in.nextInt();
        if (temp == 2){
            player2(board, p, in, upperKingPos, lowerKingPos);
        }
        else {
            onePlayer(board, p, in, upperKingPos, lowerKingPos);
        }
        in.close();
    }
    //Human vs Alpha-beta AI
    private void onePlayer(char[][] board, piece[][] p, Scanner in, int[] upperKingPos, int[] lowerKingPos){
        boolean temp;   //Whether the move was valid or not
        int i=0; //Stalemate counter 
        char[] arr; //coords of piece to move
        char[] coords;  //Coords to move piece
        char[][] lowercaseKing; //Track danger for lower case king
        char[][] uppercaseKing; //Track danger for upper case king
        minimax m = new minimax();

        lowercaseKing = new char[p.length][p.length];
        uppercaseKing = new char[p.length][p.length];
        while (true){
            printBoard(board);
            uppercaseKing=updateDangerForUpper(uppercaseKing, p, board, upperKingPos);
            lowercaseKing=updateDangerForLower(lowercaseKing, p, board, lowerKingPos);
            terminal(uppercaseKing, p, board, upperKingPos, lowercaseKing, lowerKingPos);
            temp =false;
            do{
                System.out.print("\nWhich piece would you like to move x,y: ");
                arr = in.next("[0-9],[0-9]").toCharArray();
                System.out.print("\nWhere do you want to move it x,y: ");
                coords = in.next("[0-9],[0-9]").toCharArray();
                if (arr.length!=3 || coords.length!=3) continue;
                if (arr[0]-48<board.length && arr[0]-48>=0 && arr[2]-48<board.length && arr[2]-48>=0 && coords[0]-48<board.length && coords[0]-48>=0 && coords[0]-48<board.length && coords[0]-48>=0){
                    if (p[arr[0]-48][arr[2]-48]==null){
                        System.out.println("Not a piece");
                    }
                    else if (p[arr[0]-48][arr[2]-48].getSym()<=90 && p[arr[0]-48][arr[2]-48].getSym()>=65){
                        System.out.println("Wrong Piece");
                    }
                    else {
                        if (p[arr[0]-48][arr[2]-48].getSym()=='k'){
                            temp = p[arr[0]-48][arr[2]-48].isValidMove(lowercaseKing, coords[0]-48, coords[2]-48, p);
                            if (temp==true){
                                lowerKingPos[0]=coords[0]-48;
                                lowerKingPos[1]=coords[2]-48;
                            }
                        }//Update pos of lower king
                        else {
                            temp = p[arr[0]-48][arr[2]-48].isValidMove(board, coords[0]-48, coords[2]-48, p);
                        }//Checks to see whether the move was valid or not 
                    }
                }
            } while (temp==false);
            p[arr[0]-48][arr[2]-48].move(board, coords[0]-48, coords[2]-48, p, in);
            if (p[coords[0]-48][coords[2]-48]==null) i++;
            else i=0;
            if (i==75)break;
            board=updateBoard(p, board);
            uppercaseKing=updateDangerForUpper(uppercaseKing, p, board, upperKingPos);
            lowercaseKing=updateDangerForLower(lowercaseKing, p, board, lowerKingPos);
            terminal(uppercaseKing, p, board, upperKingPos, lowercaseKing, lowerKingPos);
            p = m.minimax_decision(board, lowercaseKing, uppercaseKing, p, lowerKingPos, upperKingPos);
            board=updateBoard(p, board);
        }
    }
    //Human vs Human 
    private void player2(char[][] board, piece[][] p, Scanner in, int[] upperKingPos, int[] lowerKingPos){
        boolean temp;   //Was the move valid
        int i=0; //Stalemate counter 
        char[] arr; //Coords of chosen piece to move
        char[] coords;  //Coords of where to go
        char[][] lowercaseKing; //Track danger for lower case king
        char[][] uppercaseKing; //Track danger for upper case king
        int player1 = 2;
        int player2= 1;

        lowercaseKing = new char[p.length][p.length];
        uppercaseKing = new char[p.length][p.length];
        while (true){
            printBoard(board);
            uppercaseKing=updateDangerForUpper(uppercaseKing, p, board, upperKingPos);
            lowercaseKing=updateDangerForLower(lowercaseKing, p, board, lowerKingPos);
            terminal(uppercaseKing, p, board, upperKingPos, lowercaseKing, lowerKingPos);
            temp =false;
            do{
                System.out.print("\nWhich piece would you like to move x,y: ");
                arr = in.next("[0-9],[0-9]").toCharArray();
                System.out.print("\nWhere do you want to move it x,y: ");
                coords = in.next("[0-9],[0-9]").toCharArray();
                if (arr.length!=3 || coords.length!=3) continue;
                if (arr[0]-48<board.length && arr[0]-48>=0 && arr[2]-48<board.length && arr[2]-48>=0 && coords[0]-48<board.length && coords[0]-48>=0 && coords[0]-48<board.length && coords[0]-48>=0){
                    if (p[arr[0]-48][arr[2]-48]==null){
                        System.out.println("Not a piece");
                    }
                    else if (p[arr[0]-48][arr[2]-48].getSym()<=90 && p[arr[0]-48][arr[2]-48].getSym()>=65 && player1%2==0){
                        System.out.println("Wrong Piece");
                    }
                    else if (p[arr[0]-48][arr[2]-48].getSym()<=122 && p[arr[0]-48][arr[2]-48].getSym()>=97 && player2%2==0){
                        System.out.println("Wrong Piece");
                    }
                    else {
                        if (p[arr[0]-48][arr[2]-48].getSym()=='K'){
                            temp = p[arr[0]-48][arr[2]-48].isValidMove(uppercaseKing, coords[0]-48, coords[2]-48, p);
                            if (temp==true){
                                if (coords[0]-48==0 && coords[2]-48==0){
                                    upperKingPos[0]=0;
                                    upperKingPos[1]=1;
                                }
                                else if (coords[0]-48==0 && coords[2]-48==7){
                                    upperKingPos[0]=0;
                                    upperKingPos[1]=5;
                                }
                                else {
                                    upperKingPos[0]=coords[0]-48;
                                    upperKingPos[1]=coords[2]-48;
                                }
                            }
                        } //Updates upper king pos
                        else if (p[arr[0]-48][arr[2]-48].getSym()=='k'){
                            temp = p[arr[0]-48][arr[2]-48].isValidMove(lowercaseKing, coords[0]-48, coords[2]-48, p);
                            if (temp==true){
                                if (coords[0]-48==7 && coords[2]-48==0){
                                    lowerKingPos[0]=7;
                                    lowerKingPos[1]=1;
                                }
                                else if (coords[0]-48==7 && coords[2]-48==7){
                                    lowerKingPos[0]=7;
                                    lowerKingPos[1]=6;
                                }
                                else {
                                    lowerKingPos[0]=coords[0]-48;
                                    lowerKingPos[1]=coords[2]-48;
                                }
                            }
                        }//updates power king pos
                        else {
                            temp = p[arr[0]-48][arr[2]-48].isValidMove(board, coords[0]-48, coords[2]-48, p);
                        }   //checks if chosen coords are possible
                    }
                }
            } while (temp==false);
            p[arr[0]-48][arr[2]-48].move(board, coords[0]-48, coords[2]-48, p, in);
            if (p[coords[0]-48][coords[2]-48]==null) i++;
            else i=0;
            if (i==75)break;
            board=updateBoard(p, board);
            player1++;
            player2++;
        }
    }
    //Calculates the danger for the lowercase king 
    private char[][] updateDangerForLower(char[][] lowercaseKing, piece[][] p, char[][] board, int[] lowerKingPos){
        int[][] temp;
        for (int i=0;i<p.length;i++){
            for (int j=0;j<p.length;j++){
                if (board[i][j]!=0){
                    if (lowercaseKing[i][j]!='.'){
                        lowercaseKing[i][j]=board[i][j];
                    }
                }
                if (board[i][j]<=90 && board[i][j]>=65){
                    temp = p[i][j].possibleMoves(board, p);
                    for (int x=0;x<temp.length;x++){
                        if (temp[x][0]==-1) break;
                        if (board[i][j]=='P'){
                            if(i+1<board.length && j+1<board.length && board[i+1][j+1]==0){
                            lowercaseKing[i+1][j+1]='.';
                            }
                            if (i+1<board.length && j-1>-1 && board[i+1][j-1]==0){
                                lowercaseKing[i+1][j-1]='.';
                            }
                            break;
                        }
                        else {
                            lowercaseKing[temp[x][0]][temp[x][1]]='.';
                        }
                    }
                }
            }
        }

        return lowercaseKing;
    }
    //Calculates the danger for the uppercase king 
    private char[][] updateDangerForUpper(char[][] uppercaseKing, piece[][] p, char[][] board, int[] upperKingPos){
        int[][] temp;
        for (int i=0;i<p.length;i++){
            for (int j=0;j<p.length;j++){
                if (board[i][j]!=0){
                    uppercaseKing[i][j]=board[i][j];
                }
                if (board[i][j]<=122 && board[i][j]>=97){
                    temp = p[i][j].possibleMoves(board, p);
                    for (int x=0;x<temp.length;x++){
                        if (temp[x][0]==-1) break;
                        if (board[i][j]=='p'){
                            if(i-1>-1 && j+1<board.length && board[i-1][j+1]==0){
                                uppercaseKing[i-1][j+1]='.';
                            }
                            if (i-1>-1 && j-1>-1 && board[i-1][j-1]==0){
                                uppercaseKing[i-1][j-1]='.';
                            }
                            break;
                        }
                        uppercaseKing[temp[x][0]][temp[x][1]]='.';
                    }
                }
            }
        }

        return uppercaseKing;
    }
    //Checks to see if either lowercase king or upper case king is check or checkmate
    private void terminal(char[][] uppercaseKing, piece[][] p, char[][] board, int[] upperKingPos, char[][] lowercaseKing, int[] lowerKingPos){
        int[][] result = p[upperKingPos[0]][upperKingPos[1]].possibleMoves(uppercaseKing, p);
        int num = 0;
        for (int i=0;i<result.length;i++){
            if(result[i][0]==-1) break;
            num++;
        }
        if (uppercaseKing[upperKingPos[0]][upperKingPos[1]]=='.' && num==0){
            System.out.println("!!! Checkmate !!!");
            System.out.println("Player 1 Wins");
            in.close();
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
        else if (uppercaseKing[upperKingPos[0]][upperKingPos[1]]=='.'){
            System.out.println("!!! Check !!!");
        }

        result = p[lowerKingPos[0]][lowerKingPos[1]].possibleMoves(lowercaseKing, p);
        num = 0;
        for (int i=0;i<result.length;i++){
            if(result[i][0]==-1) break;
            num++;
        }
        if (lowercaseKing[lowerKingPos[0]][lowerKingPos[1]]=='.' && num==0){
            System.out.println("!!! Checkmate !!!");
            System.out.println("Player 2 Wins");
            in.close();
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
        else if (lowercaseKing[lowerKingPos[0]][lowerKingPos[1]]=='.'){
            System.out.println("!!! Check !!!");
        }
    }
    //Turns the piece board into a character board to be displayed
    public char[][] updateBoard(piece[][] p, char[][] board){
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){
                if (p[i][j]==null){
                    board[i][j]=0;
                }
                else {
                    board[i][j]=p[i][j].getSym();
                }
            }
        }
        return board;
    }
    //Turns the character board into a piece board 
    private piece[][] createPieces(char[][] board, int[] upperKingPos, int[] lowerKingPos){
        piece[][] p;

        p = new piece[board.length][board.length];
        for (int i=0;i<p.length;i++){
            for (int j=0;j<p.length;j++){
                if (board[i][j]=='R' || board[i][j]=='r'){
                    p[i][j]=new rook(i, j, board[i][j]);
                }
                else if(board[i][j]=='H' || board[i][j]=='h'){
                    p[i][j]=new knight(i, j, board[i][j]);
                }
                else if(board[i][j]=='B' || board[i][j]=='b'){
                    p[i][j]=new bishop(i, j, board[i][j]);
                }
                else if(board[i][j]=='Q' || board[i][j]=='q'){
                    p[i][j]=new queen(i, j, board[i][j]);
                }
                else if(board[i][j]=='K' || board[i][j]=='k'){
                    if(board[i][j]=='K'){
                        upperKingPos[0]=i;
                        upperKingPos[1]=j;
                    }
                    else if (board[i][j]=='k'){
                        lowerKingPos[0]=i;
                        lowerKingPos[1]=j;
                    }
                    p[i][j]=new king(i, j, board[i][j]);
                }
                else if(board[i][j]=='P' || board[i][j]=='p'){
                    p[i][j]=new pawn(i, j, board[i][j]);
                }
                else{
                    p[i][j]=null;
                }
            }
        }
        return p;
    }
    // Prints the board
    private void printBoard(char[][] board){
        int inc = 0;
        try{
            System.out.println("\n");
            out.write("\n");
            for (int x=0;x<=7;x++){
                System.out.print("\t"+x+"\t");
            }
            for (int i=0;i<board.length;i++){
                System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------\n");
                out.write("\n --------------------------------------------------------------------------------------------------------------------------------\n");
                System.out.print(inc++);
                for(int j=0;j<board.length;j++){
                    if (board[i][j]==0){
                        System.out.print("|\t\t");
                        out.write("|\t\t");
                    }
                    else {
                        System.out.print("|\t"+board[i][j]+"\t");
                        out.write("|\t"+board[i][j]+"\t");
                    }
                }
                System.out.print("|");
                out.write("|");
            }
            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------\n");
            out.write("\n --------------------------------------------------------------------------------------------------------------------------------\n");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main (String args[]){new chess();}
}
