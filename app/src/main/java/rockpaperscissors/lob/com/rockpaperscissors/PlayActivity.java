package rockpaperscissors.lob.com.rockpaperscissors;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import rockpaperscissors.lob.com.rockpaperscissors.Helpers.ImmersiveMode;
import static rockpaperscissors.lob.com.rockpaperscissors.Helpers.Constants.*;

public class PlayActivity extends Activity {

    private TextView scoreTextView;
    private ImageView opponentImageView;
    private ImageView yourChoiceImageView;
    private TextView opponentTextView;
    private TextView youTextView;
    private ImageButton rockImageButton;
    private ImageButton paperImageButton;
    private ImageButton scissorsImageButton;

    private int mustRefresh = 0;
    private int yourscore = 0;
    private int hisscore = 0;

    private String youlost;
    private String youwon;
    private String keepplaying;
    private String stopplaying;
    private String draw;
    private String goodjob;
    private String youlostthematch;
    private String youwonthematch;
    private String ohno;

    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        window = getWindow();

        opponentTextView = (TextView) findViewById(R.id.textView3);
        youTextView = (TextView) findViewById(R.id.textView4);

        yourChoiceImageView = (ImageView) findViewById(R.id.imageView2);

        scoreTextView = (TextView) findViewById(R.id.textView);
        scoreTextView.setVisibility(View.GONE);

        opponentImageView = (ImageView) findViewById(R.id.imageView);

        try {
            ActionBar actionBar = getActionBar();
            actionBar.hide();
        } catch (NullPointerException ignored) {}

        youlost = getString(R.string.youlost);
        youwon = getString(R.string.youwon);
        keepplaying = getString(R.string.keepplaying);
        stopplaying = getString(R.string.stopplaying);
        draw = getString(R.string.draw);
        goodjob = getString(R.string.goodjob);
        youlostthematch = getString(R.string.youlostthematch);
        youwonthematch = getString(R.string.youwonthematch);
        ohno = getString(R.string.ohno);

        rockImageButton = (ImageButton) findViewById(R.id.imageButton);
        paperImageButton = (ImageButton) findViewById(R.id.imageButton2);
        scissorsImageButton = (ImageButton) findViewById(R.id.imageButton3);

        ImmersiveMode.setImmersiveMode(window);

        rockImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourChoiceImageView.setImageResource(R.drawable.rock);
                yourChoiceImageView.setVisibility(View.VISIBLE);

                mustRefresh++;
                if (mustRefresh == 2) {
                    opponentImageView.setVisibility(View.GONE);
                    mustRefresh = 0;
                } else {
                    opponentImageView.setVisibility(View.VISIBLE);
                }
                Random r = new Random();
                int random = r.nextInt((MAX_NORMAL_MODE - MIN) + 1) + MIN;
                switch (random) {
                    case 1:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.rockdown);
                        sendScore(DRAW);
                        break;
                    case 2:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.paperdown);
                        sendScore(LOSE);
                        break;
                    case 3:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.scissorsdown);
                        sendScore(WIN);
                        break;
                }
            }
        });

        paperImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourChoiceImageView.setImageResource(R.drawable.paper);
                yourChoiceImageView.setVisibility(View.VISIBLE);
                if (mustRefresh == 2) {
                    opponentImageView.setVisibility(View.GONE);
                    mustRefresh = 0;
                } else {
                    opponentImageView.setVisibility(View.VISIBLE);
                }
                Random r = new Random();
                int random = r.nextInt((MAX_NORMAL_MODE - MIN) + 1) + MIN;
                switch (random) {
                    case 1:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.rockdown);
                        sendScore(WIN);
                        break;
                    case 2:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.paperdown);
                        sendScore(DRAW);
                        break;
                    case 3:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.scissorsdown);
                        sendScore(LOSE);
                        break;
                }
            }
        });

        scissorsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourChoiceImageView.setImageResource(R.drawable.scissors);
                yourChoiceImageView.setVisibility(View.VISIBLE);

                if (mustRefresh == 2) {
                    opponentImageView.setVisibility(View.GONE);
                    mustRefresh = 0;
                } else {
                    opponentImageView.setVisibility(View.VISIBLE);
                }
                Random r = new Random();
                int random = r.nextInt((MAX_NORMAL_MODE - MIN) + 1) + MIN;
                switch (random) {
                    case 1:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.rockdown);
                        sendScore(LOSE);
                        break;
                    case 2:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.paperdown);
                        sendScore(WIN);
                        break;
                    case 3:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.scissorsdown);
                        sendScore(DRAW);
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
    
    private void sendScore(int score) {
        if (score == WIN)
            win();
        else if (score == DRAW)
            draw();
        else
            lose();
    }

    private void draw() {
        scoreTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(draw);
        plusOneForBoth();
    }

    private void lose() {
        scoreTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(youlost);
        plusOneForOpponent();
    }

    private void win() {
        scoreTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(youwon);
        plusOneForYou();
    }

    private void plusOneForYou() {
        yourscore++;
        youTextView.setText(String.valueOf(yourscore));
        if (yourscore == 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(goodjob);
            builder.setMessage(youwonthematch);
            builder.setCancelable(false);
            builder.setPositiveButton(keepplaying, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    hisscore = 0;
                    yourscore = 0;
                    opponentTextView.setText(String.valueOf(hisscore));
                    youTextView.setText(String.valueOf(yourscore));

                    dialog.dismiss();

                    ImmersiveMode.setImmersiveMode(window);
                }
            });
            builder.setNegativeButton(stopplaying, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    hisscore = 0;
                    yourscore = 0;
                    finish();

                    dialog.dismiss();

                    ImmersiveMode.setImmersiveMode(window);
                }
            });
            builder.show();
        }
    }

    private void plusOneForOpponent() {
        hisscore++;
        opponentTextView.setText(String.valueOf(hisscore));
        if (hisscore == 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(ohno);
            builder.setMessage(youlostthematch);
            builder.setCancelable(false);
            builder.setPositiveButton(keepplaying, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    hisscore = 0;
                    yourscore = 0;
                    opponentTextView.setText(String.valueOf(hisscore));
                    youTextView.setText(String.valueOf(yourscore));

                    dialog.dismiss();

                    ImmersiveMode.setImmersiveMode(window);
                }
            });
            builder.setNegativeButton(stopplaying, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    hisscore = 0;
                    yourscore = 0;
                    finish();

                    dialog.dismiss();

                    ImmersiveMode.setImmersiveMode(window);
                }
            });
            builder.show();
        }
    }

    private void plusOneForBoth() {
        // nothing to do...
    }

    @Override
    public void onResume() {
        super.onResume();
        ImmersiveMode.setImmersiveMode(getWindow());
    }
}
