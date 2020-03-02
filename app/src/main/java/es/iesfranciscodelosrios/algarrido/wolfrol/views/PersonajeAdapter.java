package es.iesfranciscodelosrios.algarrido.wolfrol.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import es.iesfranciscodelosrios.algarrido.wolfrol.R;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;

public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder> implements View.OnClickListener {

    private ArrayList<Personaje> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class PersonajeViewHolder extends RecyclerView.ViewHolder {

        private TextView TextView_nombre;
        private TextView TextView_historia;
        private ImageView imageView;

        public PersonajeViewHolder(View itemView) {
            super(itemView);
            TextView_nombre = (TextView) itemView.findViewById(R.id.textViewNombre);
            TextView_historia = (TextView) itemView.findViewById(R.id.textViewHistoriaa);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewNombre);
        }

        public void PersonajeBind(Personaje item) {
            TextView_nombre.setText(item.getNombre());
            TextView_historia.setText(item.getHistoria());
            try {
                byte[] decodedString = Base64.decode(item.getImagen(),Base64.DEFAULT);
                Bitmap decodedByte= BitmapFactory.decodeByteArray(
                        decodedString,0,decodedString.length);
                imageView.setImageBitmap(decodedByte);
            }catch (NullPointerException n){
                imageView.setImageResource(R.drawable.logo);
            }


        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public PersonajeAdapter(@NonNull ArrayList<Personaje> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public PersonajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_data, parent, false);
        row.setOnClickListener(this);

       PersonajeViewHolder avh = new PersonajeViewHolder(row);
        return avh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(PersonajeViewHolder viewHolder, int position) {
        Personaje item = items.get(position);
        viewHolder.PersonajeBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}