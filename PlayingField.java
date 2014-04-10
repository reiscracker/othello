package belegarbeit2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;



public class PlayingField extends JPanel {
    /* 1 = white; 2 = black */
    private Field fields[][]; 
    /* Represents active player where 1 = player 1/white, 2 = player 2/black */
    private int activePlayer, otherPlayer;
    private Belegarbeit2 mainApp;
    
    
    public void newGame(int size)
    {
        fields = new Field[size][size];
        for (int x=0; x < fields.length; x++)
        {
            for (int y=0; y < fields.length; y++)
            {
                fields[x][y] = new Field(x*(getWidth() / fields.length), y*(getWidth() / fields.length), getWidth() / fields.length, getWidth() / fields.length);
            }
        }
        fields[(fields.length/2) - 1][(fields.length/2) - 1].setFieldColor(1);
        fields[(fields.length/2) - 1][fields.length/2].setFieldColor(2);
        fields[fields.length/2][fields.length/2].setFieldColor(1);
        fields[fields.length/2][(fields.length/2) - 1].setFieldColor(2);
        
        /* Reset player stone count to 2 */
        mainApp.setPlayer1Stones(2);
        mainApp.setPlayer2Stones(2);

        activePlayer = 1;
        otherPlayer = 2;

        showPossibleTurns();
        
        repaint();
    }
    
    private boolean turn(int x, int y) 
    {
        if (fields[x/(getWidth()/fields.length)][y/(getHeight()/fields.length)].getFieldColor() == 3)
        {
            /* Turn field into a stone of the active player */
            fields[x/(getWidth()/fields.length)][y/(getHeight()/fields.length)].setFieldColor(activePlayer);
            
            if (activePlayer == 1)
            {
                /* Update Player 1 stones count */
                mainApp.setPlayer1Stones(mainApp.getPlayer1Stones() + 1);
                
            }
            else
            {
                /* Update Player 2 stones count */
                mainApp.setPlayer2Stones(mainApp.getPlayer2_stones() + 1);
            }
            
            flipFields(x/(getWidth()/fields.length), y/(getHeight()/fields.length));
            /* Switch player */
            skipTurn();
            
            return true;
        }
        else
        {
            System.out.println("Zug nicht möglich");
            return false;
        }
    }
    
    public void skipTurn()
    {
        if (activePlayer == 1) 
        {
            activePlayer = 2;
            otherPlayer = 1;
        }
        else
        {
            activePlayer = 1;
            otherPlayer = 2;
        }
        mainApp.getPlayer_turn().setText("Spieler " + activePlayer + " ist am Zug");
    }
   
    private boolean isPossibleTurn(int xIndex, int yIndex) // These have to be x and y index of the array fields
    {
        // right
        if (xIndex != fields.length - 1 && fields[xIndex + 1][yIndex].getFieldColor() == otherPlayer)
        {
            int xIterator = xIndex + 1;
            while (xIterator < fields.length - 1 && fields[xIterator][yIndex].getFieldColor() == otherPlayer)
            {
                xIterator++;
            }
            if (fields[xIterator][yIndex].getFieldColor() == activePlayer) return true;
        }
        // down-right
        if (xIndex != fields.length - 1 && yIndex != fields.length - 1 && fields[xIndex + 1][yIndex + 1].getFieldColor() == otherPlayer)
        {
            int xIterator = xIndex + 1;
            int yIterator = yIndex + 1;
            while (xIterator < fields.length - 1 && yIterator < fields.length - 1 && fields[xIterator][yIterator].getFieldColor() == otherPlayer)
            {
                xIterator++;
                yIterator++;
            }
            if (fields[xIterator][yIterator].getFieldColor() == activePlayer) return true;
        }
        // down
        if (yIndex != fields.length - 1 && fields[xIndex][yIndex + 1].getFieldColor() == otherPlayer)
        {
            int yIterator = yIndex + 1;
            while (yIterator < fields.length - 1 && fields[xIndex][yIterator].getFieldColor() == otherPlayer)
            {
                yIterator++;
            }
            if (fields[xIndex][yIterator].getFieldColor() == activePlayer) return true;
        }
        // down left
        if (xIndex != 0 && yIndex != fields.length - 1 && fields[xIndex - 1][yIndex + 1].getFieldColor() == otherPlayer)
        {
            int yIterator = yIndex + 1;
            int xIterator = xIndex - 1;
            while (xIterator > 0 && yIterator < fields.length - 1 && fields[xIterator][yIterator].getFieldColor() == otherPlayer)
            {
                yIterator++;
                xIterator--;
            }
            if (fields[xIterator][yIterator].getFieldColor() == activePlayer) return true;
        }
        // left
        if (xIndex != 0 && fields[xIndex - 1][yIndex].getFieldColor() == otherPlayer)
        {
            int xIterator = xIndex - 1;
            while (xIterator > 0 && fields[xIterator][yIndex].getFieldColor() == otherPlayer)
            {
                xIterator--;
            }
            if (fields[xIterator][yIndex].getFieldColor() == activePlayer) return true;
        }
        // top left
        if (xIndex != 0 && yIndex != 0 && fields[xIndex - 1][yIndex - 1].getFieldColor() == otherPlayer)
        {
            int xIterator = xIndex - 1;
            int yIterator = yIndex - 1;
            while (xIterator > 0 && yIterator > 0 && fields[xIterator][yIterator].getFieldColor() == otherPlayer)
            {
                xIterator--;
                yIterator--;
            }
            if (fields[xIterator][yIterator].getFieldColor() == activePlayer) return true;
        }
        // top
        if (yIndex != 0 && fields[xIndex][yIndex - 1].getFieldColor() == otherPlayer)
        {
            int yIterator = yIndex - 1;
            while (yIterator > 0 && fields[xIndex][yIterator].getFieldColor() == otherPlayer)
            {
                yIterator--;
            }
            if (fields[xIndex][yIterator].getFieldColor() == activePlayer) return true;
        }
        // top right
        if (xIndex != fields.length - 1 && yIndex != 0 && fields[xIndex + 1][yIndex - 1].getFieldColor() == otherPlayer)
        {
            int yIterator = yIndex - 1;
            int xIterator = xIndex + 1;
            while (xIterator < fields.length - 1 && yIterator > 0 && fields[xIterator][yIterator].getFieldColor() == otherPlayer)
            {
                yIterator--;
                xIterator++;
            }
            if (fields[xIterator][yIterator].getFieldColor() == activePlayer) return true;
        }
        
        return false;
   }
    /* Returns True if there is at least one possible turn */
    private boolean showPossibleTurns() 
    {
        boolean foundPossibleTurn = false;
        for (int x = 0; x < fields.length; x++)
        {
            for (int y = 0; y < fields.length; y++)
            {
                /* First, set all previous possible fields back to normal */
                if (fields[x][y].getFieldColor() == 3) 
                {
                    fields[x][y].setFieldColor(0);
                }
                /* Now, check which fields are possible turns */
                if (fields[x][y].getFieldColor() == 0)
                {
                    if (isPossibleTurn(x, y))
                    {
                        fields[x][y].setFieldColor(3);
                        foundPossibleTurn = true;
                    }
                }
            }
        }
        return foundPossibleTurn;
    }
    
    public boolean checkWin() 
    {
        return false;
    }
    
    private void flipFields(int xIndex, int yIndex)
    {
        // right
        if (xIndex != fields.length - 1 && fields[xIndex + 1][yIndex].getFieldColor() == otherPlayer)
        {
            int xIterator = xIndex + 1;
            while (xIterator < fields.length - 1 && fields[xIterator][yIndex].getFieldColor() == otherPlayer)
            {
                xIterator++;
            }
            if (fields[xIterator][yIndex].getFieldColor() == activePlayer)
            {
                for (int i = xIndex + 1; i < xIterator; i++) 
                {
                    fields[i][yIndex].setFieldColor(activePlayer);
                }
            }
        }
        //down right
        if (xIndex != fields.length - 1 && yIndex != fields.length - 1 && fields[xIndex + 1][yIndex + 1].getFieldColor() == otherPlayer)
        {
            int xIterator = xIndex + 1;
            int yIterator = yIndex + 1;
            while (xIterator < fields.length - 1 && yIterator < fields.length - 1 && fields[xIterator][yIterator].getFieldColor() == otherPlayer)
            {
                xIterator++;
                yIterator++;
            }
            if (fields[xIterator][yIterator].getFieldColor() == activePlayer) 
            {
                for (int i = xIndex + 1; i < xIterator; i++) 
                {
                    for (int j = yIndex + 1; j < yIterator; j++)
                    {
                        fields[i][j].setFieldColor(activePlayer);
                    }
                }
            }
        }
        // down
        if (yIndex != fields.length - 1 && fields[xIndex][yIndex + 1].getFieldColor() == otherPlayer)
        {
            int yIterator = yIndex + 1;
            while (yIterator < fields.length - 1 && fields[xIndex][yIterator].getFieldColor() == otherPlayer)
            {
                yIterator++;
            }
            if (fields[xIndex][yIterator].getFieldColor() == activePlayer) 
            {
                for (int j = yIndex + 1; j < yIterator; j++) 
                {
                        fields[xIndex][j].setFieldColor(activePlayer);
                }
            }
        }
        // down left
        if (xIndex != 0 && yIndex != fields.length - 1 && fields[xIndex - 1][yIndex + 1].getFieldColor() == otherPlayer)
        {
            int yIterator = yIndex + 1;
            int xIterator = xIndex - 1;
            while (xIterator > 0 && yIterator < fields.length - 1 && fields[xIterator][yIterator].getFieldColor() == otherPlayer)
            {
                yIterator++;
                xIterator--;
            }
            if (fields[xIterator][yIterator].getFieldColor() == activePlayer)
            {
                for (int i = xIndex - 1; i > xIterator; i--) 
                {
                    for (int j = yIndex + 1; j < yIterator; j++)
                    {
                        fields[i][j].setFieldColor(activePlayer);
                    }
                }
            }    
        }
        // left
        if (xIndex != 0 && fields[xIndex - 1][yIndex].getFieldColor() == otherPlayer)
        {
            int xIterator = xIndex - 1;
            while (xIterator > 0 && fields[xIterator][yIndex].getFieldColor() == otherPlayer)
            {
                xIterator--;
            }
            if (fields[xIterator][yIndex].getFieldColor() == activePlayer)
            {
                for (int i = xIndex - 1; i > xIterator; i--) 
                {
                        fields[i][yIndex].setFieldColor(activePlayer);
                }
            }
        }
        // top left
        if (xIndex != 0 && yIndex != 0 && fields[xIndex - 1][yIndex - 1].getFieldColor() == otherPlayer)
        {
            int xIterator = xIndex - 1;
            int yIterator = yIndex - 1;
            while (xIterator > 0 && yIterator > 0 && fields[xIterator][yIterator].getFieldColor() == otherPlayer)
            {
                xIterator--;
                yIterator--;
            }
            if (fields[xIterator][yIterator].getFieldColor() == activePlayer)
            {
                for (int i = xIndex - 1; i > xIterator; i--) 
                {
                    for (int j = yIndex - 1; j > yIterator; j--)
                    {
                        fields[i][j].setFieldColor(activePlayer);
                    }
                }
            }    
        }
        // top
        if (yIndex != 0 && fields[xIndex][yIndex - 1].getFieldColor() == otherPlayer)
        {
            int yIterator = yIndex - 1;
            while (yIterator > 0 && fields[xIndex][yIterator].getFieldColor() == otherPlayer)
            {
                yIterator--;
            }
            if (fields[xIndex][yIterator].getFieldColor() == activePlayer)
            {
                for (int j = yIndex - 1; j > yIterator; j--) 
                {
                        fields[xIndex][j].setFieldColor(activePlayer);
                }
            }    
        }
        // top right
        if (xIndex != fields.length - 1 && yIndex != 0 && fields[xIndex + 1][yIndex - 1].getFieldColor() == otherPlayer)
        {
            int yIterator = yIndex - 1;
            int xIterator = xIndex + 1;
            while (xIterator < fields.length - 1 && yIterator > 0 && fields[xIterator][yIterator].getFieldColor() == otherPlayer)
            {
                yIterator--;
                xIterator++;
            }
            if (fields[xIterator][yIterator].getFieldColor() == activePlayer)
            {
                for (int i = xIndex + 1; i < xIterator; i++) 
                {
                    for (int j = yIndex - 1; j > yIterator; j--)
                    {
                        fields[i][j].setFieldColor(activePlayer);
                    }
                }
            }    
        }
    }
    
    public void paint(Graphics g)
    {
       super.paintComponent(g);

        for (int x=0; x < fields.length; x++)
        {
            for (int y=0; y < fields.length; y++)
            {
                fields[x][y].paint(g);
            }
        }
    }
    
    public PlayingField(Belegarbeit2 mainApp)
    {
        this.mainApp = mainApp; // Referenz auf aufrufende Klasse
        setBackground(new Color(211, 211, 211));
        addMouseListener(new MyMouseListener());
        newGame(8);
    }
    
    class MyMouseListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent me) 
        {
            turn(me.getX(), me.getY());
            /* Skip player if no possible turn is found */
            if (!showPossibleTurns())
            {
                skipTurn();
            };
            repaint();
        }
    }
   
}