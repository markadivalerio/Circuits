package components;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import circuits.*;

public class Component_Class extends Component_Abstract implements Cloneable
{
	public Component_Class()
	{
		super();
	}
	
	
	@Override
	public boolean is_wire()
	{
		return super.is_wire();
	}
	
	
	@Override
	public void set_location(Node node)
	{
		super.set_location(node);
	}
	
	
	@Override
	public void set_location(Mouse mouse)
	{
		super.set_location(mouse);
	}
	
	
	@Override
	public void allocate_ports()
	{
		super.allocate_ports();
	}
	
	
	@Override
	public void set_bounds()
	{
		super.set_bounds();
	}
	
	
	@Override
	public boolean is_inside_bounds(Node node)
	{
		return this.bounds.contains(node.xypos);
	}
	
	
	@Override
	public void calculate()
	{	
		
	}
	
	
	@Override
	public String get_type()
	{
		return super.get_type();
	}
	
	
	@Override
	public boolean has_sub_types()
	{
		return super.has_sub_types();
	}
	
	
	@Override
	public String get_sub_type()
	{
		return super.get_sub_type();
	}
	
	
	@Override
	public boolean set_sub_type(String st)
	{
		return super.set_sub_type(st);
	}
	
	
	@Override
	public Object clone()// throws CloneNotSupportedException
	{
		return super.clone();
//		try
//		{
//			return super.clone();
//		}
//		catch(CloneNotSupportedException e)
//		{
//			e.printStackTrace();
//			return null;
//		}
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
				g.drawLine(xpos, ypos, xpos + 15, ypos);
				g.drawRect(xpos + 15, ypos - 10, 60, 20);
				g.drawLine(xpos + 75, ypos, xpos + 90, ypos);
			}
			else if(direct == 'D')
			{
				g.drawLine(xpos, ypos, xpos, ypos + 15);
				g.drawRect(xpos - 10, ypos + 15, 20, 60);
				g.drawLine(xpos, ypos + 75, xpos, ypos + 90);
			}
			else if(direct == 'L')
			{
				g.drawLine(xpos, ypos, xpos - 15, ypos);
				g.drawRect(xpos - 75, ypos - 10, 60, 20);
				g.drawLine(xpos - 75, ypos, xpos - 90, ypos);
			}
			else if(direct == 'U')
			{
				g.drawLine(xpos, ypos, xpos, ypos - 15);
				g.drawRect(xpos - 10, ypos - 75, 20, 60);
				g.drawLine(xpos, ypos - 75, xpos, ypos - 90);
			}
		}
	}
	
	
	@Override
	public void draw_ports(Graphics g)
	{
		for(Node p: this.ports)
		{
			p.draw_small(g);
		}
	}
}
