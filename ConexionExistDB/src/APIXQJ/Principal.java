package APIXQJ;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class Principal {
	public static void main(String[] args) {
		XQConnection conn = null;
		XQDataSource server = new ExistXQDataSource();
		XQPreparedExpression consulta = null;
		XQResultSequence rs = null, resultado = null;
		try {
			server.setProperty("serverName", "localhost");
			server.setProperty("port","8080");
		}catch(XQException e) {
			System.out.println("Error en Server. Mensaje: " + e.getMessage());
			System.out.println("Error en Server. Causa: " + e.getCause());
		}try {
			conn = server.getConnection();
		}catch(XQException e) {
			System.out.println("Error en Server. Conexion: " + e.getMessage());
		}
		System.out.println("----Ejemplo Consulta Productos----");
		try {
			consulta = conn.prepareExpression("for $de in " + " /productos/produc return $de");
		}catch(XQException e) {
			System.out.println("Error en la expresion. Mensaje: " + e.getMessage());
			System.out.println("Error en expresion. Causa: " + e.getCause());
		}try {
			resultado = consulta.executeQuery();
			while(resultado.next()) {
				System.out.println("Elemento " + resultado.getItemAsString(null));
				}
			}catch(XQException e) {
				System.out.println("Error en la ejecucion. Mensaje: " + e.getMessage());
				System.out.println("Error en la ejecucion. Causa: " + e.getCause());
		}try {
			conn.close();
		}catch(XQException e) {
			System.out.println("Error al cerrar la conexion.");
		}
	}
}
