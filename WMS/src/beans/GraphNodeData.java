package beans;

public class GraphNodeData {
	
	private String key;
	 private String text;
	 private boolean isGroup;
	 private String group;
	 private String color;
	 private String loc;


	 // Getter Methods 

	 public String getKey() {
	  return key;
	 }

	 public String getText() {
	  return text;
	 }

	 public boolean getIsGroup() {
	  return isGroup;
	 }

	 public String getGroup() {
	  return group;
	 }

	 public String getColor() {
	  return color;
	 }

	 public String getLoc() {
	  return loc;
	 }

	 // Setter Methods 

	 public void setKey(String key) {
	  this.key = key;
	 }

	 public void setText(String text) {
	  this.text = text;
	 }

	 public void setIsGroup(boolean isGroup) {
	  this.isGroup = isGroup;
	 }

	 public void setGroup(String group) {
	  this.group = group;
	 }

	 public void setColor(String color) {
	  this.color = color;
	 }

	 public void setLoc(String loc) {
	  this.loc = loc;
	 }
	 
	 public GraphNodeData(){
		 
	 }
	
}
