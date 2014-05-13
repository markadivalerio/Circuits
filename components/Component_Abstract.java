package components;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import circuits.*;


public abstract class Component_Abstract implements Cloneable
{
	public static final int					WAVEFORM_DC			= 0;
	public static final int					WAVEFORM_AC			= 1;
	public static final int					WAVEFORM_SQUARE		= 2;
	public static final int					WAVEFORM_TRIANGLE	= 3;
	public static final int					WAVEFORM_SAWTOOTH	= 4;
	public static final int					WAVEFORM_PULSE		= 5;
	public static final int					WAVEFORM_VAR		= 6;
	
	public static final int					VOLTAGE_SOURCE		= 0;
	public static final int					CURRENT_SOURCE		= 1;
	public static final int					GENERAL_SOURCE		= 2;
	
	public static final double				TRUE				= 0.0;
	public static final double				FALSE				= 1.0;
	public static final double				NEG_INFINITY		= Double.NEGATIVE_INFINITY;
	public static final double				POS_INFINITY		= Double.POSITIVE_INFINITY;
	
	
	protected static final Utility			util				= new Utility();
	
	public String							label;
	public static ArrayList<String>			sub_types;
	public int								sub_type_index		= 0;
	public ArrayList<Node>					ports;
	public int								uid, image_id;
	public Color							color;
	public char								direction;
	public double							current, current_count;
	public boolean							selected;
	public Node								location;
	public Rectangle						bounds;
	public boolean							show_labels, show_values;
	public ArrayList<Component_Property>	properties;
	
	public ArrayList<Component_Sub_Type>	sub;
	
	
	public Component_Abstract()
	{
		label = "";
		sub_types = new ArrayList<String>(0);
		sub_type_index = 0;
		ports = new ArrayList<Node>(0);
		uid = 0;
		image_id = 0;
		color = null;
		direction = 'R';
		current = 0.0;
		current_count = 0.0;
		selected = false;
		show_labels = false;
		show_values = false;
		this.set_location(new Node());
		this.set_bounds();
		properties = new ArrayList<Component_Property>();
		sub = new ArrayList<Component_Sub_Type>();
	}
	
	
	public boolean is_wire()
	{
		return false;
	}
	
	
	public void set_location(Node node)
	{
		location = node;
		
		this.allocate_ports();
		this.set_bounds();
	}
	
	
	public void set_location(Mouse mouse)
	{
//		location.colrow.x = mouse.get_xcol();
//		location.colrow.y = mouse.get_yrow();
//		location.xypos.x = util.get_xypos_from_colrow(mouse.position.colrow).x;
//		location.xypos.y = util.get_xypos_from_colrow(mouse.position.colrow).y;
		
		location = util.get_grid_node_from_mouse(mouse);
		
		this.allocate_ports();
		this.set_bounds();
	}
	
	
	// sets the components other ports relative to the "location" node variable
	public void allocate_ports()
	{
		ports = new ArrayList<Node>();
		ports.add(location);
		Node loc2 = new Node();
		
//		if(direction == 'R')
//		{
//			loc2.colrow.x = location.colrow.x + 9;
//			loc2.colrow.y = location.colrow.y;
//		}
//		else if(direction == 'D')
//		{
//			loc2.colrow.x = location.colrow.x;
//			loc2.colrow.y = location.colrow.y + 9;
//		}
//		else if(direction == 'L')
//		{
//			loc2.colrow.x = location.colrow.x - 9;
//			loc2.colrow.y = location.colrow.y;
//		}
//		else if(direction == 'U')
//		{
//			loc2.colrow.x = location.colrow.x;
//			loc2.colrow.y = location.colrow.y - 9;
//		}
//		loc2.xypos = util.get_xypos_from_colrow(loc2.colrow);
//		ports.add(loc2);
		
		if(direction == 'R')
		{
			loc2.xypos.x = location.xypos.x + 90;
			loc2.xypos.y = location.xypos.y;
		}
		else if(direction == 'D')
		{
			loc2.xypos.x = location.xypos.x;
			loc2.xypos.y = location.xypos.y + 90;
		}
		else if(direction == 'L')
		{
			loc2.xypos.x = location.xypos.x - 90;
			loc2.xypos.y = location.xypos.y;
		}
		else if(direction == 'U')
		{
			loc2.xypos.x = location.xypos.x;
			loc2.xypos.y = location.xypos.y - 90;
		}
		loc2.colrow = util.get_colrow_from_xypos(loc2.xypos);
		ports.add(loc2);
		
		this.set_bounds();
	}
	
	
	public void set_bounds()
	{
		bounds = new Rectangle();
		if(direction == 'R')
		{
			bounds.setBounds(location.xypos.x, location.xypos.y - 15, 90, 30);
		}
		else if(direction == 'D')
		{
			bounds.setBounds(location.xypos.x - 15, location.xypos.y, 30, 90);
		}
		else if(direction == 'L')
		{
			bounds.setBounds(location.xypos.x - 90, location.xypos.y - 15, 90, 30);
		}
		else if(direction == 'U')
		{
			bounds.setBounds(location.xypos.x - 15, location.xypos.y - 90, 30, 90);
		}
		
	}
	
	
	public boolean is_inside_bounds(Mouse m)
	{
		return bounds.contains(util.get_grid_node_from_mouse(m).xypos);
	}
	
	
	public boolean is_inside_bounds(Node node)
	{
		
		return bounds.contains(node.xypos);
		
	}
	
	
	// calculates the current
	public void calculate()
	{	
		
	}
	
	
	public String get_type()
	{
		String s = "";
		s = this.getClass().getSimpleName();
		s = s.replaceAll("_", " ");
		return s;
	}
	
	
	public boolean has_sub_types()
	{
		if((this.sub_types == null) || (this.sub_types.isEmpty() == true) || (this.sub_types.size() < 1))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	public String get_sub_type()
	{
		try
		{
			return this.sub_types.get(sub_type_index);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
	public boolean set_sub_type(String st)
	{
		st = st.replaceAll("_", " ");
		if(this.has_sub_types() == true)
		{
			for(int i = 0; i < this.sub_types.size(); i++)
			{
				String subt = this.sub_types.get(i);
				if(st.equalsIgnoreCase(subt) == true)
				{
					this.sub_type_index = i;
					return true;
				}
			}
			return false; // no such sub type name given was matched that is in the list
		}
		else
		{
			return false;// component does not have any sub types
		}
	}
	
	
	@Override
	public Object clone()// throws CloneNotSupportedException
	{
		try
		{
			return super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	// draws component
	public void draw(Graphics g)
	{
		this.draw(g, location.xypos.x, location.xypos.y, direction, color);
	}
	
	
	// draws component at specific location
	public void draw(Graphics g, int x, int y)
	{
		this.draw(g, x, y, direction, color);
	}
	
	
	// draws comp at specific location & orientation
	public void draw(Graphics g, int x, int y, char direct)
	{
		this.draw(g, x, y, direct, color);
	}
	
	
	// draws comp at specific location & color
	public void draw(Graphics g, int x, int y, Color c)
	{
		this.draw(g, x, y, direction, c);
	}
	
	
	// Draws comp at specific location, orientation, and color
	public void draw(Graphics g, int x, int y, char direct, Color c)
	{
		
		this.draw_default_box(g, x, y, direct, c);
	}
	
	
	public void draw_default_box(Graphics g)
	{
		this.draw_default_box(g, location.xypos.x, location.xypos.y, direction, color);
	}
	
	
	public void draw_default_box(Graphics g, int x, int y, char direct, Color c)
	{
		if(c != null)
		{
			g.setColor(c);
		}
		int xpos = x + 3;
		int ypos = y + 3;
		
		if(direct == 'R')
		{
			g.drawLine(xpos, ypos, xpos + 15, ypos);
			g.drawRect(xpos + 15, ypos - 10, 60, 20);
			g.drawLine(xpos + 75, ypos, xpos + 90, ypos);
		}
		if(direct == 'D')
		{
			g.drawLine(xpos, ypos, xpos, ypos + 15);
			g.drawRect(xpos - 10, ypos + 15, 20, 60);
			g.drawLine(xpos, ypos + 75, xpos, ypos + 90);
		}
		if(direct == 'L')
		{
			g.drawLine(xpos, ypos, xpos - 15, ypos);
			g.drawRect(xpos - 75, ypos - 10, 60, 20);
			g.drawLine(xpos - 75, ypos, xpos - 90, ypos);
		}
		if(direct == 'U')
		{
			g.drawLine(xpos, ypos, xpos, ypos - 15);
			g.drawRect(xpos - 10, ypos - 75, 20, 60);
			g.drawLine(xpos, ypos - 75, xpos, ypos - 90);
		}
	}
	
	
	public void draw_ports(Graphics g)
	{
		for(Node p: this.ports)
		{
			p.draw_small(g);
		}
	}
	
	
//	public void draw_small_ports(Graphics g, int x, int y, char direct, Color c)
//	{	
//		int x_differ = location.xypos.x - x;
//		int y_differ = location.xypos.y - y;
//	}
//	
//	
//	public void draw_large_ports(Graphics g, int x, int y, char direct, Color c)
//	{	
//		
//	}
}
