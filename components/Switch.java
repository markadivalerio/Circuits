package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import circuits.*;

public class Switch extends Component_Class
{
	public boolean	is_open;
	
	
	public Switch()
	{
		super();
		is_open = true;
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
		ports.add(this.location);
	}
	
	
	@Override
	public void set_bounds()
	{	
		
	}
	
	
	@Override
	public void draw(Graphics g, int x, int y, char direct, Color c)
	{
		if(c != null)
		{
			g.setColor(c);
		}
		for(Node p: ports)
		{
			p.draw_small(g);
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
				
			}
			if(direct == 'D')
			{	
				
			}
			if(direct == 'L')
			{	
				
			}
			if(direct == 'U')
			{	
				
			}
		}
	}
}
