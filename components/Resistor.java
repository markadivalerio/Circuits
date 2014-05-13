package components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Resistor extends Component_Class
{
	public double	resistance;
	
	
	public Resistor()
	{
		super();
		resistance = 500.0;
		this.properties.add(new Component_Property("Resistance", resistance, "Ω"));
	}
	
	
	
	@Override
	public void draw(Graphics g, int x, int y, char direct, Color c)
	{
		if(c != null)
		{
			g.setColor(c);
		}
		
		if(this.image_id == -1)
		{
			this.draw_default_box(g, x, y, direct, c);
		}
		else
		{
			int xpos = x + 3;
			int ypos = y + 3;
			
			if(direct == 'R')
			{
				g.drawLine(xpos, ypos, xpos + 30, ypos);
				g.drawLine(xpos + 30, ypos, xpos + 35, ypos - 10);
				g.drawLine(xpos + 35, ypos - 10, xpos + 40, ypos + 10);
				g.drawLine(xpos + 40, ypos + 10, xpos + 45, ypos - 10);
				g.drawLine(xpos + 45, ypos - 10, xpos + 50, ypos + 10);
				g.drawLine(xpos + 50, ypos + 10, xpos + 55, ypos - 10);
				g.drawLine(xpos + 55, ypos - 10, xpos + 60, ypos);
				g.drawLine(xpos + 60, ypos, xpos + 90, ypos);
			}
			else if(direct == 'D')
			{
				g.drawLine(xpos, ypos, xpos, ypos + 30);
				g.drawLine(xpos, ypos + 30, xpos + 10, ypos + 35);
				g.drawLine(xpos + 10, ypos + 35, xpos - 10, ypos + 40);
				g.drawLine(xpos - 10, ypos + 40, xpos + 10, ypos + 45);
				g.drawLine(xpos + 10, ypos + 45, xpos - 10, ypos + 50);
				g.drawLine(xpos - 10, ypos + 50, xpos + 10, ypos + 55);
				g.drawLine(xpos + 10, ypos + 55, xpos, ypos + 60);
				g.drawLine(xpos, ypos + 60, xpos, ypos + 90);
			}
			else if(direct == 'L')
			{
				g.drawLine(xpos, ypos, xpos - 30, ypos);
				g.drawLine(xpos - 30, ypos, xpos - 35, ypos + 10);
				g.drawLine(xpos - 35, ypos + 10, xpos - 40, ypos - 10);
				g.drawLine(xpos - 40, ypos - 10, xpos - 45, ypos + 10);
				g.drawLine(xpos - 45, ypos + 10, xpos - 50, ypos - 10);
				g.drawLine(xpos - 50, ypos - 10, xpos - 55, ypos + 10);
				g.drawLine(xpos - 55, ypos + 10, xpos - 60, ypos);
				g.drawLine(xpos - 60, ypos, xpos - 90, ypos);
			}
			else if(direct == 'U')
			{
				g.drawLine(xpos, ypos, xpos, ypos - 30);
				g.drawLine(xpos, ypos - 30, xpos - 10, ypos - 35);
				g.drawLine(xpos - 10, ypos - 35, xpos + 10, ypos - 40);
				g.drawLine(xpos + 10, ypos - 40, xpos - 10, ypos - 45);
				g.drawLine(xpos - 10, ypos - 45, xpos + 10, ypos - 50);
				g.drawLine(xpos + 10, ypos - 50, xpos - 10, ypos - 55);
				g.drawLine(xpos - 10, ypos - 55, xpos, ypos - 60);
				g.drawLine(xpos, ypos - 60, xpos, ypos - 90);
			}
		}
	}
}
