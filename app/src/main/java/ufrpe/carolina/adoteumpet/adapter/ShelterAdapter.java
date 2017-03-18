package ufrpe.carolina.adoteumpet.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.entity.Shelter;

/**
 * Created by ana.carolina.a.silva on 30/10/2016.
 */

public class ShelterAdapter extends ArrayAdapter<Shelter>{
    Context context;
    int layoutResourceId;
    Shelter array[] = null;


    public ShelterAdapter(Context context, int layoutResourceId, Shelter[] objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.array = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ShelterHolder shelterHolder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            shelterHolder = new ShelterHolder();
            shelterHolder.imgViewShelter = (ImageView)row.findViewById(R.id.imgViewShelter);
            shelterHolder.txtViewNome = (TextView)row.findViewById(R.id.txtViewShelterNome);
            shelterHolder.txtViewEndereco = (TextView)row.findViewById(R.id.txtViewEndereco);
            shelterHolder.txtViewDataCadastro = (TextView)row.findViewById(R.id.txtViewDataCadastro);

            row.setTag(shelterHolder);
        }else{
            shelterHolder = (ShelterHolder)row.getTag();
        }

        Shelter shelter = array[position];
        //TODO: alterar lógica quando carregar dados da API
        if(position == 0){
            shelterHolder.imgViewShelter.setImageResource(R.drawable.abrigo_1);
        }else if(position == 1){
            shelterHolder.imgViewShelter.setImageResource(R.drawable.abrigo_2);
        }else if(position == 2){
            shelterHolder.imgViewShelter.setImageResource(R.drawable.abrigo_3);
        }else if(position == 3){
            shelterHolder.imgViewShelter.setImageResource(R.drawable.abrigo_4);
        }else if(position == 4){
            shelterHolder.imgViewShelter.setImageResource(R.drawable.abrigo_5);
        }else if(position == 5){
            shelterHolder.imgViewShelter.setImageResource(R.drawable.abrigo_6);
        }else{
            shelterHolder.imgViewShelter.setImageResource(R.drawable.logo_new);
        }

        shelterHolder.txtViewNome.setText(shelter.Name);
        shelterHolder.txtViewEndereco.setText(shelter.Address);
        shelterHolder.txtViewDataCadastro.setText(shelter.RegisterDate);

        return row;
    }

    static class ShelterHolder {
        ImageView imgViewShelter;
        TextView txtViewNome;
        TextView txtViewEndereco;
        TextView txtViewDataCadastro;
    }
}
