package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View v1, v2, v3, v4, v5 ,v6 ,v7, v8, v9;
    TextView tv;
    private static final int P1_CODE = 1;
    private static final int P2_CODE = 2;
    private static final int NOT_PLAYED = 0;
    private int activePlayer = P1_CODE;

    private int[] status = {NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
                            NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
                            NOT_PLAYED, NOT_PLAYED, NOT_PLAYED};


    private int[][] winningPosition = {{0,1,2},{3,4,5,},{6,7,8}
                                    ,{0,3,6}, {1,4,7}, {2,5,8}
                                    , {0,4,8}, {2,4,6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add("restart");
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                resetGame();
                return false;
            }
        });

        MenuItem item2 = menu.add("quit");
        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void init()
    {
        v1 = findViewById(R.id.view1);
        v2 = findViewById(R.id.view2);
        v3 = findViewById(R.id.view3);
        v4 = findViewById(R.id.view4);
        v5 = findViewById(R.id.view5);
        v6 = findViewById(R.id.view6);
        v7 = findViewById(R.id.view7);
        v8 = findViewById(R.id.view8);
        v9 = findViewById(R.id.view9);
        tv = findViewById(R.id.textView);

        v1.setOnClickListener(this);
        v2.setOnClickListener(this);
        v3.setOnClickListener(this);
        v4.setOnClickListener(this);
        v5.setOnClickListener(this);
        v6.setOnClickListener(this);
        v7.setOnClickListener(this);
        v8.setOnClickListener(this);
        v9.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {



        if(isEmptyCell(v))
        {
            if (activePlayer == P1_CODE) {
                v.setPadding(10,10,10,10);
                v.setBackgroundResource(R.drawable.circle2);
                changePlayer();
                tv.setText(activePlayer + "`s turn");
                status[Integer.parseInt((String) v.getTag())] = P1_CODE;
            } else
            {
                v.setPadding(10,10,10,10);
                v.setBackgroundResource(R.drawable.multiple);
                changePlayer();
                tv.setText(activePlayer + "`s turn");
                status[Integer.parseInt((String) v.getTag())] = P2_CODE;
            }
        }
        if( checkWin()) {

            changePlayer();
            tv.setText("player " + activePlayer + " Won the game");
            Toast.makeText(this, "player " + activePlayer + " won the game", Toast.LENGTH_SHORT).show();
            resetGame();
        }
        if(isFull()) {
            tv.setText("Full game");
            Toast.makeText(this, "GAME IS TIE!", Toast.LENGTH_SHORT).show();
            resetGame();
            tv.setText("game is reset");
        }
    }


    private boolean isFull()
    {
        int count = 0;
        for(int i = 0; i<status.length; i++)
        {
            if(status[i] != NOT_PLAYED)
                count++;
        }
        if(count == status.length) {
            return true;
        }
        return false;
    }
    private boolean checkWin()
    {
        for (int[] positions: winningPosition)
        {
            if ((status[positions[0]] == status[positions[1]] &&
                    status[positions[1]] == status[positions[2]] &&
                    status[positions[0]] != NOT_PLAYED))
            {
                return true;
            }
        }
        return false;
    }

    private  boolean isEmptyCell(View v)
    {
        if(status[Integer.parseInt((String) v.getTag())] == NOT_PLAYED)
            return true;
        return false;
    }

    private void changePlayer()
    {
        if(activePlayer == P1_CODE)
            activePlayer = P2_CODE;
        else
            activePlayer = P1_CODE;
    }

    public void resetGame()
    {
        for(int i=0; i<status.length; i++) {
            status[i] = NOT_PLAYED;
        }
        activePlayer = P1_CODE;
        v1.setBackgroundResource(0);
        v2.setBackgroundResource(0);
        v3.setBackgroundResource(0);
        v4.setBackgroundResource(0);
        v5.setBackgroundResource(0);
        v6.setBackgroundResource(0);
        v7.setBackgroundResource(0);
        v8.setBackgroundResource(0);
        v9.setBackgroundResource(0);
        tv.setText(null);

    }

}
