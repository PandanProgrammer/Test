
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Time extends GameUI {
    JLabel display;
    boolean checker = false;
    public Time(GameUI a){
    	
        display = new JLabel();
        add(display);

        display.setBounds(70, 20, 100, 100);
        display.setFont(new Font("TimesRoman", Font.BOLD, 40));

        final Timer t = new Timer();
        int delay = 0;
        int period = 1000;
        t.scheduleAtFixedRate(new TimerTask() {
            int i = 60;
            
            public void run() {
                display.setText(""+(i--));
                if(a.getWon())
                {
                	reset();
                }
                if (checker == true)
                {
                	i = 60;
                	checker = false;
                }
                if (i < 0) {
                    JOptionPane.showMessageDialog(null, "Game Over");
                    t.cancel();
                }
            }
            }, delay, period);

        setSize(200,200);
        setLocation(900,200);
        setLayout(null);
        setVisible(true);
    }
    
    public void reset()
    {
    	checker = true;
    }

}