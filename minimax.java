public class minimax {
    int[] pCoords;
    int[] moveTo;
    public piece[][] minimax_decision(char[][] board, char[][] lowercaseKing, char[][] uppercaseKing, piece[][]p, int[] lowerKingPos, int[] upperKingPos){
        int depth =0;

        pCoords= new int[2];
        moveTo = new int[2];
        max_value(-2147483648,2147483647,depth, board, lowercaseKing, uppercaseKing, p, lowerKingPos, upperKingPos);
        p = p[pCoords[0]][pCoords[1]].move(board, moveTo[0], moveTo[1], p, null);
        return p;
    }

    private int max_value(int alpha, int beta, int depth, char[][] board, char[][] lowercaseKing, char[][] uppercaseKing, piece[][]p, int[] lowerKingPos, int[] upperKingPos){
        int u;
        int tempu;
        int[][] possible;
        piece[][] temp;
        char[][] boardTemp;
        chess c = new chess(depth);
        if (depth == 3 || updateDangerForLower(lowercaseKing, p, board, lowerKingPos)==2 || updateDangerForUpper(uppercaseKing, p, board, upperKingPos)==2) 
            return heuristic(board, p, lowercaseKing, uppercaseKing, lowerKingPos, upperKingPos);
        u=-2147483648;
        tempu=-2147483648;
        temp = new piece[board.length][board.length];
        boardTemp = new char[board.length][board.length];
        int[] kingPosTemp = {upperKingPos[0],upperKingPos[1]};
        for (int i=0;i<board.length;i++){
            for (int j=0; j<board.length;j++){
                if (p[i][j]!= null && p[i][j].getSym()<=90 && p[i][j].getSym()>=65){
                    if (board[i][j]=='K'){
                        possible = p[i][j].possibleMoves(uppercaseKing, p);
                    }
                    else{
                        possible = p[i][j].possibleMoves(board, p);
                    }
                    for (int x=0; x<possible.length;x++){
                        if (possible[x][0]==-1) break;
                        for (int y=0; y<board.length;y++){
                            for (int k=0;k<board.length;k++){
                                temp[y][k] = p[y][k];
                                boardTemp[y][k]=board[y][k];
                            }
                        }
                        temp = p[i][j].move(board, possible[x][0], possible[x][1], temp, null);
                        boardTemp = c.updateBoard(temp, boardTemp);
                        if (boardTemp[i][j]=='K'){
                            kingPosTemp[0]= upperKingPos[0];
                            kingPosTemp[1]=upperKingPos[1];
                            upperKingPos[0]=i;
                            upperKingPos[1]=j;
                        }
                        u=max(u,min_value(alpha, beta, ++depth, boardTemp, lowercaseKing, uppercaseKing, temp, lowerKingPos, upperKingPos));
                        depth--;
                        upperKingPos[0] = kingPosTemp[0];
                        upperKingPos[1] = kingPosTemp[1];
                        if (u!=tempu && depth==0){
                            pCoords[0]=i;
                            pCoords[1]=j;
                            moveTo[0]=possible[x][0];
                            moveTo[1]=possible[x][1];
                            tempu=u;
                        }
                        if (u>=beta) return u;
                        alpha = max(alpha, u);
                    }
                }
            }
        }
        return u;
    }
    private int min_value(int alpha, int beta, int depth, char[][] board, char[][] lowercaseKing, char[][] uppercaseKing, piece[][]p, int[] lowerKingPos, int[] upperKingPos){
        int u;
        int[][] possible;
        piece[][] temp;
        char[][] boardTemp;
        chess c = new chess(depth);
        if (depth == 3 || updateDangerForLower(lowercaseKing, p, board, lowerKingPos)==2 || updateDangerForUpper(uppercaseKing, p, board, upperKingPos)==2) 
            return heuristic(board, p, lowercaseKing, uppercaseKing, lowerKingPos, upperKingPos);
        u=2147483647;
        temp = new piece[board.length][board.length];
        boardTemp = new char[board.length][board.length];
        int[] kingPosTemp = {lowerKingPos[0],lowerKingPos[1]};
        for (int i=0;i<board.length;i++){
            for (int j=0; j<board.length;j++){
                if (p[i][j]!= null && p[i][j].getSym()<=122 && p[i][j].getSym()>=97){
                    if (board[i][j]=='k'){
                        possible = p[i][j].possibleMoves(uppercaseKing, p);
                    }
                    else{
                        possible = p[i][j].possibleMoves(board, p);
                    }
                    for (int x=0; x<possible.length;x++){
                        if (possible[x][0]==-1) break;
                        for (int y=0; y<board.length;y++){
                            for (int k=0;k<board.length;k++){
                                temp[y][k] = p[y][k];
                                boardTemp[y][k]=board[y][k];
                            }
                        }
                        temp = p[i][j].move(board, possible[x][0], possible[x][1], temp, null);
                        boardTemp = c.updateBoard(temp, boardTemp);
                        if (boardTemp[i][j]=='k'){
                            kingPosTemp[0]= lowerKingPos[0];
                            kingPosTemp[1]= lowerKingPos[1];
                            lowerKingPos[0]=i;
                            lowerKingPos[1]=j;
                        }
                        u=min(u,max_value(alpha, beta, ++depth, boardTemp, lowercaseKing, uppercaseKing, temp, lowerKingPos, upperKingPos));
                        lowerKingPos[0] = kingPosTemp[0];
                        lowerKingPos[1] = kingPosTemp[1];
                        depth--;
                        if (u<=alpha) return u;
                        beta = min(beta, u);
                    }
                }
            }
        }
        return u;
    }
    private int max(int u, int min){
        return u<=min?min:u;
    }
    private int min(int u, int max){
        return u>=max?max:u;
    }
    
    private int heuristic(char[][] board, piece[][] p, char[][] lowercaseKing, char[][] uppercaseKing, int[] lowerKingPos, int[] upperKingPos){
        int result;
        result =0;
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){
                if (board[i][j]=='p') result-=10;
                else if (board[i][j]=='h') result-=30;
                else if (board[i][j]=='b') result-=30;
                else if (board[i][j]=='r') result-=50;
                else if (board[i][j]=='q') result-=90;
                else if (updateDangerForUpper(uppercaseKing, p, board, upperKingPos)==2) result-=20000;
                else if (updateDangerForUpper(uppercaseKing, p, board, upperKingPos)==1) result-=1000;
                else if (board[i][j]=='P') result+=10;
                else if (board[i][j]=='H') result+=30;
                else if (board[i][j]=='B') result+=30;
                else if (board[i][j]=='R') result+=50;
                else if (board[i][j]=='Q') result+=90;
                else if (updateDangerForLower(lowercaseKing, p, board,lowerKingPos)==2) result+=20000; 
                else if (updateDangerForLower(lowercaseKing, p, board, lowerKingPos)==1) result+=1000;
            }
        }
        return result;
    }
    public int updateDangerForLower(char[][] lowercaseKing, piece[][] p, char[][] board, int[] lowerKingPos){
        int[][] temp;
        lowercaseKing = new char[board.length][board.length];

        for (int i=0;i<p.length;i++){
            for (int j=0;j<p.length;j++){
                if (board[i][j]!=0){
                    if (lowercaseKing[i][j]!='.'){
                        lowercaseKing[i][j]=board[i][j];
                    }
                }
                if (board[i][j]=='k'){
                    lowerKingPos[0]=i;
                    lowerKingPos[1]=j;
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
        int[][] result = p[lowerKingPos[0]][lowerKingPos[1]].possibleMoves(lowercaseKing, p);
        int num = 0;
        for (int i=0;i<result.length;i++){
            if(result[i][0]==-1) break;
            num++;
        }
        if (lowercaseKing[lowerKingPos[0]][lowerKingPos[1]]=='.' && num==0){
            return 2;//Checkmate
        }
        else if (lowercaseKing[lowerKingPos[0]][lowerKingPos[1]]=='.'){
            return 1;//Check
        }
        return 0;
    }
    public int updateDangerForUpper(char[][] uppercaseKing, piece[][] p, char[][] board, int[] upperKingPos){
        int[][] temp;
        uppercaseKing = new char[board.length][board.length];
        for (int i=0;i<p.length;i++){
            for (int j=0;j<p.length;j++){
                if (board[i][j]!=0){
                    uppercaseKing[i][j]=board[i][j];
                }
                if (board[i][j]=='K'){
                    upperKingPos[0]=i;
                    upperKingPos[1]=j;
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
        int[][] result = p[upperKingPos[0]][upperKingPos[1]].possibleMoves(uppercaseKing, p);
        int num = 0;
        for (int i=0;i<result.length;i++){
            if(result[i][0]==-1) break;
            num++;
        }
        if (uppercaseKing[upperKingPos[0]][upperKingPos[1]]=='.' && num==0){
            return 2; //checkmate
        }
        else if (uppercaseKing[upperKingPos[0]][upperKingPos[1]]=='.'){
            return 1;//check
        }
        return 0;
    }
}
