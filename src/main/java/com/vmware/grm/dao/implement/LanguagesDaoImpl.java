package com.vmware.grm.dao.implement;

import com.vmware.grm.dao.LanguangesDao;
import com.vmware.grm.model.Languages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class LanguagesDaoImpl implements LanguangesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Languages> listLanguagesByLimitOffsetNameCode(int limit, int offset, String name, String code) {
        String sql = "select id,name,code,description,code_aliases from languages_language where code = ? limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{code,limit,offset} ,new RowMapper<Languages>() {
            @Override
            public Languages mapRow(ResultSet resultSet, int i) throws SQLException {
                Languages lan = new Languages();
                lan.setId(resultSet.getString("id"));
                lan.setName(resultSet.getString("name"));
                lan.setCode(resultSet.getString("code"));
                lan.setDescription(resultSet.getString("description"));
                lan.setCode_aliases(resultSet.getString("code_aliases"));
                return lan;
            }
        });
    }

    @Override
    public List<Languages> listLanguagesByLimitOffsetName(int limit, int offset, String name) {
        String sql = "select id,name,code,description,code_aliases from languages_language where name = ?  limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{name,limit,offset} ,new RowMapper<Languages>() {
            @Override
            public Languages mapRow(ResultSet resultSet, int i) throws SQLException {
                Languages lan = new Languages();
                lan.setId(resultSet.getString("id"));
                lan.setName(resultSet.getString("name"));
                lan.setCode(resultSet.getString("code"));
                lan.setDescription(resultSet.getString("description"));
                lan.setCode_aliases(resultSet.getString("code_aliases"));
                return lan;
            }
        });
    }

    @Override
    public List<Languages> listLanguagesByLimitOffsetCode(int limit, int offset, String code) {
        String sql = "select id,name,code,description,code_aliases from languages_language where  code = ? limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{code,limit,offset} ,new RowMapper<Languages>() {
            @Override
            public Languages mapRow(ResultSet resultSet, int i) throws SQLException {
                Languages lan = new Languages();
                lan.setId(resultSet.getString("id"));
                lan.setName(resultSet.getString("name"));
                lan.setCode(resultSet.getString("code"));
                lan.setDescription(resultSet.getString("description"));
                lan.setCode_aliases(resultSet.getString("code_aliases"));
                return lan;
            }
        });
    }

    @Override
    public List<Languages> listLanguagesByLimitOffset(int limit, int offset) {
        String sql = "select id,name,code,description,code_aliases from languages_language  limit ? offset ? ";
        return jdbcTemplate.query(sql, new Object[]{limit,offset} ,new RowMapper<Languages>() {
            @Override
            public Languages mapRow(ResultSet resultSet, int i) throws SQLException {
                Languages lan = new Languages();
                lan.setId(resultSet.getString("id"));
                lan.setName(resultSet.getString("name"));
                lan.setCode(resultSet.getString("code"));
                lan.setDescription(resultSet.getString("description"));
                lan.setCode_aliases(resultSet.getString("code_aliases"));
                return lan;
            }
        });
    }

    @Override
    public Languages getLanguageByName(String name) throws Exception{
        String sql = "select id,name,code,description,code_aliases from languages_language where name = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new RowMapper<Languages>() {
        @Override
        public Languages mapRow(ResultSet resultSet, int i) throws SQLException {
            Languages lan = new Languages();
            lan.setId(resultSet.getString("id"));
            lan.setName(resultSet.getString("name"));
            lan.setCode(resultSet.getString("code"));
            lan.setDescription(resultSet.getString("description"));
            lan.setCode_aliases(resultSet.getString("code_aliases"));
            return lan;
        }
    });
}

    @Override
    public Languages getLanguageByCode(String code) throws Exception{
        String sql = "select id,name,code,description,code_aliases from languages_language where code = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{code}, new RowMapper<Languages>() {
            @Override
            public Languages mapRow(ResultSet resultSet, int i) throws SQLException {
                Languages lan = new Languages();
                lan.setId(resultSet.getString("id"));
                lan.setName(resultSet.getString("name"));
                lan.setCode(resultSet.getString("code"));
                lan.setDescription(resultSet.getString("description"));
                lan.setCode_aliases(resultSet.getString("code_aliases"));
                return lan;
            }
        });
    }

    @Override
    public Languages getLanguageById(UUID uuid) throws Exception{
        String sql = "select id,name,code,description,code_aliases from languages_language where id = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{uuid}, new RowMapper<Languages>() {
            @Override
            public Languages mapRow(ResultSet resultSet, int i) throws SQLException {
                Languages lan = new Languages();
                lan.setId(resultSet.getString("id"));
                lan.setName(resultSet.getString("name"));
                lan.setCode(resultSet.getString("code"));
                lan.setDescription(resultSet.getString("description"));
                lan.setCode_aliases(resultSet.getString("code_aliases"));
                return lan;
            }
        });
    }

    @Override
    public Languages getLanguageByNameCode(Languages languages) {
        String sql = "select id,name,code,description,code_aliases from languages_language where  name = ? and code = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{languages.getName(),languages.getCode()}, new RowMapper<Languages>() {
            @Override
            public Languages mapRow(ResultSet resultSet, int i) throws SQLException {
                Languages lan = new Languages();
                lan.setId(resultSet.getString("id"));
                lan.setName(resultSet.getString("name"));
                lan.setCode(resultSet.getString("code"));
                lan.setDescription(resultSet.getString("description"));
                lan.setCode_aliases(resultSet.getString("code_aliases"));
                return lan;
            }
        });
    }

    @Override
    public int insertLanguage(Languages languages) throws Exception{
        String sql = "insert into languages_language (id,name,code,description,code_aliases) values(?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, new Object[]{UUID.fromString(languages.getId()), languages.getName(), languages.getCode(), languages.getDescription(), languages.getCode_aliases()});
        return update;
    }

    @Override
    public Languages updateLanguage(Languages languages) {
        String sql = "update languages_language set name = ? , code = ? , description = ?, code_aliases = ? where id = ?";
        int update = jdbcTemplate.update(sql , new Object[]{languages.getName(),languages.getCode(),languages.getDescription(),languages.getCode_aliases(),UUID.fromString(languages.getId())});
        if(update ==1 ) return languages;
        return null;
    }

    @Override
    public int deleteLanguage(UUID uuid) {
        String sql = "delete from languages_language where id = ?";
        int update = jdbcTemplate.update(sql,new Object[]{uuid});
        return update;
    }

    @Override
    public int countLanguages() {
        String sql = "select count(*) from languages_language ";
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }


}
