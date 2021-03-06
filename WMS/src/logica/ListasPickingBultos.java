package logica;

import java.util.ArrayList;
import java.util.List;

import beans.encuentra.DataLineaRepo;

public class ListasPickingBultos {
	private List<DTO_BultoPuntaje> bultosSirven = new ArrayList<DTO_BultoPuntaje>();
	private List<DataLineaRepo> faltantes = new ArrayList<>();
	
	public ListasPickingBultos(List<DTO_BultoPuntaje> bultosSirven, List<DataLineaRepo> faltantes) {
		this.bultosSirven = bultosSirven;
		this.faltantes = faltantes;
	}

	public List<DTO_BultoPuntaje> getBultosSirven() {
		return bultosSirven;
	}

	public List<DataLineaRepo> getFaltantes() {
		return faltantes;
	}

	public void setBultosSirven(List<DTO_BultoPuntaje> bultosSirven) {
		this.bultosSirven = bultosSirven;
	}

	public void setFaltantes(List<DataLineaRepo> faltantes) {
		this.faltantes = faltantes;
	}
	
	
}
