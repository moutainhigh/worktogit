package com.lwxf.industry4.webapp.common.crawlerUtil;

/**
 * @author lyh on 2019/12/2
 */
public class Product {

    private String pid;
    private String title;
    private String brand;
    private String pname;
    private String price;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", title='" + title + '\'' +
                ", brand='" + brand + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args){
        String str="//img12.360buyimg.com/n7/jfs/t1/21435/26/10433/617700/5c86205dE834dfeed/9f1bb148fba7d232.jpg";
        int i = str.indexOf("//");
        String substring ="";
        if(i==0){
            substring = str.substring(2);
            System.out.println(substring);
        }

    }
}
