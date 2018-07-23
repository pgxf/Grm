package com.vmware.grm.service;

import com.vmware.grm.model.Releaseprofiles;

import java.util.List;

/**
 * Author:fxing@vmware.com
 * Date:7/19/2018
 * Time:5:47 PM
 **/
public interface ReleaseprofilesService {


    Releaseprofiles getReleaseprofile(String id);

    List<Releaseprofiles> listReleaseprofiles(Integer limit, Integer offset, String name);

    Object insertReleaseprofile(Releaseprofiles releaseprofile);

    Object updateReleaseprofile(Releaseprofiles releaseprofiles);

    boolean deleteReleaseprofile(String id);
}
