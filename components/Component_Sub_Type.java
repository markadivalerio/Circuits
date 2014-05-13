package components;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import circuits.*;

public class Component_Sub_Type extends Component_Class
{
	public String							type;
	
	public ArrayList<Component_Property>	properties;
	
	
	public Component_Sub_Type()
	{
		type = null;
		properties = null;
	}
	
	
	public Component_Sub_Type(String t)
	{
		type = t;
		properties = new ArrayList<Component_Property>();
	}
}
