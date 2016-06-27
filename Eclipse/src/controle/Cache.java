package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Cache<T> implements Serializable{
		
	private static final long serialVersionUID = 107634094355526964L;
	private volatile Cache<T> instancia;
	
	@SuppressWarnings("unchecked")
	public T deserializar(String arq) throws IOException, ClassNotFoundException{
		T instancia;
		
		FileInputStream config = new FileInputStream(new File(arq));
		ObjectInputStream obj = new ObjectInputStream(config);
		
		instancia = (T) obj.readObject();
		
		obj.close();
		config.close();
		
		return instancia;
	}
		
	private Cache(){}
	
	@SuppressWarnings("unchecked")
	public Cache<T> obterInstancia(String arq) {
        if (instancia == null) {
            synchronized (Cache.class) {
                if (instancia == null) {
                	try{
                		instancia = (Cache<T>) deserializar(arq);
                	} catch(IOException | ClassNotFoundException e){
                		instancia = new Cache<T>();
                	}                	
                }
            }
        }

        return instancia;
    }
	
	public void salvarInstancia(String arq){
		try {
			FileOutputStream out = new FileOutputStream(arq);
			ObjectOutputStream st = new ObjectOutputStream(out);
			
			st.writeObject(instancia);
			
			st.close();
			out.close();
		} catch (IOException e) {
			
		}
	}
}
