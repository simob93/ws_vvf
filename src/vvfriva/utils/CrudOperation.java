package vvfriva.utils;

import org.hibernate.Transaction;

public interface CrudOperation {
	<T> void insert(T object, Transaction tx);
	<T> void update(T object, Transaction tx);
	void delete(Integer id, Transaction tx);
	void soft_delete(Integer id, Transaction tx);
}
