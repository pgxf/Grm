package com.vmware.grm.dao.implement;

import com.vmware.grm.dao.ComponentsDao;
import com.vmware.grm.model.Components;
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
 * Date:7/18/2018
 * Time:11:33 AM
 **/
@Repository
public class ComponentsDaoImpl implements ComponentsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Components getComponentByID(String id) throws Exception{
        String sql = "select * from products_component where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{UUID.fromString(id)}, new RowMapper<Components>() {
            @Override
            public Components mapRow(ResultSet resultSet, int i) throws SQLException {
                Components component = new Components();
                component.setId(resultSet.getString("id"));
                component.setName(resultSet.getString("name"));
                component.setBranch_name(resultSet.getString("branch_name"));
                component.setL10n_definition_file(resultSet.getString("l10n_definition_file"));
                component.setScm_path(resultSet.getString("scm_path"));
                component.setL10n_mode(resultSet.getString("l10n_mode"));
                component.setStatus(resultSet.getString("status"));
                component.setModified(resultSet.getTimestamp("modified"));
                component.setCreated(resultSet.getTimestamp("created"));
                component.setProduct_id(resultSet.getString("product_id"));
                return component;
            }
        });
    }

    @Override
    public List<Components> listComponentsByLimitOffset(Integer limit, Integer offset) {
        String sql = "select * from products_component limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{limit, offset}, new RowMapper<Components>() {
            @Override
            public Components mapRow(ResultSet resultSet, int i) throws SQLException {
                Components component = new Components();
                component.setId(resultSet.getString("id"));
                component.setName(resultSet.getString("name"));
                component.setBranch_name(resultSet.getString("branch_name"));
                component.setL10n_definition_file(resultSet.getString("l10n_definition_file"));
                component.setScm_path(resultSet.getString("scm_path"));
                component.setL10n_mode(resultSet.getString("l10n_mode"));
                component.setStatus(resultSet.getString("status"));
                component.setModified(resultSet.getTimestamp("modified"));
                component.setCreated(resultSet.getTimestamp("created"));
                component.setProduct_id(resultSet.getString("product_id"));
                return component;
            }
        });

    }

    @Override
    public int insertComponent(Components components) throws Exception{
        String sql = "insert into products_component(id,name,branch_name,l10n_definition_file,scm_path,l10n_mode,modified,created,product_id,status) values(?,?,?,?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(components.getId()),components.getName(),components.getBranch_name(),components.getL10n_definition_file(),components.getScm_path(),components.getL10n_mode(),components.getModified(),components.getCreated(),UUID.fromString(components.getProduct_id()),components.getStatus()});
        return update;
    }

    @Override
    public int updateComponent(Components components) {
        String sql = "update products_component set name = ? , branch_name = ? , l10n_definition_file = ? ,scm_path = ? ,l10n_mode = ? ,modified = ? ,product_id =? ,status = ? where id = ? ";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int update = jdbcTemplate.update(sql, new Object[]{components.getName(), components.getBranch_name(), components.getL10n_definition_file(), components.getScm_path(), components.getL10n_mode(), timestamp, UUID.fromString(components.getProduct_id()), components.getStatus(), UUID.fromString(components.getId())});
        return update;
    }

    @Override
    public int deleteComponent(String id) {
        String sql = "delete from products_component where id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id)});
        return update;
    }
}
