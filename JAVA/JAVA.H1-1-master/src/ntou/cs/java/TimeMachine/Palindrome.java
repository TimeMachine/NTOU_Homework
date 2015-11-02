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
public class Palindrome {
    
    public void PalindromeCheck(String integer)
    {//進行回文檢查
        if(integer.length()==7)
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
            System.out.printf("The length of "+integer+" is NOT seven, please input again!\n");
    }
    
    public void run(){
        //與使用者互動的部分
        Scanner input = new Scanner(System.in);
        String integer; 
        
        System.out.printf("Enter a 7-digit positive integer:");
        integer = input.nextLine();
        //取得輸入內容後開始檢查
        try 
        {
            PalindromeCheck(integer);
        }
        catch(RuntimeException e)
        {//例外處理
            System.out.printf(e.getMessage());
        }
     }
    
}
