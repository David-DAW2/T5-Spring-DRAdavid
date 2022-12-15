package com.example.nttdataT5SpringDRAdavid.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nttdataT5SpringDRAdavid.model.Customer;
import com.example.nttdataT5SpringDRAdavid.repositories.CustomerRepositoryI;




@Service
public class ManagementCustomerImp implements ManagementCustomerI {

	/**
	 * Inyección de repositorio
	 */
	@Autowired
	CustomerRepositoryI repoCustomer;

	@Override
	public Optional<Customer> encontrarCliente(Long id) {
		//Retorno de cliente encontrado
		return repoCustomer.findById(id);
	}

	@Override
	public Customer obtenerPorNombreYApellido(String nombre, String primerApe, String segundoApe) {
		//Retorno de cliente encontrado por los parámetros
		return repoCustomer.findByCustomerNameAndFirstSurnameAndSecondSurname(nombre, primerApe, segundoApe);
	}

	@Override
	public List<Customer> obtenerTodosLosClientes() {
		//Retorno de todos los clientes

		return repoCustomer.findAll();
	}

	@Override
	public void insertarCliente(Customer customer) {
		//Inserción de cliente en la BBDD
		repoCustomer.save(customer);
	}

	@Override
	public void BorrarCliente(Customer customer) {
		//Borrado de cliente en la BBDD
		repoCustomer.delete(customer);
	}
}
