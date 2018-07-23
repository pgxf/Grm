package com.vmware.grm.dao.implement;

import com.vmware.grm.dao.ProductLanguageDao;
import com.vmware.grm.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Author:fxing@vmware.com
 * Date:7/13/2018
 * Time:3:01 PM
 **/
@Repository
public class ProductLanguageDaoImpl implements ProductLanguageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertProductLanguage(String pid,String uid) {
        String sql = "insert into products_product_supported_languages (product_id,language_id) values (?,?)";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(pid),UUID.fromString(uid)});
        return update;
    }

    @Override
    public int deleteProductLanguage(UUID uuid) {
        String sql = "delete from products_product_supported_languages where product_id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{uuid});
        return update;
    }

    @Override
    public List<String> listLanguageNamesByProductId(String id) {
        String sql = "SELECT languages_language.name FROM products_product_supported_languages INNER JOIN languages_language ON languages_language.id=products_product_supported_languages.language_id WHERE product_id=? ";
        return jdbcTemplate.query(sql, new Object[]{UUID.fromString(id)}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("name");
            }
        });
    }
}
