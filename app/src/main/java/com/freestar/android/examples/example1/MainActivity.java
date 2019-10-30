package com.freestar.android.examples.example1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.freestar.android.sdk.adslot.DfpAdSlot;
import com.freestar.android.sdk.adslot.FreestarAdSlot;
import com.freestar.android.sdk.model.FreestarAdModel;
import com.freestar.android.sdk.model.FreestarInteraction;
import com.freestar.android.sdk.model.FreestarViewInjector;
import com.freestar.android.sdk.model.InjectorProperties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final boolean interactionSuccess = new FreestarInteraction.Builder()
            .addDisplayOption("<html><body><h2 style='color:red;font-size:40px'>Your patronage helps us better support you</h2></body></html>")
            .addDisplayOption("<html><body><h2 style='color:red;font-size:40px'>Please support our sponsors</h2></body></html>")
            .addDisplayOption("<html><body><h2 style='color:red;font-size:40px'>Ads support this site</h2></body></html>")
            .addDisplayOption("<html><body><h2 style='color:green;font-size:40px'>Ads powered by Freestar</h2></body></html>")
            .addClickSupportOption("\"<html><body><h1 style='color:orange;font-size:40px'>Thank-you for your support</h1></body></html>\"")
            .load();

    private static FreestarAdSlot adSlot = new FreestarAdSlot.Builder()
            .setPlacementId("freestar_androidapp_320x50_ATF")
                .addCustomTarget("custom1", "value2")
                .addCustomTarget("custom2", "value1")
                .setAutoRefreshSeconds(38)
                .build();
    private static FreestarAdSlot adSlotA = new FreestarAdSlot.Builder()
            .setPlacementId("freestar_androidapp_300x250_InContent")
                .addCustomTarget("custom1", "value2")
                .addCustomTarget("custom2", "value1")
                .setAutoRefreshSeconds(38)
                .build();

    private static DfpAdSlot adSlotB = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_320x50_ATF")
            .addSize(320, 50)
            .addCustomTarget("custom1", "value2")
            .addCustomTarget("custom2", "value1")
            .setAutoRefreshSeconds(30)
            .build();

    private static DfpAdSlot adSlotC = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_300x250_InContent")
            .addSize(300, 250)
            .addCustomTarget("custom1", "value2")
            .addCustomTarget("custom2", "value1")
            .setAutoRefreshSeconds(30)
            .build();


    /*
    private static DfpAdSlot adSlot = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_300x250_InContent")
            .addSize(300, 250)
            .addCustomTarget("custom1", "value2")
            .addCustomTarget("custom2", "value1")
            .setAutoRefreshSeconds(36)
            .build();
    */

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FreestarAdModel.getInstance(this);
        setContentView(R.layout.activity_main);

        ViewGroup adView = findViewById(R.id.ad_container);
        FreestarViewInjector injector = FreestarAdModel.getInstance(this).lookupViewInjector(adSlot);
        injector.injectBannerAd(
                adView,
                new InjectorProperties.Builder().build(),
                adSlot);

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
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlot).resumeAd();
    }

    @Override
    protected void onPause() {
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlot).pauseAd();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlot).destroyAd();
        FreestarAdModel.releaseInstance(this);
        super.onDestroy();
    }

}
