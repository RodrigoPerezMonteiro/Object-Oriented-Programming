package rest.textui.food;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.DuplicateKeyException;
import java.lang.Integer;
import java.lang.Double;
import java.io.*;
import java.lang.*;


public class RegisterFood extends Command<Outlet>{
	/**
	 * FIXME: there may be one or more receivers
	 */
	public RegisterFood(Outlet outlet) {
		super(false, MenuEntry.REGISTER_FOOD, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		
		String Tipo, Nome;
		int Calorias;
		Tipo = IO.readString(Message.reqType());
		
		while ((Tipo.equals(Message.typeMeat()) == false) && (Tipo.equals(Message.typeFish()) == false) && (Tipo.equals(Message.typeVegetable()) == false))
		{
		  Tipo = IO.readString(Message.reqType());
		}
		
		Nome = IO.readString(Message.reqName());
		Calorias = Integer.parseInt(IO.readString(Message.reqCalories()));
		
		if (_receiver.alimento_Existe(Nome) == false)
		{
		  _receiver.alimento_RegistarNOVO(Tipo, Nome, Calorias);
		}
		
		else 
		{
		  throw new DuplicateKeyException(Nome);
		}
	}
}
