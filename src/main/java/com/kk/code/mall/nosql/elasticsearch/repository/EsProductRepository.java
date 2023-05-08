package com.kk.code.mall.nosql.elasticsearch.repository;

import com.kk.code.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.nosql.elasticsearch.repository
 * @ClassName: EsProductRepository
 * @Author: HK
 * @Description:
 * @Date: 2023/5/8 17:25
 * @Version: 1.0
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
    /**
     * 搜索查询
     *
     * @param name     商品名称
     * @param subTitle 商品标题
     * @param keywords 商品关键字
     * @param page     分页信息
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
