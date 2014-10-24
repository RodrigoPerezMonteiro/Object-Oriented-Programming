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

public class Save extends Command<GestOutlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public Save(GestOutlet gest) {
		super(false, MenuEntry.SAVE, gest);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute()throws DialogException, IOException {
        String filename;
        
	    try{
		 _receiver.getFilename();
		 _receiver.save();
	       }
	       
	catch(NullPointerException npe)
    	{
	 filename = IO.readString(Message.newSaveAs());
	 _receiver.saveAs(filename);
        }
        
    	catch(IOException ioe)
    	{
	 filename = IO.readString(Message.newSaveAs());
	 _receiver.saveAs(filename);
	}

    }
}
