package view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seniorplus.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adaptar.AdapterUserItem;
import client.ServerManager;
import util.UserItemMsg;

public class LayoutContacts extends Fragment {

    private View rootView;
    private Context context;
    private List<UserItemMsg> groupMsgList;
    private List<UserItemMsg> contactMsgList;
    private List<String> friStr;
    //private PicAndTextBtn patbBarGroup;
    private PicAndTextBtn patbBarContact;
    private RecyclerView rvGroup;
    private RecyclerView rvContact;

    private ResultSet re;
    private Connection con;
    private Statement stmt;

    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://database-mysql.c1fqlmzaunrc.us-east-2.rds.amazonaws.com:3306/seniorplus?useSSL=false";

    private final static String USER = "admin";
    private final static String PASS = "zxs19970509";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_contacts, container, false);
        this.context = inflater.getContext();
       // initGroupViews();
        initContactViews();
        return rootView;
    }

//    private void initGroupViews() {
//        patbBarGroup = (PicAndTextBtn) rootView.findViewById(R.id.patb_bar_groups);
//        rvGroup = (RecyclerView) rootView.findViewById(R.id.rv_list_groups);
//
//        groupMsgList = new ArrayList<>();
//
//        loadGroups();
//
//        AdapterUserItem adapterGroup = new AdapterUserItem(context, groupMsgList);
//        rvGroup.setLayoutManager(new LinearLayoutManager(context));
//        rvGroup.setAdapter(adapterGroup);
//
//        patbBarGroup.setOnClickListener(new PicAndTextBtn.picAndTextBtnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (rvGroup.getVisibility() == View.VISIBLE) {
//                    rvGroup.setVisibility(View.GONE);
//                    patbBarGroup.setImageView(R.drawable.shink);
//                } else {
//                    rvGroup.setVisibility(View.VISIBLE);
//                    patbBarGroup.setImageView(R.drawable.rise);
//                }
//            }
//        });
//    }
//
//    private void loadGroups() {
//        ServerManager serverManager = ServerManager.getServerManager();
//        String username = serverManager.getUsername();
//
//        List<String> groStr = ParaseData.getGroupList(context, username);
//        for (String string : groStr) {
//            UserItemMsg msg = new UserItemMsg();
//            msg.setIconID(5);
//            msg.setState("1");
//            msg.setUsername(string);
//            groupMsgList.add(msg);
//        }
//    }

    private void initContactViews() {
        patbBarContact = (PicAndTextBtn) rootView.findViewById(R.id.patb_bar__contacts);
        rvContact = (RecyclerView) rootView.findViewById(R.id.rv_list_contacts);

        contactMsgList = new ArrayList<>();

        loadFriends();

        AdapterUserItem adapterContact = new AdapterUserItem(context, contactMsgList);
        rvContact.setLayoutManager(new LinearLayoutManager(context));
        rvContact.setAdapter(adapterContact);

        patbBarContact.setOnClickListener(new PicAndTextBtn.picAndTextBtnClickListener() {
            @Override
            public void onClick(View view) {
                if (rvContact.getVisibility() == View.VISIBLE) {
                    rvContact.setVisibility(View.GONE);
                    patbBarContact.setImageView(R.drawable.shink);
                } else {
                    rvContact.setVisibility(View.VISIBLE);
                    patbBarContact.setImageView(R.drawable.rise);
                }
            }
        });
    }

    private void loadFriends() {
        ServerManager serverManager = ServerManager.getServerManager();
        String userName = serverManager.getUsername();
        friStr = new ArrayList<>();
//        List<String> friStr = ParaseData.getFriendList(context, userName);

//        friStr.add("Bob");

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
                    Log.e("AAA", "aaaaa" + (re == null));

                    //下面是获取指定用户(id=1)的好友id列表的代码
                    Set<Integer> set = new HashSet<>();
                    String sql = "SELECT u1, u2 FROM seniorplus.friendship JOIN users ON friendship.u1 = users.id";
                    re = stmt.executeQuery(sql);
                    Log.e("getid", "friends id" + (re==null) );
                    while (re.next()) {
                        if (re.getInt("u1") == 1) {
                            int num = re.getInt("u2");
                            set.add(num);
                            System.out.println(num);
                        }
                    }

                    //接着找到他们的名字
                    sql = "select * from users";
                    re = stmt.executeQuery(sql);
                    Log.e("getname", "friends name" + (re==null) );
                    while (re.next()) {
                        if (set.contains(re.getInt("id"))) {
                            String name=re.getString("username");
                            friStr.add(name);
                            System.out.println(name);
                        }
                    }
                    //put in contact
                    int i=0;
                    System.out.println(friStr);
                    for (String string : friStr) {
                        //System.out.print ("test"+ string);
                        UserItemMsg msg = new UserItemMsg();
                        msg.setUsername(string);
//            String[] str = ParaseData.getFriendProfile(context, string);
//            int i = Integer.parseInt(str[0]);
//            msg.setIconID(Integer.parseInt(str[0]));
//            msg.setSign(str[1]);
                        msg.setIconID(i%7);
                        i++;
                        contactMsgList.add(msg);
                    }

                } catch (SQLException e) {
                    Log.e("连接失败", e.toString());
                }
            }
        }.start();
    }
}