package rest;

import java.io.*;
import java.util.*;
import java.lang.*;

import junit.framework.*;

public class JUnitTestarClientesSemEncomendas extends TestCase{

private Outlet _outlet;

	public JUnitTestarClientesComEncomendas() {}
	
	public JUnitTestarClientesComEncomendas(String s) {super(s)}
	
	public void setUp(){
		_outlet = new Outlet();
		_outlet.cliente_Registar("Cliente", "Cliente@email.domain", false);

		_outlet.restaurante_Registar("Restaurante", "Restaurante@email.domain");
		
		_outlet.alimento_RegistarNOVO("MEAT", "Bife", 10);
		_outlet.restaurante_pratodoDia_Adicionar("Restaurante", "Bife", 1, 10, 0);
		_outlet.restaurante_pratodoDia_ON("Restaurante", "Bife");
	}
	
	public void teste(){
		ArrayList<String> _Lista = new ArrayList<String>();
		Cliente c = new Cliente("Cliente", "Cliente@email.domain", false);
		_Lista.add(c.toString());
		
		Assert.assertEquals(_Lista, _outlet.cliente_ListaClientesSemEncomendas()); // No inicio nenhum cliente tem encomendas
		
		_outlet.cliente_ADDEncomenda("Cliente@email.domain", "Bife");
		_outlet.cliente_Pagar("Cliente@email.domain");
		Assert.assertEquals(new ArrayList<String>(), _outlet.cliente_ListaClientesSemEncomendas()); // No fim os clientes já têm encomendas, logo a lista deve ser vazia
		
	}  
	
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTest(new JUnitTestarClientesSemEncomendas("teste"));
		return suite;
	}

}
