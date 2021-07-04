package com.example.taverna.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.ArtikalActivity;
import com.example.taverna.GlavnaStranaActivity;
import com.example.taverna.IzabraniProdavacActivity;
import com.example.taverna.R;
import com.example.taverna.model.Artikal;
import com.example.taverna.model.PorucivanjeDTO;
import com.example.taverna.model.StavkaDTO;
import com.example.taverna.servisi.PorudzbineApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikliListaAdapter extends RecyclerView.Adapter<ArtikliListaAdapter.ViewHolder> {
    private List<Artikal> mData;
    private Context context;
    private Button button;
    SharedPreferences sharedPreferences;
    private String m_Text = "";
    Button button2;
    private PorudzbineApiService porudzbineApiService;
    private PorucivanjeDTO porucivanjeDTO = new PorucivanjeDTO();
    double cena = 0;
    String lista="";





    public ArtikliListaAdapter (List<Artikal> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView opis;
        TextView naziv;
        TextView cena;
        ImageView slika;
        EditText editText;
        String kolicina;
        LinearLayout linearLayout;
        TextView akcijskaCena;


        ViewHolder(View itemView) {
            super(itemView);

            naziv = itemView.findViewById(R.id.nazivArtikla);
            opis = itemView.findViewById(R.id.opisArtikla);
            cena = itemView.findViewById(R.id.cena);
            slika = itemView.findViewById(R.id.slikaArtikla);
            button = (Button)itemView.findViewById(R.id.dodajUKorpu);
            editText = itemView.findViewById(R.id.korpaDodaj);
            linearLayout = itemView.findViewById(R.id.akcijaLayout);
            akcijskaCena = itemView.findViewById(R.id.cenaAkcijska);

        }
    }

    @Override
    public ArtikliListaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artikli_row, parent, false);
        return new ArtikliListaAdapter .ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ArtikliListaAdapter.ViewHolder holder, int position) {
        Artikal artikal = mData.get(position);
        holder.naziv.setText(artikal.getNaziv());
        holder.opis.setText(artikal.getOpis());
        holder.cena.setText(artikal.getCena().toString());
        if(artikal.getPhoto()!=null)
            holder.slika.setImageBitmap(artikal.getPhoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArtikalActivity.class);
                Bundle b = new Bundle();
                b.putInt("ID",artikal.getId());
                intent.putExtras(b);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        double cenaArtikla;

        if(artikal.getAkcijskaCena()!=0.0){
            int cenaAkcija = artikal.getAkcijskaCena().intValue();
            holder.akcijskaCena.setText("Акцијска цена: "+cenaAkcija + " RSD");
            cenaArtikla = artikal.getAkcijskaCena().intValue();
        }else{
            cenaArtikla = artikal.getCena();
            holder.linearLayout.setAlpha(0);
        }




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kolicin1a = holder.editText.getText().toString();
                if(kolicin1a.length()==0){
                    holder.editText.requestFocus();
                    holder.editText.setError("Одаберите количину");
                }else {
                    Vibrator v1 = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
                    v1.vibrate(300);
                    StavkaDTO stavkaDTO = new StavkaDTO();
                    stavkaDTO.setArtikalId(artikal.getId());
                    stavkaDTO.setKolicina(Integer.parseInt(kolicin1a));
                    cena=cena+cenaArtikla*Integer.parseInt(kolicin1a);
                    System.out.println("aaaaaaaaaaaaaaa "+stavkaDTO.getArtikalId() + "  " + stavkaDTO.getKolicina());
                    double zajedno = cenaArtikla*Double.parseDouble(kolicin1a);
                    String text2 = "Артикал додат у корпу.\n" + artikal.getNaziv() + " x " + kolicin1a +"\nЦена " + zajedno;
                    SpannableStringBuilder biggerText = new SpannableStringBuilder(text2);
                    biggerText.setSpan(new RelativeSizeSpan(1.30f), 0, text2.length(), 0);
                    Toast.makeText(context, biggerText, Toast.LENGTH_SHORT).show();
                    lista+=artikal.getNaziv() + " x " +kolicin1a +" => " +zajedno + "\n";
                    porucivanjeDTO.getListaStavki().add(stavkaDTO);
                }
            }
        });



            IzabraniProdavacActivity.dugmeZavrsi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (porucivanjeDTO.getListaStavki().isEmpty()) {
                        Toast.makeText(context, "Ниси ништа изабрао у корпу", Toast.LENGTH_SHORT).show();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());

                        builder.setTitle("Потврда поруџбине");
                        builder.setMessage("Сигурни сте да желите да завршите поруџбину?\n" + lista + "\nУкупна цена је " +cena);

                        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                zavrsitiKupovinu(v);
                            }
                        });

                        builder.setNegativeButton("Не", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                }
            });
        }






    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void zavrsitiKupovinu(View v){

        porudzbineApiService = RetrofitClient.porudzbineApiService;
        Call<Void> call = porudzbineApiService.napraviPorudzbinu(porucivanjeDTO);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Vibrator v1 = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
                v1.vibrate(1000);
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getRootView().getContext());
                alert.setTitle("Потврђена поруџбина");
                alert.setMessage(lista +"\n" + "УКУПНА ЦЕНА ЈЕ " + cena);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, GlavnaStranaActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                alert.show();
                cena = 0;
                lista = "";
                porucivanjeDTO.getListaStavki().clear();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("cccccc ");

            }
        });


    }



    }



