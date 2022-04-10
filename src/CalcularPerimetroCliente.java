import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalcularPerimetroCliente {
	/** Objeto para a entrada de dados pelo terminal.*/
	public static Scanner entradas;
	
	/** Método para limpar todo o texto do terminal. */
	public static void limpaTerminal() {
		System.out.printf("\033[H\033[2J");
	}
	
	
	public static void main(String[] args) {
		entradas = new Scanner(System.in);
		String ipServer = null;
		byte opcao = 0;
		do {
			limpaTerminal();
			System.out.print("\n     DEFINA O IP DO SERVIDOR     "
	        	           + "\n---------------------------------"  
		       	           + "\n  1 - localhost."
		       	           + "\n  2 - Digite o IP."
		       	           + "\n  Opção: ");
			opcao = entradas.nextByte();
			entradas.nextLine();
		}while(opcao < 1 || opcao > 2);
		if(opcao == 1) {
			ipServer = "localhost";
		}else if(opcao == 2) {
			System.out.printf("\nDigite o ip do servidor:\n");
			ipServer = entradas.next();
			entradas.nextLine();
		}
		try {
			Registry registry = LocateRegistry.getRegistry(ipServer);
			CalcularPerimetro stub = (CalcularPerimetro) registry.lookup("Servidor");
			boolean loop = true;
			float lado, perimetro;
			while(loop){
				
				limpaTerminal();
				System.out.printf("\n  CALCULA PERIMETRO DO QUADRADO  "
			        	        + "\n---------------------------------"  
				       	        + "\n  Digite o Lado: ");
				lado = entradas.nextFloat();
				if(lado > 0) {
					perimetro = stub.calcularPerimetroQuadrado(lado);
					System.out.println("\nO perímetro é: " + perimetro);
				}else {
					System.out.println("\\nTamanho do lado informado é invalido!");
				}
				System.out.println("\nDigite 1 para sair ou outro número para repetir.");
				if(entradas.nextInt() == 1) {
					loop = false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		entradas.close();
	}
}
