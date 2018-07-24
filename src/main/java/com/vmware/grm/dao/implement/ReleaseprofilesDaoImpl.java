package com.vmware.grm.dao.implement;

import com.vmware.grm.dao.ReleaseprofilesDao;
import com.vmware.grm.model.Releaseprofiles;
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
 * Date:7/20/2018
 * Time:1:55 PM
 **/
@Repository
public class ReleaseprofilesDaoImpl implements ReleaseprofilesDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Releaseprofiles getReleaseprofileById(String id) throws Exception{
        String sql = "select * from releaseprofiles_releaseprofile where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{UUID.fromString(id)}, new RowMapper<Releaseprofiles>() {
            @Override
            public Releaseprofiles mapRow(ResultSet resultSet, int i) throws SQLException {
                Releaseprofiles releaseprofile = new Releaseprofiles();
                releaseprofile.setId(resultSet.getString("id"));
                releaseprofile.setName(resultSet.getString("name"));
                releaseprofile.setDescription(resultSet.getString("description"));
                releaseprofile.setTms_configuration_id(resultSet.getString("tms_configuration_id"));
                releaseprofile.setModified(resultSet.getTimestamp("modified"));
                releaseprofile.setCreated(resultSet.getTimestamp("created"));
                releaseprofile.setProduct(resultSet.getString("product_id"));
                releaseprofile.setLanguage_id(resultSet.getString("language_id"));
                return releaseprofile;
            }
        });
    }

    @Override
    public List<String> getTargetLanguagesCode(String id) {
        String sql = "SELECT l.code FROM languages_language as l  INNER JOIN (\n" +
                "select t.language_id from releaseprofiles_releaseprofile as r right join releaseprofiles_releaseprofile_target_languages as t on r.id=t.releaseprofile_id WHERE r.\"id\"=?) as p on p.language_id=l.\"id\" ";
        return jdbcTemplate.query(sql, new Object[]{UUID.fromString(id)}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String s = resultSet.getString("code");
                return s;
            }
        });
    }

    @Override
    public List<String> getI18n_quality_engineers(String id) {
        String sql = "select user_id from releaseprofiles_releaseprofile_i18n_quality_engineers where releaseprofile_id = ?";
        return jdbcTemplate.query(sql, new Object[]{UUID.fromString(id)}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String s = resultSet.getString("user_id");
                return s;
            }
        });
    }

    @Override
    public List<String> getI18n_engineers(String id) {
        String sql = "select user_id from releaseprofiles_releaseprofile_i18n_engineers where releaseprofile_id = ?";
        return jdbcTemplate.query(sql, new Object[]{UUID.fromString(id)}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String s = resultSet.getString("user_id");
                return s;
            }
        });
    }

    @Override
    public List<String> getL10n_project_managers(String id) {
        String sql = "select user_id from releaseprofiles_releaseprofile_l10n_project_managers where releaseprofile_id = ?";
        return jdbcTemplate.query(sql, new Object[]{UUID.fromString(id)}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String s = resultSet.getString("user_id");
                return s;
            }
        });
    }

    @Override
    public List<Releaseprofiles> listLanguagesByLimitOffset(Integer limit, Integer offset) {
        String sql = "select * from releaseprofiles_releaseprofile  limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{limit,offset}, new RowMapper<Releaseprofiles>() {
            @Override
            public Releaseprofiles mapRow(ResultSet resultSet, int i) throws SQLException {
                Releaseprofiles releaseprofile = new Releaseprofiles();
                releaseprofile.setId(resultSet.getString("id"));
                releaseprofile.setName(resultSet.getString("name"));
                releaseprofile.setDescription(resultSet.getString("description"));
                releaseprofile.setTms_configuration_id(resultSet.getString("tms_configuration_id"));
                releaseprofile.setModified(resultSet.getTimestamp("modified"));
                releaseprofile.setCreated(resultSet.getTimestamp("created"));
                releaseprofile.setProduct(resultSet.getString("product_id"));
                releaseprofile.setLanguage_id(resultSet.getString("language_id"));
                return releaseprofile;
            }
        });
    }

    @Override
    public List<Releaseprofiles> listLanguagesByLimitOffsetName(Integer limit, Integer offset, String name) throws Exception{
        String sql = "select * from releaseprofiles_releaseprofile where name = ? limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{name,limit,offset}, new RowMapper<Releaseprofiles>() {
            @Override
            public Releaseprofiles mapRow(ResultSet resultSet, int i) throws SQLException {
                Releaseprofiles releaseprofile = new Releaseprofiles();
                releaseprofile.setId(resultSet.getString("id"));
                releaseprofile.setName(resultSet.getString("name"));
                releaseprofile.setDescription(resultSet.getString("description"));
                releaseprofile.setTms_configuration_id(resultSet.getString("tms_configuration_id"));
                releaseprofile.setModified(resultSet.getTimestamp("modified"));
                releaseprofile.setCreated(resultSet.getTimestamp("created"));
                releaseprofile.setProduct(resultSet.getString("product_id"));
                releaseprofile.setLanguage_id(resultSet.getString("language_id"));
                return releaseprofile;
            }
        });
    }

    @Override
    public int insertI18n_quality_engineers(String id, String s) {
        String sql = "insert into releaseprofiles_releaseprofile_i18n_quality_engineers(releaseprofile_id,user_id) values(?,?)";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id),Integer.parseInt(s)});
        return update;
    }

    @Override
    public int insertI18n_engineers(String id, String s) {
        String sql = "insert into releaseprofiles_releaseprofile_i18n_engineers(releaseprofile_id,user_id) values(?,?)";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id),Integer.parseInt(s)});
        return update;
    }

    @Override
    public int insertL10n_project_managers(String id, String s) {
        String sql = "insert into releaseprofiles_releaseprofile_l10n_project_managers(releaseprofile_id,user_id) values(?,?)";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id),Integer.parseInt(s)});
        return update;
    }

    @Override
    public int insertTarget_languages(String id, String language_id) {
        String sql = "insert into releaseprofiles_releaseprofile_target_languages(releaseprofile_id,language_id) values(?,?)";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id),UUID.fromString(language_id)});
        return update;
    }

    @Override
    public int insertReleaseprofiles(Releaseprofiles releaseprofile) {
        String sql = "insert into releaseprofiles_releaseprofile(id,name,description,tms_configuration_id,modified,created,product_id,language_id) values(?,?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, new Object[]{UUID.fromString(releaseprofile.getId()), releaseprofile.getName(),releaseprofile.getDescription(), releaseprofile.getTms_configuration_id(), releaseprofile.getModified(), releaseprofile.getCreated(), UUID.fromString(releaseprofile.getProduct()), UUID.fromString(releaseprofile.getLanguage_id())});
        return update;
    }

    @Override
    public int deleteI18n_quality_engineers(String id) {
        String sql = "delete from releaseprofiles_releaseprofile_i18n_quality_engineers where releaseprofile_id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id)});
        return update;
    }

    @Override
    public int deleteI18n_engineers(String id) {
        String sql = "delete from releaseprofiles_releaseprofile_i18n_engineers where releaseprofile_id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id)});
        return update;
    }

    @Override
    public int deleteL10n_project_managers(String id) {
        String sql = "delete from releaseprofiles_releaseprofile_l10n_project_managers where releaseprofile_id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id)});
        return update;
    }

    @Override
    public int deleteTarget_languages(String id) {
        String sql = "delete from releaseprofiles_releaseprofile_target_languages where releaseprofile_id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id)});
        return update;
    }

    @Override
    public int deleteReleaseprofile(String id) {
        String sql = "delete from releaseprofiles_releaseprofile where id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{UUID.fromString(id)});
        return update;
    }
}
