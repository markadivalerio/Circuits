package components;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import circuits.*;

public class Wire extends Component_Class
{
	public static boolean		show_voltage;
	public static boolean		show_current;
	public ArrayList<Node>		path;
	public ArrayList<Rectangle>	all_bounds;
	
	
	public Wire()
	{
		super.label = "";
		super.ports = new ArrayList<Node>(0);
		super.uid = 0;
		super.image_id = 0;
		super.color = null;
		super.direction = 'R';
		super.current = 0.0;
		super.current_count = 0.0;
		super.selected = false;
		super.show_labels = false;
		super.show_values = false;
//		this.set_location(new Node());
//		this.set_bounds();
		super.location = null;
		
		show_voltage = false;
		show_current = false;
		path = new ArrayList<Node>(0);
		all_bounds = new ArrayList<Rectangle>(0);
	}
	
	
	@Override
	public boolean is_wire()
	{
		return true;
	}
	
	
	@Override
	public void allocate_ports()
	{
		ports = new ArrayList<Node>(0);
		path = new ArrayList<Node>(0);
//		ports.add(location);
//		path.add(location);
	}
	
	
	@Override
	public void set_bounds()
	{
		bounds = new Rectangle();
		all_bounds = new ArrayList<Rectangle>(0);
		// NEED TO DO!!!
	}
	
	
	public void add_node_to_path(Node n)
	{
		path.add(n);
		set_bounds();
	}
	
	
	public void add_node_to_path(Mouse m)
	{
		path.add(this.util.get_grid_node_from_mouse(m));
	}
	
	
	@Override
	public void draw(Graphics g)
	{
		if(this.color != null)
		{
			g.setColor(this.color);
		}
		if(path.size() >= 2)
		{
			for(int i = 0; i < path.size() - 1; i++)
			{
				Node n1 = path.get(i);
				Node n2 = path.get(i + 1);
				g.drawLine(n1.xypos.x + 3, n1.xypos.y + 3, n1.xypos.x + 3, n2.xypos.y + 3);
				
				Node n3 = new Node(n1.xypos.x, n2.xypos.y);
				n3.draw_small(g);
				
				g.drawLine(n1.xypos.x + 3, n2.xypos.y + 3, n2.xypos.x + 3, n2.xypos.y + 3);
				n1.draw_small(g);
			}
		}
		if(path.size() >= 1)
		{
			Node n = path.get(path.size() - 1);
			n.draw_small(g);
		}
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
				g.drawLine(xpos, ypos, xpos + 90, ypos);
			}
			if(direct == 'D')
			{
				g.drawLine(xpos, ypos, xpos, ypos + 90);
			}
			if(direct == 'L')
			{
				g.drawLine(xpos, ypos, xpos - 90, ypos);
			}
			if(direct == 'U')
			{
				g.drawLine(xpos, ypos, xpos, ypos - 90);
			}
		}
	}
	
	
	public void draw(Graphics g, Mouse mouse)
	{
		this.draw(g);
		if(path.size() >= 1)
		{
			Node n1 = path.get(path.size() - 1);
			Node n2 = util.get_grid_node_from_mouse(mouse);
			g.drawLine(n1.xypos.x + 3, n1.xypos.y + 3, n1.xypos.x + 3, n2.xypos.y + 3);
			Node n3 = new Node(n1.xypos.x, n2.xypos.y);
			n3.draw_small(g);
			g.drawLine(n1.xypos.x + 3, n2.xypos.y + 3, n2.xypos.x + 3, n2.xypos.y + 3);
		}
	}
	
	
}
