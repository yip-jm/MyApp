package com.example.myapplication.dao;

import android.util.Log;

import com.example.myapplication.entity.User;
import com.example.myapplication.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class UserDao {
    private static final String TAG = "mysql-UserDao";

    //    登录函数
    public int login(String acc, String pwd){
        HashMap<String, Object> map = new HashMap<>();
        Connection connection = JDBCUtils.getConn();
        int msg = 0;
        try{
            String sql = "select * from User where userAcc = ?";
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if(ps != null){
                    Log.e(TAG, "账号：" + acc);
                    ps.setString(1, acc);   //传递参数进sql语句
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()){

                        for(int i = 1; i<=2; i++){
                            String field = rs.getMetaData().getColumnName(i);
                            map.put(field, rs.getString(field));
                        }
                    }
                    connection.close();
                    ps.close();

                    if(map.size()!=0){
                        StringBuilder s  = new StringBuilder();
                        for(String key : map.keySet()){
                            if(key.equals("userPwd")){
                                if(pwd.equals(map.get(key))){
                                    msg = 1; //密码正确
                                }
                                else{
                                    msg = 2; //密码错误
                                }
                                break;
                            }
                        }
                    } else {
                        Log.e(TAG, "查询结果为空");
                        msg = 3;
                    }
                } else {
                    msg = 0;
                }
            } else {
                msg = 0;
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常login：" + e.getMessage());
            msg = 0;
        }
        return msg;
    }


    //    注册函数
    public boolean register(User user){
        HashMap<String, Object> map = new HashMap<>();

        Connection connection = JDBCUtils.getConn();

        try{
            String sql = "insert into User(userAcc, userPwd, userName, userBirth, userGender," +
                    "userHand, userES, userEQ, userTel, userCert) values (?,?,?,?,?,?,?,?,?,?)";

            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if(ps != null){

                    ps.setString(1, user.getAcc());
                    ps.setString(2, user.getPwd());
                    ps.setString(3, user.getName());
                    ps.setString(4, user.getBirth());
                    ps.setInt(5, user.getGender());
                    ps.setInt(6, user.getHand());
                    ps.setInt(7, user.getES());
                    ps.setInt(8, user.getEQ());
                    ps.setString(9, user.getTel());
                    ps.setInt(10, user.getCert());

                    int rs = ps.executeUpdate();

                    if (rs>0)   return true;
                    else        return false;
                }else
                    return false;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "异常register：" + e.getMessage());
            return false;
        }
    }


    //    查找用户是否存在
    public User findUser(String acc){
         Connection connection = JDBCUtils.getConn();
         User user = null;
         try{

             String sql = "select * from User where userAcc = ?";
             if(connection != null){

                 PreparedStatement ps = connection.prepareStatement(sql);
                 if(ps != null){
                     ps.setString(1, acc);
                     ResultSet rs = ps.executeQuery();

                     while(rs.next()){
                         String acc1 = rs.getString(1);
                         String pwd = rs.getString(2);
                         String name = rs.getString(3);
                         String birth  = rs.getString(4);
                         int gender = rs.getInt(5);
                         int hand = rs.getInt(6);
                         int es = rs.getInt(7);
                         int eq = rs.getInt(8);
                         String tel = rs.getString(9);
                         int cert = rs.getInt(10);
                         user = new User(acc1, pwd, name, gender, birth, hand, es, eq, tel, cert);

                     }
                 }
             }
         }catch (Exception e){
             e.printStackTrace();
             Log.d(TAG, "异常findUser：" + e.getMessage());
             return null;
         }
         return user;
    }


    //    修改用户信息
    public boolean updateUser(String acc, int col, String newinfo){
        Connection connection = JDBCUtils.getConn();
        try{
            String sql = "";
            if(col == 4) {
                sql = "update User set userBirth = ?  where userAcc = ?";
            }
            else if(col == 5){
                sql = "update User set userGender = ?  where userAcc = ?";
            }
            else if(col == 6){
                sql = "update User set userHand = ?  where userAcc = ?";
            }
            else if(col == 7){
                sql = "update User set userES = ?  where userAcc = ?";
            }
            else if(col == 8){
                sql = "update User set userEQ = ?  where userAcc = ?";
            }
            else if(col == 9){
                sql = "update User set userTel = ?  where userAcc = ?";
            }
            else if(col == 10){
                sql = "update User set userCert = ?  where userAcc = ?";
            }
            if(connection != null){

                PreparedStatement ps = connection.prepareStatement(sql);
                if(ps != null){
                    ps.setString(2, acc);
                    int rs = 0;
                    if(col == 4){
                        ps.setString(1, newinfo);
                        rs = ps.executeUpdate();
                    }
                    else if(col == 5){
                        ps.setInt(1, Integer.parseInt(newinfo));
                        rs = ps.executeUpdate();
                    }
                    else if(col == 6){
                        ps.setInt(1, Integer.parseInt(newinfo));
                        rs = ps.executeUpdate();
                    }
                    else if(col == 7){
                        ps.setInt(1, Integer.parseInt(newinfo));
                        rs = ps.executeUpdate();
                    }
                    else if(col == 8){
                        ps.setInt(1, Integer.parseInt(newinfo));
                        rs = ps.executeUpdate();
                    }
                    else if(col == 9){
                        ps.setString(1, newinfo);
                        rs = ps.executeUpdate();
                    }
                    else if(col == 10){
                        ps.setInt(1, Integer.parseInt(newinfo));
                        rs = ps.executeUpdate();
                    }

                    if(rs>0) return true;
                    else return false;

                }return false;
            }return false;
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "异常update：" + e.getMessage());
            return false;
        }
    }

}
