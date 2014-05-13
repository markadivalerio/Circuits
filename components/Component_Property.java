package components;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import circuits.*;

public class Component_Property
{
	public String	name;
	public double	value;
	public String	unit;
	
	
	public Component_Property(String n, double v, String u)
	{
		name = n;
		value = v;
		unit = u;
	}
}
