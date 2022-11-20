package com.example.myapplication.entity;

public class Cert {

    private String certAcc;
    private String certName;
    private String certUnit;
    private String certStuid;
    private String certTel;
    private String certMail;

    public Cert(){}

    public Cert(String acc, String unit, String name, String stuid, String tel, String mail){
        this.certAcc = acc;
        this.certName = name;
        this.certUnit = unit;
        this.certStuid = stuid;
        this.certTel = tel;
        this.certMail = mail;
    }

    public String getAcc(){     return certAcc;     }
    public void setAcc(String acc){   this.certAcc = acc;     }

    public String getUnit(){     return certUnit;     }
    public void setUnit(String unit){   this.certUnit = unit;     }

    public String getName(){     return certName;     }
    public void setName(String name){   this.certName = name;     }

    public String getStuid(){     return certStuid;     }
    public void setStuid(String stuid){   this.certStuid = stuid;     }

    public String getTel(){     return certTel;     }
    public void setTel(String tel){   this.certTel = tel;     }

    public String getMail(){     return certMail;     }
    public void setMail(String mail){   this.certMail = mail;     }

}


