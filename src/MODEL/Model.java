package MODEL;

import CONST.Const;
import CONTROL.BoardControl;
import CONTROL.Timer;
import UI.BUTTONS.BtnChooser;
import UI.BUTTONS.BtnGame;
import UI.BUTTONS.FlagBtn;
import UI.JTEXT.Prompter;
import UI.PANELS.ExtendPanel;
import UI.PANELS.GamePanel;
import UI.PANELS.MenuPanel;
import UI.JTEXT.TextClock;

import java.awt.*;

public class Model {
    static Timer clock;
    static TextClock textClock;
    static Prompter prompter;
    static BtnGame[][] uiGameBtn;
    static BtnChooser[] uiMenuBtn;
    static GamePanel gamePanel;
    static MenuPanel menuPanel;
    static ExtendPanel extendPanel;
    static FlagBtn flagBtn;
    static UI.Screen mainScreen;
    static boolean flagMode = false;


    public static void initiateScreen()
    {
        mainScreen = new UI.Screen("mineSweeper");
    }


    public static void initiateMenu()
    {
        menuPanel = new MenuPanel();
        mainScreen.addComp(menuPanel.getPanel());
        initiateMenuButtons();
        mainScreen.packAndShow();
    }

    public static void closeMenu()
    {
        menuPanel.close();
        for(int i = 0; i < Const.HARD; i++)
        {
            menuPanel.getPanel().remove(uiMenuBtn[i].getButton());
        }
        mainScreen.getScreen().remove(menuPanel.getPanel());
    }
    public static void updateTextClock(String time)
    {
        textClock.updateTime(time);
    }

    public static void initiateGame(int diffculty)
    {
        BoardControl.initiateBoard(diffculty);
        mainScreen.adjustScreenLayout();
        initiateGameUI();
        initiateExtendPanel();
        mainScreen.addCompo(extendPanel.getPanel());
        mainScreen.addComp(gamePanel.getPanel());
        gamePanel.showPanel();
        clock = new Timer();//start counting
        clock.start();
        //System.out.println(clock.getTimeStr());
    }

    public static void initiateExtendPanel() {
       extendPanel = new ExtendPanel();
       prompter = new Prompter();
       textClock = new TextClock();
       flagBtn = new FlagBtn();
       extendPanel.getPanel().add(flagBtn, BorderLayout.CENTER);
       extendPanel.getPanel().add(textClock, BorderLayout.EAST);
       extendPanel.getPanel().add(prompter, BorderLayout.WEST);
    }

    public static void initiateGameUI()
    {
        gamePanel = new GamePanel();
        uiGameBtn = new BtnGame[Const.col][Const.row];
        for (int i = 0; i < Const.col; i++)
        {
            for (int j = 0; j < Const.row; j++)
            {
                uiGameBtn[i][j] = new BtnGame();
                String bttName = Integer.toString(i) + "," + Integer.toString(j);
                uiGameBtn[i][j].getButton().setName(bttName);
                gamePanel.addComp(uiGameBtn[i][j].getButton());
            }
        }
        mainScreen.packAndShow();
    }


    public static void updateUIBoard(int y, int x)
    {
        int[][] board = BoardControl.updateUiBoard(y, x);
        for (int i = 0; i < Const.col; i++)
        {
            for (int j = 0; j < Const.row; j++)
            {
                uiGameBtn[i][j].setButtonState(board[i][j]);
            }
        }
    }
    public static void updateUI(int y, int x)
    {
       updateUIBoard(y, x);
       if(BoardControl.gameIsLost())
       {
           prompter.setCurrText("Loser!!");
       }
       else if(BoardControl.gameIsWon())
       {
           prompter.setCurrText("You didnt lose");
       }
    }


    public static void initiateMenuButtons()
    {
        Font bold = new Font("Arial", Font.BOLD, Const.btnTxtSize);
        uiMenuBtn = new BtnChooser[Const.HARD];
        for(int i = 0; i < Const.HARD; i++)
        {
            uiMenuBtn[i] = new BtnChooser(i + 1);
            menuPanel.addComp(uiMenuBtn[i].getButton());
        }
        uiMenuBtn[0].setText("EASY");
        uiMenuBtn[1].setText("MODERATE");
        uiMenuBtn[2].setText("HARD");
        uiMenuBtn[0].setFont(bold);
        uiMenuBtn[1].setFont(bold);
        uiMenuBtn[2].setFont(bold);
    }
     public static void reverseFlagMode()
    {
        flagMode = !flagMode;
    }
    public static boolean getFlagMode()
    {
        return flagMode;
    }
    public static void turnBtnToFlag(int y, int x)
    {
        if((BoardControl.getUiBoard())[y][x] == Const.HIDDEN)
        {
            BoardControl.enteredFlag(y, x);
            uiGameBtn[y][x].setButtonState(13); //red flag
        }
    }
}
