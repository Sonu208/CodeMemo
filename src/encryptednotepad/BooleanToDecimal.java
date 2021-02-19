/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptednotepad;

/**
 *
 * @author LAVESH
 */


public class BooleanToDecimal 
{
	private int pow(int base, int raise)
	{
		int result = 1;
		for(int i=0;i<raise;i++)
		{
			result = result * base;
		}
		return result ;

	}
	private int[] stringToIntArray(String data)
	{
		int result[] = new int[data.length()];
		for(int i=0;i<data.length();i++)
		{
			result[i]=(int)data.charAt(i)-48;
		}
		return result;

	}
	private String intArrayToString(int[] array)
	{
		StringBuffer result = new StringBuffer();
		for(int i=0;i<array.length;i++)
		{
			result.append((char)(array[i]+48));
		}
		return result.toString();
	}
	public int getDecimal(String bool)
	{
		StringBuffer data = new StringBuffer(bool);
		data = data.reverse();
		int[] array = stringToIntArray(data.toString());
		int result = 0;
		for(int i=0; i<array.length;i++)
		{
			result = result+array[i]*pow(2,i);
		}
		return result;
	}
	public static void main(String[] args) 
	{
		BooleanToDecimal btd = new BooleanToDecimal();
		System.out.println(btd.getDecimal("1110"));
		
	}
    
}
