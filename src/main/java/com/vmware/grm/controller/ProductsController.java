package com.vmware.grm.controller;

import com.vmware.grm.dto.Detail;
import com.vmware.grm.dto.LanguagesDto;
import com.vmware.grm.dto.ProductsDto;
import com.vmware.grm.model.Languages;
import com.vmware.grm.model.Products;
import com.vmware.grm.model.Test;
import com.vmware.grm.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:10:21 AM
 **/

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Resource
    private ProductsService productsService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Object listProducts(@RequestParam(value = "limit",required = false,defaultValue = "15")Integer limit, @RequestParam(value = "offset",required = false,defaultValue = "0")Integer offset, @RequestParam(value = "name",required = false)String name){
        List<Products> results = productsService.listProducts(limit, offset,name);
        int count = results.size();
        return new ProductsDto(count,results);
    }

    @PostMapping(value = "/")
    public Object insertProduct(@RequestBody(required = false)Products products) {
        System.out.println(products);
        Object o = productsService.insertProduct(products);
        return o;
    }

    @GetMapping("/{id}/")
    public Object getProduct(@PathVariable("id")String id,@RequestParam(value = "name",required = false) String name){
        Products product = productsService.getProduct(id);
        if(product==null||!product.getName().equals(name)){
            return new Detail("Not found.");
        }
        return product;
    }

    @PutMapping(value = "/{id}")
    public Object updateProduct(@RequestBody Products product,@PathVariable("id")String id){
        Products products = productsService.getProduct(id);
        if(products==null) return new Detail("Not found.");
        product.setId(products.getId());
        //检查code_freeze_date和release_date的格式
        //检查supported_languages不为空
        return products.getName().equals(product.getName())?productsService.updateProduct(product):new Detail("Not found.");
    }

    @PatchMapping(value = "/{id}")
    public Object partialUpdateProduct(@RequestBody Products product,@PathVariable("id")String id){
        Products products = productsService.getProduct(id);
        if(products==null) return new Detail("Not found.");
        product.setId(products.getId());
        //检查code_freeze_date和release_date的格式
        //检查supported_languages不为空
        return product.getName()==null||products.getName().equals(product.getName())?productsService.updateProduct(product):new Detail("Not found.");
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody Object deleteLanguage(@PathVariable("id")String id,@RequestParam(value = "name",required = false) String name){
        Products product = productsService.getProduct(id);
        if(product==null) return new Detail("Not found.");
        name = name==null?product.getName():name;
        return name.equals(product.getName())?productsService.deleteProduct(product.getId()):new Detail("Not found.");
    }

}