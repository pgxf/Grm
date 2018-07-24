package com.vmware.grm.dao.implement;

import com.vmware.grm.dao.ProductsDao;
import com.vmware.grm.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:10:25 AM
 **/

@Repository
public class ProductsDaoImpl implements ProductsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertProduct(Products products) {
        String sql = "insert into products_product (id,name,release_date,version,code_freeze_date,modified,created) values(?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, new Object[]{UUID.fromString(products.getId()), products.getName(), products.getRelease_date(), products.getVersion(), products.getCode_freeze_date(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())});
        return update;
    }

    @Override
    public List<Products> getProductByName(String name) {
        String sql = "select l.id,l.name,l.release_date,l.version,l.code_freeze_date,l.modified,l.created,p.language_id from products_product as l inner join products_product_supported_languages as p on l.id=p.product_id where name = ?";
        return jdbcTemplate.query(sql, new Object[]{name}, new RowMapper<Products>() {
            @Override
            public Products mapRow(ResultSet resultSet, int i) throws SQLException {
                Products products = new Products();
                products.setId(resultSet.getString("id"));
                products.setName(resultSet.getString("name"));
                products.setRelease_date(resultSet.getString("release_date"));
                products.setVersion(resultSet.getString("version"));
                products.setCode_freeze_date(resultSet.getString("code_freeze_date"));
                products.setModified(resultSet.getTimestamp("modified"));
                products.setCreated(resultSet.getTimestamp("created"));
                products.setSupported_languagesItem(resultSet.getString("language_id"));
                return products;
            }
        });
    }

    @Override
    public List<Products> listLanguagesByLimitOffset(int limit, int offset) {
        String sql = "select * from products_product  limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{limit,offset}, new RowMapper<Products>() {
            @Override
            public Products mapRow(ResultSet resultSet, int i) throws SQLException {
                Products products = new Products();
                products.setId(resultSet.getString("id"));
                products.setName(resultSet.getString("name"));
                products.setRelease_date(resultSet.getString("release_date"));
                products.setVersion(resultSet.getString("version"));
                products.setCode_freeze_date(resultSet.getString("code_freeze_date"));
                products.setModified(resultSet.getTimestamp("modified"));
                products.setCreated(resultSet.getTimestamp("created"));
                return products;
            }
        });
    }

    @Override
    public List<Products> listLanguagesByLimitOffsetName(Integer limit, Integer offset,String name) throws Exception{
        String sql = "select * from products_product where name = ? limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{name,limit,offset}, new RowMapper<Products>() {
            @Override
            public Products mapRow(ResultSet resultSet, int i) throws SQLException {
                Products products = new Products();
                products.setId(resultSet.getString("id"));
                products.setName(resultSet.getString("name"));
                products.setRelease_date(resultSet.getString("release_date"));
                products.setVersion(resultSet.getString("version"));
                products.setCode_freeze_date(resultSet.getString("code_freeze_date"));
                products.setModified(resultSet.getTimestamp("modified"));
                products.setCreated(resultSet.getTimestamp("created"));
                return products;
            }
        });
    }

    @Override
    public Products getProductById(String id) throws Exception{
        String sql = "select * from products_product where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{UUID.fromString(id)}, new RowMapper<Products>() {
            @Override
            public Products mapRow(ResultSet resultSet, int i) throws SQLException {
                Products products = new Products();
                products.setId(resultSet.getString("id"));
                products.setName(resultSet.getString("name"));
                products.setRelease_date(resultSet.getString("release_date"));
                products.setVersion(resultSet.getString("version"));
                products.setCode_freeze_date(resultSet.getString("code_freeze_date"));
                products.setModified(resultSet.getTimestamp("modified"));
                products.setCreated(resultSet.getTimestamp("created"));
                return products;
            }
        });
    }

    @Override
    public int deleteProduct(UUID uuid) {
        String sql = "delete from products_product where id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{uuid});
        return update;
    }

    @Override
    public Products updateProduct(Products product) {
        String sql = "update products_product set release_date = ? , version = ? , code_freeze_date = ? , modified = ? where id = ?";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int update = jdbcTemplate.update(sql , new Object[]{product.getRelease_date(),product.getVersion(),product.getCode_freeze_date(),timestamp,UUID.fromString(product.getId())});
        product.setModified(timestamp);
        try {
            Products productById = getProductById(product.getId());
            product.setCreated(productById.getCreated());
            product.setName(productById.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(update ==1 ) return product;
        return null;
    }

}