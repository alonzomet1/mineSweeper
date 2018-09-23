package CONTROL;

import MODEL.Model;

public class Timer extends Thread {
    private int Sec;
    private int Min;
    public void run(){
        while(BoardControl.gameOver == false)
        {
            try {
                Thread.sleep(1000);
                Sec++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Model.updateTextClock(this.getTimeStr());
        }
    }
    public String getTimeStr()
    {
        Min = Sec / 60;
        String strSec = Integer.toString(Sec % 60);
        String strMin = Integer.toString(Min);
        if(Sec < 10)
        {
            strSec = "0" + strSec;
        }
        if(Min < 10)
        {
            strMin = "0" + strMin;
        }
        return strMin + ":" + strSec;
    }
}
