package com.lwxf.industry4.webapp.domain.dto.dealer;

import com.lwxf.industry4.webapp.domain.dto.product.ProductDto;
import com.lwxf.industry4.webapp.domain.entity.product.Product;

public class WxDealerInfoDto {
    public Integer dingdanxinzeng;
    public Integer dingdanyouxiao;
    public Integer dingdanwancheng;
    public Integer fankuixinzeng;
    public Integer buliaoxinzeng;
    public Integer wancheng;
    public ProductDto product;

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Integer getDingdanxinzeng() {
        return dingdanxinzeng;
    }

    public void setDingdanxinzeng(Integer dingdanxinzeng) {
        this.dingdanxinzeng = dingdanxinzeng;
    }

    public Integer getDingdanyouxiao() {
        return dingdanyouxiao;
    }

    public void setDingdanyouxiao(Integer dingdanyouxiao) {
        this.dingdanyouxiao = dingdanyouxiao;
    }

    public Integer getDingdanwancheng() {
        return dingdanwancheng;
    }

    public void setDingdanwancheng(Integer dingdanwancheng) {
        this.dingdanwancheng = dingdanwancheng;
    }

    public Integer getFankuixinzeng() {
        return fankuixinzeng;
    }

    public void setFankuixinzeng(Integer fankuixinzeng) {
        this.fankuixinzeng = fankuixinzeng;
    }

    public Integer getBuliaoxinzeng() {
        return buliaoxinzeng;
    }

    public void setBuliaoxinzeng(Integer buliaoxinzeng) {
        this.buliaoxinzeng = buliaoxinzeng;
    }

    public Integer getWancheng() {
        return wancheng;
    }

    public void setWancheng(Integer wancheng) {
        this.wancheng = wancheng;
    }
}
