package com.freestar.android.examples.example1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.freestar.android.sdk.model.FreestarAdModel;
import com.freestar.android.sdk.model.FreestarViewInjector;

import org.prebid.fs.mobile.domain.CustomTargetingEntry;
import org.prebid.mobile.TargetingParams;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String AD_PLACEMENT = "adPlacement1";

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private boolean playAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TargetingParams.setYearOfBirth(1965);
        TargetingParams.setGender(TargetingParams.GENDER.MALE);

        FreestarAdModel.getInstance(this);

        String placementName = FreestarAdModel.getInstance(this).getProperty(AD_PLACEMENT);
        if (placementName != null) {
            playAds = true;
        } else {
            System.err.println("missing placement for article detail, check configuration properties");
            playAds = false;
        }

        if (playAds) {
            ViewGroup adView = findViewById(R.id.ads_layout);
            List<CustomTargetingEntry> customTargets = new CustomTargetingEntry.ListBuilder()
                    .addCustomTargeting("custom1", "value2")
                    .addCustomTargeting("custom2", "value1")
                    .build();
            FreestarViewInjector injector = FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main);
            injector.injectBannerAd(adView, "ads_layout", placementName, customTargets);
        }

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setText("");
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    field[i][j] = buttons[i][j].getText().toString();
                } catch(Exception ex) {
                    field[i][j] = "";
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playAds) {
            FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main).resumeAd();
        }
    }

    @Override
    protected void onPause() {
        if (playAds) {
            FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main).pauseAd();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (playAds) {
            FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main).destroyAd();
            FreestarAdModel.releaseInstance(this);
        }
        super.onDestroy();
    }

}
