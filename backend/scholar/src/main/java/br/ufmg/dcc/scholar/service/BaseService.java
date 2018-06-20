package br.ufmg.dcc.scholar.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

/**
 * 
 */
@Transactional
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class BaseService<E>
{
  private static final int PAGINACAO = 30;

  protected abstract JpaRepository<E, Long> getEntityRepository();

  /**
   * @return lista de objetos.
   */
  public Collection<E> findAll()
  {
    return this.getEntityRepository().findAll();
  }
  
  /**
   * @return lista de objetos.
   */
  public Page<E> findAll(PageRequest p)
  {
    return this.getEntityRepository().findAll(p);
  }

  /**
   * @param id a ser buscado.
   * @return Objeto.
   */
  public E findOne(Long id)
  {
    return this.getEntityRepository().findById(id).get();
  }

  /**
   * @param entity entity a ser exclu[ida.
   */
  public void delete(E entity)
  {
    this.getEntityRepository().delete(entity);
  }

  /**
   * Salva um objeto sem flush.
   *
   * @param entity a ser salva.
   * @return entidade salva.
   */
  public E save(E entity)
  {
    return this.getEntityRepository().save(entity);
  }

  /**
   * Salva um objeto com flush.
   *
   * @param entity a ser salva.
   * @return entidade salva.
   */
  public E saveAndFlush(E entity)
  {
    return this.getEntityRepository().saveAndFlush(entity);
  }

  /**
   * Flush caso seja necessário.
   */
  public void flush()
  {
    this.getEntityRepository().flush();
  }

  /**
   * @return paginação.
   */
  public Pageable getPaginacao(Integer page)
  {
    return PageRequest.of(page, PAGINACAO);
  }
}
