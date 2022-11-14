package com.example.myapplication.entity;

public class User {
    private String userAcc;
    private String userPwd;
    private String userName;
    private int userGender;
    private String userBirth;
    private int userHand;
    private int userES;
    private int userEQ;
    private String userTel;

    public User(){
    }

    public User(String acc, String pwd, String name, int gender, String birth,
                int hand, int es, int eq, String tel){
        this.userAcc = acc;
        this.userPwd = pwd;
        this.userName = name;
        this.userGender = gender;
        this.userHand = hand;
        this.userBirth = birth;
        this.userES = es;
        this.userEQ = eq;
        this.userTel = tel;
    }

    public String getAcc(){     return userAcc;     }
    public void setAcc(String acc){     this.userAcc = acc;     }

    public String getPwd(){     return userPwd;     }
    public void setPwd(String pwd){     this.userPwd = pwd;     }

    public String getName(){    return userName;    }
    public void setName(String name){   this.userName = name;  }

    public int getGender(){     return userGender;  }
    public void setGender(int gender){  this.userGender = gender;   }

    public String getBirth() {  return this.userBirth;  }
    public void setBirth(String birth){     this.userBirth = birth;  }

    public int getHand(){   return this.userHand;   }
    public void setHand(int hand){      this.userHand = hand;   }

    public int getES(){   return this.userES;   }
    public void setES(int es){      this.userES = es;   }

    public int getEQ(){   return this.userEQ;   }
    public void setEQ(int eq){      this.userEQ = eq;   }

    public String getTel(){     return this.userTel;    }
    public void setTel(String tel){     this.userTel = tel;  }


}

