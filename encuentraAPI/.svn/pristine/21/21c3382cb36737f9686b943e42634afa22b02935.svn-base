package integraciones.erp.visualStore.stadium.sincro;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


import beans.Fecha;
import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.DepositoMayorista;
import integraciones.erp.visualStore.objetos.ParametrosVisual;
import logica.LogicaAPI;
import persistencia.MSSQL_API;
public class Masters 
{

	private int idEmpresa;
	private List<DataIDDescripcion> secciones,categorias,marcas,clases,generos,colores,temporadas;
	private List<DataDescDescripcion> artdescripcion,barras;
	private List<DTO_Articulo> articuos;
	private List<DepositoMayorista> depositos;
	
	private ParametrosVisual parametros;

	public Masters(int idEmpresa, int cantDias)
	{
		List<String> consultasIdDesc = new ArrayList<String>();
		Fecha fecha = new Fecha(-1,0,0);
		this.idEmpresa = idEmpresa; 
		consultasIdDesc.add("select IdSeccion, Descripcion from ArtSeccion;");
		consultasIdDesc.add("select IdCategoria,Descripcion from ArtCategoria;");
		consultasIdDesc.add("select IdMarca,Descripcion from ArtMarca;");
		consultasIdDesc.add("select IdClase, Descripcion  from ArtClase;");
		consultasIdDesc.add("select IdGenero, Descripcion from ArtGenero;");
		
		consultasIdDesc.add("SELECT IdArtParte, Descripcion FROM ArtParte where IdParte = 1");//color
		consultasIdDesc.add("select idArticulo, Descripcion from articulo where FecModif >= '"+fecha.darFechaAnioMesDia()+"'");//artDesc
		consultasIdDesc.add("select idArticulo, AB.NroBarra from ArtBarra AB");
		
		consultasIdDesc.add("select IdTemporada,Descripcion from ArtTemporada");
		
		
		this.secciones = MSSQL_API.darIDDescripcion(consultasIdDesc.get(0),idEmpresa);
		this.categorias = MSSQL_API.darIDDescripcion(consultasIdDesc.get(1),idEmpresa);
		this.marcas = MSSQL_API.darIDDescripcion(consultasIdDesc.get(2),idEmpresa);
		this.clases = MSSQL_API.darIDDescripcion(consultasIdDesc.get(3),idEmpresa);
		this.generos = MSSQL_API.darIDDescripcion(consultasIdDesc.get(4),idEmpresa);
		this.colores = MSSQL_API.darIDDescripcion(consultasIdDesc.get(5),idEmpresa);
		this.artdescripcion = MSSQL_API.darDescDescripcion(consultasIdDesc.get(6),idEmpresa);
		this.barras = MSSQL_API.darDescDescripcion(consultasIdDesc.get(7),idEmpresa);
		this.temporadas = MSSQL_API.darIDDescripcion(consultasIdDesc.get(8),idEmpresa);
		
		this.parametros = LogicaAPI.darParametrosVS(idEmpresa);
		
		String consultaArticulos = "select a.idArticulo, a.Descripcion, talle.IdArtParte talle, color.IdArtParte color, idTemporada, coleccion, PE.Numero idProveedor, idMarca, idClase, idCategoria, idGenero, CONCAT('"+parametros.getUrlImg()+"',SUBSTRING(a.IdArticulo,0,"+(parametros.getLargoBase()+parametros.getLargoColor())+"),'.jpg') imagen \r\n"
				+ "from articulo a\r\n"
				+ "inner join ArtParte color on SUBSTRING(a.idArticulo,"+parametros.getLargoBase()+","+parametros.getLargoColor()+")= color.IdArtParte and color.IdParte =1\r\n"
				+ "inner join ArtParte talle on SUBSTRING(a.idArticulo,"+(parametros.getLargoBase()+parametros.getLargoColor())+","+parametros.getLargoTalle()+")= talle.IdArtParte and talle.IdParte =2\r\n"
				+ "inner join PersonaEmpresa PE on A.IdProveedor = PE.IdPersonaEmpresa\r\n "
				+ " inner join (select distinct idArticulo from MovStock MS inner join Documento D on D.IdDocumento = MS.IdDocumento where Fecha>=DATEADD(DAY,-"+cantDias+",GETDATE())) art on art.IdArticulo = A.IdArticulo "
				+ "";
		
		this.articuos = MSSQL_API.darArticulosVSS(consultaArticulos,idEmpresa);
		Hashtable<String, List<String>> barrasArt = new Hashtable<String, List<String>>();
		for (DataDescDescripcion ba : this.barras) 
		{
			if(barrasArt.containsKey(ba.getId()))
			{
				barrasArt.get(ba.getId()).add(ba.getDescripcion());
			}
			else
			{
				List<String> lista = new ArrayList<String>();
				lista.add(ba.getDescripcion());
				barrasArt.put(ba.getId(), lista);
			}
		}
		
		for (DTO_Articulo ar : this.articuos) 
		{
			ar.setCodigoBarras(barrasArt.get(ar.getIdArticulo()));
		}
		String consultaDepositos = "select distinct D.IdDeposito,D.Descripcion, TD.Direccion,TD.Ciudad, TD.Ciudad from Deposito D\r\n"
				+ "inner join Tienda T on T.IdTienda = D.IdTienda\r\n"
				+ "inner join TiendaDatos TD on TD.IdTienda = T.IdTienda\r\n"
				+ "where TD.IdListaEmpresa=1";
		
		this.depositos = MSSQL_API.darDepositosVSS(consultaDepositos,idEmpresa);
		
		
	}
	
	
	
	
	
	
		
			
	public List<DepositoMayorista> getDepositos() {
		return depositos;
	}








	public void setDepositos(List<DepositoMayorista> depositos) {
		this.depositos = depositos;
	}








	public List<DTO_Articulo> getArticuos() {
		return articuos;
	}








	public void setArticuos(List<DTO_Articulo> articuos) {
		this.articuos = articuos;
	}








	public ParametrosVisual getParametros() {
		return parametros;
	}








	public void setParametros(ParametrosVisual parametros) {
		this.parametros = parametros;
	}








	public List<DataIDDescripcion> getSecciones() {
		return secciones;
	}
	public void setSecciones(List<DataIDDescripcion> secciones) {
		this.secciones = secciones;
	}
	public List<DataIDDescripcion> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<DataIDDescripcion> categorias) {
		this.categorias = categorias;
	}
	public List<DataIDDescripcion> getMarcas() {
		return marcas;
	}
	public void setMarcas(List<DataIDDescripcion> marcas) {
		this.marcas = marcas;
	}
	public List<DataIDDescripcion> getClases() {
		return clases;
	}
	public void setClases(List<DataIDDescripcion> clases) {
		this.clases = clases;
	}
	public List<DataIDDescripcion> getGeneros() {
		return generos;
	}
	public void setGeneros(List<DataIDDescripcion> generos) {
		this.generos = generos;
	}
	public List<DataIDDescripcion> getColores() {
		return colores;
	}
	public void setColores(List<DataIDDescripcion> colores) {
		this.colores = colores;
	}
	public List<DataIDDescripcion> getTemporadas() {
		return temporadas;
	}
	public void setTemporadas(List<DataIDDescripcion> temporadas) {
		this.temporadas = temporadas;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public List<DataDescDescripcion> getArtdescripcion() {
		return artdescripcion;
	}
	public void setArtdescripcion(List<DataDescDescripcion> artdescripcion) {
		this.artdescripcion = artdescripcion;
	}
	public List<DataDescDescripcion> getBarras() {
		return barras;
	}
	public void setBarras(List<DataDescDescripcion> barras) {
		this.barras = barras;
	}
	
	
	

}
