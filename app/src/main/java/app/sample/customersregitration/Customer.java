package app.sample.customersregitration;

/**
 * Created by Geoffrey Koros on 5/22/2017.
 */

public class Customer {

    private String user_id;
    private String firstname;
    private String othername;
    private String address;
    private String customid;
    private String nationid;
    private String mobile;

    public Customer(String user_id, String firstname, String othername, String address, String customid, String nationid, String mobile){
        this.user_id = user_id;
        this.firstname = firstname;
        this.othername = othername;
        this.address = address;
        this.customid = customid;
        this.nationid = nationid;
        this.mobile = mobile;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomid() {
        return customid;
    }

    public void setCustomid(String customid) {
        this.customid = customid;
    }

    public String getNationid() {
        return nationid;
    }

    public void setNationid(String nationid) {
        this.nationid = nationid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
