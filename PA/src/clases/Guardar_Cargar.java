package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Guardar_Cargar {
	public static CompanyiaTelefonica cargarCompanyia() throws IOException,FileNotFoundException,ClassNotFoundException{
		CompanyiaTelefonica companyia=new CompanyiaTelefonica() ;
		FileInputStream fis=new FileInputStream("companyia.bin");
		ObjectInputStream ois=new ObjectInputStream(fis);
		companyia=(CompanyiaTelefonica) ois.readObject();
		ois.close();
	
		return companyia;
	}
	public static void guardarCompanyia(CompanyiaTelefonica companyia) throws IOException{
			FileOutputStream fos=new FileOutputStream("companyia.bin");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(companyia);
			oos.close();
	}
}
