package belegarbeit2;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 *
 * @author Simon Arnold, Jan Didschuneit
 * @version 0.2
 */

public class Belegarbeit2 extends JFrame {

    private JLabel statusBar, player1Label, player2Label, player1StonesLabel, player2StonesLabel, activePlayerLabel;
    private JButton skip_turn, game6, game8, game10;
    private JPanel Panel_down, Panel_top;
    private PlayingField myField;
    private String player = new String("Spieler 1");
    Image img1 = Toolkit.getDefaultToolkit().getImage("Othello_banner.jpg");
    
    
    public static void main(String[] args) {
        new Belegarbeit2();
    }
    
    public Belegarbeit2() 
    {
        initUI();
    }

    private void initUI()
    {
        setLayout(null);
        setTitle("Othello - Spiel");
       
        /* Erzeugen des oberen Panels und der dazugehörigen Buttons */
        Panel_top = new JPanel();
        Panel_top.setLayout(new GridLayout(1, 3));
        Panel_top.setBounds(0, 0, 400, 20);
        Panel_top.setBorder(LineBorder.createBlackLineBorder());
        game6 = new JButton("New 6x6");
        game6.addActionListener(new Action (6));
        Panel_top.add(game6); 
        game8 = new JButton("New 8x8");
        game8.addActionListener(new Action (8));
        Panel_top.add(game8);
        game10 = new JButton("New 10x10");
        game10.addActionListener(new Action (10));
        Panel_top.add(game10);
        add(Panel_top);
        
        /* Erzeugen des unteren Panel und der dazugehörigen Anzeigen und Button */
        Panel_down = new JPanel();
        Panel_down.setLayout(new GridLayout(3,2));
        Panel_down.setBounds(0, 421, 400, 60);
        Panel_down.setBorder(LineBorder.createBlackLineBorder());
        add(Panel_down);
        player1Label = new JLabel("Steine Spieler 1 = ");
        Panel_down.add(player1Label);
        player1StonesLabel = new JLabel();
        Panel_down.add(player1StonesLabel);
        player2Label = new JLabel("Steine Spieler 2 = ");
        Panel_down.add(player2Label);
        player2StonesLabel = new JLabel();
        Panel_down.add(player2StonesLabel);
        activePlayerLabel = new JLabel(player + " am Zug");
        Panel_down.add(activePlayerLabel);
        skip_turn = new JButton("Aussetzen");
        Panel_down.add(skip_turn);
        
        /* Implement "Aussetzen"-button action listener */
        skip_turn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ev)
            {
                myField.skipTurn();
            }
        });
        
        /* Create playing field */
        myField = new PlayingField(this);
        add(myField);
        myField.setBounds(0, 21, 400, 400);
        
        setResizable(false);
        
        setSize(400, 507);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    public int getPlayer1Stones() 
    {
        return Integer.parseInt(player1StonesLabel.getText());
    }
    
    public int getPlayer2_stones()
    {
        return Integer.parseInt(player2StonesLabel.getText());
    }
    
    public void setPlayer1Stones(int newStones)
    {
        player1StonesLabel.setText(Integer.toString(newStones));
    }
    
    public void setPlayer2Stones(int newStones)
    {
        player2StonesLabel.setText(Integer.toString(newStones));
    }
    
    public JLabel getPlayer_turn()
    {
        return activePlayerLabel;
    }
    
    class Action implements ActionListener
    {
        int size_number;
        Action (int number)
        {
              size_number = number;
        }
    
   
        public void actionPerformed(ActionEvent e)
        {
            myField.newGame(size_number);
        }
    }
    
}
