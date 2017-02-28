package ru.music.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.music.dao.interfaces.MP3Dao;
import ru.music.dao.model.MP3;

import java.sql.ResultSet;;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sqlServerDao")
public class SqlMP3Dao implements MP3Dao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void insert(MP3 mp3) {
        String sql = "INSERT INTO Music (Name, Author) VALUES (:name, :author)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", mp3.getName());
        namedParameters.addValue("author", mp3.getAuthor());

        jdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public void delete(MP3 mp3) {

    }

    @Override
    public MP3 getById(int id) {
        String sql = "SELECT * FROM Music WHERE IdRecord = :id";

        SqlParameterSource param = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, param, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMp3ListByName(String name) {
        String sql = "SELECT * FROM Music WHERE Name like :name";

        SqlParameterSource param = new MapSqlParameterSource("name", "%" + name + "%");
        return jdbcTemplate.query(sql, param, new MP3RowMapper());
    }

    @Override
    public List<MP3> getAllMp3List() {
        String sql = "SELECT * FROM Music";
        return jdbcTemplate.query(sql, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMp3ListByAuthor(String author) {
        String sql = "SELECT * FROM Music WHERE Author like :author";

        SqlParameterSource param = new MapSqlParameterSource("author", "%" + author + "%");
        return jdbcTemplate.query(sql, param, new MP3RowMapper());
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {

        @Override
        public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {
            MP3 mp3 = new MP3();
            mp3.setName(rs.getString("Name"));
            mp3.setAuthor(rs.getString("Author"));

            return mp3;
        }
    }
}
