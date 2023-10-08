package com.example.akasztofa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewLetter;
    private TextView textViewResult;
    private AppCompatButton buttonMinus;
    private AppCompatButton buttonPlus;
    private AppCompatButton buttonGuess;
    private ImageView imageView;
    private ArrayList<String> wordList;
    private HashMap<Character, Boolean> map;
    private char[] alphabet;
    private String correctWord;
    private StringBuilder sb;
    private AlertDialog.Builder alert;
    private int life;
    private int letterIndex;
    private char selectedLetter;
    public void init(){
        //elements
        textViewLetter = findViewById(R.id.textViewLetter);
        textViewResult = findViewById(R.id.textViewResult);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonGuess = findViewById(R.id.buttonGuess);
        imageView = findViewById(R.id.imageView);

        //list of words
        wordList = new ArrayList<String>();
        wordList.add("ALMA");
        wordList.add("ANDROID");
        wordList.add("KACSA");
        wordList.add("MEDVE");
        wordList.add("LAPTOP");
        wordList.add("JAVA");
        wordList.add("HTML");
        wordList.add("WEBOLDAL");
        wordList.add("URL");
        wordList.add("ASZTAL");
        Random rnd = new Random();
        correctWord = wordList.get(rnd.nextInt(10));

        //list of letters
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        map = new HashMap<Character, Boolean>();
        for(int i = 0; i < alphabet.length; i++){
            map.put(alphabet[i], false);
        }

        //set variables
        life = 13;
        letterIndex = 0;
        selectedLetter = 'A';

        //set elements
        imageView.setImageResource(R.drawable.akasztofa00);
        textViewLetter.setText(String.valueOf(selectedLetter));
        textViewLetter.setTextColor(Color.rgb(0,0,0));

        //set alertdialog
        alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Szeretnél még egyet játszani?")
                .setNegativeButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { newGame(); }
                })
                .setPositiveButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { finish(); }
                })
                .setCancelable(false)
                .create();
    }

    public void setResult(){
        sb = new StringBuilder();
        for (int i = 0; i < correctWord.length(); i++){
            sb.append("_");
        }
        textViewResult.setText(sb);
    }

    public boolean containsLetter(){
        if (correctWord.contains(String.valueOf(selectedLetter))){
            return true;
        } else{
            return false;
        }
    }

    public void newGame(){
        init();
        setResult();
    }

    public void setLetterColor(){
        if (map.get(selectedLetter)){
            textViewLetter.setTextColor(Color.rgb(255,0,0));
        } else{
            textViewLetter.setTextColor(Color.rgb(0,0,0));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setResult();

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(letterIndex == 0)
                {
                    letterIndex = 25;
                }
                else
                {
                    letterIndex = letterIndex - 1;
                }

                selectedLetter = alphabet[letterIndex];
                setLetterColor();
                textViewLetter.setText(String.valueOf(selectedLetter));
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(letterIndex == 25)
                {
                    letterIndex = 0;
                }
                else
                {
                    letterIndex = letterIndex + 1;
                }

                selectedLetter = alphabet[letterIndex];
                setLetterColor();
                textViewLetter.setText(String.valueOf(selectedLetter));
            }
        });

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set letter color
                map.put(selectedLetter, true);
                setLetterColor();

                if (containsLetter()){
                    for (int i = 0; i < correctWord.length(); i++){
                        if (selectedLetter == correctWord.charAt(i))
                        {
                            sb.setCharAt(i, selectedLetter);
                            textViewResult.setText(sb);
                            if (!sb.toString().contains("_")){
                                alert.setTitle("Helyes megfejtés!").create();
                                alert.show();
                            }
                        }
                    }
                }
                else{
                    life--;
                    switch (life){
                        case 12:
                            imageView.setImageResource(R.drawable.akasztofa01);
                            break;
                        case 11:
                            imageView.setImageResource(R.drawable.akasztofa02);
                            break;
                        case 10:
                            imageView.setImageResource(R.drawable.akasztofa03);
                            break;
                        case 9:
                            imageView.setImageResource(R.drawable.akasztofa04);
                            break;
                        case 8:
                            imageView.setImageResource(R.drawable.akasztofa05);
                            break;
                        case 7:
                            imageView.setImageResource(R.drawable.akasztofa06);
                            break;
                        case 6:
                            imageView.setImageResource(R.drawable.akasztofa07);
                            break;
                        case 5:
                            imageView.setImageResource(R.drawable.akasztofa08);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.akasztofa09);
                            break;
                        case 3:
                            imageView.setImageResource(R.drawable.akasztofa10);
                            break;
                        case 2:
                            imageView.setImageResource(R.drawable.akasztofa11);
                            break;
                        case 1:
                            imageView.setImageResource(R.drawable.akasztofa12);
                            break;
                        case 0:
                            imageView.setImageResource(R.drawable.akasztofa13);
                            alert.setTitle("Nem sikerült kitalálni!").create();
                            alert.show();
                            break;
                    }
                }
            }
        });
    }
}