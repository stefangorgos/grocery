package grocery.repository;

import java.util.List;

public interface IRepository<T> {
	public List<T> getEntities();
	
	public T getEntityById(Integer id);
}
