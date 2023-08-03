package grocery.service;

import java.util.List;

import grocery.repository.IRepository;

public class GenericService<T extends IRepository<S>, S> {
	private T repository;

	public List<S> getEntities() {
		return repository.getEntities();
	}
	
	public S getEntityBy(Integer id) {
		return repository.getEntityById(id);
	}
	
	public void setRepository(T repository) {
		this.repository = repository;
	}
}
