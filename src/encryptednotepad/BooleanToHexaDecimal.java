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


import java.util.HashMap;

public class BooleanToHexaDecimal 
{
	HashMap<String,String> boolHexMapper = new HashMap<>();
	BooleanToHexaDecimal()
	{
		boolHexMapper.put("0000","0");
		boolHexMapper.put("0001","1");
		boolHexMapper.put("0010","2");
		boolHexMapper.put("0011","3");
		boolHexMapper.put("0100","4");
		boolHexMapper.put("0101","5");
		boolHexMapper.put("0110","6");
		boolHexMapper.put("0111","7");
		boolHexMapper.put("1000","8");
		boolHexMapper.put("1001","9");
		boolHexMapper.put("1010","a");
		boolHexMapper.put("1011","b");
		boolHexMapper.put("1100","c");
		boolHexMapper.put("1101","d");
		boolHexMapper.put("1110","e");
		boolHexMapper.put("1111","f");
	}
	public String getHexaDecimal(String bool)
	{
		StringBuffer data = new StringBuffer(bool);
		StringBuffer result = new StringBuffer("");
		int zeroToBeAppended = Math.abs(data.length() % 4-4);
		if((zeroToBeAppended <4 ))
		{
			// System.out.println("....");
			data = data.reverse();
			for(int i=0; i<zeroToBeAppended;i++)
			{
				data.append("0");
			}
			data = data.reverse();
		}
		// System.out.println("Boolean : ->" + data);
		StringBuffer temp = new StringBuffer();
		for(int i=0;i<data.length();i+=4)
		{
			for(int j=i;j<i+4;j++)
			{
				temp = temp.append(data.charAt(j));
			}
			// System.out.println(boolHexMapper.get(temp.toString()));
			result = result.append(boolHexMapper.get(temp.toString()));
			temp = new StringBuffer();
		}
		// System.out.println(result);
		return result.toString();
	}
	public static void main(String[] args) 
	{
		BooleanToHexaDecimal bth = new BooleanToHexaDecimal();
		System.out.println(bth.getHexaDecimal("01010011001010101"));
	}
    
}
