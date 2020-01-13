/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Student;

/**
 *
 * @author Faysal
 */
public class Repository {
    public List<Student> GetFromDatabase() {
        DBConnect db = new DBConnect();
        List<Student> list = new ArrayList();
        Connection con = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            pstmnt = con.prepareStatement("Select * from student");
            rs=pstmnt.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setGender(rs.getString(3));
                stu.setAddress(rs.getString(4));
                list.add(stu);
                System.out.println(stu.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close(con, pstmnt, rs);
        return list;
    }
    
    
    
    public boolean addData(Student stu) {
        DBConnect db = new DBConnect();
        Connection con = null;
        PreparedStatement pstmnt = null;
        boolean flag= false;
        try {
            con = db.getConnection();
            pstmnt = con.prepareStatement("insert into student values(?,?,?,?)");
            pstmnt.setString(1,stu.getId());
            pstmnt.setString(2,stu.getName());
            pstmnt.setString(3,stu.getGender());
            pstmnt.setString(4,stu.getAddress());
            int c = 0;
            c= pstmnt.executeUpdate();
            if(c==1){
                flag= true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close(con, pstmnt, null);
        return flag;
    }
}
