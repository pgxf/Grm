package com.vmware.grm.service;

import com.vmware.grm.model.Languages;
import com.vmware.grm.model.Products;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/11/2018
 * Time:3:07 PM
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    LanguagesService languagesService;

    @Autowired
    ProductsService productsService;

    @Test
    public void getLanguageByName() {
        Languages language = new Languages(UUID.randomUUID().toString(),"wahaha","zhenhaohe",null,null);
        Languages i = languagesService.insertLanguage(language);
        System.out.println(i);
    }

//    "0090efe6-2942-4769-8b0c-af702c8165",
//            "0195c44e-6c1d-4d4d-9860-d83c4b96524e",
//            "037094f5-b606-4304-9873-37906c910da2",
//            "040fb82a-78bc-46ff-a9c5-baa3ec2"

    @Test
    public void createProductt(){
        Products products = new Products();
        List<String> stringList = new ArrayList<>();
//        stringList.add("0090efe6-2942-4769-8b0c-af702c8165");
        stringList.add("0195c44e-6c1d-4d4d-9860-d83c4b96524e3");
        stringList.add("037094f5-b606-4304-9873-37906c910da2");
//        stringList.add("040fb82a-78bc-46ff-a9c5-baa3ec2");
        products.setSupported_languages(stringList);
        products.setName("aaaaaaaaaaaaa");
        products.setCode_freeze_date(null);
        products.setVersion(null);
        products.setRelease_date(null);
        Object o = productsService.insertProduct(products);
        System.out.println(o);
    }
}
