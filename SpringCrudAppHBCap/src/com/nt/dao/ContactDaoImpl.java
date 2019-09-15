package com.nt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.nt.model.Contact;
import com.nt.model.Employee;


public class ContactDaoImpl implements ContactDao {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDaoImpl(DataSource dataSource) {
		   this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
	
	@Override
	public int save(Employee employee) {
	
		String sql = "INSERT INTO Employee (name,desg) VALUES(?, ?)";
	       return jdbcTemplate.update(sql, employee.getName(), employee.getDesg());
		}

	@Override
	public List<Contact> list() {
		
        String sql = "SELECT * FROM Contact";
		
		RowMapper<Contact> rowMapper = new RowMapper<Contact>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("contact_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				
				return new Contact(id, name, email, address, phone);
				
			}
		};
		
		return jdbcTemplate.query(sql,rowMapper);
		
			}

	
	@Override
	public int save(Contact contact) {
		String sql = "INSERT INTO Contact (name,email,address,phone) VALUES(?, ?, ?, ?)";
       return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),contact.getAddress(),contact.getPhone());
	}

	

	@Override
	public int update(Contact contact) {
		
		String sql = "UPDATE Contact SET name=?,email=?,address=?,phone=? where contact_id=?";
		return jdbcTemplate.update(sql,contact.getName(), contact.getEmail(),contact.getAddress(),contact.getPhone(),contact.getId());
	}

	
	@Override
	public Contact get(Integer id) {
		String sql = "SELECT * FROM Contact WHERE contact_id="+id;
		
		ResultSetExtractor<Contact> extracter = new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					
					return new Contact(id, name, email, address, phone);
				}
				return null;
			}
		};
		
		return jdbcTemplate.query(sql, extracter);
	}

	
	@Override
	public int delete(Integer id) {
		
		String sql = "DELETE FROM Contact where contact_id="+id;
		return jdbcTemplate.update(sql);
		
	}

	
	
}
