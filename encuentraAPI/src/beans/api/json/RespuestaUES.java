package beans.api.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaUES
{
private String piezaId;

private String etiquetaURL;

private String eventoTrackingId;

private String envioId;

private String guiaId;

public String getPiezaId ()
{
return piezaId;
}

public void setPiezaId (String piezaId)
{
this.piezaId = piezaId;
}

public String getEtiquetaURL ()
{
return etiquetaURL;
}

public void setEtiquetaURL (String etiquetaURL)
{
this.etiquetaURL = etiquetaURL;
}

public String getEventoTrackingId ()
{
return eventoTrackingId;
}

public void setEventoTrackingId (String eventoTrackingId)
{
this.eventoTrackingId = eventoTrackingId;
}

public String getEnvioId ()
{
return envioId;
}

public void setEnvioId (String envioId)
{
this.envioId = envioId;
}

public String getGuiaId ()
{
return guiaId;
}

public void setGuiaId (String guiaId)
{
this.guiaId = guiaId;
}

@Override
public String toString()
{
return "ClassPojo [piezaId = "+piezaId+", etiquetaURL = "+etiquetaURL+", eventoTrackingId = "+eventoTrackingId+", envioId = "+envioId+", guiaId = "+guiaId+"]";
}
}