package com.vmware.grm.service.implement;

import com.vmware.grm.dao.LanguangesDao;
import com.vmware.grm.dao.ProductLanguageDao;
import com.vmware.grm.dao.ProductsDao;
import com.vmware.grm.dto.Detail;
import com.vmware.grm.dto.LanguageError;
import com.vmware.grm.model.Languages;
import com.vmware.grm.model.Products;
import com.vmware.grm.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:10:23 AM
 **/

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsDao productsDao;

    @Autowired
    private ProductLanguageDao productLanguageDao;

    @Autowired
    private LanguangesDao languangesDao;

    @Transactional
    @Override
    public Object insertProduct(Products products) {
        List<String> supported_languages = products.getSupported_languages();
        products.setId(UUID.randomUUID().toString());
        for(String uid:supported_languages){
            try {
                languangesDao.getLanguageById(UUID.fromString(uid));
            } catch (Exception e) {
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                List<String> list = new ArrayList<String>();
                list.add(uid+" is not a valid UUID.");
                return new LanguageError(list);
            }
            productLanguageDao.insertProductLanguage(products.getId(),uid);
        }
        int update = productsDao.insertProduct(products);
        if(update==1){
            List<Products> productByName = productsDao.getProductByName(products.getName());
            System.out.println("productByName" +"          " + productByName);
            Products products1 = productByName.get(0);
            for (Products product:productByName) {
                products1.setSupported_languagesItem(product.getSupported_languages().get(0));
            }
            products1.getSupported_languages().remove(0);
            return products1;
        }
        return null;
    }

    @Override
    public List<Products> listProducts(Integer limit, Integer offset, String name) {
        List<Products> products =null;
        if(name==null){
            products = productsDao.listLanguagesByLimitOffset(limit, offset);
        }else {
            try {
                products = productsDao.listLanguagesByLimitOffsetName(limit,offset,name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<products.size();i++){
            List<Products> productByName = productsDao.getProductByName(products.get(i).getName());
            for (Products product:productByName){
                String id = product.getSupported_languages().get(0);
                products.get(i).setSupported_languagesItem(id);
            }
        }
        return products;
    }

    @Override
    public int countProducts(String name) {

        return 0;
    }

    @Override
    public Products getProduct(String id) {
        Products product = null;
        try {
            product = productsDao.getProductById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Transactional
    @Override
    public String deleteProduct(String id) {
        int update1 = productLanguageDao.deleteProductLanguage(UUID.fromString(id));
        int update2 = productsDao.deleteProduct(UUID.fromString(id));
        return update1>=0&&update2==1?"success":"error";
    }

    @Transactional
    @Override
    public Object updateProduct(Products product) {
        int update = productLanguageDao.deleteProductLanguage(UUID.fromString(product.getId()));
        List<String> supported_languages = product.getSupported_languages();
        supported_languages.forEach(e->{productLanguageDao.insertProductLanguage(product.getId(),e);});
        Products products = productsDao.updateProduct(product);
        return products;
    }
}
