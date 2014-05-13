package components;

import java.awt.*;
import circuits.*;

public class Node
{
	private static final Utility	util	= new Utility();
	
	public String					label;
	public Point					xypos;
	public Point					colrow;
	public double					voltage;
	public boolean					show_label, show_value;
	
	
	public Node()
	{
		this(new Point(0, 0));
	}
	
	
	public Node(int x, int y)
	{
		this(new Point(x, y));
	}
	
	
	public Node(Point p)
	{
		label = "";
		voltage = 0.0;
		xypos = p;
		colrow = util.get_colrow_from_xypos(p);
	}
	
	
	public boolean is_at(int xc, int yr)
	{
		return is_at(new Node(xc, yr));
	}
	
	
	public boolean is_at(Point p)
	{
		return is_at(new Node(p));
	}
	
	
	public boolean is_at(Node n)
	{
		if((this.colrow.equals(n.colrow) == true) && (this.xypos.equals(n.xypos) == true))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
//	
//	public Point get_colrow_from_xypos(Point xy)
//	{
//		Point cr;
//		cr = new Point(((xy.x + 2) / 10), ((xy.y + 0) / 10));
//		return cr;
//	}
//	
//	
//	public Point get_xypos_from_colrow(Point cr)
//	{
//		Point xy;
//		xy = new Point(((cr.x / 10) - 2), ((cr.y / 10) - 0));
//		return xy;
//	}
	
	
	public void draw_small(Graphics g)
	{
		g.fillRect(xypos.x + 2, xypos.y + 2, 3, 3);
	}
	
	
	public void draw_large(Graphics g)
	{
		g.fillRect(xypos.x + 1, xypos.y + 1, 5, 5);
	}
	
	
	@Override
	public String toString()
	{
		String s = "NODE: label = " + this.label + ", \tvoltage = " + voltage;
		s += "\n(" + xypos.x + ", " + xypos.y + ") = (" + colrow.x + ", " + colrow.y + ")\n";
		return s;
	}
}
