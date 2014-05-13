package components;

import java.awt.Color;
import java.awt.Graphics;

public class Capacitor extends Component_Class
{
	public double	capacitance;
	
	
	public Capacitor()
	{
		super();
		capacitance = 0.0;
	}
	
	
	@Override
	public void draw(Graphics g, int x, int y, char direct, Color c)
	{
		if(this.image_id == -1)
		{
			this.draw_default_box(g, x, y, direct, c);
		}
		else
		{
			if(c != null)
			{
				g.setColor(c);
			}
			int xpos = x + 3;
			int ypos = y + 3;
			
			if(direct == 'R')
			{
				g.drawLine(xpos, ypos, xpos + 38, ypos);
				g.fillRect(xpos + 38, ypos - 20, 5, 40);
				g.fillRect(xpos + 47, ypos - 20, 5, 40);
				g.drawLine(xpos + 52, ypos, xpos + 90, ypos);
			}
			else if(direct == 'D')
			{
				g.drawLine(xpos, ypos, xpos, ypos + 38);
				g.fillRect(xpos - 20, ypos + 38, 40, 5);
				g.fillRect(xpos - 20, ypos + 47, 40, 5);
				g.drawLine(xpos, ypos + 52, xpos, ypos + 90);
			}
			else if(direct == 'L')
			{
				g.drawLine(xpos, ypos, xpos - 43, ypos);
				g.fillRect(xpos - 43, ypos - 20, 5, 40);
				g.fillRect(xpos - 52, ypos - 20, 5, 40);
				g.drawLine(xpos - 52, ypos, xpos - 90, ypos);
			}
			else if(direct == 'U')
			{
				g.drawLine(xpos, ypos, xpos, ypos - 43);
				g.fillRect(xpos - 20, ypos - 43, 40, 5);
				g.fillRect(xpos - 20, ypos - 52, 40, 5);
				g.drawLine(xpos, ypos - 52, xpos, ypos - 90);
			}
		}
	}
}
