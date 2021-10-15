package beans;


public class EstadoTree {

	

    private boolean checked;
    private boolean expanded;
   
    
	



	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isExpanded() {
		return expanded;
	}


	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}


	public EstadoTree(boolean state,boolean exp){
    	this.checked=state;
    	this.expanded=exp;
    }

}
