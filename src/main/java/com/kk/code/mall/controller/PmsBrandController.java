package com.kk.code.mall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.code.mall.common.api.DataResult;
import com.kk.code.mall.entity.PmsBrand;
import com.kk.code.mall.service.IPmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author hk
 * @since 2023-05-05
 */
@RestController
@Log4j2
@RequestMapping("/brand")
@Api(tags = "PmsBrandController", description = "品牌管理")
public class PmsBrandController {

    @Autowired
    private IPmsBrandService pmsBrandService;

    @ApiOperation("获取所有品牌列表")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public DataResult<List<PmsBrand>> getBrandList() {
        return DataResult.success(pmsBrandService.list(null));
    }

    @ApiOperation("添加品牌")
    @PreAuthorize("hasAuthority('pms:brand:create')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public DataResult createBrand(@RequestBody PmsBrand pmsBrand) {

        if (pmsBrandService.save(pmsBrand)) {
            return DataResult.success();

        } else {
            return DataResult.error();
        }
    }


    @ApiOperation("更新指定id品牌信息")
    @PreAuthorize("hasAuthority('pms:brand:update')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public DataResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {

        pmsBrandDto.setId(id);
        if (pmsBrandService.updateById(pmsBrandDto)) {
            return DataResult.success();
        } else {
            return DataResult.error();
        }

    }

    @ApiOperation("删除指定id的品牌")
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public DataResult deleteBrand(@PathVariable("id") Long id) {

        if (pmsBrandService.removeById(id)) {
            return DataResult.success();
        } else {
            return DataResult.error();
        }
    }


    @ApiOperation("分页查询品牌列表")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public DataResult listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {

        return DataResult.success(pmsBrandService.page(new Page(pageNum, pageSize), null));
    }

    @ApiOperation("获取指定id的品牌详情")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DataResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return DataResult.success(pmsBrandService.getById(id));
    }
}

