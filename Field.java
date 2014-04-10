package belegarbeit2;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class Field extends JLabel {
    private Rectangle fieldRect;
    /* 0 = empty, 1 = white, 2 = black, 3 = possible */
    private int fieldColor;
    private BufferedImage chip = null;

    public Field(int x, int y, int width, int height)
    {
        fieldRect = new Rectangle(x, y, width, height);
        fieldColor = 0;
        try
        {
            chip = ImageIO.read(new File("chip.png"));
        } catch (IOException ex)
        {
            System.out.println("Image not found");
        }
    }
    
    public void paint(Graphics g)
    {
        super.paintComponents(g);
        
        if (fieldColor == 0)
        {
            g.setColor(Color.black);
            g.drawRect(fieldRect.x, fieldRect.y, fieldRect.width, fieldRect.height);
        }
        else if (fieldColor == 1)
        {
            g.setColor(Color.white);
            g.fillRect(fieldRect.x + 1, fieldRect.y + 1, fieldRect.width - 1, fieldRect.height - 1);
        }
        else if (fieldColor == 2)
        {
            g.setColor(Color.black);
            g.fillRect(fieldRect.x + 1, fieldRect.y + 1, fieldRect.width - 1, fieldRect.height - 1);
        }
        else if (fieldColor == 3)
        {
            g.setColor(Color.red);
            g.fillRect(fieldRect.x + 1, fieldRect.y + 1, fieldRect.width - 1, fieldRect.height - 1);
        }
        
    }
    
    public void setFieldColor(int newColor)
    {
        fieldColor = newColor;
    }
    public int getFieldColor()
    {
        return fieldColor;
    }
}