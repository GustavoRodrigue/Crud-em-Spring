package br.com.springboot.treinamento_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.treinamento_spring.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value="select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> BuscaPorNome(String name);
	
	
}
