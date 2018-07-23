package com.vmware.grm.controller;

import com.vmware.grm.dto.Detail;
import com.vmware.grm.dto.ReleaseprofilesDto;
import com.vmware.grm.model.Releaseprofiles;
import com.vmware.grm.service.ReleaseprofilesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/19/2018
 * Time:4:47 PM
 **/
@RestController
@RequestMapping("/api/releaseprofiles")
public class ReleaseprofilesController {

    @Resource
    private ReleaseprofilesService releaseprofilesService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Object listReleaseprofiles(@RequestParam(value = "limit",required = false,defaultValue = "15")Integer limit, @RequestParam(value = "offset",required = false,defaultValue = "0")Integer offset, @RequestParam(value = "name",required = false)String name){
        List<Releaseprofiles> releaseprofiles = releaseprofilesService.listReleaseprofiles(limit,offset,name);
        int count = releaseprofiles.size();
        return new ReleaseprofilesDto(count,releaseprofiles);
    }

    @GetMapping("/{id}/")
    public Object getReleaseprofile(@PathVariable("id")String id, @RequestParam(value = "name",required = false) String name){
        Releaseprofiles releaseprofiles = releaseprofilesService.getReleaseprofile(id);
        name = name==null ? releaseprofiles.getName():name;
        if(releaseprofiles==null||!releaseprofiles.getName().equals(name)) return new Detail("Not found.");
        return releaseprofiles;
    }

    @PostMapping(value = "/")
    public Object insertReleaseprofile(@RequestBody(required = false)Releaseprofiles releaseprofile) {
        System.out.println(releaseprofile);
        Object o = releaseprofilesService.insertReleaseprofile(releaseprofile);
        return o;
    }

    @PutMapping(value = "/{id}")
    public Object updateReleaseprofile(@RequestBody Releaseprofiles releaseprofiles,@PathVariable("id")String id){
        Releaseprofiles releaseprofile = releaseprofilesService.getReleaseprofile(id);
        if(releaseprofiles==null) return new Detail("Not found.");
        releaseprofiles.setId(releaseprofile.getId());
        releaseprofiles.setDescription(releaseprofiles.getDescription()==null?releaseprofile.getDescription():releaseprofiles.getDescription());
        releaseprofiles.setTms_configuration_id(releaseprofiles.getTms_configuration_id()==null?releaseprofile.getTms_configuration_id():releaseprofiles.getTms_configuration_id());
        releaseprofiles.setCreated(releaseprofile.getCreated());
        Object o = releaseprofilesService.updateReleaseprofile(releaseprofiles);
        return o;
    }

    @PatchMapping(value = "/{id}")
    public Object partialUpdateReleaseprofile(@RequestBody Releaseprofiles releaseprofiles,@PathVariable("id")String id){
        Releaseprofiles releaseprofile = releaseprofilesService.getReleaseprofile(id);
        if(releaseprofiles==null) return new Detail("Not found.");
        releaseprofiles.setId(releaseprofile.getId());
        releaseprofiles.setName(releaseprofiles.getName()==null?releaseprofile.getName():releaseprofiles.getName());
        releaseprofiles.setDescription(releaseprofiles.getDescription()==null?releaseprofile.getDescription():releaseprofiles.getDescription());
        releaseprofiles.setTms_configuration_id(releaseprofiles.getTms_configuration_id()==null?releaseprofile.getTms_configuration_id():releaseprofiles.getTms_configuration_id());
        releaseprofiles.setProduct(releaseprofiles.getProduct()==null?releaseprofile.getProduct():releaseprofiles.getProduct());
        releaseprofiles.setLanguage_id(releaseprofiles.getLanguage_id()==null?releaseprofile.getLanguage_id():releaseprofiles.getLanguage_id());
        releaseprofiles.setCreated(releaseprofile.getCreated());
        Object o = releaseprofilesService.updateReleaseprofile(releaseprofiles);
        return o;
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody Object deleteReleaseprofile(@PathVariable("id")String id,@RequestParam(value = "name",required = false) String name){
        Releaseprofiles releaseprofile = releaseprofilesService.getReleaseprofile(id);
        if(releaseprofile==null) return new Detail("Not found.");
        name = name==null?releaseprofile.getName():name;
        if(!releaseprofile.getName().equals(name)) return new Detail("Not found.");
        boolean update = releaseprofilesService.deleteReleaseprofile(id);
        return update?"success":"error";
    }

}
