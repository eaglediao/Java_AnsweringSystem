package com.eaglediao.qa.dao.impl;

import com.eaglediao.qa.dao.QuestionDao;
import com.eaglediao.qa.domain.Question;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    public SessionFactory sessionFactory;

    public Question getQuestionByType(String type) {

        return null;
    }

    @Transactional
    public Question getQuestionByID(int id) throws Exception {

        return getqa();
//        Query query = this.sessionFactory.getCurrentSession().createQuery("from Question q where q.qid = :qid");
//        return (Question)query.setParameter("qid",id).uniqueResult();
    }

    @Transactional
    public int getQuestionCount() throws Exception {

//        Query query = this.sessionFactory.getCurrentSession().createQuery("select count(*) from Question ");
//        return ((Long)query.uniqueResult()).intValue();
        return 1;
    }

    @Override
    @Transactional
    public List<Question> getQuestionList(int offset, int count) {
//        Query query = this.sessionFactory.getCurrentSession().createQuery("from Question");
//        return query.setMaxResults(count).setFirstResult(offset).list();
        List l = new ArrayList<Question>();
        l.add(getqa());
        return l;
    }

    public Question getqa() {
        Question q = new Question();
        q.setAnswer("a");
        q.setContent("1111?");
        q.setOption("a 1;b 2;c 3;d 4");
        q.setQid(1);
        q.setScore(1);
        return q;
    }
}
