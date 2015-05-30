package negocio;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class Parser {

	public Parser() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String [] args){
		Parser p=new Parser();
		p.leerXML("C:/Users/Didier/workspace/EjemploP4/WebContent/alumno.xml");
		p.crearXML();
		
	}
	public void leerXML(String ruta){
		SAXBuilder lector=new SAXBuilder();
		try {
			Document alumnosXml= lector.build(ruta);//build verifica o manda excepcion
			Element raiz = alumnosXml.getRootElement();
			
			//se puede hacer una validación contra una dtd o xsd, buscar función validate
			List <Element> alumnos= raiz.getChildren();//punto para invocar función recursiva
			for(int i=0;i<alumnos.size();i++){
				System.out.println(alumnos.get(i).getChild("direccion").getAttributeValue("calle"));
				System.out.println(alumnos.get(i).getChildText("observaciones"));
				
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void crearXML(){
		Element raiz=new Element("alumnos");
		Element alumno1=new Element("alumno");
		Element direccion1=new Element("direccion");
		direccion1.setAttribute("calle","Calle 1");
		raiz.addContent(alumno1);
		alumno1.addContent(direccion1);
		Document alumnosXml= new Document(raiz);
		XMLOutputter serializar = new XMLOutputter();
		Writer wr = new StringWriter();
		try {
			serializar.output(alumnosXml, wr);
			System.out.println(wr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
