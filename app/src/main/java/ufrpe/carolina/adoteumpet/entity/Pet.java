package ufrpe.carolina.adoteumpet.entity;

/**
 * Created by ana.carolina.a.silva on 10/30/2016.
 */

public class Pet {
    public Integer Id;
    public String urlImagem;
    public String nome;
    public String especie;
    public String sexo;
    public String tagPet;

    public Pet(){
        super();
    }

    public Pet(Integer id, String urlImagem, String nomePet, String especiePet, String sexoPet, String tagPet){
        super();
        this.Id = id;
        this.urlImagem = urlImagem;
        this.nome = nomePet;
        this.especie = especiePet;
        this.sexo = sexoPet;
        this.tagPet = tagPet;
    }

}
