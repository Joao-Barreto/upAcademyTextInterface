package io.altar.jseproject.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.altar.jseproject.model.Entity;

public abstract class EntityRepository<T extends Entity> {
	private Map<Long, T> baseDeDadosLocal = new HashMap<Long, T>();
	private long maiorId =0;

	private long maiorId() {
		return ++maiorId;
	}

	public long createEntity(T entity) {
		// Adicionar à base de dados Map
		entity.setId(maiorId());
		baseDeDadosLocal.put(maiorId, entity);
		return maiorId;
	}

	public Collection<T> consultEntity() {
		//return de todos
		return  baseDeDadosLocal.values();
	}

	public T consultEntity(long entityId) {
		//return de um entity com o ID recebido
		return baseDeDadosLocal.get(entityId);
	}

	public void editEntity(T entity) {
		//editar os atributos de um entity que ja tem ID
		long id = entity.getId();
		baseDeDadosLocal.put(id, entity);
	}

	public void removeEntity(long entityId) {
		// remover uma entidade a partir do seu Id
		baseDeDadosLocal.remove(entityId);
	}
}
