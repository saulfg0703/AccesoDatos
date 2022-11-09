package XMLDB;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class Principal {
	protected static String driver = "org.exist.xmldb.DatabaseImpl";
	private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionPruebas";
	private static Database database;
	private static String usu = "admin";
	private static String usuPwd = "1234";
	private static Class cl = null;
	private static XPathQueryService service;
	private static ResourceSet result = null;
	private static Collection col = null;

	public static void main(String[] args) {
		try {
			cl = Class.forName(driver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("No se encuentra la clase del driver: " + cnfe.getMessage());
		}
		try {
			database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		try {
			col = DatabaseManager.getCollection(URI, usu, usuPwd);
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		try {
			service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		try {
			result = service.query("for $b in /EMPLEADOS/EMP_ROW[APELLIDO='TOVAR'] return $b");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		try {
			ResourceIterator i;
			i = result.getIterator();
			if (!i.hasMoreResources()) {
				System.out.println("LA CONSULTA NO DEVUELVE NADA");
			}
			while (i.hasMoreResources()) {
				Resource r = i.nextResource();
				System.out.println((String) r.getContent());
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		try {
			col.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}
	//Prueba
}
