package circuits;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import components.*;

/*
 * import java.awt.*;
 * import java.awt.event.*;
 * import javax.swing.*;
 * import javax.swing.event.*;
 * import java.io.*;
 * import java.util.*;
 * import static java.lang.System.*;
 * import static java.lang.Character.*;
 * import java.awt.event.*;
 * import components.*;
 */

public class Utility extends Board
{
	protected ArrayList<Component_Class>	all_components;
	protected ArrayList<Wire>				all_wires;
	protected ArrayList<Node>				all_nodes;
	protected Color							circuit_color, selected_color;
	
	
	public Utility()
	{
		all_components = new ArrayList<Component_Class>();
		all_wires = new ArrayList<Wire>();
		all_nodes = new ArrayList<Node>();
		circuit_color = Color.GREEN;
		selected_color = Color.RED;
	}
	
	
	public Node get_grid_node_from_mouse(Mouse m)
	{
		Node n = new Node();
		n.colrow.x = mouse.get_xcol();
		n.colrow.y = mouse.get_yrow();
		n.xypos.x = this.get_xypos_from_colrow(mouse.position.colrow).x;
		n.xypos.y = this.get_xypos_from_colrow(mouse.position.colrow).y;
		return n;
	}
	
	
	public Point get_colrow_from_xypos(Point xy)
	{
		Point cr;
		cr = new Point(((xy.x + 2) / 10), ((xy.y + 2) / 10));
		return cr;
	}
	
	
	public Point get_xypos_from_colrow(Point cr)
	{
		Point xy;
		xy = new Point(((cr.x * 10) - 2), ((cr.y * 10) - 2));
		return xy;
	}
	
	
	public Component_Class set_new_uid(Component_Class comp)
	{
		return comp;
	}
	
	
	public Component_Class construct_component(Class c)
	{
		Component_Class result = new Component_Class();
		try
		{
			Constructor construct = c.getConstructor();
			return (Component_Class)construct.newInstance();
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
		return result;
	}
	
	
	public Component_Class get_new_component_from_name(String s)
	{
		// List m;
		// ArrayList<Components> m = new ArrayList<Components>();
		Component_Class c = null;
		// Object c = null;
		s = s.toUpperCase();
		s = s.replaceAll("_", " ");
		
		if((s == null) || s.contains("SELECT"))
		{
			c = null;
		}
		else if(s.contains("WIRE"))
		{
			c = new Wire();
		}
		else if(s.contains("POWER SOURCE"))
		{
			c = new Power_Source();
		}
		else if(s.contains("GROUND"))
		{
			c = new Ground();
		}
		else if(s.contains("RESISTOR"))
		{
			c = new Resistor();
		}
		else if(s.contains("CAPACITOR"))
		{
			c = new Capacitor();
		}
		else if(s.contains("INDUCTOR"))
		{
			c = new Inductor();
		}
		else if(s.contains("SWITCH"))
		{
			c = new Switch();
		}
		// else if(s.contains("OP AMP"))
		else if(s.contains("OP AMP"))
		{
			c = new Op_Amp();
		}
		else if(s.contains("TRANSISTOR"))
		{
			c = new Transistor();
		}
		else if(s.contains("DIODE"))
		{
			c = new Diode();
		}
		else if(s.contains("MOSFET"))
		{
			c = new MOSFET();
		}
		else if(s.contains("CUSTOM"))
		{
			c = new Custom();
		}
		else
		{
			c = null;
		}
		// protected String[] components_list = {"0. Select","1. Wire","2. Power Source","3. Ground","4. Sensor",
		// "5. Resistor","6. Capacitor","7. Inductor","8. Switch","9. Op Amp","10. Transistor","11. Diode",
		// "12. MOSFET","13. Custom Component"};
		return c;
	}
	
	
//	public void add_to_board(String name)
//	{
//		// out.println(name);
//		/*
//		 * Component comp = new Component();
//		comp = comp.get_component_from_id(selected_id);
//		comp.set_id(selected_id);
//		comp.set_color(Color.GREEN);
//		comp.set_direction(orientation);
//		ArrayList<Node> np = comp.get_node_ports();
//		np.add(new Node(xcol_pos, yrow_pos));
//		comp.set_node_ports(np);
//		comp.set_uid(circuit_components.size() + 1);
//		circuit_components.add(comp);
//		return comp;
//		 */
//		Component_Class comp = get_new_component_from_name(name);
//		// comp.location = this.mouse.position;
//		comp.set_location(this.mouse.position);
//		// comp.location = this.get_mouse().position;
//		comp.allocate_ports();
//		this.set_new_uid(comp);
//		comp.color = circuit_color;
//		all_components.add(comp);
//		
//	}
	
	public Component_Class get_comp_under_mouse()
	{
		for(Component_Class c: all_components)
		{
			if(c.is_inside_bounds(mouse) == true)
			{
				return c;
			}
		}
		for(Component_Class w: all_wires)
		{
			if(w.is_inside_bounds(mouse) == true)
			{
				return w;
			}
		}
		return null;
	}
	
	
	public void add_to_board(Component_Class c)
	{
		if(c.is_wire() == false)
		{
			add_component_to_board(c);
		}
		else
		{
			Wire w = (Wire)c;
			add_wire_to_board(w);
		}
	}
	
	
	public void add_component_to_board(Component_Class comp)
	{
		// out.println("here");
		// Component_Class comp = new Component_Class();// get_new_component_from_string(this.menu.selected);
		// c.location = new Node(this.mouse.xpos, this.mouse.ypos);
		// c.location = new Node(this.mouse.position.xypos);
		comp.set_location(this.get_mouse());
		comp.direction = super.orientation;
		// comp.allocate_ports();
		comp = set_new_uid(comp);
		comp.color = Color.WHITE;
		all_components.add(comp);
	}
	
	
	public void add_wire_to_board(Wire w)
	{
		all_wires.add(w);
	}
	
	
	public void remove_from_board(Component_Class c)
	{
		if(c != null)
		{
			if(c.is_wire() == true)
			{
				Wire wire = (Wire)c;
				all_wires.remove(wire);
			}
			else if(c.is_wire() == false)
			{
				all_components.remove(c);
			}
		}
	}
	
	
	public void draw(Graphics g)
	{
		
		for(Component_Class c: all_components)
		{
			c.draw(g);
			c.draw_ports(g);
		}
		for(Wire w: all_wires)
		{
			w.draw(g);
		}
		for(Node n: all_nodes)
		{
			n.draw_small(g);
		}
	}
}
/*
 * circuits.Utility.Utility(ArrayList<Component>, ArrayList<Wire>)
 * circuits.Utility.components_connected_to(Component)
 * circuits.Utility.components_connected_to(Node)
 * circuits.Utility.components_connected_to(Wire)
 * circuits.Utility.contains_type(ArrayList<Component>, String)
 * circuits.Utility.get_all_except(ArrayList<Component>, Component)
 * circuits.Utility.get_all_except(ArrayList<Node>, Node)
 * circuits.Utility.get_all_except(ArrayList<Wire>, Wire)
 * circuits.Utility.get_all_except_type(ArrayList<Component>, String)
 * circuits.Utility.get_all_nodes(ArrayList<Component>)
 * circuits.Utility.get_all_type(ArrayList<Component>, String)
 * circuits.Utility.get_component_ports_equivalent_nodes(Component)
 * circuits.Utility.is_point_a_node(Point)
 * circuits.Utility.listx_contains_any_of_listy(ArrayList<? extends Object>, ArrayList<? extends Object>)
 * circuits.Utility.nodes_connected_to(Component)
 * circuits.Utility.nodes_connected_to(Node)
 * circuits.Utility.nodes_connected_to(Wire)
 * circuits.Utility.remove_duplicates(ArrayList<? extends Object>)
 * circuits.Utility.set_actual_nodes_to_component_ports_values(Component)
 * circuits.Utility.set_component_ports_to_actual_node_values(Component)
 * circuits.Utility.wires_connected_to(Component, int)
 * circuits.Utility.wires_connected_to(Node)
 * circuits.Utility.wires_connected_to(Wire)
*/
