package projeto.dtc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.dtc.model.DadosAnalise;

public interface DadosRepository extends JpaRepository<DadosAnalise, Long> {
}
