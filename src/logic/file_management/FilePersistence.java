package logic.file_management;

import java.io.*;

public class FilePersistence {

    static String ruta = System.getProperty("user.home")+ File.separator;

    public static void guardar(Object obj, String archivo){

        try{
            File f = new File(ruta +archivo);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            System.out.println("Guardado...");
        }
        catch (Exception err){
            System.out.println("ERROR: "+err.getMessage());
        }
    }

    public static Object leer(String archivo){
        System.out.println("RUTA: "+ruta+archivo);
        try{
            File f = new File(ruta+archivo);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream  ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            System.out.println("Recuerado...");
            return o;
        }
        catch (Exception err){
            System.out.println("ERROR: "+err.getMessage());
            return null;
        }
    }

}
