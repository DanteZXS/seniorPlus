package com.example.seniorplus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import java.sql.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import client.ServerManager;

public class SignUpActivity extends AppCompatActivity{
//    private ServerManager serverManager = ServerManager.getServerManager();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_upctivity);
//        serverManager.start();
//    }
//    private TabHost tabHost;
//
//    private Button btnLogin;
//    private EditText etLoginUsername;
//    private EditText etLoginPassword;
//
//    private Button btnRegister;
//    private EditText etRegisterUsername;
//    private EditText etRegisterPassword;
//    private EditText etInsurePassword;
//
//    private ServerManager serverManager = ServerManager.getServerManager();

    private EditText username;
    private EditText password;
    private Button register;

    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://database-mysql.c1fqlmzaunrc.us-east-2.rds.amazonaws.com:3306/seniorplus?useSSL=false";

    private final static String USER = "admin";
    private final static String PASS = "zxs19970509";

    private ResultSet re;
    private Connection con;
    private Statement stmt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_upctivity);
//        initViews();
        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);
        register = findViewById(R.id.register_btn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("AAA",username.getText().toString()+","+password.getText().toString());
                final String str = "select * from users";

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
                            Log.e("AAA","aaaaa" + (re ==null));
                            if (re != null)
                                Log.i("标记", "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
                            while (re.next())
                                Log.i("Data", re.getString("username"));

                        } catch (SQLException e) {
                            Log.e("连接失败", e.toString());
                        }
                    }
                }.start();
            }
        });
    }

//    private void initViews() {
//        tabHost = (TabHost) findViewById(R.id.tabHost);
//
//        btnLogin = (Button) findViewById(R.id.btn_login);
//        etLoginUsername = (EditText) findViewById(R.id.et_login_username);
//        etLoginPassword = (EditText) findViewById(R.id.et_login_password);
//
//        btnRegister = (Button) findViewById(R.id.btn_register);
//        etRegisterUsername = (EditText) findViewById(R.id.et_register_username);
//        etRegisterPassword = (EditText) findViewById(R.id.et_register_password);
//        etInsurePassword = (EditText) findViewById(R.id.et_insure_password);
//
//        tabHost.setup();
//        tabHost.addTab(tabHost.newTabSpec("Login").setIndicator("Login").setContent(R.id.layout_login));
//        tabHost.addTab(tabHost.newTabSpec("Register").setIndicator("Register").setContent(R.id.layout_register));
//
//        for (int i = 0; i < 2; i++) {
//            TextView tv = ((TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title));
//            tv.setAllCaps(false);
//            tv.setTextSize(16);
//        }
//
//        btnLogin.setOnClickListener(this);
//        btnRegister.setOnClickListener(this);
//        serverManager.start();
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_login: {
//                String username = etLoginUsername.getText().toString();
//                String password = etLoginPassword.getText().toString();
//                if (login(username, password)) {
//                    serverManager.setUsername(username);
//                    Intent intent = new Intent(this, ActivityChatMain.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    etLoginUsername.setText("");
//                    etLoginPassword.setText("");
//                }
//                break;
//            }
//            case R.id.btn_register: {
//                Intent intent = new Intent(this, ActivityChatMain.class);
//                startActivity(intent);
//                finish();
//                break;
//            }
//            default:
//                break;
//        }
//    }
//
//    private boolean login(String username, String password) {
//        // check username and password whether legal
//        if (username == null || username.length() > 10 || password.length() > 20) {
//            return false;
//        }
//        // send msg to servers
//        String msg = "[LOGIN]:[" + username + ", " + password + "]";
//        serverManager.sendMessage(this, msg);
//        // get msg from servers return
//        String ack = serverManager.getMessage();
//        // deal msg
//        if (ack == null) {
//            return false;
//        }
//        serverManager.setMessage(null);
//        String p = "\\[ACKLOGIN\\]:\\[(.*)\\]";
//        Pattern pattern = Pattern.compile(p);
//        Matcher matcher = pattern.matcher(ack);
//        return matcher.find() && matcher.group(1).equals("1");
//    }
}
