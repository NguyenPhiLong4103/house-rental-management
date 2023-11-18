/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author duchi
 */
public class Token {
    private int id;
    private int user_id;
    private String code;
    private String expriedDate;
    private int type;
    public Token() {
    }

    public Token(int id, int user_id, String code, String expriedDate, int type) {
        this.id = id;
        this.user_id = user_id;
        this.code = code;
        this.expriedDate = expriedDate;
        this.type = type;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpriedDate() {
        return expriedDate;
    }

    public void setExpriedDate(String expriedDate) {
        this.expriedDate = expriedDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "Token{" + "id=" + id + ", user_id=" + user_id + ", code=" + code + ", expriedDate=" + expriedDate + ", type=" + type + '}';
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    

    
    
}
