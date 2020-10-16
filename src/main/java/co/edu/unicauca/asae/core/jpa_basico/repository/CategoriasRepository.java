/**
 * 
 */
package co.edu.unicauca.asae.core.jpa_basico.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.core.jpa_basico.modelo.Categoria;

/**
 * @author Andres
 *
 */
public interface CategoriasRepository extends JpaRepositoryImplementation<Categoria, Integer> {

}
