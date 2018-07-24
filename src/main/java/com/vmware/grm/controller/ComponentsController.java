package com.vmware.grm.controller;

import com.vmware.grm.dto.*;
import com.vmware.grm.model.Components;
import com.vmware.grm.model.Products;
import com.vmware.grm.service.ComponentsService;
import com.vmware.grm.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/18/2018
 * Time:10:25 AM
 **/
@Api(value = "成份管理",tags = {"成份管理"},description = "描述信息")
@RestController
@RequestMapping("/api/components")
public class ComponentsController {

    @Resource
    private ComponentsService componentsService;

    @Resource
    private ProductsService productsService;

    @ApiOperation(value = "列出所有成分信息",notes = "",produces = "application/json")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Object listComponents(@RequestParam(value = "limit",required = false,defaultValue = "15")Integer limit, @RequestParam(value = "offset",required = false,defaultValue = "0")Integer offset, @RequestParam(value = "product__id",required = false)String product_id, @RequestParam(value = "search",required = false)String search){
        List<Components> results = componentsService.listComponents(limit,offset);
        ComponentsLanguagesResult componentsLanguagesResult = new ComponentsLanguagesResult(0, new ArrayList<ComponentsLanguages>());
        if(product_id==null&&search==null){
           results.forEach(e->{componentsLanguagesResult.getResults().add(new ComponentsLanguages(e,componentsService.listLanguageNamesById(e.getProduct_id()),productsService.getProduct(e.getProduct_id())));});
           componentsLanguagesResult.setCount(componentsLanguagesResult.getResults().size());
           return componentsLanguagesResult;
        }
        if(product_id!=null&&search==null){
            for(Components components:results){
                boolean equals = components.getProduct_id().equals(product_id);
                if(equals){
                    componentsLanguagesResult.getResults().add(new ComponentsLanguages(components,componentsService.listLanguageNamesById(components.getProduct_id()),productsService.getProduct(components.getProduct_id())));
                    componentsLanguagesResult.setCount(componentsLanguagesResult.getResults().size());
                    return componentsLanguagesResult;
                }
            }
            return new String("{\"product__id\": [\"Enter a valid UUID.\"]}");
        }
        //not write search function
        if(product_id==null&&search!=null){

        }
        if(product_id!=null&&search!=null){

        }
        System.out.println(results);
        return results;
    }

    @GetMapping("/{id}/")
    public Object getComponent(@PathVariable("id")String id, @RequestParam(value = "product__id",required = false) String product_id, @RequestParam(value = "search",required = false)String search){
        Components components = componentsService.getComponents(id);
        System.out.println(components);
        //not add search judge
        if(components==null) return new Detail("Not found");
        if(product_id==null||product_id.equals(components.getProduct_id())) return new ComponentsLanguages(components,componentsService.listLanguageNamesById(components.getProduct_id()),productsService.getProduct(components.getProduct_id()));
        else return new String("{\"product__id\":[\"Enter a valid UUID.\"]}");
    }

    @PostMapping(value = "/")
    public Object insertComponents(@RequestBody(required = false) Components components){
        System.out.println(components);
        String product_id = components.getProduct_id();
        Products product = productsService.getProduct(product_id);
        if(product==null) return new String("{\"Product\": [\"Enter a valid UUID.\"]}");
        Components component = componentsService.insertLanguage(components);
        return new ComponentsLanguages(component,componentsService.listLanguageNamesById(component.getProduct_id()),productsService.getProduct(component.getProduct_id()));
    }

    @PutMapping(value = "/{id}")
    public Object updateComponent(@RequestBody Components components,@PathVariable("id")String id){
        Components component = componentsService.getComponents(id);
        if(component==null) return new Detail("Not found.");
        Products product = productsService.getProduct(components.getProduct_id());
        if(product==null) return new String("{\"Product\": [\"Enter a valid UUID.\"]}");
        components.setId(id);
        components.setBranch_name(components.getBranch_name()==null?component.getBranch_name():components.getBranch_name());
        components.setL10n_definition_file(components.getL10n_definition_file()==null?component.getL10n_definition_file():components.getL10n_definition_file());
        components.setScm_path(components.getScm_path()==null?component.getScm_path():components.getScm_path());
        components.setL10n_mode(components.getL10n_mode()==null?component.getL10n_mode():components.getL10n_mode());
        components.setStatus(components.getStatus()==null?component.getStatus():components.getStatus());
        //not add search
        int update = componentsService.updateComponent(components);
        if(update==1){
            component = componentsService.getComponents(id);
            return new ComponentsLanguages(component,componentsService.listLanguageNamesById(component.getProduct_id()),productsService.getProduct(component.getProduct_id()));
        }else return "error";
    }

    @PatchMapping(value = "/{id}")
    public Object partialUpdateComponent(@RequestBody Components components,@PathVariable("id")String id){
        Components component = componentsService.getComponents(id);
        if(component==null) return new Detail("Not found.");
        components.setId(id);
        components.setName(components.getName()==null?component.getName():components.getName());
        components.setBranch_name(components.getBranch_name()==null?component.getBranch_name():components.getBranch_name());
        components.setL10n_definition_file(components.getL10n_definition_file()==null?component.getL10n_definition_file():components.getL10n_definition_file());
        components.setScm_path(components.getScm_path()==null?component.getScm_path():components.getScm_path());
        components.setL10n_mode(components.getL10n_mode()==null?component.getL10n_mode():components.getL10n_mode());
        components.setStatus(components.getStatus()==null?component.getStatus():components.getStatus());
        components.setProduct_id(components.getProduct_id()==null?component.getProduct_id():components.getProduct_id());
        Products product = productsService.getProduct(components.getProduct_id());
        if(product==null) return new String("{\"Product\": [\"Enter a valid UUID.\"]}");
        //not add search
        int update = componentsService.updateComponent(components);
        if(update==1){
            component = componentsService.getComponents(id);
            return new ComponentsLanguages(component,componentsService.listLanguageNamesById(component.getProduct_id()),productsService.getProduct(component.getProduct_id()));
        }else return "error";

    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody Object deleteComponent(@PathVariable("id")String id,@RequestParam(value = "product__id",required = false) String product_id){
        Components component = componentsService.getComponents(id);
        if(component==null) return new Detail("Not found.");
        product_id = product_id==null?component.getProduct_id():product_id;
        if(!component.getProduct_id().equals(product_id)) return new String("{\"Product\": [\"Enter a valid UUID.\"]}");
        int update = componentsService.deleteComponent(id);
        return update==1?"success":"error";
    }
}
