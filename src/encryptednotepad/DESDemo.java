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
public class DESDemo {
    String c[],d[],keys[],l[],r[];
	DESDemo()
	{
		this.c = new String[17];
		this.d = new String[17];
		this.keys = new String[17];
		this.l = new String[17];
		this.r = new String[17];
	}
	private String stringToHexaDecimal(String data)
	{
		DecimalToBoolean dtb = new DecimalToBoolean();
		String bool;
		BooleanToHexaDecimal bth = new BooleanToHexaDecimal();	
		StringBuffer result = new StringBuffer();
		for(int i=0;i<data.length();i++)
		{
			bool = (dtb.getBoolean((int)data.charAt(i)));
			result.append(bth.getHexaDecimal(bool));
		}
		// result = new StringBuffer(this.appendLineFeed(result.toString()));
		// System.out.println(result);
		// result = new StringBuffer(this.appendZeros(result.toString()));
		return result.toString();
	}
	public String[] group2(String data)
	{
		String result[] = new String[(data.length()/2)];
		for(int i=0;i<result.length;i++)
		{
			result[i] = "";
		}
		String dataArray[] = data.split("");
		for(int i=0,k=0;i<data.length();i+=2,k++)
		{
			for(int j=i;j<i+2;j++)
			{
				// System.out.println(k);
				result[k] = result[k].concat(dataArray[j]);
			}
		}
		return result;
	}
	public String hexDecimalToString(String hex)
	{
		StringBuffer result = new StringBuffer();
		String hexGroup[] = group2(hex);
		for(int i=0;i<hexGroup.length;i++)
		{
			String bin = new HexaDecimalToBoolean().getBoolean(hexGroup[i]);
			result.append((char)new BooleanToDecimal().getDecimal(bin));
		}
		return result.toString();
	}
	public String keyPermutation1(String key)
	{
		String data = key;
		int permutationTable[]  = {57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4}; 
		StringBuffer result = new StringBuffer();
		for(int i=0;i<permutationTable.length;i++)
		{
			result.append(data.charAt(permutationTable[i]-1));	
		} 
		return result.toString();
	}

	public String[] breakInTwoHalves(String data)
	{
		StringBuffer c0 = new StringBuffer();
		StringBuffer d0 = new StringBuffer();
		for(int i=0;i<data.length()/2;i++)
		{
			c0.append(data.charAt(i));
		}
		for(int j=(data.length()/2);j<data.length();j++)
		{
			d0.append(data.charAt(j));
		}
		// System.out.println("c0 -> " + c0);
		// System.out.println("d0 -> "+d0);
		String result[] = new String[2];
		result[0] = c0.toString();
		result [1] = d0.toString();
		return result;
	}
	public String leftShifter(String data,int count)
	{
		// StringBuffer toBeShifted = new StringBuffer(data);
		char ch;
		StringBuffer intermediate = new StringBuffer(data);
		for(int i=0;i<count;i++)
		{
			ch = intermediate.charAt(0);
			intermediate =  intermediate.deleteCharAt(0);
			intermediate.append(ch);
		}
		return intermediate.toString();
	}
	public void setCAndD(String[] initKeys) 
	{
		int leftShifts[] = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1}; 
		// System.out.println(leftShifts.length);
		this.c[0] = initKeys[0];
		this.d[0] = initKeys[1];
		for(int i=0;i<leftShifts.length;i++)
		{
			this.c[i+1] = leftShifter(this.c[i],leftShifts[i]);
			this.d[i+1] = leftShifter(this.d[i],leftShifts[i]);
		}
	}
	public String mergeTwoHalves(String left,String right)
	{
		return left.concat(right);
	}
	public String keyPermutation2(String key)
	{
		int permutationTable[] = { 14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32};
		StringBuffer result = new StringBuffer();
		for(int i=0;i<permutationTable.length;i++)
		{
			result.append(key.charAt(permutationTable[i]-1));	
		} 
		return result.toString();

	} 
	public void setSixteenKeysForEncryption()
	{
		for(int i=1;i<17;i++)
		{
			keys[i] = keyPermutation2(mergeTwoHalves(c[i],d[i]));
		}
	}
	public void setSixteenKeysForDecryption()
	{
		for(int i=1,j=16;i<17 && j>=0 ;i++,j--)
		{
			keys[j] = keyPermutation2(mergeTwoHalves(c[i],d[i]));
		}
	}
	public String appendZeros(String data)
	{
		StringBuffer result = new StringBuffer(data);
		int zeroToBeAppended = Math.abs(data.length()%64-64);
		for(int i=0;i<zeroToBeAppended;i++)
			result.append("0");
		return result.toString();
	}
	public String initialPermutation(String msg)
	{
		int [] permutationTable = {58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};		
		StringBuffer result = new StringBuffer();
		for(int i=0;i<permutationTable.length;i++)
		{
			result.append(msg.charAt(permutationTable[i]-1));	
		} 
		return result.toString();
	}
	public void setLeftAndRight()
	{
		for(int i=1;i<17;i++)
		{
			this.l[i] = this.r[i-1];
			this.r[i] = this.xor(this.l[i-1],this.f(this.r[i-1],keys[i]));
		}	
	}
	public String xor(String input1,String input2)
	{
		// Integer ip1 = Integer.parseInt(input1);
		// Integer ip2 = Integer.parseInt(input2);
		// System.out.println(ip1 ^ ip2);
		// return new Integer((ip1 ^ ip2)).toString();
		// System.out.println(Integer.parseInt(input1)^Integer.parseInt(input2)).toString();
		// return "";
		StringBuffer result = new StringBuffer();
		for(int i=input1.length()-1;i>=0;i--)
		{
			int operand1 = Integer.parseInt(new Character(input1.charAt(i)).toString());
			int operand2 = Integer.parseInt(new Character(input2.charAt(i)).toString());

			result.append(new Integer(operand1 ^ operand2).toString());

		}
		result.reverse();
		return result.toString();
	}
	public String[] group6(String data)
	{
		String result[] = new String[(data.length()/6)];
		for(int i=0;i<result.length;i++)
		{
			result[i] = "";
		}
		String dataArray[] = data.split("");
		for(int i=0,k=0;i<data.length();i+=6,k++)
		{
			for(int j=i;j<i+6;j++)
			{
				// System.out.println(k);
				result[k] = result[k].concat(dataArray[j]);
			}
		}
		return result;
	}
	public String[] group16(String data)
	{
		String result[] = new String[(data.length()/16)];
		for(int i=0;i<result.length;i++)
		{
			result[i] = "";
		}
		String dataArray[] = data.split("");
		for(int i=0,k=0;i<data.length();i+=16,k++)
		{
			for(int j=i;j<i+16;j++)
			{
				// System.out.println(k);
				result[k] = result[k].concat(dataArray[j]);
			}
		}
		return result;
	}
	public String sBoxSelection(String data[])
	{
		StringBuffer result = new StringBuffer();
		if(data.length == 8)
		{			
			int s[][][] = {{{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},
						   {{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}},
						   {{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},			
						   {{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},
						   {{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},
						   {{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},
						   {{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},
						   {{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}}};

			for(int i=0;i<8;i++)
			{
				String temp = data[i];
				String middleFour = data[i].substring(1,data[i].length()-1);
				// System.out.println(middleFour);
				String rowData = Character.toString(data[i].charAt(0)).concat(Character.toString(data[i].charAt(data[i].length()-1)));
				int row = new BooleanToDecimal().getDecimal(rowData);
				// System.out.println(row);
				BooleanToDecimal btd = new BooleanToDecimal();
				DecimalToBoolean  dtb = new DecimalToBoolean();
				String partResult = dtb.getFormattedBoolean(s[i][row][btd.getDecimal(middleFour)],4);
				result.append(partResult);
			}
		}
		return result.toString();
	}
	public String f(String r,String key)
	{
		// System.out.println(r.length());
		// System.out.println(key.length());
		String ebit = eBitSelection(r);
		String xor = xor(ebit,key);
		// System.out.println(xor);
		// System.out.println(xor.length());
		String groups[] = group6(xor);
		String sbox = sBoxSelection(groups);
		String sboxP = sboxPermutation(sbox);
		// System.out.println(sboxP);
		return sboxP;
	}
	public String sboxPermutation(String msg)
	{
		int [] permutationTable = {16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25};		
		StringBuffer result = new StringBuffer();
		for(int i=0;i<permutationTable.length;i++)
		{
			result.append(msg.charAt(permutationTable[i]-1));	
		} 
		return result.toString();

	}
	public String eBitSelection(String data)
	{
		int [] permutationTable = {32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1};		
		StringBuffer result = new StringBuffer();
		for(int i=0;i<permutationTable.length;i++)
		{
			result.append(data.charAt(permutationTable[i]-1));	
		} 
		return result.toString();

	}
	public String finalPermutation(String data)
	{
		int [] permutationTable = {40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,38,6,46,14,54,22,62,30,37,5,45,13,53,21,61,29,36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,34,2,42,10,50,18,58,26,33,1,41,9,49,17,57,25};		
		// System.out.println(permutationTable.length);
		StringBuffer result = new StringBuffer();
		for(int i=0;i<permutationTable.length;i++)
		{
			result.append(data.charAt(permutationTable[i]-1));	
		} 
		return result.toString();

	}
	public String encrypt(String msg,String key)
	{
		String tempKey = key.length() >= 8 ? key.substring(0,8) : "";
		String hexaKey = stringToHexaDecimal(tempKey);
		String binaryKey = new HexaDecimalToBoolean().getBoolean(key);
		String keyPermut = keyPermutation1(binaryKey);
		String[] twoHalves = breakInTwoHalves(keyPermut);
		setCAndD(twoHalves);
		setSixteenKeysForEncryption();
		String binaryMsg = new HexaDecimalToBoolean().getBoolean(msg);
		String initialPermutationMsg = initialPermutation(binaryMsg);
		String partialProcessedData[] = breakInTwoHalves(initialPermutationMsg);
		this.l[0] = partialProcessedData[0];
		this.r[0] = partialProcessedData[1];
		// System.out.println(this.l[0]);
		// System.out.println(this.r[0]);
		setLeftAndRight();
		StringBuffer r16l16 = new StringBuffer(r[16]).append(l[16]);
		String encryptedBinaryMsg = finalPermutation(r16l16.toString());
		String encryptedHexadecimalMsg = new BooleanToHexaDecimal().getHexaDecimal(encryptedBinaryMsg);
		// System.out.println(encryptedHexadecimalMsg);
		return encryptedHexadecimalMsg;
	}
	public String decrypt(String msg,String key)
	{
		// String tempKey = key.length() >= 8 ? key.substring(0,8) : "";
		// String hexaKey = stringToHexaDecimal(tempKey);
		String binaryKey = new HexaDecimalToBoolean().getBoolean(key);
		String keyPermut = keyPermutation1(binaryKey);
		String[] twoHalves = breakInTwoHalves(keyPermut);
		setCAndD(twoHalves);
		setSixteenKeysForDecryption();
		String binaryMsg = new HexaDecimalToBoolean().getBoolean(msg);
		String initialPermutationMsg = initialPermutation(binaryMsg);
		String partialProcessedData[] = breakInTwoHalves(initialPermutationMsg);
		this.l[0] = partialProcessedData[0];
		this.r[0] = partialProcessedData[1];
		// System.out.println(this.l[0]);
		// System.out.println(this.r[0]);
		setLeftAndRight();
		StringBuffer r16l16 = new StringBuffer(r[16]).append(l[16]);
		String encryptedBinaryMsg = finalPermutation(r16l16.toString());
		String encryptedHexadecimalMsg = new BooleanToHexaDecimal().getHexaDecimal(encryptedBinaryMsg);
		// System.out.println(encryptedHexadecimalMsg);
		return encryptedHexadecimalMsg;
		// return "";

	}
	public String getEncryptedMessage(String msg,String key)
	{
		 StringBuffer result = new StringBuffer();
		 String hexMsg = stringToHexaDecimal(msg);
		 System.out.println(hexMsg);
		String formattedMsg = appendZeros(hexMsg);
		System.out.println(formattedMsg);
		// String binaryMsg = new HexaDecimalToBoolean().getBoolean(formattedMsg);
		String[] msgGroup = group16(formattedMsg);
		for(int i=0;i<msgGroup.length;i++)
		{
			result.append(encrypt(msgGroup[i],key));
		}
		return result.toString();

		// return encrypt(formattedMsg,key);
	}
	public String getDecryptedMessage(String msg,String key)
	{
		StringBuffer result = new StringBuffer();
		// String binaryMsg = new HexaDecimalToBoolean().getBoolean(msg);
		String[] msgGroup = group16(msg);
		for(int i=0;i<msgGroup.length;i++)
		{
			result.append(decrypt(msgGroup[i],key));
		}
		// return result.toString();
		 String data = hexDecimalToString(result.toString());
		return data;

		// return decrypt(msg,key);

	}
        public static void main(String args[])
        {
            String encrypted =  new DESDemo().getEncryptedMessage("lavesh is a champion he will never quit","133457799bbcdff1");
            String decrypted =  new DESDemo().getDecryptedMessage(encrypted,"133457799bbcdff1");
            
             System.out.println(encrypted);
             
            System.out.println("decrypted message" + decrypted);
        }
    
}
