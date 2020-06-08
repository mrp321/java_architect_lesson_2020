package cn.sitedev.spring.transaction.dao;

import cn.sitedev.spring.transaction.entity.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    protected void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Member> selectAll() throws Exception {
        String sql = "SELECT * FROM t_member";
        return this.jdbcTemplate.query(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet resultSet, int i) throws SQLException {
                Member member = new Member();
                member.setName(resultSet.getString("name"));
                member.setId(resultSet.getLong("id"));
                member.setAddr(resultSet.getString("addr"));
                member.setAge(resultSet.getInt("age"));
                return member;
            }
        });
    }

    public boolean insert(Member member) throws Exception {
        String sql = "INSERT INTO t_member(id, name, addr, age) VALUES (?, ?, ?, ?)";
        int count = this.jdbcTemplate.update(sql, member.getId(), member.getName(), member.getAddr(), member.getAge());
        return count > 0;
    }

    public boolean delete(long id) throws Exception {
        return this.jdbcTemplate.update("DELETE FROM t_member WHERE id = ?", id) > 0;
    }

    public boolean update(long id, String name) throws Exception {
        return this.jdbcTemplate.update("UPDATE t_member SET name = ? WHERE id = ?", name, id) > 0;
    }
}
