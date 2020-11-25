package com.projeto_padrao.adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.projeto_padrao.R;
import com.projeto_padrao.activities.eventos.EventosActivity;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.eventos.Evento;
import com.projeto_padrao.models.eventos.Favorito;
import java.sql.Date;
import java.util.List;
public class FavoritosAdapter extends BaseAdapter {
    Favorito favorito;
    Context context;
    List<Favorito> favoritos;
    private LayoutInflater mInflater;

    public FavoritosAdapter(Context context, List<Favorito> favoritos) {
        this.favoritos = favoritos;
        this.context = context;
    }
    @Override
    public int getCount() {
        return favoritos.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Favorito favorito = favoritos.get(position);
        favorito.setContext(context);
        Usuario usuarioLogado = Usuario.verificaUsuarioLogado();
        final FavoritosAdapter.ListContent holder;
        View v = convertView;
        if (v == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.favoritos_item_lista, null);
            holder = new FavoritosAdapter.ListContent();
            holder.favorito_lista_listview = (ListView) v.findViewById(R.id.favorito_lista_listview);
            holder.favoritos_lista_textview_nome = (TextView) v.findViewById(R.id.favoritos_lista_textview_nome);
            holder.favoritos_item_view = (View) v.findViewById(R.id.favoritos_item_view);
            holder.favoritos_item_favorito = (ImageView) v.findViewById(R.id.favoritos_item_favorito);
            holder.favoritos_lista_textview_data = (TextView) v.findViewById(R.id.favoritos_lista_textview_data);
            holder.favoritos_lista_textview_hora = (TextView) v.findViewById(R.id.favoritos_lista_textview_hora);
            holder.favoritos_lista_textview_local = (TextView) v.findViewById(R.id.favoritos_lista_textview_local);
            holder.favoritos_lista_textview_preco = (TextView) v.findViewById(R.id.favoritos_lista_textview_preco);
            holder.favoritos_item_favoritado = (ImageView) v.findViewById(R.id.favoritos_item_favoritado);

            holder.favoritos_item_favoritado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.favoritos_item_favorito.setVisibility(View.VISIBLE);
                    holder.favoritos_item_favoritado.setVisibility(View.GONE);



                    if (usuarioLogado != null) {
                        favorito.deletarFavorito(usuarioLogado.getKey());
                    }
                }
            });


            v.setTag(holder);
        } else {
            holder = (FavoritosAdapter.ListContent) v.getTag();
        }

        Evento eventoFavorito = Evento.findById(Evento.class,favorito.getEvento());


         holder.favoritos_lista_textview_nome.setText(eventoFavorito.getNomeEvento());
         holder.favoritos_lista_textview_hora.setText((("Horário: " + eventoFavorito.getHorario())));
         holder.favoritos_lista_textview_data.setText(("Data: " + eventoFavorito.getData()));
         holder.favoritos_lista_textview_local.setText(eventoFavorito.getLocal());
         holder.favoritos_lista_textview_preco.setText("Preço: " + String.valueOf(eventoFavorito.getPreco()));
        return v;
    }
    public static class ListContent {
        TextView favoritos_lista_textview_nome;
        View favoritos_item_view;
        ImageView favoritos_item_favorito;
        ListView favorito_lista_listview;
        TextView favoritos_lista_textview_data;
        TextView favoritos_lista_textview_hora;
        TextView favoritos_lista_textview_local;
        TextView favoritos_lista_textview_preco;
        ImageView favoritos_item_favoritado;
    }
}