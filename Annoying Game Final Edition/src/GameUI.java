import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI extends JFrame{
    private Background it = new Background();

    public GameUI()
    {
        getContentPane().add(it);

        it.setFocusable(true);
    }
    
    public boolean getWon()
    {
    	if (it.transfer())
    	{
    		it.set();
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////



class Background extends JPanel implements KeyListener{
    private int x = (int)(Math.random()*270);
    private int y = (int)(Math.random()*270);
    private int specialX = (int)(Math.random()*270);
    private int specialY = (int)(Math.random()*270);
    private int rounds = 1;
    private boolean win = false;
    int[] xpositions = {0,390,50,165,115,105,95,55,335,280,200};
    int[] ypositions = {10,0,400,195,115,105,275,345,285,60,290};
    private ArrayList<Integer> listers = new ArrayList<Integer>();
    public Background()
    {
        addKeyListener(this);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Color c = new Color(165, 181, 240);
        g.setColor(c);
        //Border
        g.fillRect(0, 10, 10, 400);
        g.fillRect(390, 10, 10, 400);
        g.fillRect(0, 0, 400, 10);
        g.fillRect(0, 400, 400, 10);
       
       
        while((specialX <= x + 20 && specialX >= x - 20) && (specialY <= y + 20 && specialY >= y - 20))
        {
            specialX = (int)(Math.random()*300)+35;
            specialY = (int)(Math.random()*300)+35;
            rounds++;
            win = true;
            if (rounds >= 2)
            {
                listers = new ArrayList<Integer>();
                boolean checker;
                while (listers.size() != 4)
                {
                    checker = true;
                    int thing = (int)((Math.random()*26) + 65);
                    for (int i = 0; i < listers.size();i++)
                    {
                        if(thing == listers.get(i))
                        {
                            checker = false;
                        }
                    }
                    if (checker == true)
                    {
                        listers.add(thing);
                        System.out.println(thing);
                    }
                }
            }
            if (rounds >= 5)
            {
                listers = new ArrayList<Integer>();
                boolean checker = true;
                while (listers.size() != 4)
                {
                    checker = true;
                    int thing = (int)((Math.random()*40) + 49);
                    while (thing > 57 && thing < 65)
                    {
                        thing = (int)((Math.random()*40) + 49);
                    }
                    for (int i = 0; i < listers.size();i++)
                    {
                        if(thing == listers.get(i))
                        {
                            checker = false;
                        }
                    }
                    if (checker == true)
                    {
                        listers.add(thing);
                        System.out.println(thing);
                    }

                }
            }
            if (rounds >= 10)
            {
                listers = new ArrayList<Integer>();
                boolean checker = true;
                while (listers.size() != 4)
                {
                    checker = true;
                    int thing = (int)((Math.random()*183) + 8);
                    while ((thing > 8 && thing < 13) || (thing > 13 && thing < 16) || (thing > 20 && thing < 27) || (thing > 27 && thing < 32) || (thing > 32 && thing < 44) || (thing > 46 && thing < 48) || (thing > 57 && thing < 64) || (thing > 90 && thing < 112) || (thing > 123 && thing < 145) || (thing > 145 && thing < 186) || (thing == 187) || (thing == 189) || (thing > 192 && thing < 219) || (thing >= 224))
                    {
                        thing = (int)((Math.random()*183) + 8);
                    }
                    for (int i = 0; i < listers.size();i++)
                    {
                        if(thing == listers.get(i))
                        {
                            checker = false;
                        }
                    }
                    if (checker == true)
                    {
                        listers.add(thing);
                        System.out.println(thing);
                    }

                }
            }

        }

        Graphics2D goal = (Graphics2D) g;
        goal.setColor(Color.orange);
        goal.fillOval(specialX, specialY, 20, 20);
        Graphics2D player = (Graphics2D) g;
        player.setColor(Color.blue);
        player.fillRect(x, y, 25, 25);
    }
    public void rePainterY(int a)
    {
    	if (!(collisionCheckerY(a)))
    	{
    		y += a;
            repaint();
    	}
    	else
    	{
    		y = 365;
    	}
        
    }
    public void rePainterX(int a)
    {
    	if (!(collisionCheckerX(a)))
    	{
    		x += a;
            repaint();
    	}
    	else
    	{
    		x = 365;
    	}
       
    }
    public void rePainternegY(int a)
    {
    	if (!(collisionCheckernegY(a)))
    	{
    		y += a;
            repaint();
    	}
    	else
    	{
    		y = 20;
    	}
        
    }
    public void rePainternegX(int a)
    {
    	if (!(collisionCheckernegX(a)))
    	{
    		x += a;
            repaint();
    	}
    	else
    	{
    		x = 20;
    	}
       
    }
    public void keyPressed(KeyEvent e)
    {
        int controllerUP;
        int controllerDOWN;
        int controllerLEFT;
        int controllerRIGHT;
        	if ((x >= 30 && x <= 350) || (y >= 30 && y <= 350))
        		if (rounds == 1)
        		{
        			switch (e.getKeyCode())
        			{
        				case KeyEvent.VK_DOWN: rePainterY(10); break;
        				case KeyEvent.VK_UP: rePainternegY(-10); break;
        				case KeyEvent.VK_LEFT: rePainternegX(-10); break;
        				case KeyEvent.VK_RIGHT: rePainterX(10); break;
        			}
        		}
        		else if (rounds >= 2)
        		{
        			controllerUP = listers.get(0);
        			controllerDOWN = listers.get(1);
        			controllerLEFT = listers.get(2);
        			controllerRIGHT = listers.get(3);

        			if (e.getKeyCode() == controllerUP)
        			{
        				rePainternegY(-10);
        			}
        			else if (e.getKeyCode() == controllerDOWN)
        			{
        				rePainterY(10);
        			}
        			else if (e.getKeyCode() == controllerLEFT)
        			{
        				rePainternegX(-10);
        			}
        			else if (e.getKeyCode() == controllerRIGHT)
        			{
        				rePainterX(10);
        			}
        		}
        		
        		repaint();
        	}



    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }
    
    public boolean transfer()
    {
    	return win;
    }
    
    public void set()
    {
    	win = false;
    }
    
    public boolean collisionCheckernegX(int a)
    {
    	boolean checker = false;
    	if (x - a <= 20)
    	{
    		checker = true;
    	}
    	
    	return checker;
    }
    public boolean collisionCheckernegY(int a)
    {
    	boolean checker = false;
    	if (y - a <= 20)
    	{
    		checker = true;
    	}
    	
    	return checker;
    }
    public boolean collisionCheckerX(int a)
    {
    	boolean checker = false;
    	if( x + a >= 375)
    	{
    		checker = true;
    	}
    	return checker;
    	
    }
    public boolean collisionCheckerY(int a)
    {
    	boolean checker = false;
    	if( y + a >= 375)
    	{
    		checker = true;
    	}
    	return checker;
    }
}
//////////////////////////////////////////////////////////////////////////////////////






/* //maze
        g.fillRect(50, 0, 15, 195);//first
        g.fillRect(50, 195, 130, 15);
        g.fillRect(165, 115, 15, 85);
        g.fillRect(115, 105, 65, 15);
        g.fillRect(105, 105, 15, 55);

        g.fillRect(0, 275, 95, 15);//second
        g.fillRect(95, 275, 15, 85);
        g.fillRect(55, 345, 40, 15);

        g.fillRect(335, 0, 15, 285);//last
        g.fillRect(280, 285, 70, 15);

        g.fillRect(280, 60, 15, 240);

        g.fillRect(200, 290, 15, 110);*/

