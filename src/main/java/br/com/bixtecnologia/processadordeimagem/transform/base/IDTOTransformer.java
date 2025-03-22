package br.com.bixtecnologia.processadordeimagem.transform.base;

import java.util.List;
import java.util.Set;

public interface IDTOTransformer<E extends Object, DTO extends Object> {

	DTO toDTO(E source);

	List<DTO> toDTOS(List<E> sources);

	Set<DTO> toDTOS(Set<E> sources);

	E fromDTO(DTO source);

	List<E> fromDTOS(List<DTO> sources);

	Set<E> fromDTOS(Set<DTO> sources);
	
	public E transformFromDTO(DTO source);
	
	public DTO transformToDTO(E source);

}
