package ufrpe.carolina.adoteumpet.entity;

/**
 * Created by Antonio on 20/03/17.
 */

public class ProfilePet {
    public Integer Id;
    public String PhotoUrl;
    public String Name;
    public String Status;
    public String Description;
    public String Gender;
    public String Age;
    public String Size;
    public String Breed;
    public String ContactPhone;
    public String ContactEmail;
    public String ContactName;

    public ProfilePet(){
        super();
    }

    public ProfilePet(Integer mId, String mPhotoUrl, String mName, String mStatus, String mDescription, String mGender,
                      String mAge, String mSize, String mBreed, String mContactPhone, String mContactEmail, String mContactName){
        super();
        this.Id = mId;
        this.PhotoUrl = mPhotoUrl;
        this.Name = mName;
        this.Status = mStatus;
        this.Description = mDescription;
        this.Gender = mGender;
        this.Age = mAge;
        this.Size = mSize;
        this.Breed = mBreed;
        this.ContactPhone = mContactPhone;
        this.ContactEmail = mContactEmail;
        this.ContactName = mContactName;
    }
}
