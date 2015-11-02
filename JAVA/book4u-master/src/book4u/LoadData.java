/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;

/**
 *
 * @author mao
 */
public class LoadData {
    public int currentProeess;//目前讀取到第n個檔案
    public int endValue;//總檔案數

    public LoadData() {
        LoadDataInit();
    }
    
    public void LoadDataInit(){
        currentProeess = 0;
        endValue = 1;
    }
}