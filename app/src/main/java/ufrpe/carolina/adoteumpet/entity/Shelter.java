package ufrpe.carolina.adoteumpet.entity;

/**
 * Created by ana.carolina.a.silva on 30/10/2016.
 */

public class Shelter {
    public int Id;
    public String Name;
    public String Phone;
    public String Email;
    public String Address;
    public String photoUrl;
    public Integer IdUserApp;
    public String RegisterDate;



    public Shelter(){
        super();
    }

    public Shelter(String mName,String mAddress,String mRegisterDate){
        super();
        this.Name = mName;
        this.Address = mAddress;
        this.RegisterDate = mRegisterDate;
    }

    public Shelter(int mId,String mName, String mEmail, String mAddress, String mPhotoUrl, String mPhone, Integer mIdUserApp, String mRegisterDate){
        super();
        this.Id = mId;
        this.Name = mName;
        this.Phone = mPhone;
        this.Email = mEmail;
        this.Address = mAddress;
        this.photoUrl = mPhotoUrl;
        this.IdUserApp = mIdUserApp;
        this.RegisterDate = mRegisterDate;
    }
}
