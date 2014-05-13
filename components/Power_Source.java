package components;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;

public class Power_Source extends Component_Class
{
	public static final int	WAVEFORM_DC			= 0;
	public static final int	WAVEFORM_AC			= 1;
	public static final int	WAVEFORM_SQUARE		= 2;
	public static final int	WAVEFORM_TRIANGLE	= 3;
	public static final int	WAVEFORM_SAWTOOTH	= 4;
	public static final int	WAVEFORM_PULSE		= 5;
	public static final int	WAVEFORM_VAR		= 6;
	
	
	public static final int	VOLTAGE_SOURCE		= 0;
	public static final int	CURRENT_SOURCE		= 1;
	public static final int	GENERAL_SOURCE		= 2;
	
	public boolean			clock;
	public int				waveform;
	public double			current, voltage, frequency, freq_time_zero, bias, phase_shift, duty_cycle;
	
	
	public Power_Source()
	{
		super();
		clock = false;
		waveform = 0;
		
		current = 0.1;
		voltage = 5;
		frequency = 40;
		
		freq_time_zero = 0;
		duty_cycle = 0.5;
		
		this.sub_types = new ArrayList<String>(Arrays.asList("Voltage Source", "Current Source", "General Source"));
		this.sub_type_index = 0;
	}
	
	
	public void set_clock(boolean c)
	{
		clock = c;
	}
	
	
	public boolean get_clock()
	{
		return clock;
	}
	
	
	public void set_waveform(int wf)
	{
		waveform = wf;
	}
	
	
	public int get_waveform()
	{
		return waveform;
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
			
			if(this.sub_type_index == VOLTAGE_SOURCE)
			{
				draw_voltage_source(g, x, y, direct, c);
			}
			else if(this.sub_type_index == CURRENT_SOURCE)
			{
				draw_current_source(g, x, y, direct, c);
			}
			else if(this.sub_type_index == GENERAL_SOURCE)
			{
				draw_general_source(g, x, y, direct, c);
			}
			else
			{
				this.draw_default_box(g, x, y, direct, c);
			}
		}
	}
	
	
	public void draw_general_source(Graphics g, int x, int y, char direct, Color c)
	{
		int xpos = x + 3;
		int ypos = y + 3;
		
		this.draw_default_box(g, x, y, direct, c);
		
		
		if(direct == 'R')
		{
			g.drawLine(xpos + 64, ypos - 4, xpos + 64, ypos + 4);// plus on right
			g.drawLine(xpos + 60, ypos, xpos + 68, ypos);
			g.drawLine(xpos + 22, ypos, xpos + 30, ypos);// minus on left
			
			g.drawLine(xpos + 36, ypos, xpos + 54, ypos);// right arrow
			g.drawLine(xpos + 45, ypos - 5, xpos + 54, ypos);
			g.drawLine(xpos + 45, ypos + 5, xpos + 54, ypos);
		}
		else if(direct == 'D')
		{
			g.drawLine(xpos, ypos + 59, xpos, ypos + 68);// plus on bottom
			g.drawLine(xpos - 4, ypos + 64, xpos + 4, ypos + 64);
			g.drawLine(xpos - 4, ypos + 28, xpos + 4, ypos + 28);// minus on top
			
			g.drawLine(xpos, ypos + 36, xpos, ypos + 54);// down arrow
			g.drawLine(xpos - 5, ypos + 45, xpos, ypos + 54);
			g.drawLine(xpos + 5, ypos + 45, xpos, ypos + 54);
		}
		else if(direct == 'L')
		{
			g.drawLine(xpos - 64, ypos - 4, xpos - 64, ypos + 4);// plus on left
			g.drawLine(xpos - 60, ypos, xpos - 68, ypos);
			g.drawLine(xpos - 22, ypos, xpos - 30, ypos);// minus on right
			
			g.drawLine(xpos - 36, ypos, xpos - 54, ypos);// left arrow
			g.drawLine(xpos - 45, ypos - 5, xpos - 54, ypos);
			g.drawLine(xpos - 45, ypos + 5, xpos - 54, ypos);
		}
		else if(direct == 'U')
		{
			g.drawLine(xpos, ypos - 59, xpos, ypos - 68);// plus on top
			g.drawLine(xpos - 4, ypos - 64, xpos + 4, ypos - 64);
			g.drawLine(xpos - 4, ypos - 28, xpos + 4, ypos - 28);// minus on bottom
			
			g.drawLine(xpos, ypos - 36, xpos, ypos - 54);// up arrow
			g.drawLine(xpos - 5, ypos - 45, xpos, ypos - 54);
			g.drawLine(xpos + 5, ypos - 45, xpos, ypos - 54);
		}
		
	}
	
	
	public void draw_voltage_source(Graphics g, int x, int y, char direct, Color c)
	{
		int xpos = x + 3;
		int ypos = y + 3;
		
		if(direct == 'R')
		{
			g.drawLine(xpos, ypos, xpos + 30, ypos);// outline
			g.drawOval(xpos + 30, ypos - 15, 30, 30);
			g.drawLine(xpos + 60, ypos, xpos + 90, ypos);
			
			g.drawLine(xpos + 52, ypos - 4, xpos + 52, ypos + 4);// plus on right
			g.drawLine(xpos + 48, ypos, xpos + 56, ypos);
			g.drawLine(xpos + 34, ypos, xpos + 42, ypos);// minus on left
		}
		else if(direct == 'D')
		{
			g.drawLine(xpos, ypos, xpos, ypos + 30);// outline
			g.drawOval(xpos - 15, ypos + 30, 30, 30);
			g.drawLine(xpos, ypos + 60, xpos, ypos + 90);
			
			g.drawLine(xpos, ypos + 47, xpos, ypos + 56);// plus on bottom
			g.drawLine(xpos - 4, ypos + 52, xpos + 4, ypos + 52);
			g.drawLine(xpos - 4, ypos + 40, xpos + 4, ypos + 40);// minus on top
		}
		else if(direct == 'L')
		{
			g.drawLine(xpos, ypos, xpos - 30, ypos);// outline
			g.drawOval(xpos - 60, ypos - 15, 30, 30);
			g.drawLine(xpos - 60, ypos, xpos - 90, ypos);
			
			g.drawLine(xpos - 52, ypos - 4, xpos - 52, ypos + 4);// plus on left
			g.drawLine(xpos - 48, ypos, xpos - 56, ypos);
			g.drawLine(xpos - 34, ypos, xpos - 42, ypos);// minus on right
		}
		else if(direct == 'U')
		{
			g.drawLine(xpos, ypos, xpos, ypos - 30);// outline
			g.drawOval(xpos - 15, ypos - 60, 30, 30);
			g.drawLine(xpos, ypos - 60, xpos, ypos - 90);
			
			g.drawLine(xpos, ypos - 47, xpos, ypos - 56);// plus on top
			g.drawLine(xpos - 4, ypos - 52, xpos + 4, ypos - 52);
			g.drawLine(xpos - 4, ypos - 40, xpos + 4, ypos - 40);// minus on bottom
		}
	}
	
	
	public void draw_current_source(Graphics g, int x, int y, char direct, Color c)
	{
		int xpos = x + 3;
		int ypos = y + 3;
		
		if(direct == 'R')
		{
			g.drawLine(xpos, ypos, xpos + 30, ypos);// outline
			g.drawOval(xpos + 30, ypos - 15, 30, 30);
			g.drawLine(xpos + 60, ypos, xpos + 90, ypos);
			
			g.drawLine(xpos + 36, ypos, xpos + 54, ypos);// right arrow
			g.drawLine(xpos + 45, ypos - 5, xpos + 54, ypos);
			g.drawLine(xpos + 45, ypos + 5, xpos + 54, ypos);
		}
		else if(direct == 'D')
		{
			g.drawLine(xpos, ypos, xpos, ypos + 30);// outline
			g.drawOval(xpos - 15, ypos + 30, 30, 30);
			g.drawLine(xpos, ypos + 60, xpos, ypos + 90);
			
			g.drawLine(xpos, ypos + 36, xpos, ypos + 54);// down arrow
			g.drawLine(xpos - 5, ypos + 45, xpos, ypos + 54);
			g.drawLine(xpos + 5, ypos + 45, xpos, ypos + 54);
		}
		else if(direct == 'L')
		{
			g.drawLine(xpos, ypos, xpos - 30, ypos);// outline
			g.drawOval(xpos - 60, ypos - 15, 30, 30);
			g.drawLine(xpos - 60, ypos, xpos - 90, ypos);
			
			g.drawLine(xpos - 36, ypos, xpos - 54, ypos);// left arrow
			g.drawLine(xpos - 45, ypos - 5, xpos - 54, ypos);
			g.drawLine(xpos - 45, ypos + 5, xpos - 54, ypos);
		}
		else if(direct == 'U')
		{
			g.drawLine(xpos, ypos, xpos, ypos - 30);// outline
			g.drawOval(xpos - 15, ypos - 60, 30, 30);
			g.drawLine(xpos, ypos - 60, xpos, ypos - 90);
			
			g.drawLine(xpos, ypos - 36, xpos, ypos - 54);// up arrow
			g.drawLine(xpos - 5, ypos - 45, xpos, ypos - 54);
			g.drawLine(xpos + 5, ypos - 45, xpos, ypos - 54);
		}
	}
}
