import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game extends JFrame{


    public Game (){

        //Create JFrame
        super("Game");
        setLayout(new BorderLayout());
        setLocation(100,100);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //First screen that user sees
        //Welcomes user
        JPanel p1 = new JPanel();
        JPanel p1_1 = new JPanel();
        JLabel l1 = new JLabel("Welcome to Keyboard Destroyer");
        JButton b1 = new JButton("OK");
        l1.setFont(new Font("TimesRoman", Font.BOLD,30));

        p1.add(l1);
        p1_1.add(b1);
        add(p1, BorderLayout.CENTER);
        add(p1_1, BorderLayout.SOUTH);

        //JPanel for instructions
        JPanel p2 = new JPanel();
        JPanel p2_1 = new JPanel(new GridLayout(1,2,40,40));
        JTextArea text = new JTextArea("  Instructions" +
                "\n\n  You have 60 seconds to:" +
                "\n  Find the corresponding keys to move: Up, Down, Left or Right" +
                "\n  Navigate through the maze and get to the circle" +
                "\n  AVOID touching the maze" +
                "\n  Every round, the movement functionalities are assigned to new keys " +
                "\n\n  Good Luck and Have Fun!" );
        JButton begin = new JButton("Let's Begin");
        JButton quit = new JButton("Quit");

        text.setEditable(false);
        text.setFont(new Font("TimesRoman",Font.BOLD, 15));
        Color color = new Color(255, 255, 0);
        text.setBackground(color);

        p2.add(text);
        p2_1.add(begin);
        p2_1.add(quit);

        //Action for button 1
        //Click "OK" and change panel
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(p1);
                remove(p1_1);
                add(p2, BorderLayout.CENTER);
                add(p2_1, BorderLayout.SOUTH);
                validate();
            }
        });

        //Action for button 2
        //Click "OK" and change panel
        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(p2);
                remove(p2_1);
                dispose();

                GameUI thing = new GameUI();
                thing.setSize(415,445);
                thing.setVisible(true);
                Time t = new Time(thing);
                
                
              
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

    }

}
