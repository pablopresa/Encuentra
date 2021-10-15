package dataTypes;

public class DataIDIDID  implements Comparable{
	private int id1;
	private int id2;
	private int id3;
	
	public int getId1() {
		return id1;
	}
	public void setId1(int id1) {
		this.id1 = id1;
	}
	public int getId2() {
		return id2;
	}
	public void setId2(int id2) {
		this.id2 = id2;
	}
	public int getId3() {
		return id3;
	}
	public void setId3(int id3) {
		this.id3 = id3;
	}
	
	@Override
	public int compareTo(Object o) 
	{
		DataIDIDID dl = (DataIDIDID)o;        

	        	if(this.getId1()==dl.getId1())
	        	{
	        	     return 0;
		        }
	        	
	        	else if(this.getId1()>dl.getId1())
	        	{
	        		return 1;
	        	}
	        	else
	        	{
	        		return -1;
	        	}
	        
	}

}
