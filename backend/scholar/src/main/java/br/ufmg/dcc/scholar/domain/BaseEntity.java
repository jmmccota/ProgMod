package br.ufmg.dcc.scholar.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.ObjectUtils;

@MappedSuperclass
public abstract class BaseEntity implements Comparable<BaseEntity> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + "]";
	}

	/**
	 * Implementação da interface comparable.
	 * 
	 * @param obj
	 *            Objeto a ser comparado.
	 * @return -1, 0, 1
	 */
	public int compareTo(BaseEntity obj) {
		if (obj == null) {
			return 1;
		} else {
			Long idOutro = obj.getId();
			if (this.getId() == null && idOutro == null) {
				Integer h1 = new Integer(this.hashCode());
				Integer h2 = obj.hashCode();
				return h1.compareTo(h2);
			} else {
				Long id1 = (Long) ObjectUtils.defaultIfNull(this.getId(), 0L);
				Long id2 = (Long) ObjectUtils.defaultIfNull(idOutro, 0L);
				return id1.compareTo(id2);
			}
		}
	}
}
