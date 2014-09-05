package rockpaperscissors.lob.com.rockpaperscissors;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;


public class PlayActivity extends Activity {

    static final int max = 3;
    static final int min = 1;
    static int mustRefresh = 0;
    static int youscore = 0;
    static int hisscore = 0;

    static TextView tv;
    static ImageView iv;
    static ImageView yourchoice;
    static TextView opponent;
    static TextView you;
    static ImageButton rock;
    static ImageButton paper;
    static ImageButton scissors;

    static String youlost;
    static String youwon;
    static String keepplaying;
    static String stopplaying;
    static String draw;
    static String goodjob;
    static String youlostthematch;
    static String youwonthematch;
    static String ohno = "Oh no!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        opponent = (TextView)findViewById(R.id.textView3);
        you = (TextView)findViewById(R.id.textView4);

        yourchoice = (ImageView)findViewById(R.id.imageView2);

        tv = (TextView)findViewById(R.id.textView);
        tv.setVisibility(View.GONE);

        iv = (ImageView)findViewById(R.id.imageView);

        ActionBar ab = getActionBar();
        ab.hide();

        if (Locale.getDefault().getLanguage().equals("it")) {
            youlost = "Hai Perso!";
            youwon = "Hai Vinto!";
            keepplaying = "Continua a giocare";
            stopplaying = "Forse dopo...";
            draw = "Pareggio";
            goodjob = "Bravo!";
            youlostthematch = "Hai perso la partita";
            youwonthematch = "Hai vinto la partita!";
        } else {
            youlost = "You lost!";
            youwon = "You won!";
            keepplaying = "Keep playing";
            stopplaying = "Maybe later...";
            draw = "Draw";
            goodjob = "Good Job!";
            youlostthematch = "You lost the match";
            youwonthematch = "You won the match!";
        }

        setImmersiveMode();

        rock = (ImageButton)findViewById(R.id.imageButton);
        paper = (ImageButton)findViewById(R.id.imageButton2);
        scissors = (ImageButton)findViewById(R.id.imageButton3);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourchoice.setImageResource(R.drawable.rock);
                yourchoice.setVisibility(View.VISIBLE);

                mustRefresh++;
                if (mustRefresh == 2) {
                    iv.setVisibility(View.GONE); mustRefresh = 0;
                } else {
                    iv.setVisibility(View.VISIBLE);
                }
                Random r = new Random();
                int random = r.nextInt((max - min) + 1) + min;
                switch (random) {
                    case 1:
                        iv.setVisibility(View.VISIBLE);
                        iv.setImageResource(R.drawable.rockdown);
                        draw();
                        break;
                    case 2:
                        iv.setVisibility(View.VISIBLE);
                        iv.setImageResource(R.drawable.paperdown);
                        lose();
                        break;
                    case 3:
                        iv.setVisibility(View.VISIBLE);
                        iv.setImageResource(R.drawable.scissorsdown);
                        win();
                        break;
                }
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourchoice.setImageResource(R.drawable.paper);
                yourchoice.setVisibility(View.VISIBLE);
                if (mustRefresh == 2) { iv.setVisibility(View.GONE); mustRefresh = 0; } else { iv.setVisibility(View.VISIBLE); }
                Random r = new Random();
                int random = r.nextInt((max - min) + 1) + min;
                switch (random) {
                    case 1:
                        iv.setImageResource(R.drawable.rockdown);
                        win();
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.paperdown);
                        draw();
                        break;
                    case 3:
                        iv.setImageResource(R.drawable.scissorsdown);
                        lose();
                        break;
                }
            }
        });

        scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourchoice.setImageResource(R.drawable.scissors);
                yourchoice.setVisibility(View.VISIBLE);
                if (mustRefresh == 2) { iv.setVisibility(View.GONE); mustRefresh = 0; } else { iv.setVisibility(View.VISIBLE); }
                Random r = new Random();
                int random = r.nextInt((max - min) + 1) + min;
                switch (random) {
                    case 1:
                        iv.setImageResource(R.drawable.rockdown);
                        lose();
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.paperdown);
                        win();
                        break;
                    case 3:
                        iv.setImageResource(R.drawable.scissorsdown);
                        draw();
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    void draw() {
        tv.setVisibility(View.VISIBLE);
        tv.setText(draw);
        plusoneforboth();
    }

    void lose() {
        tv.setVisibility(View.VISIBLE);
        tv.setText(youlost);
        plusoneforopponent();
    }

    void win() {
        tv.setVisibility(View.VISIBLE);
        tv.setText(youwon);
        plusoneforyou();
    }

    void plusoneforyou() {
        youscore++;
        you.setText(String.valueOf(youscore));
        if (youscore == 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(goodjob);
            builder.setMessage(youwonthematch);
            builder.setCancelable(false);
            builder.setPositiveButton(keepplaying, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    hisscore = 0;
                    youscore = 0;
                    opponent.setText(String.valueOf(hisscore));
                    you.setText(String.valueOf(youscore));

                    dialog.dismiss();

                    setImmersiveMode();
                }
            });
            builder.setNegativeButton(stopplaying, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    hisscore = 0;
                    youscore = 0;
                    finish();

                    dialog.dismiss();

                    setImmersiveMode();
                }
            });
            builder.show();
        }
    }

    void plusoneforopponent() {
        hisscore++;
        opponent.setText(String.valueOf(hisscore));
        if (hisscore == 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(ohno);
            builder.setMessage(youlostthematch);
            builder.setCancelable(false);
            builder.setPositiveButton(keepplaying, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    hisscore = 0;
                    youscore = 0;
                    opponent.setText(String.valueOf(hisscore));
                    you.setText(String.valueOf(youscore));

                    dialog.dismiss();

                    setImmersiveMode();
                }
            });
            builder.setNegativeButton(stopplaying, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    hisscore = 0;
                    youscore = 0;
                    finish();

                    dialog.dismiss();

                    setImmersiveMode();
                }
            });
            builder.show();
        }
    }

    static void plusoneforboth() {
        //nothing...
    }

    @Override
    public void onResume() {
        super.onResume();
        setImmersiveMode();
    }

    void setImmersiveMode() {
        getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
    }
}
