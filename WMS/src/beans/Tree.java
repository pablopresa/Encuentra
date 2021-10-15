package beans;

import java.util.List;


public class Tree {

	

    private String text;
    private EstadoTree state;
    private List<Tree> nodes;
    private String href;
   
    
	
    
    public String getText() {
		return text;
	}




	public void setText(String text) {
		this.text = text;
	}




	public EstadoTree getState() {
		return state;
	}




	public void setState(EstadoTree state) {
		this.state = state;
	}




	public List<Tree> getNodes() {
		return nodes;
	}




	public void setNodes(List<Tree> nodes) {
		this.nodes = nodes;
	}




	public String getHref() {
		return href;
	}




	public void setHref(String href) {
		this.href = href;
	}




	public Tree(){
    	
    }

}
