package com.projeto_padrao.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.projeto_padrao.R;
import com.projeto_padrao.activities.eventos.EventosActivity;
import com.projeto_padrao.activities.usuario.ListarUsuariosActivity;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.eventos.Evento;
import com.projeto_padrao.models.eventos.Favorito;
import java.util.List;

public class EventosAdapter extends BaseAdapter {


    Favorito favorito;
    Context context;
    List<Evento> eventos;
    private LayoutInflater mInflater;


    public EventosAdapter(Context context, List<Evento> eventos) {
        this.eventos = eventos;
        this.context = context;
    }
    @Override
    public int getCount() {
        return eventos.size();
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
        final Evento evento = eventos.get(position);
        evento.setContext(context);
        Usuario usuarioLogado = Usuario.verificaUsuarioLogado();

        final EventosAdapter.ListContent holder;
        View v = convertView;
        if (v == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.eventos_item_lista, null);
            holder = new EventosAdapter.ListContent();

            holder.eventos_lista_textview_nome = (TextView) v.findViewById(R.id.eventos_lista_textview_nome);
            holder.eventos_item_view = (View) v.findViewById(R.id.eventos_item_view);
            holder.eventos_item_favorito = (ImageView) v.findViewById(R.id.eventos_item_favorito);
            holder.evento_lista_listview = (ListView) v.findViewById(R.id.evento_lista_listview);
            holder.eventos_lista_textview_data = (TextView) v.findViewById(R.id.eventos_lista_textview_data);
            holder.eventos_lista_textview_hora = (TextView) v.findViewById(R.id.eventos_lista_textview_hora);
            holder.eventos_lista_textview_local = (TextView) v.findViewById(R.id.eventos_lista_textview_local);
            holder.eventos_lista_textview_preco = (TextView) v.findViewById(R.id.eventos_lista_textview_preco);
            holder.eventos_item_favoritado = (ImageView) v.findViewById(R.id.eventos_item_favoritado);


            holder.eventos_item_favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //holder.eventos_item_favorito.setVisibility(View.GONE);
                    //holder.eventos_item_favoritado.setVisibility(View.VISIBLE);


                    Favorito favorito = new Favorito();

                    favorito.setEvento(evento.getId());
                    favorito.setUsuario(usuarioLogado.getId());

                    Usuario usuario = Usuario.verificaUsuarioLogado();
                    favorito.adicionarFavorito(usuario.getKey(), context);

                    List<Favorito> favoritosSalvos = Favorito.find(Favorito.class, "evento = ?", evento.getId().toString());



                   if(favoritosSalvos.size() == 0){

                        holder.eventos_item_favorito.setVisibility(View.GONE);

                    }else{

                        holder.eventos_item_favoritado.setVisibility(View.VISIBLE);

                    }
                    ((EventosActivity)context).inicializandoComponentes();
                }
            });

            v.setTag(holder);
        } else {
            holder = (EventosAdapter.ListContent) v.getTag();
        }

        holder.eventos_lista_textview_nome.setText(evento.getNomeEvento());
        holder.eventos_lista_textview_hora.setText((("Horário: " + evento.getHorario())));
        holder.eventos_lista_textview_data.setText(("Data: " + evento.getData()));
        holder.eventos_lista_textview_local.setText(evento.getLocal());
        holder.eventos_lista_textview_preco.setText("Preço: " + String.valueOf(evento.getPreco()));
        return v;
    }

    public static class ListContent {
        TextView eventos_lista_textview_nome;
        View eventos_item_view;
        ImageView eventos_item_favorito;
        ListView evento_lista_listview;
        TextView eventos_lista_textview_data;
        TextView eventos_lista_textview_hora;
        TextView eventos_lista_textview_local;
        TextView eventos_lista_textview_preco;
        ImageView eventos_item_favoritado;


    }
}

