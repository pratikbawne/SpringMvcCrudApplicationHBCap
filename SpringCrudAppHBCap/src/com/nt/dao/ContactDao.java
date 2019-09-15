package com.nt.dao;

import java.util.List;

import com.nt.model.Contact;
import com.nt.model.Employee;

public interface ContactDao {
	
	public int save(Employee employee);
	
	public List<Contact> list();
	
    public int save(Contact contact);
	
	public int update(Contact contact);
	
	public Contact get(Integer id);
	
	public int delete(Integer id);
	
	
	

}
