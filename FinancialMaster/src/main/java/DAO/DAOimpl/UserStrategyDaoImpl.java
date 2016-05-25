package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.UserStrategyDao;
import DAO.pojo.Bench;
import DAO.pojo.UserStock;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;

public class UserStrategyDaoImpl implements UserStrategyDao{

	@Override
	public boolean insert(UserStrategy userStrategy) throws Exception {
		Session session=DBconnection.getSession();
		session.save(userStrategy);
		Transaction tx=session.beginTransaction();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public UserStrategy findByID(UserStrategyId userStrategyId) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(UserStrategy.class);
		criteria.add(Restrictions.eq("id.userName", userStrategyId.getUserName()));
		criteria.add(Restrictions.eq("id.strategy", userStrategyId.getStrategy()));		
		List UserStrategyList=criteria.list();
		session.close();
		if(UserStrategyList.size()==0){
			return null;
		}else{
			return (UserStrategy)UserStrategyList.get(0);
		}
	}

	@Override
	public List findAll() throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(UserStrategy.class);
		List UserStrategyList=criteria.list();
		session.close();
		return UserStrategyList;
	}

	@Override
	public boolean delete(UserStrategyId userStrategyId) throws Exception {
		Session session=DBconnection.getSession();
		Transaction tx=session.beginTransaction();
		UserStrategy userStrategy=new UserStrategy(userStrategyId);
		session.delete(userStrategy);
		tx.commit();
		session.close();
		return true;
	}

	
}
