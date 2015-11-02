/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;
import java.io.*;

/**
 *
 * @author LEE
 */
public class SLMethod implements Serializable{
    private File file = null;
	public SLMethod(String pathName){
		this.file = new File(pathName);
	}
        void setPath(String path){
            this.file = new File(path);
        }
	public boolean save(Object obj) throws Exception{
			ObjectOutputStream oos =null;
			boolean flag = false;	
                        
                        //判斷存檔成功否
			try{
                           
				oos = new ObjectOutputStream(new FileOutputStream(this.file));
                                
				oos.writeObject(obj);
                                
				flag = true;									//成功
			}catch(Exception e){
                            e.printStackTrace();
                            System.err.println(e);
				throw e;										//拋出 EXCEPTION
			}finally{
                            //不管成功與否最後都必須關閉OOS
				if(oos != null){
					oos.close();
				}
                           
			}
		return flag;
		}
	public Object load()throws Exception{
		Object obj =null;
		ObjectInputStream ois = null;
		try{
			ois = new ObjectInputStream(new FileInputStream(this.file));
			obj = ois.readObject();
		}catch(Exception e){
			throw e;
		}finally{
			if(ois !=null){
				ois.close();
			}
		}
		return obj;												//送回得到的物件
	}
}
