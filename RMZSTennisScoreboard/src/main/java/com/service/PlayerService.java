package com.service;

import com.model.Match;
import com.model.Player;
import com.repository.CrudRepository;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class PlayerService implements CrudRepository<Player, Long> {


    @Override
    public void save(Player entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.persist(entity);
            session.flush();
            transaction.commit();
            session.close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveGroup(Player [] player){
        for (Player p : player) {
            save(p);
        }
    }

    @Override
    public void delete(Player entity) {

    }

    @Override
    public void update(Player entity) {

    }

    @Override
    public List<Player> findAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List list = session.createCriteria(Player.class).list();
            session.close();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Player> findById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Optional<Player> toReturn = Optional.of(session.get(Player.class, id));
        session.close();
        return toReturn;
    }

}
