package rest.textui;

import static ist.po.ui.Dialog.IO;

import rest.*;
import java.util.*;
import java.lang.Comparable;
import java.io.*;

import rest.textui.outlet.Message;


public class Outlet{

    public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException{
    
    GestOutlet man = new GestOutlet();

		
    try{
			
	String file = IO.readString(Message.openFile());
	man.open(file);
	
	rest.textui.outlet.MenuBuilder.menuFor(man.getOutlet());
	
	String filename = IO.readString(rest.textui.outlet.Message.saveFile());
	man.saveAs(filename);


	IO.closeDown();
	
	}
	catch (FileNotFoundException e) {
	    IO.println(Message.fileNotFound());
	}
	catch (IOException e) {
	    IO.println(e.getMessage());
	}
	catch (ClassNotFoundException e) {
	    IO.println(e.getMessage());
	}
    }
}