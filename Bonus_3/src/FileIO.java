import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileIO {
    public static ArrayList<Transmitter> readFile(File file) {
        ArrayList<Transmitter> trans = new ArrayList<Transmitter>();
        
        try (FileInputStream fileInputStream = new FileInputStream(file);
        	 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {
        	
            int arr = objectInputStream.readInt();
            for (int i = 0; i < arr; i++) {
                Transmitter t = (Transmitter)objectInputStream.readObject();
                trans.add(t);
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return trans;
    }
}
