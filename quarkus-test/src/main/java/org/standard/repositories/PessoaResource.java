package org.standard.repositories;

import org.standard.models.Pessoa;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface PessoaResource extends PanacheEntityResource<Pessoa, Long> {

}
