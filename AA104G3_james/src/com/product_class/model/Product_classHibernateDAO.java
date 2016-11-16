package com.product_class.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.product.model.ProductHibernateDAO;
import com.product.model.ProductVO;

import hibernate.util.HibernateUtil;

public class Product_classHibernateDAO implements Product_classDAO_interface {

	@Override
	public void insert(Product_classVO product_classVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(product_classVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

	}

	@Override
	public void update(Product_classVO product_classVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(product_classVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void delete(String classno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Product_classVO product_classVO = (Product_classVO) session.get(Product_classVO.class, classno);
			session.delete(product_classVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public Product_classVO findByPrimaryKey(String classno) {
		Product_classVO product_classVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			product_classVO = (Product_classVO) session.get(Product_classVO.class, classno);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return product_classVO;
	}

	@Override
	public List<Product_classVO> getAll() {
		List<Product_classVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Product_classVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public Set<ProductVO> getProductsByClassno(String classno) {
		Set<ProductVO> set = findByPrimaryKey(classno).getProducts();
		return set;
	}

	public static void main(String[] args) {
		Product_classHibernateDAO dao = new Product_classHibernateDAO();

		/* insert */
		// Product_classVO product_classVO = new Product_classVO();
		// Set<ProductVO> set = new HashSet<ProductVO>();
		//
		// ProductVO newProduct = new ProductVO();
		// newProduct.setProno("000007"); //加這行為update
		// newProduct.setStono("000001");
		// newProduct.setProduct_classVO(product_classVO);
		// newProduct.setProname("超好玩遊戲");
		// newProduct.setProprice(500);
		// newProduct.setProstate("0");
		// newProduct.setProqty(12);
		// newProduct.setAdddate(java.sql.Date.valueOf("1972-5-30"));
		// newProduct.setPlayernum("3");
		// newProduct.setPlayerage("5~100");
		// newProduct.setLang("火星文");
		// newProduct.setPlaytime("8小時");
		// newProduct.setProsumm("超好玩");
		// newProduct.setProdesc("快來玩超好玩遊戲");
		// newProduct.setPropic(new byte[0]);
		//
		// set.add(newProduct);
		//
		// product_classVO.setClassno("000008");
		// product_classVO.setClassname("超好玩類");
		// product_classVO.setProducts(set);
		// dao.insert(product_classVO);

		/* delete */
		dao.delete("000008");

		/* getAll */
		List<Product_classVO> list = dao.getAll();
		for (Product_classVO aProduct_class : list) {
			System.out.print(aProduct_class.getClassname() + " ");
			Set<ProductVO> set2 = aProduct_class.getProducts();
			for (ProductVO productVO : set2) {
				System.out.print(productVO.getProname() + " ");
			}
			System.out.println();
		}
	}

}
