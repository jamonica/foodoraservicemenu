/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.dao;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author kouka
 */
public abstract class DAO<T> {
	protected Connection cnx;

	public DAO(Connection cnx) {
		super();
		this.cnx = cnx;
	}
	
	public Connection getCnx() {
		return cnx;
	}

	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}

	public abstract boolean create(T x);
	public abstract T read(String id);
	public abstract boolean update(T x);
	public abstract boolean delete(T x);
	public abstract List<T> findAll();
}

