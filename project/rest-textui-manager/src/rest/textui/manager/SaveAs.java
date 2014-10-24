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

public class SaveAs extends Command<GestOutlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public SaveAs(GestOutlet gest) {
		super(false, MenuEntry.SAVE_AS, gest);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute()throws DialogException, IOException {
            
             try{
		  String filename = IO.readString(Message.saveAs());
		  _receiver.saveAs(filename);
		}
		
    	catch(NullPointerException npe){IO.println(npe.toString());}
    	catch(IOException ioe){IO.println(ioe.toString());}
    	
    	}
}
