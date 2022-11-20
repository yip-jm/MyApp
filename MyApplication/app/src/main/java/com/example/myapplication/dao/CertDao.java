package com.example.myapplication.dao;

import com.example.myapplication.entity.Cert;
import android.util.Log;

import com.example.myapplication.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class CertDao {
    private static final String TAG = "mysql-CertDao";

    public boolean register(Cert cert){
        HashMap<String, Object> map = new HashMap<>();
        Connection connection = JDBCUtils.getConn();

        try{
            String sql = "insert into Cert(certAcc, certUnit, certName, certStuid," +
                    "certTel, certMail) values (?,?,?,?,?,?)";

            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if(ps != null){
                    ps.setString(1, cert.getAcc());
                    ps.setString(2, cert.getUnit());
                    ps.setString(3, cert.getName());
                    ps.setString(4, cert.getStuid());
                    ps.setString(5, cert.getTel());
                    ps.setString(6, cert.getMail());

                    int rs = ps.executeUpdate();

                    if(rs>0)    return true;
                    else    return false;
                }else
                    return false;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "异常Cert Register：" + e.getMessage());
            return false;
        }

    }

    public Cert findCert(String acc){
        Connection connection = JDBCUtils.getConn();
        Cert cert = null;

        try{
            String sql = "select * from Cert where certAcc = ?";
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(sql);
                if(ps != null){
                    ps.setString(1, acc);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String unit = rs.getString(2);
                        String name = rs.getString(3);
                        String stuid = rs.getString(4);
                        String tel = rs.getString(5);
                        String mail = rs.getString(6);

                        cert = new Cert(acc, unit, name, stuid, tel, mail);

                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常findCert" + e.getMessage());
            return null;
        }

        return cert;
    }



}
