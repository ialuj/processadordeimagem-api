package br.com.bixtecnologia.processadordeimagem.transform.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.bixtecnologia.processadordeimagem.domain.models.BaseModel;
import br.com.bixtecnologia.processadordeimagem.dto.BaseModelDTO;

/**
 * @author Jose Julai Ritsure
 * @param <E>   Entity Model (deve estender BaseModel)
 * @param <DTO> DTO (deve estender BaseModelDTO)
 */
public abstract class DTOTransformerImpl<E extends Object, DTO extends Object> implements IDTOTransformer<E, DTO> {
	
	@Override
	public DTO toDTO(E source) {
		return transformToDTO(source);
	}

	@Override
	public List<DTO> toDTOS(List<E> sources) {
		List<DTO> targets = new ArrayList<DTO>();
		for (E source: sources) {
			targets.add(toDTO(source));
		}
		return targets;
	}

	@Override
	public Set<DTO> toDTOS(Set<E> sources) {
		Set<DTO> targets = new TreeSet<DTO>();
		for (E source: sources) {
			targets.add(toDTO(source));
		}
		return targets;
	}

	@Override
	public E fromDTO(DTO source) {
		return transformFromDTO(source);
	}

	@Override
	public List<E> fromDTOS(List<DTO> sources) {
		List<E> targets = new ArrayList<E>();
		for (DTO source : sources) {
			targets.add(transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<E> fromDTOS(Set<DTO> sources) {
		Set<E> targets = new TreeSet<E>();
		for (DTO source : sources) {
			targets.add(transformFromDTO(source));
		}
		return targets;
	}

	/**
	 * Transforma um DTO em uma entidade de modelo.
	 *
	 * @param source Objeto DTO a ser transformado.
	 * @return Objeto entidade correspondente.
	 */
	/*public E transformFromDTO(DTO source) {
		if (source == null) {
			return null;
		}
		try {
			@SuppressWarnings("unchecked")
			E entity = (E) source.getClass().getDeclaredConstructor().newInstance();
			copyProperties(source, entity);
			return entity;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao transformar DTO em entidade", e);
		}
	}*/

	/**
	 * Transforma uma entidade de modelo em um DTO.
	 *
	 * @param source Objeto entidade a ser transformado.
	 * @return Objeto DTO correspondente.
	 */
	/*public DTO transformToDTO(E source) {
		if (source == null) {
			return null;
		}
		try {
			@SuppressWarnings("unchecked")
			DTO dto = (DTO) source.getClass().getDeclaredConstructor().newInstance();
			copyProperties(source, dto);
			return dto;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao transformar entidade em DTO", e);
		}
	}*/

	/**
	 * Copia os valores dos atributos de um objeto para outro usando Reflection.
	 *
	 * @param source Objeto fonte.
	 * @param target Objeto alvo.
	 */
	/*private void copyProperties(Object source, Object target) {
		Field[] fields = source.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(source);

				// Encontrar o campo correspondente no objeto de destino
				Field targetField = getField(target.getClass(), field.getName());
				if (targetField != null) {
					targetField.setAccessible(true);
					targetField.set(target, value);
				}
			} catch (Exception e) {
				throw new RuntimeException("Erro ao copiar propriedades", e);
			}
		}
	}*/

	/**
	 * Obtém um campo de uma classe, verificando também as classes pai.
	 *
	 * @param clazz     Classe onde buscar o campo.
	 * @param fieldName Nome do campo.
	 * @return O campo encontrado ou null se não existir.
	 */
	/*private Field getField(Class<?> clazz, String fieldName) {
		while (clazz != null) {
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass(); // Verifica a superclasse
			}
		}
		return null;
	}*/

	@SuppressWarnings("hiding")
	protected <E extends BaseModel, DTO extends BaseModelDTO> DTO setValues(final E source, final DTO target) {
		target.setId(source.getId());
		target.setCreatedBy(source.getCreatedBy());
		target.setCreationDate(source.getCreationDate());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setUpdateDate(source.getUpdateDate());
		target.setUuid(source.getUuid());
		return target;
	}

	@SuppressWarnings("hiding")
	protected <E extends BaseModel, DTO extends BaseModelDTO> E setValues(final DTO source, final E target) {
		target.setId(source.getId());
		target.setCreatedBy(source.getCreatedBy());
		target.setCreationDate(source.getCreationDate());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setUpdateDate(source.getUpdateDate());
		target.setUuid(source.getUuid());
		return target;
	}

}
