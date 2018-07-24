package com.vmware.grm.controller;

import com.vmware.grm.dto.CreateError;
import com.vmware.grm.dto.Detail;
import com.vmware.grm.dto.LanguagesDto;
import com.vmware.grm.model.Languages;
import com.vmware.grm.service.LanguagesService;
import com.vmware.grm.service.implement.LanguagesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Author:fxing@vmware.com
 * Date:7/10/2018
 * Time:2:31 PM
 **/
@Api(value = "语言管理",tags = {"语言管理"},description = "描述信息")
@RestController
@RequestMapping("/api/languages")
public class LanguagesController {

    @Resource
    private LanguagesService languagesService;

    @ApiOperation(value = "列出所有语言",notes = "",produces = "application/json")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public LanguagesDto listLanguages(@RequestParam(value = "limit",required = false,defaultValue = "15")Integer limit,@RequestParam(value = "offset",required = false,defaultValue = "0")Integer offset,@RequestParam(value = "name",required = false)String name,@RequestParam(value = "code",required = false)String code){
        List<Languages> results = languagesService.listLanguages(limit, offset,name ,code);
        int count = languagesService.countLanguages(name,code);
        return new LanguagesDto(count,results);
    }

    @GetMapping("/{id}/")
    public Object getLanguage(@PathVariable("id")String id,@RequestParam(value = "name",required = false) String name,@RequestParam(value = "code",required = false)String code){
        Languages languages = languagesService.getLanguage(id);
        if(languages==null) return new Detail("Not found");
        name = name == null ? languages.getName():name;
        code = code == null ? languages.getCode():code;
        return name.equals(languages.getName())&&code.equals(languages.getCode())?languages:new Detail("Not found");
    }

    @PostMapping(value = "/")
    public Object insertLanguage(@RequestBody(required = false) Languages language){
        System.out.println(language);
        Languages l2 = languagesService.getLanguageByName(language.getName());
        Languages l3 = languagesService.getLanguageByCode(language.getCode());
        return l2==null&&l3==null?languagesService.insertLanguage(language):(l2!=null&&l3!=null?new CreateError("language with this Name already exists.","language with this Code already exists."):(l2==null?new CreateError().setCode("language with this Code already exists."):new CreateError().setName("language with this Name already exists.")));
    }

    @PutMapping(value = "/{id}")
    public Object updateLanguage(@RequestBody Languages languages,@PathVariable("id")String id){
        Languages lan = languagesService.getLanguage(id);
        if(lan==null) return new Detail("Not found.");
        languages.setId(lan.getId());
        return lan.getName().equals(languages.getName())&&lan.getCode().equals(languages.getCode())?languagesService.updateLanguage(languages,lan):new Detail("Not found.");
    }

    @PatchMapping(value = "/{id}")
    public Object partialUpdateLanguage(@RequestBody Languages languages,@PathVariable("id")String id){
        Languages lan = languagesService.getLanguage(id);
        if(lan==null) return new Detail("Not found.");
        languages.setId(lan.getId());
        return (languages.getName()==null&&languages.getCode()==null)||(languages.getName().equals(lan.getName())&&languages.getCode().equals(lan.getCode()))?languagesService.updateLanguage(languages,lan):new Detail("Not found.");
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody Object deleteLanguage(@PathVariable("id")String id,@RequestParam(value = "name",required = false) String name,@RequestParam(value = "code",required = false)String code){
        Languages language = languagesService.getLanguage(id);
        if(language==null) return new Detail("Not found.");
        name = name==null?language.getName():name;
        code = code==null?language.getCode():code;
        return name.equals(language.getName())&&code.equals(language.getCode())?languagesService.deleteLanguage(language.getId()):new Detail("Not found.");
    }
}
