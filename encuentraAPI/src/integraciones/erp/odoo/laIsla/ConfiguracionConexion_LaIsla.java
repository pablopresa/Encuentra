package integraciones.erp.odoo.laIsla;

public class ConfiguracionConexion_LaIsla {
	private String url, db, username, password;

	public ConfiguracionConexion_LaIsla(String url, String db, String username, String password) {
		this.url = url;
		this.db = db;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public String getDb() {
		return db;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static ConfiguracionConexion_LaIsla productivo() {
		ConfiguracionConexion_LaIsla ccls = new ConfiguracionConexion_LaIsla("https://pos.laisla.com.uy", "laisla", "ws_encuentra", "1234ws_encuentra.1");
		return ccls;
	}
	
	public static ConfiguracionConexion_LaIsla test_tcp() {
		ConfiguracionConexion_LaIsla ccls = new ConfiguracionConexion_LaIsla("http://localhost:8069", "laisla", "ws_encuentra", "1234ws_encuentra.12");
		return ccls;
	}
	
	public static ConfiguracionConexion_LaIsla test() {
		ConfiguracionConexion_LaIsla ccls = new ConfiguracionConexion_LaIsla("https://laisla.quanam.com", "laisla", "ws_encuentra", "1234ws_encuentra.1");
		return ccls;
	}
}
