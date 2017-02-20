package ufrpe.carolina.adoteumpet.entity;

/**
 * Created by ana.carolina.a.silva on 30/10/2016.
 */

public class Shelter {
    public String Nome;
    public String Endereco;
    public String DataCadastro;

    public Shelter(){
        super();
    }

    public Shelter(String mNome, String mEndereco, String mDataCadastro){
        super();
        this.Nome = mNome;
        this.Endereco = mEndereco;
        this.DataCadastro = mDataCadastro;
    }
}
