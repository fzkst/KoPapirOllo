package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView compHeart1, compHeart2, compHeart3, playerHeart1, playerHeart2, playerHeart3, compHand, playerHand;
    private TextView textViewDontetlen, eredmenyPlayer, eredmenyComp;
    private Button buttonKo, buttonPapir, buttonOllo;
    private String pHand, cHand;
    private Integer dontetlenekSzama, jatekosPontok, computerPontok;
    private AlertDialog.Builder builderJatekVege;
    private Toast toast;
    private ViewGroup vg;

    public void Init(){
        compHeart1 = findViewById(R.id.compHeart1);
        compHeart2 = findViewById(R.id.compHeart2);
        compHeart3 = findViewById(R.id.compHeart3);
        playerHeart1 = findViewById(R.id.playerHeart1);
        playerHeart2 = findViewById(R.id.playerHeart2);
        playerHeart3 = findViewById(R.id.playerHeart3);
        compHand = findViewById(R.id.compHand);
        playerHand = findViewById(R.id.playerHand);
        textViewDontetlen = findViewById(R.id.textViewDontetlen);
        eredmenyPlayer = findViewById(R.id.eredmenyPlayer);
        eredmenyComp = findViewById(R.id.eredmenyComp);
        buttonKo = findViewById(R.id.buttonKo);
        buttonPapir = findViewById(R.id.buttonPapir);
        buttonOllo = findViewById(R.id.buttonOllo);
        builderJatekVege = new AlertDialog.Builder(MainActivity.this);
        builderJatekVege.setCancelable(false)
                .setTitle("Nyertél! / Vesztettél!")
                .setMessage("Szeretne új játékot játszani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewGame();
                        Game(); }
                })
                .create();
        dontetlenekSzama = 0;
        pHand = "";
        cHand = "";
        jatekosPontok = 0;
        computerPontok = 0;
        vg = findViewById(R.id.mainLayout);
    }


    public void KiNyert(){
        ComputerMove();
        if (pHand == cHand){
            dontetlenekSzama++;
            toast.makeText(MainActivity.this, "Döntetlen!", Toast.LENGTH_SHORT).show();
            textViewDontetlen.setText(String.valueOf(dontetlenekSzama));
        } else if (pHand == "ko" && cHand == "ollo" || pHand == "papir" && cHand == "ko" || pHand == "ollo" && cHand == "papir"){
            jatekosPontok++;
            Toast.makeText(MainActivity.this, "Ezt a kört te nyerted!", Toast.LENGTH_SHORT).show();
            switch (jatekosPontok){
                case 1:
                    compHeart3.setBackgroundResource(R.drawable.heart1);
                    break;
                case 2:
                    compHeart2.setBackgroundResource(R.drawable.heart1);
                    break;
                case 3:
                    compHeart1.setBackgroundResource(R.drawable.heart1);
                    break;
            }
            eredmenyPlayer.setText(String.valueOf(jatekosPontok));
        } else {
            computerPontok++;
            Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerrte!", Toast.LENGTH_SHORT).show();
            switch (computerPontok){
                case 1:
                    playerHeart3.setBackgroundResource(R.drawable.heart1);
                    break;
                case 2:
                    playerHeart2.setBackgroundResource(R.drawable.heart1);
                    break;
                case 3:
                    playerHeart1.setBackgroundResource(R.drawable.heart1);
                    break;
            }
            eredmenyComp.setText(String.valueOf(computerPontok));
        }
        

        if (jatekosPontok == 3){
            builderJatekVege.setTitle("Nyertél!").create().show();
        }
        if (computerPontok == 3){
            builderJatekVege.setTitle("Vesztettél!").create().show();
        }

    }



    public String ComputerMove(){
        Random rnd = new Random();
        int veletlenSzam = rnd.nextInt(3) + 1;
        compHand.setBackgroundResource(0);
        switch (veletlenSzam){
            case 1:
                cHand = "ko";
                compHand.setImageResource(R.drawable.rock);
                break;
            case 2:
                cHand = "papir";
                compHand.setImageResource(R.drawable.paper);
                break;
            case 3:
                cHand = "ollo";
                compHand.setImageResource(R.drawable.scissors);
                break;
        }
        return cHand;
    }

    public void NewGame(){
        pHand = "";
        cHand = "";
        jatekosPontok = 0;
        computerPontok = 0;
        dontetlenekSzama = 0;
        textViewDontetlen.setText("0");
        eredmenyComp.setText("0");
        eredmenyPlayer.setText("0");
        compHeart1.setImageResource(R.drawable.heart2);
        compHeart2.setImageResource(R.drawable.heart2);
        compHeart3.setImageResource(R.drawable.heart2);
        playerHeart1.setImageResource(R.drawable.heart2);
        playerHeart2.setImageResource(R.drawable.heart2);
        playerHeart3.setImageResource(R.drawable.heart2);
    }


    public void Game(){


        buttonKo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerHand.setBackgroundResource(0);
                playerHand.setImageResource(R.drawable.rock);
                pHand = "ko";
                KiNyert();
            }
        });
        buttonPapir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerHand.setBackgroundResource(0);
                playerHand.setImageResource(R.drawable.paper);
                pHand = "papir";
                KiNyert();
            }
        });
        buttonOllo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerHand.setBackgroundResource(0);
                playerHand.setImageResource(R.drawable.scissors);
                pHand = "ollo";
                KiNyert();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Game();
    }
}

