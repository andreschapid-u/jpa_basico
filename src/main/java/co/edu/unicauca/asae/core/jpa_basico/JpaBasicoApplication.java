package co.edu.unicauca.asae.core.jpa_basico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import co.edu.unicauca.asae.core.jpa_basico.modelo.Categoria;
import co.edu.unicauca.asae.core.jpa_basico.repository.CategoriasRepository;

@SpringBootApplication
public class JpaBasicoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository servicioAccesoBD;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaBasicoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicacion iniciada");

		System.out.println(this.servicioAccesoBD);
		// guardar();
		// buscarPorID();
		// modificar();
		// eliminar();
		// buscarTodoJPA();
		// borarTodoJPA();
		// buscarTodoPaginado();
		buscarTodoPaginadoYOrdenado();
		System.out.println("Aplicacion finalizada");
	}
	

	private void buscarTodoPaginadoYOrdenado() {
		Page<Categoria> page = servicioAccesoBD.findAll(PageRequest.of(1, 5, Sort.by("nombre")));
		System.out.println("Número de elementos: "+ page.getTotalElements());
		System.out.println("Número de páginas: "+ page.getTotalPages());
		
		for(Categoria categoria : page.getContent()){
			System.out.println("Nombre: "+categoria.getNombre());
		}
	}


	private void buscarTodoPaginado() {
		Page<Categoria> page = servicioAccesoBD.findAll(PageRequest.of(1, 5));
		for(Categoria categoria : page.getContent()){
			System.out.println("Nombre: "+categoria.getNombre());
		}
	}


	private void guardar() {
		Categoria objCategoria = new Categoria();
		objCategoria.setNombre("Desarrollador web");
		objCategoria.setDescripcion("Se requiere un desarrollador");
		
		this.servicioAccesoBD.save(objCategoria);
	}
	
	private void buscarPorID() {
		Optional<Categoria> optional = this.servicioAccesoBD.findById(1);
		if(optional.isPresent()) {
			Categoria categoria = optional.get();
			System.out.println("Id: "+categoria.getId());
			System.out.println("Nombre: "+categoria.getNombre());
			System.out.println("Descripcion: "+categoria.getDescripcion());
			
		}else {
			System.out.println("Categoria no encontrada.");
		}
		
	}
	
	private void modificar() {
		Optional<Categoria> optional = this.servicioAccesoBD.findById(1);
		if(optional.isPresent()) {
			Categoria categoria = optional.get();
			categoria.setNombre("Ing. Sistmas");
			categoria.setDescripcion("Ingeniero de sistemas con 2 años de experiencia.");
//			System.out.println("Nombre: "+categoria.getNombre());
//			System.out.println("Descripcion: "+categoria.getDescripcion());
			this.servicioAccesoBD.save(categoria);
			System.out.println("Categoria modificada");
		}else {
			System.out.println("Categoria no encontrada.");
		}
		
	}
	
	private void eliminar() {
		Optional<Categoria> optional = this.servicioAccesoBD.findById(1);
		if(optional.isPresent()) {
			Categoria categoria = optional.get();
			this.servicioAccesoBD.deleteById(categoria.getId());
			System.out.println("Categoria Eliminada");
		}else {
			System.out.println("Categoria no encontrada.");
		}
		
	}

	private void buscarTodoJPA() {
		List<Categoria> categorias = servicioAccesoBD.findAll();
		for (Categoria categoria : categorias) {
			System.out.println("Nombre: "+categoria.getNombre());
			
		}
	}

	private void borarTodoJPA() {
		servicioAccesoBD.deleteAllInBatch();
		System.out.println("Se han borrado todos los registros!");
	}


	private void buscrTodoOrdenado() {
		List<Categoria> categorias = servicioAccesoBD.findAll(Sort.by("nombre"));
		for (Categoria categoria : categorias) {
			System.out.println("Nombre: "+categoria.getNombre());
		}
	}

	
}
