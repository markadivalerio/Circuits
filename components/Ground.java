package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ground extends Component_Class
{
	public Ground()
	{
		super();
	}
	
	
	@Override
	public boolean is_wire()
	{
		return false;
	}
	
	
	@Override
	public void allocate_ports()
	{
		this.ports = new ArrayList<Node>();
		this.ports.add(this.location);
		this.set_bounds();
	}
	
	
	@Override
	public void set_bounds()
	{
		this.bounds = new Rectangle();
		if(direction == 'R')
		{
			bounds.setBounds(location.xypos.x, location.xypos.y - 20, 50, 40);
		}
		else if(direction == 'D')
		{
			bounds.setBounds(location.xypos.x - 20, location.xypos.y, 40, 50);
		}
		else if(direction == 'L')
		{
			bounds.setBounds(location.xypos.x - 50, location.xypos.y - 20, 50, 40);
		}
		else if(direction == 'U')
		{
			bounds.setBounds(location.xypos.x - 20, location.xypos.y - 50, 40, 50);
		}
	}
	
	
	@Override
	public void calculate()
	{	
		
	}
	
	
	@Override
	public void draw(Graphics g, int x, int y, char direct, Color c)
	{
		if(this.image_id == -1)
		{
			this.draw_default_box(g, x, y, direct, c);
		}
		else if(this.image_id == 0)
		{
			if(c != null)
			{
				g.setColor(c);
			}
			int xpos = x + 3;
			int ypos = y + 3;
			
			if(direct == 'R')
			{
				g.drawLine(xpos, ypos, xpos + 30, ypos);
				g.fillRect(xpos + 30, ypos - 20, 2, 40);
				g.fillRect(xpos + 40, ypos - 10, 2, 20);
				g.fillRect(xpos + 50, ypos - 5, 2, 10);
			}
			if(direct == 'L')
			{
				g.drawLine(xpos, ypos, xpos - 30, ypos);
				g.fillRect(xpos - 32, ypos - 20, 2, 40);
				g.fillRect(xpos - 42, ypos - 10, 2, 20);
				g.fillRect(xpos - 52, ypos - 5, 2, 10);
			}
			if(direct == 'U')
			{
				g.drawLine(xpos, ypos, xpos, ypos - 30);
				g.fillRect(xpos - 20, ypos - 30, 40, 2);
				g.fillRect(xpos - 10, ypos - 38, 20, 2);
				g.fillRect(xpos - 5, ypos - 46, 10, 2);
			}
			if(direct == 'D')
			{
				g.drawLine(xpos, ypos, xpos, ypos + 30);
				g.fillRect(xpos - 20, ypos + 30, 40, 2);
				g.fillRect(xpos - 10, ypos + 38, 20, 2);
				g.fillRect(xpos - 5, ypos + 46, 10, 2);
			}
		}
	}
}
