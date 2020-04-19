package com.example.seniorplus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private EditText usernameEt;
    private EditText passwordEt;
    private Button registerBtn;

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
        usernameEt = findViewById(R.id.register_username);
        passwordEt = findViewById(R.id.register_password);
        registerBtn = findViewById(R.id.register_btn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("AAA",usernameEt.getText().toString()+","+passwordEt.getText().toString());
                final String username = usernameEt.getText().toString();
                final String password = passwordEt.getText().toString();
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
                            while (re.next()){
                                Log.i("Data", re.getString("username"));
                            }

                            String sql = "INSERT INTO users (username,password) " +
                                    "VALUES(" + "'"+username+"'" +", "+"'"+password+"'"+")";
                            stmt.executeUpdate(sql);

//                            //下面是获取指定用户(id=1)的好友id列表的代码
//                            Set<Integer> set = new HashSet<>();
//                            sql = "SELECT u1, u2 FROM seniorplus.friendship JOIN users ON friendship.u1 = users.id";
//                            re = stmt.executeQuery(sql);
//                            while(re.next()){
//                                if (re.getInt("u1")==1){
//                                    set.add(re.getInt("u2"));
//                                }
//                            }
//
//                            //接着找到他们的名字
//                            List<String> names = new ArrayList<>();
//                            sql = "select * from users";
//                            re = stmt.executeQuery(sql);
//                            while(re.next()){
//                                if (set.contains(re.getInt("id"))){
//                                    names.add(re.getString("username"));
//                                }
//                            }
//
//                            //查看好友列表
//                            for (int i = 0; i < names.size(); i++){
//                                Log.v("friends",names.get(i));
//                            }


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