package circuits;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import components.*;


public class Board extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
	protected static Circuits			frame;
	protected static final Menu_Box		menu_box		= new Menu_Box();
	protected static final Info_Box		info_box		= new Info_Box();
	protected static final Utility		util			= new Utility();
	protected static final Mouse		mouse			= new Mouse();
	protected static final Keyboard		keyboard		= new Keyboard();
	protected static final int			xsize			= 1000;
	protected static final int			ysize			= 1000;
	protected static final Color		new_blue		= new Color(56, 106, 237);	// a type of blue
	protected static final Color		dark_green		= new Color(50, 146, 3);	// a type of green
																					
																					
																					
	protected static char				orientation		= 'R';
	protected static Component_Class	selected_comp	= null;
	
	
	public Board()
	{
		this.setPreferredSize(new Dimension(xsize, ysize));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		
		
		// initialize();
	}
	
	
	// public void initialize()
	public void initialize(Circuits c)
	{
		// selected = util.get_new_component_from_string(menu.selected);
		// partial_wire = new Wire_Comp();
		// orientation = 'U';
		
//		keys = new HashMap<Character, Boolean>();
//		keys.put(ROTATE, false);
//		keys.put(SELECT, false);
//		keys.put(ENTER, false);
		
		orientation = 'R';
		selected_comp = null;
		
		// frame = (Circuits)SwingUtilities.getRoot(this);
		frame = c;
		if(frame != null)
		{
			menu_box.initialize(c);
			info_box.initialize(c);
			// out.println(frame);
		}
	}
	
	
	public void rotate_orientation()
	{
		switch(orientation)
		{
			case 'R':
				orientation = 'D';
				break;
			case 'D':
				orientation = 'L';
				break;
			case 'L':
				orientation = 'U';
				break;
			case 'U':
				orientation = 'R';
				break;
		}
		menu_box.refresh = true;
	}
	
	
	public Mouse get_mouse()
	{
		return mouse;
	}
	
	
	public JSplitPane get_menu_jsplitpane()
	{
		JScrollPane scroll_info_box = new JScrollPane(info_box);
		scroll_info_box.setViewportView(info_box);
		scroll_info_box.getVerticalScrollBar().setUnitIncrement(25);
		scroll_info_box.setMinimumSize(new Dimension(250, 900));
		
		JSplitPane split_menu = new JSplitPane(JSplitPane.VERTICAL_SPLIT, menu_box, scroll_info_box);
		split_menu.setDividerLocation(400);
		split_menu.setPreferredSize(new Dimension(250, 700));
		
		return split_menu;
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw_background(g);
//		draw_board(g);
		// perform_actions(g);
		// sync
		perform_actions2(g);
	}
	
	
	public void draw_background(Graphics g)
	{
		if(menu_box.show_info = true)
		{
			menu_box.update_board(this);
			menu_box.repaint();
		}
		
		if((mouse.on_board == true) && (mouse.hovering_component != null))
		{
			g.setColor(Color.YELLOW);
			mouse.draw_hovering_component(g);
		}
		
		
		for(int y = 0; y < 100; y++)
		{
			for(int x = 0; x < 100; x++)// NOTE - gets laggy around 120x120
			{
				if(((x % 10) == 0) && ((y % 10) == 0))
				{
					g.setColor(Color.CYAN);
					g.fillOval(x * 10 - 1, y * 10 - 1, 3, 3);
				}
				else
				{
					g.setColor(new_blue);
					g.fillOval(x * 10, y * 10, 2, 2);
				}
			}
		}
		
		mouse.draw_hovering_node(g);
		
		util.draw(g);// draws the components that have been set down
	}
	
	
//	public void draw_board(Graphics g)
//	{
//		util.draw(g);
//	}
	
	
	
	public void sync(Graphics g)
	{
		// retrieves the component on the board that is underneath the mouse when it is clicked &
		// brings up the component details in the info box, and (right click) deletes it from the board
		if((mouse.is_clicked == true) && (mouse.on_board == true))
		{
//			if((mouse.button == mouse.LEFT_CLICK) &&
//					(circuits_menu.selected.equalsIgnoreCase(circuits_menu.MENU_SELECT) == true))
//			{
//				info_box.set(util.get_comp_under_mouse());
//				// return;
//			}
//			else if((mouse.button == mouse.RIGHT_CLICK) &&
//					(circuits_menu.selected.equalsIgnoreCase(circuits_menu.MENU_SELECT) == true))
//			{
//				// DELETE COMPONENT FROM THE BOARD
//				info_box.set(util.get_comp_under_mouse());
//				util.remove_from_board(util.get_comp_under_mouse());
//				// return;
//			}
			out.println(util.get_comp_under_mouse().getClass().getSimpleName());
		}
		
		Component_Class temp = util.get_new_component_from_name(menu_box.selected);
		
		if(temp != null)
		{
			temp.image_id = menu_box.jcombo_images.getSelectedIndex();
			if(temp.has_sub_types() == true)
			{
				temp.set_sub_type((String)menu_box.jcombo_types.getSelectedItem());
			}
		}
		info_box.set(temp);
		if(temp == null)
		{
			mouse.partial_wire = null;
			mouse.hovering_component = null;
		}
		else if(temp.is_wire() == false)
		{
			mouse.partial_wire = null;
			
			mouse.hovering_component = this.info_box.component;
			mouse.hovering_component.direction = orientation;
			mouse.hovering_component.set_location(mouse);
		}
		else if(temp.is_wire() == true)
		{
			mouse.partial_wire = new Wire();
			mouse.partial_wire.direction = orientation;
			mouse.partial_wire.set_location(mouse);
			
			mouse.hovering_component = null;
		}
		menu_box.repaint();
		menu_box.setFocusable(false);
		this.requestFocusInWindow();
		menu_box.setFocusable(true);
	}
	
	
	public void perform_actions2(Graphics g)
	{
		if(menu_box.refresh == true)
		{
			sync(g);
			menu_box.refresh = false;
		}
		
		
		// this adds a component or wire to the board
		if((mouse.is_clicked == true) && (mouse.on_board == true))
		{
			if((mouse.button == mouse.LEFT_CLICK) && (menu_box.selected.equalsIgnoreCase(menu_box.MENU_SELECT) == true))
			{
				sync(g);
				info_box.set(util.get_comp_under_mouse());
				// return;
			}
			else if((mouse.button == mouse.RIGHT_CLICK) &&
					(menu_box.selected.equalsIgnoreCase(menu_box.MENU_SELECT) == true))
			{
				// DELETE COMPONENT FROM THE BOARD
				sync(g);
				info_box.set(util.get_comp_under_mouse());
				util.remove_from_board(util.get_comp_under_mouse());
				// return;
			}
			
			
			// if a component is selected to add, and the mouse is clicked on the board (and the component is not a
			// wire)
			if((mouse.hovering_component != null) && (mouse.partial_wire == null))
			{
				util.add_to_board((Component_Class)this.info_box.component.clone());
			}
			// if the mouse is clicked on the board and the component selected to add is a wire
			else if((mouse.hovering_component == null) && (mouse.partial_wire != null))
			{
				// left click will add a corner and continue with the current wire
				if(mouse.button == mouse.LEFT_CLICK)
				{
					mouse.partial_wire.add_node_to_path(util.get_grid_node_from_mouse(mouse));
				}
				else if(mouse.button == mouse.RIGHT_CLICK)
				{
					util.add_to_board(mouse.partial_wire);
					mouse.partial_wire = new Wire();
				}
			}
			
		}
		
		
		
		if(mouse.is_clicked == true)
		{
			mouse.is_clicked = false;
		}
	}
	
	
	public void perform_actions(Graphics g)
	{
		// if there is a change in menu selection...
		// 1. update menu's component type and orientation
		// 2. update mouse's hovering component type, location, and orientation
		// 3. repaint the new selected component in the menu
		// 4. put focus back onto the board (off of the drop down menu)
		if(menu_box.refresh == true)
		{
			menu_box.set_component(util.get_new_component_from_name(menu_box.selected), orientation);
			
			if(menu_box.component != null)
			{
				if(menu_box.component.has_sub_types() == true)
				{
					menu_box.component.set_sub_type((String)menu_box.jcombo_types.getSelectedItem());
				}
				this.info_box.set(menu_box.component);
				if(menu_box.component.is_wire() == false)
				{
					mouse.partial_wire = null;
					
					// mouse.hovering_component = util.get_new_component_from_name(circuits_menu.selected);
					mouse.hovering_component = this.info_box.component;
					mouse.hovering_component.direction = orientation;
					mouse.hovering_component.set_location(mouse);
				}
				else if(menu_box.component.is_wire() == true)
				{
					mouse.partial_wire = new Wire();
					mouse.partial_wire.direction = orientation;
					mouse.partial_wire.set_location(mouse);
					
					mouse.hovering_component = null;
				}
			}
			else if(menu_box.component == null)
			{
				this.info_box.clear();
				mouse.partial_wire = null;
				mouse.hovering_component = null;
			}
			// info_box.repaint();
			menu_box.repaint();
			menu_box.setFocusable(false);
			this.requestFocusInWindow();
			menu_box.setFocusable(true);
		}
		
		
		
		if(mouse.is_clicked == true)// if the mouse was clicked
		{
			if((mouse.on_board == true) && (menu_box.component != null))
			{
				// util.add_to_board(menu.selected);
				if(menu_box.component.is_wire() == false)
				{
//					Component_Class comp = util.get_new_component_from_name(circuits_menu.component.getClass().getSimpleName());
//					comp.direction = orientation;
//					util.add_to_board(comp);
					util.add_to_board((Component_Class)this.info_box.component.clone());
				}
				
				// left click - adds the mouse's position to the wire's path
				if((menu_box.component.is_wire() == true) && (mouse.button == MouseEvent.BUTTON1))
				{
					mouse.partial_wire.add_node_to_path(util.get_grid_node_from_mouse(mouse));
				}
				// right click - finishes wire and adds it to the board
				else if((menu_box.component.is_wire() == true) && (mouse.button == MouseEvent.BUTTON3))
				{
					util.add_to_board(mouse.partial_wire);
					mouse.partial_wire = new Wire();
				}
			}
			
			
			else if((mouse.on_board == true) && (menu_box.component == null))
			{
				// check if a component has been selected
				selected_comp = util.get_comp_under_mouse();
				if(selected_comp != null)
				{
					// System.out.println(selected_comp.getClass().getSimpleName());
					// this.info_box = selected_comp.info_box;
					// this.info_box.validate();
					this.info_box.set(selected_comp);
					// Info_Box ib = new Info_Box(selected_comp);
					// out.println(ib.component.getClass().getSimpleName());
				}
				else if(selected_comp == null)
				{
					this.info_box.clear();
				}
			}
			mouse.is_clicked = false;
		}
		
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		mouse.clicked(e);
	}
	
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		mouse.moved(e);
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		mouse.dragged(e);
	}
	
	
	@Override
	public void mousePressed(MouseEvent e)
	{}
	
	
	@Override
	public void mouseReleased(MouseEvent e)
	{}
	
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		mouse.set_on_board(true);
		mouse.update(e);
	}
	
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		mouse.set_on_board(false);
		mouse.update(e);
	}
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		keyboard.key_pressed(e);
	}
	
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		keyboard.key_released(e);
	}
	
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		keyboard.key_typed(e);
		
//		switch(toUpperCase(keyboard.key))
//		{
//			case '\t':// tab
//				rotate_orientation();
//				break;
//			case '\n':// enter
//				// do something
//				break;
//			case ' ':
//				circuits_menu.change_to("SELECT");
//				break;
//			case 'W':
//				circuits_menu.change_to("WIRE");
//				break;
//			case 'R':
//				circuits_menu.change_to("RESISTOR");
//				break;
//			case 'C':
//				circuits_menu.change_to("CAPACITOR");
//				break;
//			case 'I':
//				circuits_menu.change_to("INDUCTOR");
//				break;
//		}
//		circuits_menu.refresh = true;
	}
}
