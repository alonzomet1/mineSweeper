package CONTROL;

import CONST.Const;

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
    public static void initiateBoard(int difficulty, int row, int col)
    {
        board = new int[row][col];
        assistBoard = new int[row + 2][col + 2];
        uiBoard = new int[row][col];
        initiateMines(difficulty, row, col);
        initiateBoardAssist(row, col);
        initiateRest(row, col);
        initiateUiBoard(row, col);
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
        if(x == Const.col || x < 0 || y == Const.row || y < 0) { }
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

    public static void initiateUiBoard(int row, int col)
    {
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                uiBoard[i][j] = Const.HIDDEN;
            }
        }
    }

    public static void initiateMines(int difficulty, int row, int col)
    {
        int y, x;
        board = new int[row][col];
        int sumMines = difficulty * Const.col * Const.row / Const.k;
        //generate mines on board
        if (sumMines < row * col)
        {
            for(int i = 0; i < sumMines; i++)
            {
                x = randomWithRange(0, col - 1);
                y = randomWithRange(0, row - 1);
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


    public static void initiateBoardAssist(int row, int col)
    {
        for(int i = 1; i <= row; i++)
        {
            for(int j = 1; j <= col; j++)
            {
                assistBoard[i][j] = board[i - 1][j - 1];
            }
        }
    }


    //initiate every block that is not mine
    public static void initiateRest(int row, int col)
    {
        int blockVal;
        for(int y = 0; y < row; y++)
        {
            for(int x = 0; x < col; x++)
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
        for (int i = 0; i < Const.row; i++)
        {
            for (int j = 0; j < Const.col; j++)
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
        for (int i = 0; i < Const.row && wonGame; i++)
        {
            for (int j = 0; j < Const.col && wonGame; j++)
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
