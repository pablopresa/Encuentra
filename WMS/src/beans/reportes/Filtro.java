package beans.reportes;

import java.util.List;

import dataTypes.DataIDDescripcion;

public class Filtro 
{
	private String tipo, name, value,placeholder, label,id;
	private List<DataIDDescripcion> values;
	private boolean required;
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<DataIDDescripcion> getValues() {
		return values;
	}
	public void setValues(List<DataIDDescripcion> values) {
		this.values = values;
	}
	
	public Filtro(String tipo, String name, String value, String placeholder,boolean required,String label) {
		this.tipo = tipo;
		this.name = name;
		this.value = value;
		this.placeholder = placeholder;
		this.required = required;
		this.label = label;
		
	}
	
	public Filtro(String tipo, String name, String placeholder, List<DataIDDescripcion> values, boolean required, String label) 
	{
		this.tipo = tipo;
		this.name = name;
		this.placeholder = placeholder;
		this.values = values;
		this.required = required;
		this.label = label;
		
	}
	
	public Filtro(String tipo, String name, String value, String placeholder, List<DataIDDescripcion> values, boolean required,
			String label) 
	{
		this.tipo = tipo;
		this.name = name;
		this.value = value;
		this.placeholder = placeholder;
		this.values = values;
		this.required = required;
		this.label = label;
		
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	
	
	

}
