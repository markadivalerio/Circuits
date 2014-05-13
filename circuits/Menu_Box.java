package circuits;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import components.*;


public class Menu_Box extends JPanel implements ActionListener, ItemListener, MouseListener, MouseMotionListener//@formatter: off
{
	private static final String new_line = "\n";
	protected static final String MENU_SELECT = "SELECT";
	
	private static Circuits			frame;
	public static Board				board;
	
	
	public String					selected;
	public boolean					refresh;
	public boolean					show_info;
	public static Component_Class	component;
	public static JTextField		search_bar;
	public static JComboBox			jcombo_components,jcombo_types, jcombo_images;
	public static JButton			select_button, move_button, delete_button;
	
	public ArrayList<String>		components_list	= new ArrayList<String>(Arrays.asList(
													"0. Select", "1. Wire", "2. Power Source", "3. Ground", "4. Resistor",
													"5. Capacitor", "6. Inductor", "7. Switch", "8. Op Amp", "9. Transistor",
													"10. Diode", "11. MOSFET", "12. Custom"));
	//@formatter: on
	
	
	public Menu_Box()
	{
		this.setLayout(null);
		
		select_button = new JButton("Select");
		select_button.setBounds(0, 10, 75, 25);
		select_button.addActionListener(this);
		this.add(select_button);
		
		move_button = new JButton("Move");
		move_button.setBounds(75, 10, 85, 25);
		move_button.addActionListener(this);
		this.add(move_button);
		
		delete_button = new JButton("Delete");
		delete_button.setBounds(160, 10, 75, 25);
		delete_button.addActionListener(this);
		this.add(delete_button);
		
		
		search_bar = new JTextField("");
		search_bar.setBounds(75, 40, 160, 25);
		search_bar.addActionListener(this);
		this.add(search_bar);
		
		jcombo_components = new JComboBox(components_list.toArray());
		jcombo_components.setSelectedIndex(0);
		jcombo_components.addActionListener(this);
		jcombo_components.setBounds(5, 70, 230, 25);
		this.add(jcombo_components);
		
		jcombo_types = new JComboBox(new String[] {"Types"});
		jcombo_types.setSelectedIndex(0);
		jcombo_types.addActionListener(this);
		jcombo_types.setBounds(60, 100, 170, 25);
		jcombo_types.setVisible(false);
		this.add(jcombo_types);
		
		jcombo_images = new JComboBox(new String[] {"Default Image"});
		jcombo_images.setSelectedIndex(0);
		jcombo_images.addActionListener(this);
		jcombo_images.setBounds(60, 130, 170, 25);
		jcombo_images.setVisible(false);
		this.add(jcombo_images);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.setMinimumSize(new Dimension(250, 400));
		this.setBackground(Color.BLACK);
		this.setOpaque(true);
	}
	
	
	public void initialize(Circuits c)
	{
		String s = ((String)jcombo_components.getSelectedItem());
		selected = s.substring(s.indexOf(". ") + 2);
		refresh = true;
		show_info = true;
		
		// frame = (Circuits)SwingUtilities.getRoot(this);
		frame = c;
		// initialize_menu_bar();
	}
	
	
	public void initialize_menu_bar()
	{
		MenuBar menubar = new MenuBar();
		Menu m = null;
		
		m = new Menu("File");
		menubar.add(m);
		m.add(get_menu_item("New Board"));
		m.add(get_menu_item("Open"));
		m.add(get_menu_item("Save"));
		m.addSeparator();
		m.add(get_menu_item("Exit"));
		
		m = new Menu("Edit");
		menubar.add(m);
		m.add(get_menu_item("Undo"));
		m.add(get_menu_item("Redo"));
		m.addSeparator();
		m.add(get_menu_item("Cut"));
		m.add(get_menu_item("Copy"));
		m.add(get_menu_item("Paste"));
		m.addSeparator();
		m.add(get_menu_item("Zoom In"));
		m.add(get_menu_item("Zoom Out"));
		
		m = new Menu("View");
		menubar.add(m);
		m.add(get_check_item("Show Current"));
		m.add(get_check_item("Show Voltage"));
		m.add(get_check_item("Show Power"));
		m.add(get_check_item("Conventional Current Motion"));
		m.add(get_check_item("White Background"));
		m.add(get_check_item("Show Grid"));
		m.add(get_check_item("Show Labels"));
		
		/*
		 * LIST OF ALL COMPONENTS
		 */
		m = new Menu("Components");
		menubar.add(m);
		m.add(get_check_item("Select"));
		m.add(get_check_item("Delete"));
		m.add(get_check_item("Add To Favorites"));
		m.add(get_check_item("Remove From Favorites"));
		
		m.addSeparator();
		
		Menu m_fav = new Menu("Favorites");
		m.add(m_fav);
		m_fav.add(get_check_item("Add Wire", "Wire"));
		m_fav.add(get_check_item("Add Ground", "Ground"));
		m_fav.add(get_check_item("Add Power Source", "Power Source"));
		m_fav.add(get_check_item("Add Switch", "Switch"));
		m_fav.add(get_check_item("Add Resistor", "Resistor"));
		m_fav.add(get_check_item("Add Capacitor", "Capacitor"));
		m_fav.add(get_check_item("Add Inductor", "Inductor"));
		
		m.addSeparator();
		m.add(get_check_item("Add Wire", "Wire"));
		
		Menu m_source = new Menu("Source");
		m.add(m_source);
		m_source.add(get_check_item("Add Ground", "Ground"));
		m_source.add(get_check_item("Add DC Current Source", "Power Source"));
		m_source.add(get_check_item("Add DC Voltage Source", "Power Source"));
		m_source.add(get_check_item("Add DC Power Source", "Power Source"));
		m_source.add(get_check_item("Add AC Current Source", "Power Source"));
		m_source.add(get_check_item("Add AC Voltage Source", "Power Source"));
		m_source.add(get_check_item("Add AC Power Source", "Power Source"));
		m_source.add(get_check_item("Add Clock", "Power Source"));
		
		Menu m_pass = new Menu("Passive");
		m.add(m_pass);
		m_pass.add(get_check_item("Add Switch", "Switch"));
		m_pass.add(get_check_item("Add Resistor", "Resistor"));
		m_pass.add(get_check_item("Add Capacitor", "Capacitor"));
		m_pass.add(get_check_item("Add Inductor", "Inductor"));
		m_pass.add(get_check_item("Add LED", "LED"));
		
		
		Menu m_act = new Menu("Active");
		m.add(m_act);
		m_act.add(get_check_item("Add Switch", "Switch"));
		m_act.add(get_check_item("Add Diode", "Diode"));
		m_act.add(get_check_item("Add Transistor", "Transistor"));
		m_act.add(get_check_item("Add Operational Amplifier", "Op Amp"));
		m_act.add(get_check_item("Add MOSFET", "MOSFET"));
		m_act.add(get_check_item("Add JFET", "JFET"));
		
		
		Menu m_logic = new Menu("Logic");
		m.add(m_logic);
		m_logic.add(get_check_item("Add Inverter NOT Gate", "Logic_NOT"));
		m_logic.add(get_check_item("Add AND Gate", "Logic_AND"));
		m_logic.add(get_check_item("Add OR Gate", "Logic_OR"));
		m_logic.add(get_check_item("Add NAND Gate", "Logic_NAND"));
		m_logic.add(get_check_item("Add NOR Gate", "Logic_NOR"));
		m_logic.add(get_check_item("Add XOR Gate", "Logic_XOR"));
		m_logic.add(get_check_item("Add XNOR Gate", "Logic_XNOR"));
		m_logic.add(get_check_item("MUX", "Mux"));
		m_logic.add(get_check_item("Encoder", "Encoder"));
		m_logic.add(get_check_item("Decoder", "Decoder"));
		
		Menu m_chips = new Menu("Chips");
		m.add(m_chips);
		/*
		 * ADC
		 * DAC
		 * Comparator
		 * 
		 */
		
		Menu m_other = new Menu("Other");
		m.add(m_other);
		
		m.addSeparator();
		m.add(get_check_item("Add Custom Component", "Custom"));
		
		
		/*
		 * END OF ALL COMPONENTS
		 */
		
		
		m = new Menu("Simulation");
		menubar.add(m);
		m.add(get_menu_item("Options"));
		
		
		frame.setMenuBar(menubar);
	}
	
	
	public MenuItem get_menu_item(String s)
	{
		MenuItem mi = new MenuItem(s);
		mi.addActionListener(this);
		return mi;
	}
	
	
	public CheckboxMenuItem get_check_item(String s)
	{
		CheckboxMenuItem mi = new CheckboxMenuItem(s);
		mi.addItemListener(this);
		mi.setActionCommand("");
		return mi;
	}
	
	
	public CheckboxMenuItem get_check_item(String s, String t)
	{
		CheckboxMenuItem mi = new CheckboxMenuItem(s);
		mi.addItemListener(this);
		mi.setActionCommand(t);
		return mi;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == jcombo_components)
		{
			String s = ((String)jcombo_components.getSelectedItem());
			DefaultComboBoxModel model = (DefaultComboBoxModel)jcombo_components.getModel();
			selected = s.substring(s.indexOf(". ") + 2);
			Component_Class temp = frame.board.util.get_new_component_from_name(selected);
			
			if(temp != null)
			{
				if(temp.has_sub_types() == true)
				{
					jcombo_images.setVisible(true);
					
					jcombo_types.setVisible(true);
					DefaultComboBoxModel mo = new DefaultComboBoxModel(component.sub_types.toArray());
					jcombo_types.setModel(mo);
					jcombo_types.setSelectedIndex(temp.sub_type_index);
				}
				else
				{
					jcombo_types.setVisible(false);
					
					jcombo_images.setVisible(true);
					// jcombo_images.setBounds(50, 100, 170, 25);
				}
				
				
			}
			else if(temp == null)// i.e. select
			{
				jcombo_types.setVisible(false);
				jcombo_images.setVisible(false);
			}
			refresh = true;
			update_check_marks();
		}
		if(e.getSource() == jcombo_types)
		{
			String s = (String)jcombo_types.getSelectedItem();
			Component_Class temp = frame.board.util.get_new_component_from_name((String)jcombo_components.getSelectedItem());
			temp.set_sub_type(s);
			this.set_component(temp, frame.board.orientation);
			refresh = true;
		}
		if(e.getSource() == jcombo_images)
		{
			int image_index = jcombo_images.getSelectedIndex();
			component.image_id = image_index;
			refresh = true;
		}
		if(e.getSource() == search_bar)
		{
			String text = search_bar.getText();
			// search_bar.append(text + new_line);
			out.println(text);
			search_bar.selectAll();
		}
		if(e.getSource() == select_button)
		{
			this.change_to("Select");
		}
	}
	
	
	
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		this.repaint();
		Object item = e.getItemSelectable();
		if(item instanceof CheckboxMenuItem)
		{
			MenuItem mi = (MenuItem)item;
			String s = mi.getActionCommand();
			if(s.length() > 0)
			{
				try
				{
					this.change_to(s);
					this.set_component(frame.board.util.get_new_component_from_name(s), frame.board.orientation);
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
				}
			}
		}
	}
	
	
	public void change_to(String selection)
	{
		if(selection == null)
		{
			jcombo_components.setSelectedIndex(0);
			String sel = ((String)jcombo_components.getSelectedItem());
			DefaultComboBoxModel model = (DefaultComboBoxModel)jcombo_components.getModel();
			selected = sel.substring(sel.indexOf(". ") + 2);
			refresh = true;
			return;
		}
		for(int index = 0; index < components_list.size(); index++)
		{
			String s = components_list.get(index);
			s = s.substring(s.indexOf(". ") + 2);
			s = s.toUpperCase();
			if(s.equalsIgnoreCase(selection))
			{
				jcombo_components.setSelectedIndex(index);
				String sel = ((String)jcombo_components.getSelectedItem());
				DefaultComboBoxModel model = (DefaultComboBoxModel)jcombo_components.getModel();
				selected = sel.substring(sel.indexOf(". ") + 2);
				refresh = true;
				return;
			}
		}
	}
	
	
	public void update_board(Board b)
	{
		board = b;
	}
	
	
	public void update_check_marks()
	{	
		
	}
	
	
	public void update_scroll_box()
	{	
		
	}
	
	
	public void set_component(Component_Class c, char direct)
	{
		component = c;
		
		if(component != null)
		{
			component.direction = direct;
			component.color = Color.YELLOW;
			Node loc = new Node();
			Node loc2 = new Node();
			if(component.direction == 'R')
			{
				loc = new Node(60, 360);
				loc2 = new Node(150, 360);
			}
			else if(component.direction == 'D')
			{
				loc = new Node(105, 270);
				loc2 = new Node(105, 360);
			}
			else if(component.direction == 'L')
			{
				loc = new Node(150, 360);
				loc2 = new Node(60, 360);
			}
			else if(component.direction == 'U')
			{
				loc = new Node(105, 360);
				loc2 = new Node(105, 270);
			}
			
			
			if(component.is_wire() == true)
			{
				((Wire)component).add_node_to_path(loc);
				((Wire)component).add_node_to_path(loc2);
			}
			else
			{
				component.set_location(loc);
			}
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
//		if(comp != null)
//		{
//			comp.draw(g);
//		}
		g.setColor(Color.WHITE);
		g.drawString("Search:", 20, 40 + 17);
		if(jcombo_types.isVisible() == true)
		{
			g.drawString("Type:", (jcombo_types.getBounds().x - 45), jcombo_types.getBounds().y + 17);
		}
		if(jcombo_images.isVisible() == true)
		{
			g.drawString("Image:", (jcombo_images.getBounds().x - 45), jcombo_images.getBounds().y + 17);
		}
		if(show_info == true)
		{
			draw_info(g);
		}
		
		refresh = false;
	}
	
	
	public void draw_info(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.drawString("xypos:  " + board.mouse.get_xpos() + ", " + board.mouse.get_ypos(), 20, 250);
		g.drawString("colrow:  " + board.mouse.get_xcol() + ", " + board.mouse.get_yrow(), 20, 275);
		g.drawString("Orientation:  " + board.orientation, 20, 300);
		g.drawString("Components:  " + board.util.all_components.size(), 20, 325);
		g.drawString("Wires:  " + board.util.all_wires.size(), 20, 350);
		
		String loc = "";
		if(this.board.get_mouse().on_board == true)
		{
			loc = "On Board";
		}
		else if(this.board.get_mouse().on_info_box == true)
		{
			loc = "On Info Box";
		}
		else if(this.board.get_mouse().on_menu == true)
		{
			loc = "On Menu";
		}
		else
		{
			loc = "Not On Application";
		}
		g.drawString("Mouse : " + loc, 20, 375);
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		board.mouse.clicked(e);
	}
	
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		board.mouse.moved(e);
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		board.mouse.dragged(e);
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
		board.mouse.set_on_menu(true);
		board.mouse.update(e);
	}
	
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		board.mouse.set_on_menu(false);
		board.mouse.update(e);
	}
}
