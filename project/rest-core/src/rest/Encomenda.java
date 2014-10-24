package rest;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Encomenda implements Serializable
{
   private Cliente _Client;
   private PratodoDia _Prato;
   private Restaurante _Restaurante;
   private int _nrDoses;
   
   public Encomenda(Cliente Client, PratodoDia NomePrato, Restaurante restaurante, int nr)
   {
     _Client = Client;
     _Prato = NomePrato;
     _Restaurante = restaurante;
     _nrDoses = nr;
   }
   
   public void enviaPagamentoRestaurante()
   {
     int PrecoPorUnidade = _Prato.getPrecoFinal();
     _Restaurante.recebePagamento(PrecoPorUnidade * _nrDoses);
   }
   
   public void enviaDadosRestaurante()
   {
     _Restaurante.setPDDVendidos(_nrDoses);
   }
   
   public void enviaCobrancaCliente()
   {
     int PrecoPorUnidade = _Prato.getPrecoFinal();
     _Client.recebeCobranca(PrecoPorUnidade * _nrDoses);
   }
   
   public void enviaDadosCliente()
   {
     _Client.setPDD(_nrDoses);
   }
 
   public PratodoDia getPrato()
   {
      return _Prato;
   }
   
   public Restaurante getRestaurante()
   {
      return _Restaurante;
   }
   
   public Cliente getCliente()
   {
      return _Client;
   }
   
   public String getEmail()
   {
      return _Client.getEmail();
   }
   
   public int getnrDoses()
   {
      return _nrDoses;
   }
   
   public String toString()
   {
      return _Client.getEmail() + "|" + _Restaurante.getName() + "|" + _Prato.getName() + "|" + _nrDoses;
   }
}