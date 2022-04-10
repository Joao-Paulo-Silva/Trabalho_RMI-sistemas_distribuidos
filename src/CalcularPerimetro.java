import java.rmi.*;

public interface CalcularPerimetro extends Remote{
	public float calcularPerimetroQuadrado(float lado) throws RemoteException;;
}
