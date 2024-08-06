package com.kevingutierrez.webapp.biblioteca.service;

import java.util.List;


import com.kevingutierrez.webapp.biblioteca.model.Cliente;

public interface IClienteService {
    public List<Cliente> listarClientes();

    public Cliente guardarCliente(Cliente cliente);

    public Cliente buscarClientePorId(Long DPI);

    public void eliminarCliente(Cliente cliente);
}
