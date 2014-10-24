package rest.textui.clients;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.DuplicateKeyException;

import java.io.*;
import java.util.*;
import java.lang.*;


public class RegisterClient extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public RegisterClient(Outlet outlet) {
		super(false, MenuEntry.REGISTER_CLIENT, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, DuplicateKeyException {
		
		String Tipo, Nome, Email;
		Tipo = IO.readString(Message.reqType());
		
		while (((Tipo.equals(Message.clientTypeOmnivorous())) == false) && ((Tipo.equals(Message.clientTypeVegetarian())) == false))
		{
		  Tipo = IO.readString(Message.reqType());
		}
		
		Nome = IO.readString(Message.reqName());
		Email = IO.readString(Message.reqEmail());
		
		if (_receiver.cliente_Existe(Email) == false)
		{
		  _receiver.cliente_Registar(Nome, Email, Tipo);
		}
		
		else 
		{
		  throw new DuplicateKeyException(Email);
		}
	}
}
