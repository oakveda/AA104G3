package com.product.model;

import java.util.ArrayList;
import java.util.List;
import hibernate.util.HibernateUtil;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Product;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProductHibernateDAO implements ProductDAO_interface {

	@Override
	public void insert(ProductVO productVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(productVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void update(ProductVO productVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(productVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void delete(String prono) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete ProductVO where prono = ?");
			query.setParameter(0, prono);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public ProductVO findByPrimaryKey(String prono) {
		ProductVO productVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			productVO = (ProductVO) session.get(ProductVO.class, prono);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().commit();
			throw e;
		}
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ProductVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<ProductVO> getAllByName() {
		List<ProductVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ProductVO order by proname");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<ProductVO> getAll(Map<String, String[]> map) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ProductVO"+jdbcUtil_CompositeQuery_Product.get_WhereCondition(map));
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}		
		return list;
	}
	
	public static void main(String[] args) {
		ProductHibernateDAO dao = new ProductHibernateDAO();
		
		/*getAll*/
		List<ProductVO> list = dao.getAll();
		for (ProductVO productVO : list) {
			System.out.print(productVO.getProno()+" ");			
			System.out.print(productVO.getProname()+" ");			
			System.out.print(productVO.getProprice()+" ");			
			System.out.print(productVO.getProsumm()+" ");			
			System.out.print(productVO.getProdesc()+" ");			
			System.out.print(productVO.getProduct_classVO().getClassname());
			System.out.println();
		}
	}

}
