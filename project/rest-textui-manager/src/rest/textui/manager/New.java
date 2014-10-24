package rest.textui.manager;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.GestOutlet;
import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class New extends Command<GestOutlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public New(GestOutlet gest) {
		super(false, MenuEntry.NEW, gest);
	}

	/**
	 * @see ist.po.ui.Command#execute()r
	 */
	@Override
	public final void execute() throws DialogException, IOException {
	  
	  String ficheiro = _receiver.getOutlet().getFilename();
	  try 
	  {
	    if (_receiver.getOutlet().getChanged())
	    {
	      boolean savechanges = IO.readBoolean(Message.saveBeforeExit());
	      if (savechanges)
	      {
		if (ficheiro.equals(null))
		{
		  ficheiro = IO.readString(Message.newSaveAs());
		  
		  _receiver.getOutlet().setFilename(ficheiro);
		  _receiver.getOutlet().setChanged(false);
		  _receiver.saveAs(ficheiro);
		}
		
		else
		{
		  _receiver.getOutlet().setChanged(false);
		  _receiver.saveAs(_receiver.getOutlet().getFilename());
		}
	      }
	    }
	    _receiver.novo();

	}
	
	catch(FileNotFoundException fnfe)
	{
	  IO.println(Message.fileNotFound());
	}
	
	catch(IOException ioe)
	{
	  IO.println(ioe.getMessage());
	}

    }
}
