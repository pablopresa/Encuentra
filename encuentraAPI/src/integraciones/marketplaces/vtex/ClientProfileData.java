package integraciones.marketplaces.vtex;

public class ClientProfileData
{
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String documentType;
    private String document;
    private String phone;
    private String corporateName;
    private String tradeName;
    private String corporateDocument;
    private String stateInscription;
    private String corporatePhone;
    private boolean isCorporate;
    private String userProfileId;
    private String customerClass;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setDocumentType(String documentType){
        this.documentType = documentType;
    }
    public String getDocumentType(){
        return this.documentType;
    }
    public void setDocument(String document){
        this.document = document;
    }
    public String getDocument(){
        return this.document;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setCorporateName(String corporateName){
        this.corporateName = corporateName;
    }
    public String getCorporateName(){
        return this.corporateName;
    }
    public void setTradeName(String tradeName){
        this.tradeName = tradeName;
    }
    public String getTradeName(){
        return this.tradeName;
    }
    public void setCorporateDocument(String corporateDocument){
        this.corporateDocument = corporateDocument;
    }
    public String getCorporateDocument(){
        return this.corporateDocument;
    }
    public void setStateInscription(String stateInscription){
        this.stateInscription = stateInscription;
    }
    public String getStateInscription(){
        return this.stateInscription;
    }
    public void setCorporatePhone(String corporatePhone){
        this.corporatePhone = corporatePhone;
    }
    public String getCorporatePhone(){
        return this.corporatePhone;
    }
    public void setIsCorporate(boolean isCorporate){
        this.isCorporate = isCorporate;
    }
    public boolean getIsCorporate(){
        return this.isCorporate;
    }
    public void setUserProfileId(String userProfileId){
        this.userProfileId = userProfileId;
    }
    public String getUserProfileId(){
        return this.userProfileId;
    }
    public void setCustomerClass(String customerClass){
        this.customerClass = customerClass;
    }
    public String getCustomerClass(){
        return this.customerClass;
    }
}

