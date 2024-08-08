package br.com.fiap.techchallengeproduct.external.infrastructure.repositories;

import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.enums.TypeStatus;
import br.com.fiap.techchallengeproduct.external.infrastructure.entities.ProductDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductDB, UUID> {

    List<ProductDB> findByTypeProductAndTypeStatus(TypeProduct typeProduct, TypeStatus active);

    ProductDB findAllById(UUID id);
}