package com.eaglediao.qa.dao.impl;

import com.eaglediao.qa.dao.UserDao;
import com.eaglediao.qa.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    public HibernateTemplate template;
    @Autowired
    public SessionFactory sessionFactory;


    public User getUserByID(String employeeID) throws Exception{
//        Query query = this.sessionFactory.getCurrentSession().createQuery("from  User u where u.employeeId = :" +
//                "employeeID");
//        return (User)query.setParameter("employeeID",employeeID).uniqueResult();
            return getuser();
    }


    public void updateUser(User user) throws  Exception{
//        this.template.update(user);
    }

    public User getuser() {
        User u   =new User();
        u.setName("dyj");
        u.setPassword("12345");
        u.setEmployeeId("dwx278693");
        return u;
    }
}