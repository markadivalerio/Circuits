package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import circuits.*;

public class Custom extends Component_Class
{
	public Custom()
	{
		super();
	}
	
	
	@Override
	public void allocate_ports()
	{	
		
	}
	
	
	@Override
	public void set_bounds()
	{	
		
	}
	
	
	@Override
	public void calculate()
	{	
		
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
			super.draw_default_box(g, x, y, direct, c);
			
//			int xpos = x + 3;
//			int ypos = y + 3;
//			
//			if(direct == 'R')
//			{
//				
//			}
//			if(direct == 'D')
//			{
//				
//			}
//			if(direct == 'L')
//			{
//				
//			}
//			if(direct == 'U')
//			{
//				
//			}
			for(Node p: this.ports)
			{
				p.draw_small(g);
			}
		}
	}
	
}
