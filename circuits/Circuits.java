package circuits;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import java.awt.Menu;
import components.*;

/*
 * THINGS TO DO:
 * 
 * 1. Get Select working (when clicking on an existing board component, gets component's details)
 * 2. Get Wire_Comp fixed (no turns - just multiple straight wires connected together)
 * 3. Get Delete Working
 * 4. Get Circuits Top Menu Bar done
 * 5. Get Multiple images on each device done
 * 6. Get Info Box working
 * 7. Get Utility fixed when adding components to the board
 * 8. Get Utility fixed when adding wires to the board (mainly combining wires)
 * 9. Get Utility fixed with the all_nodes arraylist
 * 10. Get Menu finished with all components and each component's drawing
 * 11. Finish all component classes
 */
public class Circuits extends JFrame// implements ActionListener, ItemListener
// implements ActionListener//implements MouseListener, MouseMotionListener, KeyListener
{
	// private final static int SLEEP = 5;
	
	public static Board	board;
	
	
	public Circuits()
	{
		super("Circuits");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		// this.addKeyListener(this);
		
		
		board = new Board();
		JScrollPane scroll_board = new JScrollPane(board);
		scroll_board.setViewportView(board);
		scroll_board.getVerticalScrollBar().setUnitIncrement(25);
		scroll_board.getHorizontalScrollBar().setUnitIncrement(25);
		scroll_board.setMinimumSize(new Dimension(650, 700));
		
		JSplitPane split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, board.get_menu_jsplitpane(), scroll_board);
		split_pane.setDividerLocation(250);
		split_pane.setPreferredSize(new Dimension(1000, 700));
		
		this.add(split_pane);
		
		// board.initialize();
		initialize();
		this.setVisible(true);
	}
	
	
	public void initialize()
	{
		board.initialize(this);
	}
	
	
	@Override
	public void paintComponents(Graphics g)
	{
		super.paintComponents(g);
	}
	
	
	public static void main(String[] args) throws Exception
	{
		Circuits circuits = new Circuits();
		
		while(true)
		{
			circuits.repaint();
			// Thread.sleep(SLEEP);
		}
	}
}
