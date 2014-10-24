package rest;

import java.io.*;
import java.util.*;
import java.lang.*;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.lang.Comparable;
import java.lang.Integer;
import java.lang.Double;
import java.lang.ClassNotFoundException;
import java.lang.Math;

public class GestOutlet implements Serializable { 

private Outlet _outlet;

public GestOutlet()
{
  _outlet = new Outlet();
}

public Outlet getOutlet(){

  return _outlet;
}

public void openFile(String file){

  _outlet.openFile(file);
}

public String getFilename(){

  return _outlet.getFilename();
}

public void novo(){

  _outlet.clear();
}

public void open(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{ 
 
	ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
	
	Outlet o = new Outlet();
	
	o = (Outlet)in.readObject();
	_outlet.reload(o.getRestaurantes(), o.getClientes(), o.getAlimentos(), o.getFilename());
	in.close();
}

public void saveAs(String filename) throws IOException{

	    _outlet.setFilename(filename);
	    ObjectOutputStream out = new ObjectOutputStream (new BufferedOutputStream(new FileOutputStream(filename)));
	    out.writeObject(_outlet);
	    out.close();  


}

public void save() throws IOException{

	    String filename = _outlet.getFilename();
	    ObjectOutputStream out = new ObjectOutputStream (new BufferedOutputStream(new FileOutputStream(filename)));
	    out.writeObject(_outlet);
	    out.close();
}
}
