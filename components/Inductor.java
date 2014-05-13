package components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Inductor extends Component_Class
{
	public double	inductance;
	
	
	public Inductor()
	{
		super();
		inductance = 0.0;
	}
	
	
//	@Override
//	public void allocate_ports()
//	{
//		this.ports = new ArrayList<Node>();
//		ports.add(this.location);
//	}
	
	
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
				g.drawLine(xpos, ypos, xpos + 20, ypos);
				g.drawArc(xpos + 20, ypos - 8, 16, 16, -60, 245);
				g.drawArc(xpos + 30, ypos - 8, 16, 16, -40, 275);
				g.drawArc(xpos + 40, ypos - 8, 16, 16, -40, 275);
				g.drawArc(xpos + 50, ypos - 8, 16, 16, 0, 235);
				g.drawLine(xpos + 66, ypos, xpos + 90, ypos);
				
			}
			if(direct == 'D')
			{
				g.drawLine(xpos, ypos, xpos, ypos + 20);
				g.drawArc(xpos - 8, ypos + 20, 16, 16, 85, -225);
				g.drawArc(xpos - 8, ypos + 30, 16, 16, 130, -275);
				g.drawArc(xpos - 8, ypos + 40, 16, 16, 130, -275);
				g.drawArc(xpos - 8, ypos + 50, 16, 16, 130, -225);
				g.drawLine(xpos, ypos + 66, xpos, ypos + 90);
			}
			if(direct == 'L')
			{
				g.drawLine(xpos, ypos, xpos - 20, ypos);
				g.drawArc(xpos - 36, ypos - 8, 16, 16, 0, -235);
				g.drawArc(xpos - 46, ypos - 8, 16, 16, 40, -275);
				g.drawArc(xpos - 56, ypos - 8, 16, 16, 40, -275);
				g.drawArc(xpos - 66, ypos - 8, 16, 16, 60, -245);
				g.drawLine(xpos - 66, ypos, xpos - 90, ypos);
			}
			if(direct == 'U')
			{
				g.drawLine(xpos, ypos, xpos, ypos - 20);
				g.drawArc(xpos - 8, ypos - 36, 16, 16, 50, 225);
				g.drawArc(xpos - 8, ypos - 46, 16, 16, 50, 275);
				g.drawArc(xpos - 8, ypos - 56, 16, 16, 50, 275);
				g.drawArc(xpos - 8, ypos - 66, 16, 16, 90, 230);
				g.drawLine(xpos, ypos - 66, xpos, ypos - 90);
			}
		}
	}
}
