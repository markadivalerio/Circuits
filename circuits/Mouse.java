package circuits;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import components.*;

public class Mouse
{
	private static final Utility	util			= new Utility();
	
	public static final int			LEFT_CLICK		= MouseEvent.BUTTON1;
	public static final int			RIGHT_CLICK		= MouseEvent.BUTTON3;
	
	public static final int			STATUS_SELECT	= 1;
	public static final int			STATUS_MOVE		= 2;
	public static final int			STATUS_DELETE	= 3;
	
	public boolean					on_board, on_menu, on_info_box;
	public boolean					is_clicked;
	public int						button, status;
	public Node						position;
	public static Component_Class	hovering_component;
	public static Wire				partial_wire;
	
	
	public Mouse()
	{
		on_board = true;
		is_clicked = false;
		button = 0;
		status = this.STATUS_SELECT;
		position = new Node(0, 0);
		hovering_component = null;
		partial_wire = null;
	}
	
	
//	public void draw_hovering_component(Graphics g, Component_Class comp)
//	{
//		if((on_board == true) && (comp != null))
//		{
//			comp.draw(g, (get_xcol() * 10) - 2, (get_yrow() * 10) - 2, Color.WHITE);
//			
//		}
//	}
	
	
	public int get_xpos()
	{
		return position.xypos.x;
	}
	
	
	public int get_ypos()
	{
		return position.xypos.y;
	}
	
	
	public int get_xcol()
	{
		return position.colrow.x;
	}
	
	
	public int get_yrow()
	{
		return position.colrow.y;
	}
	
	
	public void set_on_board(boolean b)
	{
		on_board = b;
		on_menu = false;
		on_info_box = false;
	}
	
	
	public void set_on_menu(boolean m)
	{
		on_board = false;
		on_menu = m;
		on_info_box = false;
	}
	
	
	public void set_on_info_box(boolean ib)
	{
		on_board = false;
		on_menu = false;
		on_info_box = ib;
	}
	
	
	public void update(MouseEvent e)
	{
		position.xypos.x = e.getX();
		position.xypos.y = e.getY();
		position.colrow = util.get_colrow_from_xypos(position.xypos);
		
		if(hovering_component != null)
		{
			hovering_component.set_location(this);
		}
	}
	
	
	public void clicked(MouseEvent e)
	{
		is_clicked = true;
		button = e.getButton();
		update(e);
		// out.println(position.toString());
	}
	
	
	public void moved(MouseEvent e)
	{
		update(e);
	}
	
	
	public void dragged(MouseEvent e)
	{
		is_clicked = true;
		button = e.getButton();
		update(e);
	}
	
	
	public void pressed(MouseEvent e)
	{
		is_clicked = true;
		button = e.getButton();
		update(e);
	}
	
	
	public void released(MouseEvent e)
	{
		is_clicked = false;
		button = e.getButton();
		update(e);
	}
	
	
	public void draw_hovering_node(Graphics g)
	{
		if(on_board == true)
		{
			g.setColor(Color.RED);
			g.fillRect((get_xcol() * 10) - 1, (get_yrow() * 10) - 1, 5, 5);
		}
		
	}
	
	
	public void draw_hovering_component(Graphics g)
	{
		if((on_board == true) && (hovering_component != null))
		{
			hovering_component.draw(g, (get_xcol() * 10) - 2, (get_yrow() * 10) - 2, g.getColor());
		}
		if((on_board == true) && (partial_wire != null))
		{
			partial_wire.draw(g, this);
		}
	}
	
}
