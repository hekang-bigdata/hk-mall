package com.kk.code.mall.nosql.elasticsearch.impl;

import com.kk.code.mall.mapper.EsProductMapper;
import com.kk.code.mall.nosql.elasticsearch.document.EsProduct;
import com.kk.code.mall.nosql.elasticsearch.repository.EsProductRepository;
import com.kk.code.mall.nosql.elasticsearch.service.EsProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.nosql.elasticsearch.impl
 * @ClassName: EsProductServiceImpl
 * @Author: HK
 * @Description:
 * @Date: 2023/5/8 17:28
 * @Version: 1.0
 */
@Log4j2
@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    private EsProductMapper esProductMapper;
    @Autowired
    private EsProductRepository productRepository;


    @Override
    public int importAll() {
        List<EsProduct> esProductList = esProductMapper.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = esProductMapper.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = productRepository.save(esProduct);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
