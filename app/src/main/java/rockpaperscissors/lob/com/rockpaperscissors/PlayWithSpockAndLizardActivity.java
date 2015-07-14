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

public class PlayWithSpockAndLizardActivity extends Activity {

    private TextView scoreTextView;
    private ImageView opponentImageView;
    private ImageView yourChoiceImageView;
    private TextView opponentTextView;
    private TextView youTextView;
    private ImageButton rockImageButton;
    private ImageButton paperImageButton;
    private ImageButton scissorsImageButton;
    private ImageButton lizardImageButton;
    private ImageButton spockImageButton;

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
        setContentView(R.layout.activity_play_with_spock_and_lizard);

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

        youlost = getStringFromResource(R.string.youlost);
        youwon = getStringFromResource(R.string.youwon);
        keepplaying = getStringFromResource(R.string.keepplaying);
        stopplaying = getStringFromResource(R.string.stopplaying);
        draw = getStringFromResource(R.string.draw);
        goodjob = getStringFromResource(R.string.goodjob);
        youlostthematch = getStringFromResource(R.string.youlostthematch);
        youwonthematch = getStringFromResource(R.string.youwonthematch);
        ohno = getStringFromResource(R.string.ohno);

        rockImageButton = (ImageButton) findViewById(R.id.imageButton);
        paperImageButton = (ImageButton) findViewById(R.id.imageButton2);
        scissorsImageButton = (ImageButton) findViewById(R.id.imageButton3);
        lizardImageButton = (ImageButton) findViewById(R.id.imageButton4);
        spockImageButton = (ImageButton) findViewById(R.id.imageButton5);

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
                int random = r.nextInt((MAX - MIN) + 1) + MIN;
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
                    case 4:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.lizarddown);
                        sendScore(LOSE);
                        break;
                    case 5:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.spockdown);
                        sendScore(LOSE);
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
                int random = r.nextInt((MAX - MIN) + 1) + MIN;
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
                    case 4:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.lizarddown);
                        sendScore(LOSE);
                        break;
                    case 5:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.spockdown);
                        sendScore(WIN);
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
                int random = r.nextInt((MAX - MIN) + 1) + MIN;
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
                    case 4:
                        opponentImageView.setVisibility(View.VISIBLE);
                        sendScore(WIN);
                        opponentImageView.setImageResource(R.drawable.lizarddown);
                        break;
                    case 5:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.spockdown);
                        sendScore(LOSE);
                        break;
                }
            }
        });
        lizardImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourChoiceImageView.setImageResource(R.drawable.lizard);
                yourChoiceImageView.setVisibility(View.VISIBLE);
                if (mustRefresh == 2) {
                    opponentImageView.setVisibility(View.GONE);
                    mustRefresh = 0;
                } else {
                    opponentImageView.setVisibility(View.VISIBLE);
                }
                Random r = new Random();
                int random = r.nextInt((MAX - MIN) + 1) + MIN;
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
                        sendScore(LOSE);
                        break;
                    case 4:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.lizarddown);
                        sendScore(DRAW);
                        break;
                    case 5:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.spockdown);
                        sendScore(WIN);
                        break;
                }
            }
        });
        spockImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourChoiceImageView.setImageResource(R.drawable.spock);
                yourChoiceImageView.setVisibility(View.VISIBLE);
                if (mustRefresh == 2) {
                    opponentImageView.setVisibility(View.GONE);
                    mustRefresh = 0;
                } else {
                    opponentImageView.setVisibility(View.VISIBLE);
                }
                Random r = new Random();
                int random = r.nextInt((MAX - MIN) + 1) + MIN;
                switch (random) {
                    case 1:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.rockdown);
                        sendScore(WIN);
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
                    case 4:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.lizarddown);
                        sendScore(LOSE);
                        break;
                    case 5:
                        opponentImageView.setVisibility(View.VISIBLE);
                        opponentImageView.setImageResource(R.drawable.spockdown);
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

    private String getStringFromResource(int res) {
        return getResources().getString(res);
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
        ImmersiveMode.setImmersiveMode(window);
    }
}