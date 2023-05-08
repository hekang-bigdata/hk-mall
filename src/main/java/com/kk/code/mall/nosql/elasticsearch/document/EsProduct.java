package com.kk.code.mall.nosql.elasticsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.nosql.elasticsearch
 * @ClassName: EsProduct
 * @Author: HK
 * @Description: 搜索中的商品信息
 * @Date: 2023/5/8 17:23
 * @Version: 1.0
 */
@Data
@Document(indexName = "pms", type = "product", shards = 1, replicas = 0)
public class EsProduct {
    private static final long serialVersionUID = -1L;
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;
    private String pic;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;

}
