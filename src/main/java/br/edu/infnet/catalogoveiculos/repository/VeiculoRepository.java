package br.edu.infnet.catalogoveiculos.repository;

import br.edu.infnet.catalogoveiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
