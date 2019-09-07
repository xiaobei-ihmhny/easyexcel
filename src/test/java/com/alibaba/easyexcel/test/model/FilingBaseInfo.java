package com.alibaba.easyexcel.test.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaobei-ihmhny
 * @date 2019-09-07 07:00:00
 */
public class FilingBaseInfo {

    private static final long serialVersionUID = -7990688527141140139L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 平台id
     */
    private Long platformId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 报备人姓名
     */
    private String registrarName;

    /**
     * 报备人电话
     */
    private String registrarPhone;

    /**
     * 点单平台id
     */
    private Long purchasePlatformId;

    /**
     * 点单平台名称
     */
    private String purchasePlatformName;

    /**
     * 采购单位（全称）
     */
    private String purchaseCompany;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货地址省份代码
     */
    private String deliveryProvinceCode;

    /**
     * 收货地址省份名称
     */
    private String deliveryProvinceName;

    /**
     * 收货地址市区代码
     */
    private String deliveryCityCode;

    /**
     * 收货地址市区名称
     */
    private String deliveryCityName;

    /**
     * 收货地址区/县代码
     */
    private String deliveryDistrictCode;

    /**
     * 收货地址区/县名称
     */
    private String deliveryDistrictName;

    /**
     * 收货完整地址
     */
    private String deliveryFullAddress;

    /**
     * 项目报备开始时间
     */
    private Date registrarStartTime;

    /**
     * 项目报备结束时间
     */
    private Date registrarEndTime;

    /**
     * 报备商品总数
     */
    private Long goodsNums;

    /**
     * 挂网价（销售价）总计
     */
    private BigDecimal totalSalesPrice;

    /**
     * 成交价总计
     */
    private BigDecimal totalFinalPrice;

    /**
     * 报备状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 报备编号
     */
    private Long filingNumber;

    public Long getFilingNumber() { return filingNumber; }

    public void setFilingNumber(Long filingNumber) { this.filingNumber = filingNumber; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRegistrarName() {
        return registrarName;
    }

    public void setRegistrarName(String registrarName) {
        this.registrarName = registrarName;
    }

    public String getRegistrarPhone() {
        return registrarPhone;
    }

    public void setRegistrarPhone(String registrarPhone) {
        this.registrarPhone = registrarPhone;
    }

    public Long getPurchasePlatformId() {
        return purchasePlatformId;
    }

    public void setPurchasePlatformId(Long purchasePlatformId) {
        this.purchasePlatformId = purchasePlatformId;
    }

    public String getPurchasePlatformName() {
        return purchasePlatformName;
    }

    public void setPurchasePlatformName(String purchasePlatformName) {
        this.purchasePlatformName = purchasePlatformName;
    }

    public String getPurchaseCompany() {
        return purchaseCompany;
    }

    public void setPurchaseCompany(String purchaseCompany) {
        this.purchaseCompany = purchaseCompany;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getDeliveryProvinceCode() {
        return deliveryProvinceCode;
    }

    public void setDeliveryProvinceCode(String deliveryProvinceCode) {
        this.deliveryProvinceCode = deliveryProvinceCode;
    }

    public String getDeliveryProvinceName() {
        return deliveryProvinceName;
    }

    public void setDeliveryProvinceName(String deliveryProvinceName) {
        this.deliveryProvinceName = deliveryProvinceName;
    }

    public String getDeliveryCityCode() {
        return deliveryCityCode;
    }

    public void setDeliveryCityCode(String deliveryCityCode) {
        this.deliveryCityCode = deliveryCityCode;
    }

    public String getDeliveryCityName() {
        return deliveryCityName;
    }

    public void setDeliveryCityName(String deliveryCityName) {
        this.deliveryCityName = deliveryCityName;
    }

    public String getDeliveryDistrictCode() {
        return deliveryDistrictCode;
    }

    public void setDeliveryDistrictCode(String deliveryDistrictCode) {
        this.deliveryDistrictCode = deliveryDistrictCode;
    }

    public String getDeliveryDistrictName() {
        return deliveryDistrictName;
    }

    public void setDeliveryDistrictName(String deliveryDistrictName) {
        this.deliveryDistrictName = deliveryDistrictName;
    }

    public String getDeliveryFullAddress() {
        return deliveryFullAddress;
    }

    public void setDeliveryFullAddress(String deliveryFullAddress) {
        this.deliveryFullAddress = deliveryFullAddress;
    }

    public Date getRegistrarStartTime() {
        return registrarStartTime;
    }

    public void setRegistrarStartTime(Date registrarStartTime) {
        this.registrarStartTime = registrarStartTime;
    }

    public Date getRegistrarEndTime() {
        return registrarEndTime;
    }

    public void setRegistrarEndTime(Date registrarEndTime) {
        this.registrarEndTime = registrarEndTime;
    }

    public Long getGoodsNums() {
        return goodsNums;
    }

    public void setGoodsNums(Long goodsNums) {
        this.goodsNums = goodsNums;
    }

    public BigDecimal getTotalSalesPrice() {
        return totalSalesPrice;
    }

    public void setTotalSalesPrice(BigDecimal totalSalesPrice) {
        this.totalSalesPrice = totalSalesPrice;
    }

    public BigDecimal getTotalFinalPrice() {
        return totalFinalPrice;
    }

    public void setTotalFinalPrice(BigDecimal totalFinalPrice) {
        this.totalFinalPrice = totalFinalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FilingBaseInfo{" +
                "id=" + id +
                ", platformId=" + platformId +
                ", shopId=" + shopId +
                ", registrarName='" + registrarName + '\'' +
                ", registrarPhone='" + registrarPhone + '\'' +
                ", purchasePlatformId=" + purchasePlatformId +
                ", purchasePlatformName=" + purchasePlatformName +
                ", purchaseCompany='" + purchaseCompany + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", deliveryProvinceCode='" + deliveryProvinceCode + '\'' +
                ", deliveryProvinceName='" + deliveryProvinceName + '\'' +
                ", deliveryCityCode='" + deliveryCityCode + '\'' +
                ", deliveryCityName='" + deliveryCityName + '\'' +
                ", deliveryDistrictCode='" + deliveryDistrictCode + '\'' +
                ", deliveryDistrictName='" + deliveryDistrictName + '\'' +
                ", deliveryFullAddress='" + deliveryFullAddress + '\'' +
                ", registrarStartTime=" + registrarStartTime +
                ", registrarEndTime=" + registrarEndTime +
                ", goodsNums=" + goodsNums +
                ", totalSalesPrice=" + totalSalesPrice +
                ", totalFinalPrice=" + totalFinalPrice +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", filingNumber=" + filingNumber +
                '}';
    }
}