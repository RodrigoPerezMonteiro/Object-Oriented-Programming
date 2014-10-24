package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class AlertaNovelty extends Alerta implements Serializable
{
  private String _Message;
  private String _Tipo;

  public AlertaNovelty(String Message)
  {
    _Message = Message;
    _Tipo = "NOVELTY";
  }
  
  public String getMessage()
  {
    return _Message;
  }
  
  public String getTipo()
  {
    return _Tipo;
  }
  
  public String toString()
  {
    return _Tipo + "|";
  }
  
} 
