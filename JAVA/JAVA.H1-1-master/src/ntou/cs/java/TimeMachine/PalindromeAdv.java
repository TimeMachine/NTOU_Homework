/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ntou.cs.java.TimeMachine;

import java.util.Scanner;

/**
 *
 * @author Kami
 */
public class PalindromeAdv {
    
    public void PalindromeCheck(String integer)
    {//進行回文檢查
        if(integer.length()%2==1)
        {
            boolean result = true;
            for(int i=0;i<integer.length()/2;i++)
            {
                if(!(integer.charAt(i)==integer.charAt(integer.length()-1-i)))
                    result = false;
            }
            if(result)
                System.out.printf(integer+" is a palindrome!!!\n");
            else
                System.out.printf(integer+" is NOT a palindrome!\n");
        }
        else
            System.out.printf("The length of "+integer+" is not an odd number, please input again!\n");
    }
    
    public void run(){
        //與使用者互動的部分
        Scanner input = new Scanner(System.in);
        String integer; 
        
        System.out.printf("Enter a positive integer:");
        integer = input.nextLine();
        //取得輸入內容後開始檢查
        try
        {
            if(Long.parseLong(integer)>Integer.MAX_VALUE)
                //檢查輸入內容是否超過MAX_VALUE
                System.out.printf(integer+" is out-of-bound, please input again!\n");
            else 
                PalindromeCheck(integer);
        }
        catch(RuntimeException e)
        {//例外處理
                PalindromeCheck(integer);
        }
    }
}
