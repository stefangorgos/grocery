package grocery.service;

import java.util.List;

public interface IService<T, S> {
	public List<S> getEntities();
}
