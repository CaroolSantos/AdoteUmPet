package ufrpe.carolina.adoteumpet.entity;

/**
 * Created by ana.carolina.a.silva on 10/30/2016.
 */

public class Pet {
    public String urlImagem;
    public String nome;
    public String especie;
    public String sexo;

    public Pet(){
        super();
    }

    public Pet(String urlImagem, String nomePet, String especiePet, String sexoPet){
        super();
        this.urlImagem = urlImagem;
        this.nome = nomePet;
        this.especie = especiePet;
        this.sexo = sexoPet;
    }

}
