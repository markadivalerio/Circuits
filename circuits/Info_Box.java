package circuits;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import components.*;

public class Info_Box extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
	private static final Utility		util	= new Utility();
	
	private static Circuits				frame;
	public static Board					board;
	public static Component_Class		component;
	public static JButton				save, cancel, remove;
	public static JTextField			label_text;
	public static ArrayList<JTextField>	prop_name_textfield;
	public static ArrayList<JTextField>	prop_value_textfield;
	
	
	public Info_Box()
	{
		this.setPreferredSize(new Dimension(225, 1800));
		this.setLayout(null);
		this.setBackground(Color.GRAY);
		this.setMinimumSize(new Dimension(225, 1800));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.clear();
		// initialize();
	}
	
	
//	public Info_Box(Component_Class c)
//	{
//		this.setPreferredSize(new Dimension(225, 1600));
//		this.setLayout(null);
//		this.setBackground(Color.GRAY);
//		this.setMinimumSize(new Dimension(225, 1600));
//		
//		this.set(c);
//		// initialize();
//	}
	
	
	// public void initialize()
	public void initialize(Circuits c)
	{
		// frame = (Circuits)SwingUtilities.getRoot(this);
		frame = c;
		
		save = new JButton("Save");
		save.setBounds(0, 5, 75, 25);
		save.addActionListener(this);
		save.setVisible(false);
		this.add(save);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(75, 5, 75, 25);
		cancel.addActionListener(this);
		cancel.setVisible(false);
		this.add(cancel);
		
		remove = new JButton("Remove");
		remove.setBounds(150, 5, 75, 25);
		remove.addActionListener(this);
		remove.setVisible(false);
		this.add(remove);
		
		label_text = new JTextField("");
		label_text.setBounds(110, 55, 100, 25);
		label_text.addActionListener(this);
		label_text.setVisible(false);
		this.add(label_text);
		
		if(component != null)
		{
			if((component.label == null) || (component.label.equals("")))
			{
				label_text.setText("");
			}
			else
			{
				label_text.setText(component.label);
			}
		}
		
		prop_name_textfield = new ArrayList<JTextField>(64);
		prop_value_textfield = new ArrayList<JTextField>(64);
		for(int j = 0; j < 64; j++)// components can have max of 64 properties
		{
			JTextField jtf1 = new JTextField("" + j);
			jtf1.setBounds(25, 150 + (25 * j), 100, 20);
			jtf1.addActionListener(this);
			jtf1.setVisible(false);
			prop_name_textfield.add(jtf1);
			this.add(jtf1);
			
			JTextField jtf2 = new JTextField("" + j);
			jtf2.setBounds(130, 150 + (25 * j), 60, 20);
			jtf2.addActionListener(this);
			jtf2.setVisible(false);
			prop_value_textfield.add(jtf2);
			this.add(jtf2);
		}
	}
	
	
	public void set(Component_Class c)
	{
		if(c != null)
		{
			component = (Component_Class)c.clone();
		}
		else
		{
			this.clear();
		}
	}
	
	
	public void clear()
	{
		component = null;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// what happens when JButton or JComboBox is clicked
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		
		if(component == null)
		{
			save.setVisible(false);
			cancel.setVisible(false);
			remove.setVisible(false);
			label_text.setVisible(false);
			
			for(int i = 0; i < this.prop_name_textfield.size(); i++)
			{
				JTextField jtf1 = this.prop_name_textfield.get(i);
				JTextField jtf2 = this.prop_value_textfield.get(i);
				jtf1.setVisible(false);
				jtf2.setVisible(false);
			}
		}
		else if(component != null)
		{
			save.setVisible(true);
			cancel.setVisible(true);
			remove.setVisible(true);
			label_text.setVisible(true);
			
			if(component.properties.size() > 0)
			{
				g.drawString("Properties:", 40, 135);
				for(int i = 0; i < this.prop_name_textfield.size(); i++)
				{
					JTextField jtf1 = this.prop_name_textfield.get(i);
					JTextField jtf2 = this.prop_value_textfield.get(i);
					if(i < component.properties.size())
					{
						g.drawString((i + 1) + ". ", 5, 150 + (25 * i) + 17);
						
						jtf1.setText("" + (component.properties.get(i)).name);
						jtf1.setVisible(true);
						
						jtf2.setText("" + (component.properties.get(i)).value);
						jtf2.setVisible(true);
						
						g.drawString((component.properties.get(i)).unit, 205, 150 + (25 * i) + 17);
					}
					else
					{
						jtf1.setVisible(false);
						jtf2.setVisible(false);
					}
				}
			}
			else
			// there is a valid component (that is not null) but it has no properties
			{
				for(int i = 0; i < this.prop_name_textfield.size(); i++)
				{
					JTextField jtf1 = this.prop_name_textfield.get(i);
					JTextField jtf2 = this.prop_value_textfield.get(i);
					jtf1.setVisible(false);
					jtf2.setVisible(false);
				}
			}
			
			
			String name = component.getClass().getSimpleName();
			name = name.replaceAll("_", " ");
			if(component.has_sub_types() == true)
			{
				name = component.sub_types.get(component.sub_type_index);
			}
			g.drawString(name, 10, 50);
			g.drawString("Label:", 140, 50);
			
			if(component.is_wire() == false)
			{
				component.direction = 'R';
				component.set_location(new Node(5, 70));
				component.draw(g, 5, 70, 'R', Color.YELLOW);
				component.draw_ports(g);
			}
			else if(component.is_wire() == true)
			{
				Wire display_wire = new Wire();
				display_wire.set_location(new Node(5, 70));
				display_wire.add_node_to_path(new Node(5, 70));
				display_wire.add_node_to_path(new Node(5, 160));
				display_wire.draw(g, 5, 70, 'R', Color.YELLOW);
				display_wire.draw_ports(g);
			}
		}
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
		board.mouse.set_on_info_box(true);
		board.mouse.update(e);
	}
	
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		board.mouse.set_on_info_box(false);
		board.mouse.update(e);
	}
}
