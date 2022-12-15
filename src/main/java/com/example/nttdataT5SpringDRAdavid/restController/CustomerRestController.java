package com.example.nttdataT5SpringDRAdavid.restController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nttdataT5SpringDRAdavid.NttdataT5SpringDrAdavidApplication;
import com.example.nttdataT5SpringDRAdavid.model.Customer;
import com.example.nttdataT5SpringDRAdavid.services.ManagementCustomerImp;

@RestController
@RequestMapping("/home")
public class CustomerRestController {
	private static final Logger LOG = LoggerFactory.getLogger(NttdataT5SpringDrAdavidApplication.class);

	/**
	 * Inyección de servicios
	 */
	@Autowired
	ManagementCustomerImp gestorClientes;

	/**
	 * Insertar clientes en el servicio
	 * 
	 * @param newCustomer
	 * @param res
	 */
	@PostMapping("/crearCliente")
	public void newCustomCreation(@ModelAttribute("cliente") Customer newCustomer, BindingResult res) {
		LOG.info("------------------------------INSERCIÓN DE CLIENTE--------------------------");

		gestorClientes.insertarCliente(newCustomer);
	}

	/**
	 * Recuperar clientes a través del servicio
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/mostrarClientes")
	public List<Customer> listOfCustomers(Model model) {
		LOG.info("------------------------------RECUPERACIÓN DE TODOS LOS CLIENTES--------------------------");

		return gestorClientes.obtenerTodosLosClientes();
	}

	/**
	 * Recuperar cliente por parámetros obtenidos en la URL
	 * 
	 * @param customerName
	 * @param firstSurname
	 * @param secondSurname
	 * @param model
	 * @return
	 */
	@PostMapping("/buscarCliente")
	public Customer SearchCustomer(@RequestParam String customerName, @RequestParam String firstSurname,
			@RequestParam String secondSurname, Model model) {
		LOG.info("------------------------------RECUPERACIÓN DEL CLIENTE BUSCADO POR PARAMETROS--------------------------");

		return gestorClientes.obtenerPorNombreYApellido(customerName, firstSurname, secondSurname);

	}

	@ExceptionHandler(BindException.class)
	public String checkExceptions(BindingResult res) {

		// Resultado
		String output = "";

		// Verificación de errores.
		if (res.hasErrors()) {

			output = "/showErrorView";

		}

		return output;
	}
}
