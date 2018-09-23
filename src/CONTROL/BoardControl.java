package CONTROL;

import CONST.Const;
import MAIN.Run;

public class BoardControl {
    static int[][] board;
    static int[][] assistBoard;
    static int[][] uiBoard;
    static boolean lostGame = false;
    static boolean wonGame = false;
    static boolean gameOver = false; //var for timer to know when game finished
    public static int[][] getUiBoard()
    {
        return uiBoard;
    }
    public static boolean gameIsLost()
    {
        return lostGame;
    }
    public static boolean gameIsWon()
    {
        return wonGame;
    }
    public static void initiateBoard(int difficulty)
    {
        board = new int[Const.col][Const.row];
        assistBoard = new int[Const.col + 2][Const.row + 2];
        uiBoard = new int[Const.col][Const.row];
        initiateMines(difficulty);
        initiateBoardAssist();
        initiateRest();
        Run.printArr(board);
        System.out.println("aa");
        initiateUiBoard();
        Run.printArr(uiBoard);
    }
    public static int[][] updateUiBoard(int y, int x)
    {
        if(board[y][x] == Const.EMPTY)
        {
            GetNearEmptyBlocks(y, x);
        }
        else if(board[y][x] == Const.MINE) //game lost
        {
            transfromUIBoardToLost();
            lostGame = true;
            gameOver = true;
        }
        else
        {
            uiBoard[y][x] = board[y][x];
        }
        if(!lostGame)
        {
            checkWonGame();
        }
        if(wonGame)
        {
            gameOver = true;
            return board;
        }
        return uiBoard;
    }


    //update all near empty block on uiboard
    public static void GetNearEmptyBlocks(int y, int x)
    {
        if(x == Const.row || x < 0 || y == Const.col || y < 0) { }
        else
        {
            if(board[y][x] == Const.EMPTY && (uiBoard[y][x] == Const.HIDDEN || uiBoard[y][x] == Const.FLAG))
            {
                uiBoard[y][x] = Const.EMPTY;
                GetNearEmptyBlocks(y + 1, x);
                GetNearEmptyBlocks(y - 1, x);
                GetNearEmptyBlocks(y, x + 1);
                GetNearEmptyBlocks(y,x - 1);
            }
        }
    }

    public static void initiateUiBoard()
    {
        for(int i = 0; i < Const.col; i++)
        {
            for(int j = 0; j < Const.row; j++)
            {
                uiBoard[i][j] = Const.HIDDEN;
            }
        }
    }

    public static void initiateMines(int difficulty)
    {
        int y, x;
        board = new int[Const.col][Const.row];
        int sumMines = difficulty * Const.col * Const.row / Const.k;
        //generate mines on board
        if (sumMines < Const.row * Const.col)
        {
            for(int i = 0; i < sumMines; i++)
            {
                x = randomWithRange(0, Const.row - 1);
                y = randomWithRange(0, Const.col - 1);
                if(board[y][x] != Const.MINE)
                {
                    board[y][x] = Const.MINE;
                }
                else //when is already mine, run one more time
                {
                    i--;
                }
            }
        }
    }


    public static void initiateBoardAssist()
    {
        for(int i = 1; i <= Const.col; i++)
        {
            for(int j = 1; j <= Const.row; j++)
            {
                assistBoard[i][j] = board[i - 1][j - 1];
            }
        }
    }


    //initiate every block that is not mine
    public static void initiateRest()
    {
        int blockVal;
        for(int y = 0; y < Const.col; y++)
        {
            for(int x = 0; x < Const.row; x++)
            {
                if(board[y][x] != Const.MINE)
                {
                    blockVal = 0;
                    blockVal += (assistBoard[y][x] == Const.MINE) ? 1 : 0;
                    blockVal += (assistBoard[y][x + 1] == Const.MINE) ? 1 : 0;
                    blockVal += (assistBoard[y][x + 2] == Const.MINE) ? 1 : 0;
                    blockVal += (assistBoard[y + 1][x] == Const.MINE) ? 1 : 0;
                    blockVal += (assistBoard[y + 1][x + 1] == Const.MINE) ? 1 : 0;
                    blockVal += (assistBoard[y + 1][x + 2] == Const.MINE) ? 1 : 0;
                    blockVal += (assistBoard[y + 2][x] == Const.MINE) ? 1 : 0;
                    blockVal += (assistBoard[y + 2][x + 1] == Const.MINE) ? 1 : 0;
                    blockVal += (assistBoard[y + 2][x + 2] == Const.MINE) ? 1 : 0;
                    board[y][x] = blockVal;
                }
            }
        }
    }


    public static void transfromUIBoardToLost()
    {
        for (int i = 0; i < Const.col; i++)
        {
            for (int j = 0; j < Const.row; j++)
            {
                if (board[i][j] == Const.MINE)
                {
                    uiBoard[i][j] = Const.ACCIDENTAL_MINE;
                }
                else
                {
                    uiBoard[i][j] = board[i][j];
                }
            }
        }
    }


    public static void checkWonGame()
    {
        wonGame = true;
        for (int i = 0; i < Const.col && wonGame; i++)
        {
            for (int j = 0; j < Const.row && wonGame; j++)
            {
                if(uiBoard[i][j] != board[i][j] && board[i][j] != Const.MINE) //doesnt matter if it is a mine or not because lost game is deployed first
                {
                    wonGame = false;
                }
            }
        }
    }


    public static void enteredFlag(int y, int x)
    {
        uiBoard[y][x] = Const.FLAG;
    }

    //easy random number generator
    private static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
