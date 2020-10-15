package com.revature.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	public T create(T t);
	
	public T get(int id);
	
	public T update(T t);
	
	public void delete(T t);
	
	public List<T> getAll();
}
