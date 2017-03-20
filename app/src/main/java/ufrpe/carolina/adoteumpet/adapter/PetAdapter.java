package ufrpe.carolina.adoteumpet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.entity.Pet;

/**
 * Created by ana.carolina.a.silva on 10/30/2016.
 */

public class PetAdapter extends ArrayAdapter<Pet>{
    Context context;
    int layoutResourceId;
    Pet data[] = null;

    public PetAdapter(Context context, int layoutResourceId, Pet[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PetHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PetHolder();
            holder.img_Pet = (ImageView)row.findViewById(R.id.img_Pet);
            holder.txt_nome_pet = (TextView)row.findViewById(R.id.txt_nome_pet);
            holder.txt_especie_pet = (TextView)row.findViewById(R.id.txt_especie_pet);
            holder.txt_sexo_pet = (TextView)row.findViewById(R.id.txt_sexo_pet);
            holder.txt_tagPet = (TextView)row.findViewById(R.id.txt_tagPet);

            row.setTag(holder);
        }
        else
        {
            holder = (PetHolder)row.getTag();
        }

        Pet pet = data[position];
        //TODO alterar lógica quando carregar dados da API
        if(position == 0){
            holder.img_Pet.setImageResource(R.drawable.toddy);
        }else if(position == 1){
            holder.img_Pet.setImageResource(R.drawable.rock);
        }else if(position == 2){
            holder.img_Pet.setImageResource(R.drawable.gato);
        }else{
            holder.img_Pet.setImageResource(R.drawable.logo_new);
        }
        holder.txt_nome_pet.setText(pet.nome);
        holder.txt_especie_pet.setText(pet.especie);
        holder.txt_sexo_pet.setText(pet.sexo);
        holder.txt_tagPet.setText(pet.tagPet);

        return row;
    }

    static class PetHolder
    {
        ImageView img_Pet;
        TextView txt_nome_pet;
        TextView txt_especie_pet;
        TextView txt_sexo_pet;
        TextView txt_tagPet;
    }
}
