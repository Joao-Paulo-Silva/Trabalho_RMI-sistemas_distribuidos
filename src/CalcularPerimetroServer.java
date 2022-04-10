import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class CalcularPerimetroServer implements CalcularPerimetro{
	public CalcularPerimetroServer() {}  // Construtor

	public static void main(String[] args) {
		try {
			CalcularPerimetroServer server = new CalcularPerimetroServer();
			//Exporta o server para o stub ("apendice ou toco") do RMI na porta 0
			CalcularPerimetro stub = (CalcularPerimetro) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.getRegistry();
			// Registra a stub no RMI para que ela seja obtida pelos clientes
			registry.rebind("Servidor", stub);
			
			System.out.println("Servidor inicializado com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}

	/**
	* Método que calcula o perímetro de um quadrado a partir de um lado informado pelo usuário.
    	* @param lado - Lado do quadrado tipo float.
    	* @return perimetro - Perímetro calculado a partir do lado.
	* @throws RemoteException
	*/
	public float calcularPerimetroQuadrado(float lado) throws RemoteException {
		System.out.println("Executando metodo neste host");
		float perimetro = 4 * lado;
		return perimetro;
	}
}
