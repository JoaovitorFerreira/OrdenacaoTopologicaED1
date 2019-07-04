import java.io.IOException;
import java.util.Scanner;

public class Main
{
	public static void main(String args[]) throws IOException
	{
		OrdenacaoTopologica ord = new OrdenacaoTopologica();
		
		String nomeEntrada = "entrada.txt";
	
		ord.realizaLeitura(nomeEntrada);
		if(!ord.executa()) {
			System.out.println("O conjunto não é parcialmente ordenado.");
		}
			
	    else {
	    	System.out.println("O conjunto é parcialmente ordenado.");
	    }
			
	}
}
