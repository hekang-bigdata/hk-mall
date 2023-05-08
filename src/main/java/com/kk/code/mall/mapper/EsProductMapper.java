package com.kk.code.mall.mapper;

import com.kk.code.mall.nosql.elasticsearch.document.EsProduct;

import java.util.List;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.mapper
 * @ClassName: EsProductMapper
 * @Author: HK
 * @Description:
 * @Date: 2023/5/8 17:37
 * @Version: 1.0
 */
public interface EsProductMapper {
    List<EsProduct> getAllEsProductList(Long id);
}
