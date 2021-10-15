package integraciones.erp.sapBO.stadium;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class RespWSSAP
{
	private String description;

    private String isError;

    private String code;

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String Description)
    {
        this.description = Description;
    }

    public String getIsError ()
    {
        return isError;
    }

    public void setIsError (String IsError)
    {
        this.isError = IsError;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String Code)
    {
        this.code = Code;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+description+", IsError = "+isError+", Code = "+code+"]";
    }
}