package mx.edu.ittepic.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button boton;
    TextView numero;
    float random,nfinal,avance,cap;
    String num,num2,num3;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.btn);
        numero = findViewById(R.id.txtnum);
        avance=0;

        final DecimalFormat df = new DecimalFormat("#.0");
        random = (float) (Math.random()*3);
        num = df.format(random);
        numero.setText(""+num);
        nfinal=Float.parseFloat(num);

        timer = new CountDownTimer(10000,400) {
            @Override
            public void onTick(long millisUntilFinished) {
                avance= (float) (avance+0.1);
                num2 = df.format(avance);
                boton.setText(""+num2);

                if (avance>=2.9){
                    avance= (float) 0.0;
                }

            }

            @Override
            public void onFinish() {
                timer.start();

            }
        };timer.start();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num3=num2;
                cap= Float.parseFloat(num3);
                if (nfinal==cap){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                    alerta.setTitle("FELICIDADES GANASTE");
                   alerta.setMessage("¿VOLVER A JUGAR?");
                    alerta.setPositiveButton("REPETIR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                            timer.cancel();
                        }
                    });
                    AlertDialog dialog =alerta.create();
                    dialog.show();

                }else {
                    Toast.makeText(MainActivity.this, "PERDISTE", Toast.LENGTH_SHORT).show();
                    random = (float) (Math.random()*3);
                    num = df.format(random);
                    numero.setText(""+num);

                }



            }
        });


    }
}

