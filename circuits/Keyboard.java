package circuits;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import components.*;

public class Keyboard// implements KeyListener
{
	private static final Utility	util			= new Utility();
	// [key code, modifier code]
	private static final int[]		SHIFT_CODE		= new int[] {16,64};
	private static final int[]		CONTROL_CODE	= new int[] {17,128};
	private static final int[]		COMMAND_CODE	= new int[] {157,256};
	private static final int[]		ALT_CODE		= new int[] {18,512};
	
	public char						key;
	public int						id, code, location_int;
	public boolean					shift, control, command, alt;
	public String					location_string;
	
	public Map						special			= new HashMap<String, Boolean>();
	
	
	
	public Keyboard()
	{
		initialize();
	}
	
	
	public void initialize()
	{
		special = new HashMap<String, Boolean>();
		special.put("SHIFT", false);
		special.put("COMMAND", false);
		special.put("ALT", false);
		special.put("CONTROL", false);
	}
	
	
	public void key_typed(KeyEvent e)
	{
		key_stroke(e);
	}
	
	
	public void key_pressed(KeyEvent e)
	{
		key_stroke(e);
	}
	
	
	public void key_released(KeyEvent e)
	{
		key_stroke(e);
	}
	
	
	public void key_stroke(KeyEvent e)
	{
		id = e.getID();
		if(id == KeyEvent.KEY_TYPED)
		{
			key = e.getKeyChar();
		}
		else
		{
			code = e.getKeyCode();
			// key = KeyEvent.ge
//			switch(code)
//			{
//				case KeyEvent.VK_TAB: character = '\t'; break;
//				case KeyEvent.VK_ENTER: character = '\n'; break;
//			}
		}
		set_key_location(e);
		set_modifiers(e);// sets shift, control, command, and alt booleans
	}
	
	
	public void set_key_location(KeyEvent e)
	{
		int location_int = e.getKeyLocation();
		if(location_int == KeyEvent.KEY_LOCATION_STANDARD)
		{
			location_string = "STANDARD";
		}
		else if(location_int == KeyEvent.KEY_LOCATION_LEFT)
		{
			location_string = "LEFT";
		}
		else if(location_int == KeyEvent.KEY_LOCATION_RIGHT)
		{
			location_string = "RIGHT";
		}
		else if(location_int == KeyEvent.KEY_LOCATION_NUMPAD)
		{
			location_string = "NUMPAD";
		}
		else
		// if(loc == KeyEvent.KEY_LOCATION_UNKNOWN)
		{
			location_string = "UNKNOWN";
		}
	}
	
	
	public void set_modifiers(KeyEvent e)
	{
		shift = false;
		control = false;
		command = false;
		alt = false;
		int remainder = e.getModifiersEx();
		if((remainder / ALT_CODE[1]) >= 1)// 512
		{
			remainder = remainder - ALT_CODE[1];
			alt = true;
		}
		if((remainder / COMMAND_CODE[1]) >= 1)// 256
		{
			remainder = remainder - COMMAND_CODE[1];
			command = true;
		}
		if((remainder / CONTROL_CODE[1]) >= 1)// 128
		{
			remainder = remainder - CONTROL_CODE[1];
			control = true;
		}
		if((remainder / ALT_CODE[1]) >= 1)// 64
		{
			remainder = remainder - SHIFT_CODE[1];
			shift = true;
		}
	}
}
