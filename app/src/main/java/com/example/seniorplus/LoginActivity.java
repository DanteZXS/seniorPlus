package com.example.seniorplus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import client.ServerManager;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBtn;

    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://database-mysql.c1fqlmzaunrc.us-east-2.rds.amazonaws.com:3306/seniorplus?useSSL=false";

    private final static String USER = "admin";
    private final static String PASS = "zxs19970509";

    private ResultSet re;
    private Connection con;
    private Statement stmt;
    private ServerManager server=ServerManager.getServerManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt = findViewById(R.id.login_username);
        passwordEt = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        server.start();



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = usernameEt.getText().toString();
                final String password = passwordEt.getText().toString();
                final String str = "select * from users";
                final Map<String,String> map = new HashMap<>();

                new Thread() {
                    public void run() {
                        try {
                            Class.forName(JDBC_DRIVER);
                        } catch (ClassNotFoundException e) {
                            Log.e("驱动加载失败", e.toString());
                        }
                        try {
                            con = DriverManager.getConnection(DB_URL, USER, PASS);
                            stmt = con.createStatement();
                            re = stmt.executeQuery(str);
                            if (re != null)
                                Log.i("标记", "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
                            while (re.next()){
                                Log.i("Data", re.getString("username"));
                                map.put(re.getString("username"),re.getString("password"));
                            }

                            if(!map.containsKey(username) || !map.get(username).equals(password)){
                                //Toast.makeText(LoginActivity.this,"Wrong UserName or Password",Toast.LENGTH_SHORT).show();
                            }else{
                                server.setUsername(username);
                                Intent intent = new Intent(LoginActivity.this, ActivityChatMain.class);
                                startActivity(intent);
                            }


                        } catch (SQLException e) {
                            Log.e("连接失败", e.toString());
                        }
                    }
                }.start();
            }
        });
    }
}