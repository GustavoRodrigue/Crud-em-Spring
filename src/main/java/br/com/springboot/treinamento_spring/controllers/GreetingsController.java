package br.com.springboot.treinamento_spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.treinamento_spring.model.Usuario;
import br.com.springboot.treinamento_spring.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /* Injeção de dependencia*/
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
	
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso APi " + name + "!";
    }
    
    
    @GetMapping(value = "listatodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios =  usuarioRepository.findAll(); /*execulta a consulta no banco de dados*/
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);  /*Retorna a lista em json*/
    }
    
    @PostMapping(value = "salvar") /*mapeia a url*/
    @ResponseBody /* descrição da resposta*/
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){ /*recebe os dados para salvar*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping(value = "delete") /*mapeia a url*/
    @ResponseBody /* descrição da resposta*/
    public ResponseEntity<String> delete (@RequestParam Long idUser){ /*recebe os dados para deletar*/
    	
    	usuarioRepository.deleteById(idUser);
    	
    	return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarUserId") /*mapeia a url*/
    @ResponseBody /* descrição da resposta*/
    public ResponseEntity<Usuario> buscarUserId (@RequestParam(name="idUser") Long idUser){ /*recebe os dados para consultar*/
    	
    	Usuario usuario =  usuarioRepository.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
   
    @PutMapping(value = "atualizar") /*mapeia a url*/
    @ResponseBody /* descrição da resposta*/
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){ /*recebe os dados para salvar*/
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualizar.", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorNome") /*mapeia a url*/
    @ResponseBody /* descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name="name") String name){ /*recebe os dados para consultar*/
    	
    	List<Usuario> usuario =  usuarioRepository.BuscaPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
   
}
