package MAIN;
import CONST.Const;
import CONTROL.BoardControl;
import CONTROL.Timer;
import MODEL.Model;
import UI.*;

import javax.jws.WebParam;

public class Run {
    public static void main(String[] args) {
        Model.initiateScreen();
        Model.initiateMenu();
    }
    public static void printArr(int[][] a)
    {
        for(int i = 0; i < Const.col; i++)
        {
            for(int j = 0; j < Const.row; j++)
            {
                System.out.print(a[i][j] + ",");
            }
            System.out.println();
        }
    }
}
