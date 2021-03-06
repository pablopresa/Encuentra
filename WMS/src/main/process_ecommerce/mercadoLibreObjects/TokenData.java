package main.process_ecommerce.mercadoLibreObjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TokenData
{
    private String scope;

    private String user_id;

    private String expires_in;

    private String token_type;

    private String refresh_token;

    private String access_token;

    public String getScope ()
    {
        return scope;
    }

    public void setScope (String scope)
    {
        this.scope = scope;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getExpires_in ()
    {
        return expires_in;
    }

    public void setExpires_in (String expires_in)
    {
        this.expires_in = expires_in;
    }

    public String getToken_type ()
    {
        return token_type;
    }

    public void setToken_type (String token_type)
    {
        this.token_type = token_type;
    }

    public String getRefresh_token ()
    {
        return refresh_token;
    }

    public void setRefresh_token (String refresh_token)
    {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token ()
    {
        return access_token;
    }

    public void setAccess_token (String access_token)
    {
        this.access_token = access_token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [scope = "+scope+", user_id = "+user_id+", expires_in = "+expires_in+", token_type = "+token_type+", refresh_token = "+refresh_token+", access_token = "+access_token+"]";
    }
}
	
