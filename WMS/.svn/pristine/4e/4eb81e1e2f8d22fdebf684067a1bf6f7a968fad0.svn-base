package beans.encuentra;

import dataTypes.DataIDDescripcion;
import persistencia._EncuentraPersistir;

public class DepositoAdmin {
	private String direccion, nombre, ciudad, departamento, telefono;
	private int id, login, nroPuerta, local;
	private DataIDDescripcion tipo;
	private ParametrosPreparacion parametros;
	

	public int getNroPuerta() {
		return nroPuerta;
	}

	public void setNroPuerta(int nroPuerta) {
		this.nroPuerta = nroPuerta;
	}

	public int getLocal() {
		return local;
	}

	public void setLocal(int local) {
		this.local = local;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public DataIDDescripcion getTipo() {
		return tipo;
	}

	public void setTipo(DataIDDescripcion tipo) {
		this.tipo = tipo;
	}

	public DepositoAdmin() {
	}
	
	public void setEstanteriaPickingYExpedicion(int idEmpresa) {
		_EncuentraPersistir eper = new _EncuentraPersistir();
		
		String error = "";
		
		try {
			String set = "estanteriaPicking";
			String descripcion = "Picking "+ id;
			String idOjo = id+"P";
			int idUso = 5;
			
			for (int i = 0; i < 2; i++) 
			{
				if(i==1) 
				{
					set = "estanteriaExpedicion";
					descripcion = "Expedicion "+ id;
					idOjo = id+"E";
					idUso = 4;
				}
				
				String qEstanteria ="INSERT INTO estanterias(Descripcion, numPiso, TipoSector, CantidadEstantes, CantidadModulos, Color, Sector, idDeposito, idEmpresa, idUso)\r\n" + 
						"VALUES ('"+descripcion+"', 1, 12, 1, 1, 'green', 0, "+id+", "+idEmpresa+", "+idUso+");";
				
				String uDeposito ="UPDATE depositos \r\n" + 
						"	SET "+set+" = (SELECT idEstanteria FROM estanterias\r\n" + 
						"	WHERE idEmpresa = "+idEmpresa+" AND idDeposito = "+id+" AND Descripcion = '"+descripcion+"')\r\n" + 
						"WHERE idEmpresa = "+idEmpresa+" AND idDeposito = "+id;
				
				String qOjos ="INSERT INTO ojos(idOjo, idEstanteria, idModulo, idEstante, idRecorrido, area, Ancho, Alto, Profindidad, IdEmpresa)\r\n" + 
						"VALUES ('"+idOjo+"', (SELECT idEstanteria FROM estanterias\r\n" + 
						"	WHERE idEmpresa = "+idEmpresa+" AND idDeposito = "+id+" AND Descripcion = '"+descripcion+"'), " + 
						"	1, 1, 999999, 720000, 0, 0, 0, "+idEmpresa+");";
				
				eper.persistir(qEstanteria);
				eper.persistir(uDeposito);
				eper.persistir(qOjos);
			}
			
			error = "Depósito: "+id;
			System.out.println(error);
			
		} catch (Exception e) {
			error = "Error al crear Estanterias de depósito: "+id;
			System.out.println(error);
		}
		
	}

	public ParametrosPreparacion getParametros() {
		return parametros;
	}

	public void setParametros(ParametrosPreparacion parametros) {
		this.parametros = parametros;
	}

}
