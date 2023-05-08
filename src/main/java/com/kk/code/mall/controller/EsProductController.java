package com.kk.code.mall.controller;

import com.kk.code.mall.common.api.DataResult;
import com.kk.code.mall.nosql.elasticsearch.document.EsProduct;
import com.kk.code.mall.nosql.elasticsearch.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.controller
 * @ClassName: EsProductController
 * @Author: HK
 * @Description:
 * @Date: 2023/5/8 17:30
 * @Version: 1.0
 */
@RestController
@Api(tags = "EsProductController", description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    public DataResult<Integer> importAllList() {
        int count = esProductService.importAll();
        return DataResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public DataResult<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return DataResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public DataResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return DataResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseBody
    public DataResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return DataResult.success(esProduct);
        } else {
            return DataResult.error();
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public DataResult<Page<EsProduct>> search(@RequestParam(required = false) String keyword,
                                              @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                              @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return DataResult.success(esProductPage);
    }

}
