package at.fhj.swd13.pse.db.dao;

import java.util.List;

import javax.persistence.Query;

import at.fhj.swd13.pse.db.DAOBase;
import at.fhj.swd13.pse.db.DbContext;
import at.fhj.swd13.pse.db.entity.Message;
import at.fhj.swd13.pse.db.entity.Person;

public class MessageDAOImpl extends DAOBase implements MessageDAO {

	public MessageDAOImpl(DbContext dbContext) {
		super(dbContext);
	}

	@Override
	public void insert(Message message) {
		dbContext.persist(message);
	}

	@Override
	public Message getById(int messageId) {
		return null;
	}

	@Override
	public void remove(int messageId) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> loadAll() {

		Query query = dbContext.createNamedQuery("Message.findAllOrderedByNewest");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> loadForUser(Person user) {
		// TODO Update query
		Query query = dbContext.createNamedQuery("Message.findForUser");
		return query.getResultList();
	}

}
