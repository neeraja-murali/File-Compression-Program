/*Author: Neeraja Murali Dharan 
 *File Name: DictEntry.java
 *Last Modified: 21 October 2015
 *Description: class represents an entry in a dictionary, associates 
 *a string key with an integer code
 */
public class DictEntry {
	//contains two private fields key and code
	private String key; 
	private int code; 
	
	//constructor returns a new DictEntry object with specified key and code
	public DictEntry(String key, int code){
		this.key=key;
		this.code=code;
	}
	
	//getter method returns key in DictEntry
	public String getKey(){
		return this.key;
	}
	
	//getter method returns code in DictEntry
	public int getCode(){
		return this.code; 
	}

}
